package com.digitallight.u_gorescue;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    Button b1;
    EditText et1, et2;
    TextView et3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        b1 = (Button) findViewById(R.id.button);
        et1 = (EditText) findViewById(R.id.editText);
        et2 = (EditText) findViewById(R.id.editText2);
        et3 = (TextView) findViewById(R.id.TextBelumDaftar);
        Toast.makeText(getApplicationContext(), "user001 || user1234", Toast.LENGTH_SHORT).show();
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (et1.getText().toString().equals("user001")&& et2.getText().toString().equals("user1234")){
                    Intent intent = new Intent(LoginActivity.this,Home.class);
                    startActivity(intent);
                    finish();
                }else {
                    Toast.makeText(getApplicationContext(), "Username dan Password Tidak Cocok", Toast.LENGTH_SHORT).show();
                }
            }
        });

        et3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent = new Intent(LoginActivity.this,RegActivity.class);
                    startActivity(intent);
            }
        });
    }
}
