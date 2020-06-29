package com.example.whatsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.mikhaellopez.circularimageview.CircularImageView;

public class Setting_Menu_Items extends AppCompatActivity {
    Toolbar setting_toolbar;
    CircularImageView setting_circularImageView;
    TextView setting_name;
    Intent intent;
    LinearLayout profile_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting__menu__items);
        intent=getIntent();

        setting_toolbar= (Toolbar) findViewById(R.id.toolbar_setting_screen);
        setSupportActionBar(setting_toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setting_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        setting_circularImageView=findViewById(R.id.setting_circularImage_id);
        setting_name=findViewById(R.id.setting_name_id);
        final String name=intent.getStringExtra("setting_name");
        final  String imageURL=intent.getStringExtra("imageURL");
        final  String email=intent.getStringExtra("email");
        Glide.with(Setting_Menu_Items.this).load(imageURL).into(setting_circularImageView);
        setting_name.setText(name);
        profile_id=findViewById(R.id.setting_profile_detail_id);
        profile_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent=new Intent(Setting_Menu_Items.this,User_Profile_Screen.class);
                intent.putExtra("name",name);
                intent.putExtra("imageURL",imageURL);
                intent.putExtra("email",email);
                startActivity(intent);
            }
        });
    }
}