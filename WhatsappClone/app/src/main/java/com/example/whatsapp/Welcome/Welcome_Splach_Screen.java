package com.example.whatsapp.Welcome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

import com.example.whatsapp.R;
import com.example.whatsapp.Tabbed_Screen.Tabbed_Screen;
import com.example.whatsapp.User_Login_Screen;
import com.google.firebase.auth.FirebaseAuth;

public class Welcome_Splach_Screen extends AppCompatActivity {

    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome__splach__screen);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        auth=FirebaseAuth.getInstance();
        Thread t=new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1*500);
                    if (auth.getCurrentUser() !=null)
                    {
                        startActivity(new Intent(Welcome_Splach_Screen.this, Tabbed_Screen.class));
                        finish();
                    }
                    else
                    {
                        startActivity(new Intent(Welcome_Splach_Screen.this, Welcome_to_Whatsapp_Screen.class));
                        finish();
                    }

                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }            }
        });
        t.start();
    }
}