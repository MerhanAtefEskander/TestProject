package com.example.testproject;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Patterns;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.testproject.data.CustomDialog;
import com.example.testproject.data.DbHelper;
import com.example.testproject.data.User;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class SignupActivity extends AppCompatActivity {
    private String record;
    private Activity activity;
    private String id;
   private TextView signup;
    private TextView name;
    private TextView phone_number;
    private TextView church;
    private TextView Email;
    private TextView Password;
    private TextView confirm_password;
   private EditText name_edittext,phone_number_edittext,church_edittext,email_edittext,password_edittext, confirmpassword_edittext;
   private Button signup_btn,get_data_btn,delete_table;
   private CheckBox show_password,show_confirm_password;
    DbHelper dbHelper;
    SQLiteDatabase DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_page);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        signup =(TextView) findViewById(R.id.sign_up_page);
        name =(TextView) findViewById(R.id.full_Name_textview);
        phone_number =(TextView) findViewById(R.id.phone_number_textview);
        church =(TextView) findViewById(R.id.church_textview);
        Email =(TextView) findViewById(R.id.Email_textview);
        Password =(TextView) findViewById(R.id.password_textview);
        confirm_password =(TextView) findViewById(R.id.confirm_password_textview);

        name_edittext =(EditText) findViewById(R.id.First_Name_edittext);
        phone_number_edittext =(EditText) findViewById(R.id.phone_number_edittext);
        church_edittext = (EditText) findViewById(R.id.church_edittext);
        email_edittext = (EditText) findViewById(R.id.email);
        password_edittext =(EditText) findViewById(R.id.password);
        confirmpassword_edittext =(EditText) findViewById(R.id.confirm_password);

        show_password =(CheckBox)findViewById(R.id.show_password);
        show_confirm_password = (CheckBox) findViewById(R.id.show_confirm_password);
        dbHelper = new DbHelper(this);

        show_password.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean ischecked) {
                if(ischecked){
                    password_edittext.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }else{
                    password_edittext.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });

        show_confirm_password.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean ischecked) {
                if(ischecked){
                    confirmpassword_edittext.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }else{
                    confirmpassword_edittext.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });


        signup_btn =(Button) findViewById(R.id.button_signup);
        signup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = BaseColumns._ID;
             String name = name_edittext.getText().toString();
             String phonenumber = phone_number_edittext.getText().toString();
             String church = church_edittext.getText().toString();
             String email = email_edittext.getText().toString();
             String password = password_edittext.getText().toString();
             String confirm_password = confirmpassword_edittext.getText().toString();


         boolean checkinsertdata = dbHelper.insertuser(name,phonenumber,church,email,password,confirm_password);
             if (checkinsertdata == true){
                 validateEmail();
                 validatePassword();
                 validateName();
                 validatePhoneNumber();
                 Toast.makeText(SignupActivity.this,"New Data Entry",Toast.LENGTH_SHORT).show();
                 signUpFunction();

             }else{
                 validateEmail();
                 validatePassword();
                 validateName();
                 validatePhoneNumber();
                 Toast.makeText(SignupActivity.this,"there is a problem",Toast.LENGTH_SHORT).show();
             }

            }
        });



        get_data_btn =(Button) findViewById(R.id.button_get_data);
        get_data_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<User> res =  dbHelper.getUserData();
                if (res.size() == 0) {
                    Toast.makeText(SignupActivity.this, "No Entry", Toast.LENGTH_SHORT).show();
                    return;
                }
                showdata();
            }

        });


//        delete_table=(Button) findViewById(R.id.button_delete_table);
//        delete_table.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                dbHelper.onUpgrade(DB,1,2);
//
//            }
//        });





}
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

public void showdata(){
    Intent intent = new Intent(this, RetrieveDataActivity.class);
    startActivity(intent);
}
public void signUpFunction(){
    Intent intent = new Intent(this,MainActivity.class);
    startActivity(intent);

}

private boolean validateEmail(){
        String emailInput =  email_edittext.getText().toString().trim();
        if(emailInput.isEmpty()){
            email_edittext.setError("Field can't be empty");
            return false;
        }
        else if (!EMAIL_ADDRESS.matcher(emailInput).matches()){
            email_edittext.setError("Please enter valid email number");
            return false;

        }else
        {
            email_edittext.setError(null);
            return true;
        }
}

private boolean validatePassword(){
       String  passwordinput = password_edittext.getText().toString().trim();
       if (passwordinput.isEmpty()){
           password_edittext.setError("Field can't be empty");
           return false;
       }
       else if(!PASSWORD_PATTERN.matcher(passwordinput).matches()){
           password_edittext.setError("Password too weak");
           return false;
       }
       else{
           password_edittext.setError(null);
           return true;
       }
}

    private boolean validatePhoneNumber(){
        String  phoneinput = phone_number_edittext.getText().toString().trim();
        if (phoneinput.isEmpty()){
            phone_number_edittext.setError("Field can't be empty");
            return false;
        }
        else if(!PHONE_PATTERN.matcher(phoneinput).matches()){
            phone_number_edittext.setError("at least 11 digit");
            return false;
        }
        else{
            phone_number_edittext.setError(null);
            return true;
        }

    }

    private boolean validateName(){
        String nameinput = name_edittext.getText().toString().trim();
        if(nameinput.isEmpty()){
            name_edittext.setError("Field can't be empty");
            return false;
        }
        else if(!NAME_PATTERN.matcher(nameinput).matches()){
            name_edittext.setError("Field can't be empty");
            return false;
        }
        else
        {
            name_edittext.setError(null);
            return true;
        }
    }

public static final Pattern EMAIL_ADDRESS = Pattern.compile(
        "[a-zA-Z0-9\\+\\.\\_\\%\\+]{1,256}" +
                "\\@" +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                "("+
                "\\." +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}"+
                ")+"

);
    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    //"(?=.*[0-9])" +         //at least 1 digit
                    //"(?=.*[a-z])" +         //at least 1 lower case letter
                    //"(?=.*[A-Z])" +         //at least 1 upper case letter
                    "(?=.*[a-zA-Z])" +      //any letter
                    "(?=.*[@#$%^&+=])" +    //at least 1 special character
                    "(?=\\S+$)" +           //no white spaces
                    ".{4,}" +               //at least 4 characters
                    "$");

    public static final Pattern PHONE_PATTERN =
            Pattern.compile(
                 "^01[0125][0-9]{8}$"
            ) ;

    public static final Pattern NAME_PATTERN=
        Pattern.compile(
                "^"+
                "[a-zA-Z\\s]+"+
                        " "+
                        "[a-zA-Z\\s]+"+
                        " "+
                        "[a-zA-Z\\s]+"

        );

}

