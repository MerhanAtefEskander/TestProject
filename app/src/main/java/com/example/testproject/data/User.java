package com.example.testproject.data;

public class User {
    int id;
    String name , phone ,church , email,password,confirmpassword;

    public User(String name, String phone, String church, String email, String password, String confirmpassword) {
        this.name = name;
        this.phone = phone;
        this.church = church;
        this.email = email;
        this.password = password;
        this.confirmpassword = confirmpassword;
    }
 public User(int id,String name, String phone, String church, String email, String password, String confirmpassword) {
        this.id=id;
        this.name = name;
        this.phone = phone;
        this.church = church;
        this.email = email;
        this.password = password;
        this.confirmpassword = confirmpassword;
    }


    public int getId() {
        return id;
    }

    public String getChurch() {
        return church;
    }

    public void setChurch(String church) {
        this.church = church;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmpassword() {
        return confirmpassword;
    }

    public void setConfirmpassword(String confirmpassword) {
        this.confirmpassword = confirmpassword;
    }
}
