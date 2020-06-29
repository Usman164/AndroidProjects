package com.example.custom_navigation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.matthewtamlin.sliding_intro_screen_library.indicators.DotIndicator;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MassageAppointment extends AppCompatActivity {
    TextView single,couple,back_to_back,chair;

    DotIndicator dotIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_massage_appointment);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        single=findViewById(R.id.single_massage);
        couple=findViewById(R.id.couple_massage);
        back_to_back=findViewById(R.id.back_to_back_massage);
        chair=findViewById(R.id.chair_massage);
        single.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Single Massage",Toast.LENGTH_SHORT).show();
            }
        });
        couple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Couple Massage",Toast.LENGTH_SHORT).show();
            }
        });
        back_to_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Back-to-Back Massage",Toast.LENGTH_SHORT).show();
            }
        });
        chair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Chair Massage",Toast.LENGTH_SHORT).show();
            }
        });
//        init();
    }


}
