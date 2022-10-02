package com.example.testproject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.testproject.data.DbHelper;
import com.example.testproject.data.User;

import java.security.PublicKey;
import java.util.ArrayList;

public class RetrieveDataActivity extends AppCompatActivity {
    DbHelper DB;
    ListView listView;
    ArrayList<User> arrayList;
    UserAdapter adapter;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.view_data);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        DB = new DbHelper(this);
        listView= findViewById(R.id.user_info);
        showuserdata();




    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    public void showuserdata() {
        arrayList = DB.getUserData();
        adapter = new UserAdapter(this,DB,arrayList);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
