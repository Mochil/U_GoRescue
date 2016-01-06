package com.digitallight.u_gorescue;

import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

/**
 * Created by Pena Orange on 06/01/2016.
 */
public interface RegisterAPI {
    @FormUrlEncoded
    @POST("/insert/insert.php")
    public void insertUser(
            @Field("username") String username,
            @Field("password") String password,
            @Field("no_identitas") String no_identitas,
            @Field("nama") String nama,
            @Field("nomor_hp") String nomor_hp,
            @Field("email") String email,
            Callback<Response> callback);
}
