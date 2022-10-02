package com.example.testproject.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;


import com.example.testproject.UserAdapter;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

public class DbHelper extends SQLiteOpenHelper {


    public static final String DATABASE_NAME = "Personal_Information.db";
    public static final String TABLE_1_NAME = "PersonalInformation";
    public static final Integer DATABASE_VERSION = 2;


    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("Create Table "+TABLE_1_NAME+" ( user_id integer primary key autoincrement , name TEXT, phonenumber TEXT, church TEXT, email TEXT,password TEXT,confirm_password TEXT )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("DROP TABLE IF EXISTS "+TABLE_1_NAME );
        onCreate(DB);
    }


    public boolean insertuser(String name, String phonenumber, String church, String email, String password, String confirm_password) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("phonenumber", phonenumber);
        contentValues.put("church", church);
        contentValues.put("email", email);
        contentValues.put("password", password);
        contentValues.put("confirm_password", confirm_password);

        long results = DB.insert(TABLE_1_NAME, null, contentValues);
        if (results == -1) {
            return false;
        } else {
            return true;
        }


    }

    public ArrayList<User> getUserData() {
        SQLiteDatabase DB = this.getReadableDatabase();
        ArrayList<User> arrayList = new ArrayList<User>();
        Cursor cursor = DB.rawQuery("Select * from "+TABLE_1_NAME, null, null);
        if (cursor.moveToNext()) {

            do {
                int id =Integer.parseInt( cursor.getString(0));
                String name = cursor.getString(1);
                String phone = cursor.getString(2);
                String church = cursor.getString(3);
                String email = cursor.getString(4);
                String password = cursor.getString(5);
                String confirmpassword = cursor.getString(6);
                User user = new User( id,name, phone, church, email, password, confirmpassword);
                arrayList.add(user);
            }
            while (cursor.moveToNext());

        }
        cursor.close();
        return arrayList;
    }


    public boolean update(String originalname ,String name, String phone, String church, String email, String password, String confirm_password) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
     //   contentValues.put("_id",id);
        contentValues.put("name", name);
        contentValues.put("phonenumber", phone);
        contentValues.put("church", church);
        contentValues.put("email", email);
        contentValues.put("password", password);
        contentValues.put("confirm_password", confirm_password);
        long results = DB.update(TABLE_1_NAME, contentValues, "name=?", new String[]{String.valueOf(originalname)});
        if (results == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean deleterecord(int user_id ) {
        SQLiteDatabase DB = this.getWritableDatabase();
       long results = DB.delete(TABLE_1_NAME,"user_id=?",new String[]{String.valueOf(user_id)});
        if (results == -1) {
            return false;
        } else {
            return true;
        }

    }
}

