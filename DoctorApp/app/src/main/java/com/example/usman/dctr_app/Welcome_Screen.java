package com.example.usman.dctr_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class  Welcome_Screen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome__screen);
        Thread t = new Thread(new Runnable() {
            @Override
            public void run()
            {
                try {
                    Thread.sleep(3*1000);
                    startActivity(new Intent(getApplicationContext(),Dctr_Login.class ));
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();
    }
}
