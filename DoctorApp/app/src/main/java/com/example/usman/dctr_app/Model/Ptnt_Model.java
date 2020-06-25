package com.example.usman.dctr_app.Model;

public class Ptnt_Model {
    private String Name;
    private String Email;
    private String Password;
    private String Phone;
    private String Gender;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getImageURL() {
        return ImageURL;
    }

    public void setImageURL(String imageURL) {
        ImageURL = imageURL;
    }

    private String ImageURL;

    public Ptnt_Model(String name, String email, String password, String phone, String gender, String imageURL) {
        Name = name;
        Email = email;
        Password = password;
        Phone = phone;
        Gender = gender;
        ImageURL = imageURL;
    }
}
