package com.example.testproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.testproject.data.DbHelper;


public class MainActivity extends AppCompatActivity {
TextView welcome;
Button signup , signin;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_page);

//        DbHelper DB = new DbHelper(this);
//        DB.getData();

        welcome =(TextView) findViewById(R.id.welcome_text_view);

        signup = (Button) findViewById(R.id.sign_up);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
          public void onClick(View view) {
                btnSignUp();
              }
});

        signin = (Button) findViewById(R.id.sign_in);
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                btnSignIn();
            }
        });
}

public void btnSignUp(){
Intent intent = new Intent(this,SignupActivity.class);
 startActivity(intent);
}

public void btnSignIn(){
    Intent intent = new Intent(this,LoginActivity.class);
    startActivity(intent);
    }
}