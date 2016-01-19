package com.digitallight.u_gorescue;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.digitallight.u_gorescue.rest.UsersApi;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
/**
 * Created by Pena Orange on 06/01/2016.
 */
public class RegActivity extends AppCompatActivity implements View.OnClickListener{
    //Declaring views
    private String editTextIdUser = null;
    private EditText editTextUsername;
    private EditText editTextPassword;
    private EditText editTextNoIdentitas;
    private EditText editTextNama;
    private EditText editTextNomorHp;
    private EditText editTextEmail;

    private Button buttonRegister;

    //This is our root url
    public static final String ROOT_URL = "http://developsalis.esy.es/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pendaftaran);
        getSupportActionBar().hide();

        ButterKnife.bind(this);
        //Initializing Views
        editTextUsername = (EditText) findViewById(R.id.editTextUsername);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        editTextNoIdentitas = (EditText) findViewById(R.id.editTextNoIdentitas);
        editTextNama = (EditText) findViewById(R.id.editTextNama);
        editTextNomorHp = (EditText) findViewById(R.id.editTextNomorHp);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);

        buttonRegister = (Button) findViewById(R.id.buttonRegister);

        //Adding listener to button
        buttonRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        insertUser();
    }

    private void insertUser(){
        //Here we will handle the http request to insert user to mysql db
        //Creating a RestAdapter
        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(ROOT_URL) //Setting the Root URL
                .build(); //Finally building the adapter

        //Creating object for our interface
        UsersApi api = adapter.create(UsersApi.class);

        //Defining the method insertuser of our interface
        api.insertUser(
                //Passing the values by getting it from editTexts
                editTextIdUser,
                editTextUsername.getText().toString(),
                editTextPassword.getText().toString(),
                editTextNoIdentitas.getText().toString(),
                editTextNama.getText().toString(),
                editTextNomorHp.getText().toString(),
                editTextEmail.getText().toString(),

                //Creating an anonymous callback
                new Callback<Response>() {
                    @Override
                    public void success(Response result, Response response) {
                        //On success we will read the server's output using bufferedreader
                        //Creating a bufferedreader object
                        BufferedReader reader = null;

                        //An string to store output from the server
                        String output = "";

                        try {
                            //Initializing buffered reader
                            reader = new BufferedReader(new InputStreamReader(result.getBody().in()));

                            //Reading the output in the string
                            output = reader.readLine();
                            Toast.makeText(RegActivity.this, output, Toast.LENGTH_LONG).show();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        //Displaying the output as a toast
                        Toast.makeText(RegActivity.this, "Berhasil", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(RegActivity.this,LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        //If any error occured displaying the error as toast
                        Toast.makeText(RegActivity.this, "Gagal",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    @OnClick(R.id.btnBack)
    public void back() {
        Intent intent = new Intent(getBaseContext(), LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
