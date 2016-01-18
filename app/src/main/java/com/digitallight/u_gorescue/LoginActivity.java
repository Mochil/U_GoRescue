package com.digitallight.u_gorescue;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.digitallight.u_gorescue.model.User;
import com.digitallight.u_gorescue.rest.UsersApi;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class LoginActivity extends AppCompatActivity {
    //Root URL of our web service
    public static final String ROOT_URL = "http://developsalis.esy.es/";
    private List<User> users;

    Button b1;
    EditText et1, et2;
    TextView et3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        getSupportActionBar().hide();
        b1 = (Button) findViewById(R.id.button);
        et1 = (EditText) findViewById(R.id.editText);
        et2 = (EditText) findViewById(R.id.editText2);
        et3 = (TextView) findViewById(R.id.TextBelumDaftar);
        Toast.makeText(getApplicationContext(), "user001 || user1234", Toast.LENGTH_SHORT).show();
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getLogin();

            }
        });

        et3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent = new Intent(LoginActivity.this,RegActivity.class);
                    startActivity(intent);
                    finish();
            }
        });
    }

    private void getLogin(){
        //While the app fetched data we are displaying a progress dialog
        final ProgressDialog loading = ProgressDialog.show(this,"Validasi Akun","Tunggu sebentar",false,false);

        //Creating a rest adapter
        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(ROOT_URL)
                .build();

        //Creating an object of our api interface
        UsersApi api = adapter.create(UsersApi.class);

        //Defining the method
        api.getBooks(new Callback<List<User>>() {
            @Override
            public void success(List<User> list, Response response) {
                //Dismissing the loading progressbar
                loading.dismiss();

                //Storing the data in our list
                users = list;

                boolean hasilValidasi = false;

                String[] items = new String[users.size()];
                String username = et1.getText().toString().trim();
                String password = et2.getText().toString().trim();

                //Traversing through the whole list to get all the names
                for(int i=0; i<users.size(); i++){
                    //Storing names to string array
                    if (users.get(i).getUsername().equals(username) && users.get(i).getPassword().equals(password)) {
                        hasilValidasi = true;
                        break;
                    }else{
                        hasilValidasi = false;
                    }
                }

                if (hasilValidasi == true) {
                    Toast.makeText(getApplicationContext(), "Berhasil" ,Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this,MainTest.class);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(getApplicationContext(), "Gagal" ,Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }
}
