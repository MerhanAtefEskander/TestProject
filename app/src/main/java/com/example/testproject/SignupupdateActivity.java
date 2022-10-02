package com.example.testproject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
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
import androidx.appcompat.app.AppCompatActivity;

import com.example.testproject.data.DbHelper;
import com.example.testproject.data.User;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class SignupupdateActivity extends AppCompatActivity {
    private String record;
    private Activity activity;
    User user;
    private int user_id;
   private TextView signup;
    private TextView name;
    private TextView phone_number;
    private TextView church;
    private TextView Email;
    private TextView Password;
    private TextView confirm_password;
   private EditText name_edittext,phone_number_edittext,church_edittext,email_edittext,password_edittext, confirmpassword_edittext;
   private Button save_update_data,delete_record_button;
    private CheckBox show_password,show_confirm_password;
    DbHelper DB;

    String Name , phone , Church , email , password , confirmpassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_page_update);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        signup =(TextView) findViewById(R.id.sign_up_page_update);
        name =(TextView) findViewById(R.id.full_Name_textview_update);
        phone_number =(TextView) findViewById(R.id.phone_number_textview_update);
        church =(TextView) findViewById(R.id.church_textview_update);
        Email =(TextView) findViewById(R.id.Email_textview_update);
        Password =(TextView) findViewById(R.id.password_textview_update);
        confirm_password =(TextView) findViewById(R.id.confirm_password_textview_update);

        name_edittext =(EditText) findViewById(R.id.First_Name_edittext_update);
        phone_number_edittext =(EditText) findViewById(R.id.phone_number_edittext_update);
        church_edittext = (EditText) findViewById(R.id.church_edittext_update);
        email_edittext = (EditText) findViewById(R.id.email_update);
        password_edittext =(EditText) findViewById(R.id.password_update);
        confirmpassword_edittext =(EditText) findViewById(R.id.confirm_password_update);
        DB = new DbHelper(this);

      //  user_id = getIntent().getIntExtra("user_id");
        Name = getIntent().getStringExtra("name");
        phone = getIntent().getStringExtra("phonenumber");
        Church = getIntent().getStringExtra("church");
        email = getIntent().getStringExtra("email");
        password = getIntent().getStringExtra("password");
        confirmpassword = getIntent().getStringExtra("confirm_password");

        name_edittext.setText(Name);
        phone_number_edittext.setText(phone);
        church_edittext.setText(Church);
        email_edittext.setText(email);
        password_edittext.setText(password);
        confirmpassword_edittext.setText(confirmpassword);

        show_password =(CheckBox)findViewById(R.id.show_password);
        show_confirm_password = (CheckBox) findViewById(R.id.show_confirm_password);

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


        save_update_data =(Button) findViewById(R.id.button_save_update);
        save_update_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
         DB.update(Name,name_edittext.getText().toString(),phone_number_edittext.getText().toString(),church_edittext.getText().toString(),email_edittext.getText().toString(),password_edittext.getText().toString(),confirmpassword_edittext.getText().toString());
                Toast.makeText(SignupupdateActivity.this, "Data updated", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(SignupupdateActivity.this,RetrieveDataActivity.class);
                startActivity(intent);

            }
        });

        delete_record_button =(Button) findViewById(R.id.button_delete_record);
        delete_record_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(SignupupdateActivity.this);
                builder.setTitle("Confirmation to delete record");
                builder.setMessage("Are you sure to delete " + Name + " ?");
                builder.setIcon(android.R.drawable.ic_menu_delete);
                builder.setCancelable(false);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        DB.deleterecord(user_id);
                        Toast.makeText(SignupupdateActivity.this, "Remove record", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(SignupupdateActivity.this, RetrieveDataActivity.class);
                        startActivity(intent);

                    }
                });
                builder.setNegativeButton("No",null);
                builder.show();
            }
        });

}

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

public void signUpFunction(){
    Intent intent = new Intent(this,HomePage.class);
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

