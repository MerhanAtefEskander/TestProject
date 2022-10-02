package com.example.testproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private TextView login,forgetpassword,email, password;
    private EditText email_edittext,password_edittext;
   private Button login_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);

        login = (TextView) findViewById(R.id.login);
        email = (TextView) findViewById(R.id.Email_login_textview);
        password = (TextView) findViewById(R.id.password_login_textview) ;
        forgetpassword = (TextView) findViewById(R.id.forget_password);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        login_btn = (Button) findViewById(R.id.button_login);
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginFunction();
            }
        });
    }

    public void loginFunction(){
        Intent intent = new Intent(this,HomePage.class);
        startActivity(intent);
    }
}
