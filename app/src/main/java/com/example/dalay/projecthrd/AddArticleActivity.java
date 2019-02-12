package com.example.dalay.projecthrd;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dalay.projecthrd.Service.ServiceGenerator;
import com.example.dalay.projecthrd.adapter.ArticleAdapter;
import com.example.dalay.projecthrd.entity.Article;
import com.example.dalay.projecthrd.entity.Category;
import com.example.dalay.projecthrd.entity.CategoryResponse;
import com.example.dalay.projecthrd.entity.ImageResponse;
import com.example.dalay.projecthrd.entity.PostArticle;
import com.example.dalay.projecthrd.entity.Response;
import com.example.dalay.projecthrd.entity.ResponseRemove;
import com.example.dalay.projecthrd.netework.ArticleService;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

public class AddArticleActivity extends AppCompatActivity {
    private Spinner spinner;
    private ArrayList<Category> categories = new ArrayList<>();
    private int cateId=0;
    private EditText title,desc;
    private ImageView pickImage;
    private File file;
    private String imageFilePath;
    public static final int CAMERA_REQUEST = 10;
    public static final int ADD = 1;
    public static final int UPDATE = 2;
    public static final int RequestPermissionCode = 11;
    private int TYPE;
    private PostArticle postArticle;
    private String imageUrl;

    private ProgressBar progressBar;
    private ArticleService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_article);
        spinner = findViewById(R.id.spinner);
        title = findViewById(R.id.title);
        desc = findViewById(R.id.description);
        Button bt = findViewById(R.id.addNew);
        pickImage = findViewById(R.id.pickImage);
        progressBar = findViewById(R.id.progressBar);
        service = ServiceGenerator.createService(ArticleService.class);
        getallCate();

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                cateId = categories.get(i).getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                    if(file==null){
                        String mTitle = title.getText().toString();
                        String mDesc = desc.getText().toString();

                       // postArticle = setArticle(1,cateId,mTitle,mDesc,"","1");

                        postArticle = new PostArticle();
                        postArticle.setAUTHOR(1);
                        postArticle.setCATEGORY_ID(cateId);
                        postArticle.setTITLE(mTitle);
                        postArticle.setDESCRIPTION(mDesc);
                        postArticle.setIMAGE("");
                        postArticle.setSTATUS("1");
                        uploadArticle(postArticle);
                    }else{
                        uploadImage(file);
                    }

            }
        });
        pickImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,1);
            }
        });
    }
    private String getRealPathFromURI(Uri contentURI) {
        String result;
        Cursor cursor = getContentResolver().query(contentURI, null, null, null, null);
        if (cursor == null) { // Source is Dropbox or other similar local file path
            result = contentURI.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DISPLAY_NAME);
            result = cursor.getString(idx);
            cursor.close();
        }
        return result;
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1 && data!=null){
            Uri uri = data.getData();
            String name = getRealPathFromURI(uri);
            File tempFile = new File(this.getFilesDir().getAbsolutePath(), name);
            //Copy URI contents into temporary file.
            try {
                if(tempFile.createNewFile()){
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                    OutputStream os = new BufferedOutputStream(new FileOutputStream(tempFile));
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, os);
                    os.close();
                }
            }
            catch (IOException e) {
                //Log Error
                e.printStackTrace();
            }
            //Now fetch the new URI
            file = tempFile;
            Uri newUri = Uri.fromFile(tempFile);
            pickImage.setImageURI(newUri);
        }
    }

//    private PostArticle setArticle(int authId, int cateId, String title, String desc, String imageUrl, String status){
//        postArticle = new PostArticle();
//        postArticle.setAUTHOR(authId);
//        postArticle.setCATEGORY_ID(cateId);
//        postArticle.setTITLE(title);
//        postArticle.setDESCRIPTION(desc);
//        postArticle.setIMAGE(imageUrl);
//        postArticle.setSTATUS(status);
//        return postArticle;
//    }



    private void uploadArticle(PostArticle article){
//        response = new Response();

        Call<ResponseRemove> call = service.uploadArticle(article);
        ;;        call.enqueue(new Callback<ResponseRemove>() {
            @Override
            public void onResponse(Call<ResponseRemove> call, retrofit2.Response<ResponseRemove> response) {
                Intent intent = new Intent();
                setResult(RESULT_OK,intent);
                finish();
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<ResponseRemove> call, Throwable t) {
                Toast.makeText(AddArticleActivity.this, t.toString(), Toast.LENGTH_SHORT).show();

            }
        });

    }
    private void uploadImage(File fileImage){
//        response = new Response();
        RequestBody requestBody = RequestBody.create(MediaType.parse("*/*"),fileImage);
        MultipartBody.Part fileUpload = MultipartBody.Part.createFormData("FILE",fileImage.getName(),requestBody);
        RequestBody fileName = RequestBody.create(MediaType.parse("text/plain"),fileImage.getName());


        Call<ImageResponse> call = service.uploadImage(fileUpload,fileName);
        call.enqueue(new Callback<ImageResponse>() {
            @Override
            public void onResponse(Call<ImageResponse> call, retrofit2.Response<ImageResponse> response) {
                if(response.isSuccessful()){
                    String mTitle = title.getText().toString();
                    String mDesc = desc.getText().toString();
                    //postArticle = setArticle(1,cateId,mTitle,mDesc,response.body().getDATA(),"1");
                    postArticle = new PostArticle();
                    postArticle.setAUTHOR(1);
                    postArticle.setCATEGORY_ID(cateId);
                    postArticle.setTITLE(mTitle);
                    postArticle.setDESCRIPTION(mDesc);
                    postArticle.setIMAGE(response.body().getDATA());
                    postArticle.setSTATUS("1");
                    uploadArticle(postArticle);
                }
            }

            @Override
            public void onFailure(Call<ImageResponse> call, Throwable t) {
                Toast.makeText(AddArticleActivity.this, t.toString(), Toast.LENGTH_SHORT).show();

            }
        });

    }
    private void getallCate(){
//        response = new Response();
        Call<CategoryResponse> call = service.getAllCate();
        call.enqueue(new Callback<CategoryResponse>() {
            @Override
            public void onResponse(Call<CategoryResponse> call, retrofit2.Response<CategoryResponse> response) {
                ArrayList<Category> category = new ArrayList<>();
                category.addAll( response.body().getCategories());
                categories = category  ;
                List<String> cateName = new ArrayList<>();
                for(int in =0; in<category.size();in++){
                    cateName.add(category.get(in).getName());
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),
                        android.R.layout.simple_spinner_dropdown_item,cateName);
                spinner.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<CategoryResponse> call, Throwable t) {

            }
        });

    }
}
