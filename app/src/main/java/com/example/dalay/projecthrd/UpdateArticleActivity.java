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
import android.widget.Spinner;
import android.widget.Toast;

import com.example.dalay.projecthrd.CallBack.onUpdatedArticle;
import com.example.dalay.projecthrd.Service.ServiceGenerator;
import com.example.dalay.projecthrd.entity.Article;
import com.example.dalay.projecthrd.entity.Category;
import com.example.dalay.projecthrd.entity.CategoryResponse;
import com.example.dalay.projecthrd.entity.ImageResponse;
import com.example.dalay.projecthrd.entity.PostArticle;
import com.example.dalay.projecthrd.entity.ResponseRemove;
import com.example.dalay.projecthrd.netework.ArticleService;
import com.squareup.picasso.Picasso;

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

public class UpdateArticleActivity extends AppCompatActivity {
    EditText title,dese;
    ImageView imageView;
     Button addNew;
     public  Article article;
     private int position;
    Category category;
    PostArticle update;
    Spinner spinner;
    private ArrayList<Category> categories = new ArrayList<>();
    int cateId = 0;
    File file;
    ImageView pickImage;

    ArticleService service;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_article);

        title =findViewById(R.id.title);
        dese =findViewById(R.id.description);
        addNew=findViewById(R.id.addNew);
        spinner=findViewById(R.id.spinner);
        pickImage = findViewById(R.id.pickImage);
        service = ServiceGenerator.createService(ArticleService.class);
        getallCate();
        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            position = bundle.getInt("position");
             article = bundle.getParcelable("article");
             if(article!=null){
                 title.setText(article.getTitle());
                 dese.setText(article.getDescription());
                 if(article.getCategory()!=null){
                     int i = article.getCategory().getId();
                     spinner.setSelection(i);
                 }
                 if(article.getImage()!=null){
                     if(article.getImage().isEmpty()){
                         pickImage.setImageResource(R.drawable.ic_launcher_background);
                     }else {
                         Picasso.get().load(article.getImage())
                                 .error(R.drawable.ic_launcher_background)
                                 .into(pickImage);
                     }
                 }

             }
        }
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                cateId = categories.get(i).getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        addNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(file==null){
                    update = new PostArticle();
                    update.setTITLE(title.getText().toString());
                    update.setDESCRIPTION(dese.getText().toString());
                    update.setAUTHOR(0);

                    update.setCATEGORY_ID(cateId);
                    update.setIMAGE(article.getImage());
                    update.setSTATUS("1");
                    updateArticle(article.getId(),position,update);
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
                    update = new PostArticle();
                    update.setTITLE(title.getText().toString());
                    update.setDESCRIPTION(dese.getText().toString());
                    update.setAUTHOR(0);

                    update.setCATEGORY_ID(cateId);
                    update.setIMAGE(response.body().getDATA());
                    update.setSTATUS("1");
                    updateArticle(article.getId(),position,update);
                }
            }

            @Override
            public void onFailure(Call<ImageResponse> call, Throwable t) {

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

    private void updateArticle(long id, final int position , final PostArticle article){
//        response = new Response();
        ArticleService service = ServiceGenerator.createService(ArticleService.class);
        Call<ResponseRemove> call = service.updateArticle(id,article);
        call.enqueue(new Callback<ResponseRemove>() {
            @Override
            public void onResponse(Call<ResponseRemove> call, retrofit2.Response<ResponseRemove> response) {
                Article a = response.body().getArticle();
                Intent intent = new Intent();
                setResult(RESULT_OK,intent);
                finish();
            }

            @Override
            public void onFailure(Call<ResponseRemove> call, Throwable t) {

                Log.e("123456790",t.toString());
            }
        });

    }
    private void getallCate(){
//        response = new Response();
        ArticleService service = ServiceGenerator.createService(ArticleService.class);
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
