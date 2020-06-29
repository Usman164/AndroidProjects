package com.example.custom_navigation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

public class Gift_Cards extends AppCompatActivity {
    Button locatio_gift,gift1,gift2,gift3,gift4,gift5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gift__cards);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Intent setText=getIntent();
        final Bundle bundle=setText.getExtras();


        locatio_gift=findViewById(R.id.gift_location_id_btn);
        gift1=findViewById(R.id.gift_1_id_btn);
        gift2=findViewById(R.id.gift_2_id_btn);
        gift3=findViewById(R.id.gift_3_id_btn);
        gift4=findViewById(R.id.gift_4_id_btn);
        gift5=findViewById(R.id.gift_5_id_btn);


        locatio_gift.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Gift_Cards.this,select_your_gift.class);
                startActivity(intent);
            }
        });
        gift1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(getApplicationContext(),Massage_Duration_Gift.class);
                String value=bundle.getString("name");
                intent.putExtra("country",value);
                intent.putExtra("massage1","Single Massage");
                intent.putExtra("gift1","1");
                startActivity(intent);
            }
        });
        gift2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(getApplicationContext(),Massage_Duration_Gift.class);
                String value=bundle.getString("name");
                intent.putExtra("country",value);
                intent.putExtra("massage1","Couple Massage");
                intent.putExtra("gift1","2");
                startActivity(intent);
            }
        });
        gift3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(getApplicationContext(),Massage_Duration_Gift.class);
                String value=bundle.getString("name");
                intent.putExtra("country",value);
                intent.putExtra("massage1","Massage Membership");
                intent.putExtra("gift1","3");
                startActivity(intent);
            }
        });
        gift4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(getApplicationContext(),Massage_Duration_Gift.class);
                String value=bundle.getString("name");
                intent.putExtra("country",value);
                intent.putExtra("massage1","Packages");
                intent.putExtra("gift1","4");
                startActivity(intent);
            }
        });
        gift5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(getApplicationContext(),Massage_Duration_Gift.class);
                String value=bundle.getString("name");
                intent.putExtra("country",value);
                intent.putExtra("massage1","Other Amount");
                intent.putExtra("gift1","5");
                startActivity(intent);
            }
        });
        if (bundle!=null) {
            String string=(String) bundle.get("name");
            locatio_gift.setText(string);
            gift1.setEnabled(true);
            gift2.setEnabled(true);
            gift3.setEnabled(true);
            gift4.setEnabled(true);
            gift5.setEnabled(true);
        }
        else
        {
            locatio_gift.setText("Selected Name");
            gift1.setEnabled(false);
            gift2.setEnabled(false);
            gift3.setEnabled(false);
            gift4.setEnabled(false);
            gift5.setEnabled(false);
            gift1.setBackgroundColor(getResources().getColor(R.color.de_activeBTN));
            gift2.setBackgroundColor(getResources().getColor(R.color.de_activeBTN));
            gift3.setBackgroundColor(getResources().getColor(R.color.de_activeBTN));
            gift4.setBackgroundColor(getResources().getColor(R.color.de_activeBTN));
            gift5.setBackgroundColor(getResources().getColor(R.color.de_activeBTN));
        }
    }
}
