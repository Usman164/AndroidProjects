package com.example.usman.dctr_app.Model;

/**
 * Created by umarz on 4/19/2020.
 */

public class Dctr_model
{
    private String Name, Email,Password, Phone , Spec,Gender, ImageURL;
    public Dctr_model(String name, String email, String password, String phone,String spec, String rating, String gender, String imageURL) {

        Name = name;
        Email = email;
        Password = password;
        Phone = phone;
        Ratingbar = Float.parseFloat(rating);
        Spec = spec;
        Gender = gender;
        ImageURL = imageURL;
    }


    private Float Ratingbar;

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

    public void setPhone(String phone)
    {
        Phone = phone;
    }
    public Float getD_Rating() {
        return Ratingbar;
    }

    public void setD_Rating(Float d_Rating) {
        Ratingbar = d_Rating;
    }

    public String getSpec() {
        return Spec;
    }

    public void setSpec(String spec) {
        Spec = spec;
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




}
