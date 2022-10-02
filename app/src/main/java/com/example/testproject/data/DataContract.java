package com.example.testproject.data;

import android.provider.BaseColumns;

public final class DataContract {
    //it contains the names of columns in our table and the name of the table

    public final static class DataEntry implements BaseColumns {

        public final static String TABLE_NAME ="PersonalInformation";
        public final static String _ID =BaseColumns._ID;
        public final static String COLUMN_NAME ="name";
        public final static String COLUMN_PHONE = "phone";
        public final static String COLUMN_CHURCH ="church";
        public final static String COLUMN_EMAIL ="email";
        public final static String COLUMN_PASSWORD ="password";
        public final static String COLUMN_CONFIRM_PASSWORD ="confirm_password";
    }
}
