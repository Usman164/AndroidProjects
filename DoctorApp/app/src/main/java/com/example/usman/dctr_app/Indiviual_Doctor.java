package com.example.usman.dctr_app;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import com.squareup.picasso.Picasso;

public class Indiviual_Doctor extends AppCompatActivity {
    private Context ctx;

    ImageView imageView;
    TextView textViewName,textViewSpec;
    RatingBar dctr_rating;

    String new_name,new_specialization;
    String new_image;
    Float new_rating;

    Button complain_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indiviual__doctor);

        imageView=findViewById(R.id.doctor_image);
        textViewName = findViewById(R.id.dctr_name);
        textViewSpec = findViewById(R.id.D_special);
        dctr_rating=findViewById(R.id.dctr_rating);
        complain_btn=findViewById(R.id.btn_comp_ptnt);
        complain_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Indiviual_Doctor.this, Patient_Complaint_box.class));
            }
        });

        new_name = getIntent().getStringExtra("name");
        new_rating = getIntent().getFloatExtra("d_Rating",0.0f);
        new_specialization = getIntent().getStringExtra("spec");
        new_image = getIntent().getStringExtra("imageURL");

        Picasso.with(ctx).load(new_image).fit().into(imageView);
        textViewName.setText(new_name);
        textViewSpec.setText(new_specialization);
        dctr_rating.setRating(new_rating);

    }
}
