package com.digitallight.u_gorescue.rest;

import java.util.List;

import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;

/**
 * Created by F on 1/19/2016.
 */
public interface PangdarApi {
    /*Retrofit get annotation with our URL
       And our method that will return us the list ob Book
    */
    @FormUrlEncoded
    @POST("/api/pangdar")
    public void insertPangdar(
            @Field("id_pangdar") String id_pangdar,
            @Field("tanggal") String tanggal,
            @Field("latitude") Double latitude,
            @Field("longtitude") Double longtitude,
            @Field("nama_jalan") String nama_jalan,
            @Field("nama_subjalan") String nama_subjalan,
            @Field("nama_daerah") String nama_daerah,
            @Field("id_sopir") String id_sopir,
            @Field("id_user") String id_user,
            Callback<Response> callback);
}
