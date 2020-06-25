package com.example.whatsapp.Welcome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.example.whatsapp.R;
import com.example.whatsapp.Register_Screen;
import com.example.whatsapp.User_Login_Screen;

public class Welcome_to_Whatsapp_Screen extends AppCompatActivity {
Button agree;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_to__whatsapp__screen);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        agree=findViewById(R.id.agree_and_continue_id);
        agree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Welcome_to_Whatsapp_Screen.this, User_Login_Screen.class));
                finish();
            }
        });
    }
}