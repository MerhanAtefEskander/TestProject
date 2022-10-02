package com.example.testproject.data;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.testproject.R;
import com.example.testproject.SignupActivity;

public class CustomDialog extends AppCompatDialogFragment {

    private EditText phone_number_condition;
    private Activity  activity;
    DbHelper DB;
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_dialog, null);
        builder.setView(view);
        builder.setTitle("Update Data");
        activity = getActivity();
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {


                    }
                })
                .setPositiveButton("View Data", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
//                        String result = DB.getData().getString(1);
//                     String phonecondition = phone_number_condition.getText().toString().trim();
//                     if(phonecondition == result){
//                       Intent intent = new Intent(activity, SignupActivity.class);
//                         startActivity(intent);
//                     }
//                     else {
//                        Toast.makeText(activity, "This phone number is not exist", Toast.LENGTH_SHORT).show();
//
//                     }
                    }
                });

        return builder.create();

    }
}
