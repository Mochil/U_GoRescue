package com.digitallight.u_gorescue.rest;

import com.digitallight.u_gorescue.model.User;

import java.util.List;

import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;

/**
 * Created by F on 1/17/2016.
 */
public interface UsersApi {
    /*Retrofit get annotation with our URL
       And our method that will return us the list ob Book
    */
    @GET("/api/users")
    public void getBooks(Callback<List<User>> response);

    @FormUrlEncoded
    @POST("/api/users")
    public void insertUser(
            @Field("id_user") String name,
            @Field("username") String username,
            @Field("password") String password,
            @Field("no_identitas") String no_identitas,
            @Field("nama") String nama,
            @Field("nomor_hp") String no_hp,
            @Field("email") String email,
            Callback<Response> callback);
    
}
