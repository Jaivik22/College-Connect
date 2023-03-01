package com.example.college_connect_faculty;

import java.util.TreeMap;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface api {

    @FormUrlEncoded
    @POST("uploadSubjects.php")
    Call<ResponseBody>uploadSubject(
            @Field("semester") String semester,
            @Field("branch") String branch,
            @Field("subject")String subject
            );
    @FormUrlEncoded
    @POST("showSubjects.php")
    Call<ResultSubject> showSubject(
            @Field("semester") String semester,
            @Field("branch") String branch
    );
    @Multipart
    @POST("filesUpload.php")
    Call<ResponseBody> fileUpload(
            @Part MultipartBody.Part fileName,
            @Part("branch") RequestBody branch,
            @Part("semester") RequestBody semester,
            @Part("subject") RequestBody subject

    );

    @FormUrlEncoded
    @POST("noticeUpload.php")
    Call<ResponseBody> noticeUpload(
                    @Field("notice") String notice,
                    @Field("title") String title
    );
}
