package com.example.dalay.projecthrd;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.dalay.projecthrd.entity.FacebookUser;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import org.json.JSONObject;

import java.util.Arrays;

public class LoginActivity extends AppCompatActivity {
    CallbackManager callbackManager;
    static final String Email="email";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        boolean isLoggedIn = accessToken !=null && !accessToken.isExpired();
        if(isLoggedIn){
            GraphRequest graphRequest = GraphRequest.newMeRequest(accessToken, new GraphRequest.GraphJSONObjectCallback() {
                @Override
                public void onCompleted(JSONObject object, GraphResponse response) {

                    Log.e("error", "Success");
                    facebookResult(object);

                }
            });
            Bundle bundle = new Bundle();
            bundle.putString("fields","id,first_name,last_name,email,picture.type(large)");
            graphRequest.setParameters(bundle);
            graphRequest.executeAsync();
        }

        callbackManager = CallbackManager.Factory.create();

        LoginButton loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.setReadPermissions(Arrays.asList(Email,"public_profile"));
        // If you are using in a fragment, call loginButton.setFragment(this);

        // Callback registration
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                AccessToken accessToken = loginResult.getAccessToken();
                GraphRequest graphRequest = GraphRequest.newMeRequest(accessToken, new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {

                        Log.e("error", "Success");
                        facebookResult(object);

                    }
                });
                Bundle bundle = new Bundle();
                bundle.putString("fields","id,first_name,last_name,email,picture.type(large)");
                graphRequest.setParameters(bundle);
                graphRequest.executeAsync();

            }


            @Override
            public void onCancel() {
                // App code
            }


            @Override
            public void onError(FacebookException exception) {
                // App code
            }
        });
    }


    public void facebookResult(JSONObject object){
//        textView = findViewById(R.id.tvResult);
//        textView.setText(object.toString())
            Log.i("12345678",object.toString());
            Intent intent = new Intent(this,MainActivity.class);
            JsonParser parser = new JsonParser();
            JsonElement mJson =  parser.parse(object.toString());
            Gson gson = new Gson();
            FacebookUser fbUser = gson.fromJson(mJson, FacebookUser.class);
            intent.putExtra("user",fbUser);
            startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);

    }

}
