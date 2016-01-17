package com.digitallight.u_gorescue.rest;

import com.digitallight.u_gorescue.model.User;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by F on 1/17/2016.
 */
public interface UsersApi {
    /*Retrofit get annotation with our URL
       And our method that will return us the list ob Book
    */
    @GET("/api/users")
    public void getBooks(Callback<List<User>> response);
}
