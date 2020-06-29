package com.example.whatsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class User_Profile_Screen extends AppCompatActivity {
Intent intent;
ImageView profile_image,back_arrow,edit_btn;
TextView user_name,user_email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user__profile__screen);

        profile_image=findViewById(R.id.user_profile_screen_image_view);
        back_arrow=findViewById(R.id.profile_screen_back_Arrow);
        edit_btn=findViewById(R.id.profile_screen_edit_option);
        back_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(User_Profile_Screen.this,Setting_Menu_Items.class);
                startActivity(intent);
                finish();
            }
        });
        edit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(User_Profile_Screen.this, "Edit Your Profile", Toast.LENGTH_SHORT).show();
            }
        });
        user_name=findViewById(R.id.user_profile_screen_user_name_id);
        user_email=findViewById(R.id.user_profile_screen_email_id);
        intent=getIntent();
        String userName=intent.getStringExtra("name");
        String image=intent.getStringExtra("imageURL");
        String email=intent.getStringExtra("email");
        user_name.setInputType(InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);
        user_name.setText(userName);
        user_email.setText(email);
        Glide.with(User_Profile_Screen.this).load(image).into(profile_image);
    }
}