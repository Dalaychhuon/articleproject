package com.example.dalay.projecthrd.netework;

import android.database.Observable;

import com.example.dalay.projecthrd.entity.Article;
import com.example.dalay.projecthrd.entity.CategoryResponse;
import com.example.dalay.projecthrd.entity.ImageResponse;
import com.example.dalay.projecthrd.entity.PostArticle;
import com.example.dalay.projecthrd.entity.Response;
import com.example.dalay.projecthrd.entity.ResponseRemove;


import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface ArticleService {
    @GET("/v1/api/articles")
    Call<Response> getAllArticle();

    @DELETE("/v1/api/articles/{id}")
    Call<ResponseRemove> removeArticle(@Path("id") long id);

    @PUT("/v1/api/articles/{id}")
    Call<ResponseRemove> updateArticle(@Path("id") long id, @Body PostArticle article);

    @GET("/v1/api/categories")
    Call<CategoryResponse> getAllCate();

    @POST("/v1/api/articles")
    Call<ResponseRemove> uploadArticle(@Body PostArticle response);

    @Multipart
    @POST("/v1/api/uploadfile/single")
    Call<ImageResponse> uploadImage(@Part MultipartBody.Part file, @Part("FILE") RequestBody name);
}
