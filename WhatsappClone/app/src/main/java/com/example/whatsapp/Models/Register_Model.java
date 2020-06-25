package com.example.whatsapp.Models;

public class Register_Model {
    String name,email,pass,phone,gender,imageUrl;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Register_Model(String name, String email, String pass, String phone, String gender, String imageUrl) {
        this.name = name;
        this.email = email;
        this.pass = pass;
        this.phone = phone;
        this.gender = gender;
        this.imageUrl = imageUrl;
    }
}
