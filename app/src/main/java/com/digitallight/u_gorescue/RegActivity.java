package com.digitallight.u_gorescue;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.JsonParser;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Pena Orange on 06/01/2016.
 */
public class RegActivity extends AppCompatActivity implements View.OnClickListener{
    EditText editTextName;
    EditText editTextUsername;
    EditText editTextPassword;
    EditText editNoIdentitas;
    EditText editTextEmail;
    EditText editTextTelepon;

    JsonParser jParser = new JsonParser();
    ProgressDialog pDialog;
    private Button buttonRegister;

    public static final String Root_URL = "http://developsalis.esy.es";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pendaftaran);
        getSupportActionBar().hide();

        //Initializing Views
        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextUsername = (EditText) findViewById(R.id.editTextUsername);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editNoIdentitas = (EditText) findViewById(R.id.editNoIdentitas);
        editTextTelepon = (EditText) findViewById(R.id.editTextTelepon);

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
                .setEndpoint(Root_URL) //Setting the Root URL
                .build(); //Finally building the adapter

        //Creating object for our interface
        RegisterAPI api = adapter.create(RegisterAPI.class);

        //Defining the method insertuser of our interface
        api.insertUser(
                //Passing the values by getting it from editTexts
                editTextName.getText().toString(),
                editTextUsername.getText().toString(),
                editTextPassword.getText().toString(),
                editTextEmail.getText().toString(),
                editNoIdentitas.getText().toString(),
                editTextTelepon.getText().toString(),

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
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        //If any error occured displaying the error as toast
                        Toast.makeText(RegActivity.this, "Gagal",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

//    public class input extends AsyncTask<String, String, String>{
//    String success;
//
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//            pDialog = new ProgressDialog(RegActivity.this);
//            pDialog.setMessage("Lagi Proses bro...");
//            pDialog.setIndeterminate(false);
//            pDialog.show();
//        }
//
//        @Override
//        protected String doInBackground(String... arg0) {
//            String nama = editTextName.getText().toString();
//            String username = editTextUsername.getText().toString();
//            String password = editTextPassword.getText().toString();
//            String email = editTextEmail.getText().toString();
//            String no_identitas = editNoIdentitas.getText().toString();
//            String nomor_hp = editTextTelepon.getText().toString();
//
//            List<NameValuePair> params = new ArrayList<NameValuePair>();
//            params.add(new BasicNameValuePair("username", username));
//            params.add(new BasicNameValuePair("password", password));
//            params.add(new BasicNameValuePair("no_identitas", no_identitas));
//            params.add(new BasicNameValuePair("nama", nama));
//            params.add(new BasicNameValuePair("no_hp", nomor_hp));
//            params.add(new BasicNameValuePair("email", email));
//            JSONObject json = jParser.makeHttpRequest(url, "POST", params);
//
//            try {
//                success = json.getString("success");
//
//            } catch (Exception e) {
//                Toast.makeText(getApplicationContext(), "Error",
//                        Toast.LENGTH_LONG).show();
//            }
//
//            return null;
//        }
//    }
}
