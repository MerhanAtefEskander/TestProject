package com.example.testproject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.testproject.data.DbHelper;
import com.example.testproject.data.User;

import java.util.ArrayList;

public class UserAdapter extends BaseAdapter {
    Context context ;
    ArrayList<User> arrayList;
    DbHelper DB ;
   // private UserAdapter userAdapter;




    public UserAdapter(Context context,DbHelper DB, ArrayList<User> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
        this.DB=DB;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView=inflater.inflate(R.layout.record,null);


        TextView user_info_id = convertView.findViewById(R.id.user_info_id);
        TextView user_info_name = convertView.findViewById(R.id.user_info_name);
        TextView user_info_phone= convertView.findViewById(R.id.user_info_phone);
        TextView user_info_church = convertView.findViewById(R.id.user_info_church);
        TextView user_info_email = convertView.findViewById(R.id.user_info_email);
        TextView user_info_password = convertView.findViewById(R.id.user_info_password);
        TextView user_info_ConfirmPassword = convertView.findViewById(R.id.user_info_confirm_password);





        User user = arrayList.get(position);

        int user_id = user.getId();
        String name = user.getName();
        String phone = user.getPhone();
        String church = user.getChurch();
        String email = user.getEmail();
        String password = user.getPassword();
        String confirmpassword = user.getConfirmpassword();

        user_info_id.setText(String.valueOf(position +1));
        user_info_name.setText(name);
        user_info_phone.setText(phone);
        user_info_church.setText(church);
        user_info_email.setText(email);
        user_info_password.setText(password);
        user_info_ConfirmPassword.setText(confirmpassword);

        Button delete = convertView.findViewById(R.id.delete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Confirmation to delete record ");
                builder.setMessage("Are you sure to delete " + name + " ?");
                builder.setIcon(android.R.drawable.ic_menu_delete);
                builder.setCancelable(false);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        DB.deleterecord(user_id);
                        Toast.makeText(context, "Remove record", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(context, RetrieveDataActivity.class);
                        context.startActivity(intent);

                    }
                });
                builder.setNegativeButton("No",null);
                builder.show();
            }}
        );
        UserAdapter userAdapter =new UserAdapter(context,DB,arrayList);

        Button update = convertView.findViewById(R.id.update);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, SignupupdateActivity.class);
                intent.putExtra("name",user.getName());
                intent.putExtra("phonenumber",user.getPhone());
                intent.putExtra("church",user.getChurch());
                intent.putExtra("email",user.getEmail());
                intent.putExtra("password",user.getPassword());
                intent.putExtra("confirm_password",user.getConfirmpassword());
                context.startActivity(intent);

            }
        });

        return convertView;
    }



}
