package com.digitallight.u_gorescue;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    Button b1;
    EditText et1, et2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        b1 = (Button) findViewById(R.id.button);
        et1 = (EditText) findViewById(R.id.editText);
        et2 = (EditText) findViewById(R.id.editText);
        Toast.makeText(getApplicationContext(), "admin || admin", Toast.LENGTH_SHORT).show();
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (et1.getText().toString().equals("admin")&& et2.getText().toString().equals("admin")){
                    Intent intent = new Intent(LoginActivity.this,Home.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(getApplicationContext(), "Mungkin Tidak Cocok", Toast.LENGTH_SHORT).show();
                }

            }
        });



    }
}
