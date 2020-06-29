package com.example.custom_navigation;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class anim_splach extends AppCompatActivity {
    Animation topAnim,bottomAnim;
    ImageView topImage,bottomImage;
    private static int Splach_TimeOut =5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_anim_splach);
        topImage=findViewById(R.id.id_Logo_PHC);
        bottomImage=findViewById(R.id.id_detail_PHC);

        topAnim = AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this,R.anim.bottom_animation);

        topImage.setAnimation(topAnim);
        bottomImage.setAnimation(bottomAnim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(anim_splach.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        },Splach_TimeOut);
    }
}
