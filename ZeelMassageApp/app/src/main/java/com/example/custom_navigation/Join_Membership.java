package com.example.custom_navigation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class Join_Membership extends AppCompatActivity {

    @Override
    public void onBackPressed() {

        super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join__membership);
        Intent intent=new Intent(this,MainActivity.class);
        String i="abcsjdhc";
        intent.putExtra("value",i);
        startActivity(intent);


    }
}
