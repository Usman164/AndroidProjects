package com.example.custom_navigation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class After_Appropriate_Gift_Selected_Personal_Detail extends AppCompatActivity {
    Button next_personal_detail;
    EditText rec_fname,rec_lname,rec_email,giver_name,giver_email,message_text;
    TextView massageType;
    String r_fname,r_lname,r_email,g_name,g_email,message_string_text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after__appropriate__gift__selected__personal__detail);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        rec_fname=findViewById(R.id.recipient_first_name_id);
        rec_lname=findViewById(R.id.recipient_last_name_id);
        rec_email=findViewById(R.id.recipient_email_id);
        giver_name=findViewById(R.id.giver_name_id);
        giver_email=findViewById(R.id.giver_email_id);
        message_text=findViewById(R.id.text_message_id);

        massageType=findViewById(R.id.massage_type_id);

        Intent setText=getIntent();
        Bundle text=setText.getExtras();
        final String string=(String) text.get("description");
        massageType.setText(string);

        next_personal_detail=findViewById(R.id.massage_personal_detail_next_btn);
        next_personal_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckAllValidation();
            }
        });
    }
    private void CheckAllValidation() {
        r_fname = rec_fname.getText().toString().trim();
        r_lname = rec_lname.getText().toString().trim();
        r_email = rec_email.getText().toString().trim();
        g_name = giver_name.getText().toString().trim();
        g_email = giver_email.getText().toString().trim();
        message_string_text = message_text.getText().toString().trim();
        if (TextUtils.isEmpty(r_fname)){
            rec_fname.setError("Please Enter First_Name");
        }
        else if (TextUtils.isEmpty(r_lname)){
            rec_lname.setError("Please Enter Last_Name");
        }
        else if (TextUtils.isEmpty(r_email)){
            rec_email.setError("Please Enter Email");
        }
        else if (!(Patterns.EMAIL_ADDRESS.matcher(r_email).matches())){
            rec_email.setError("Please Enter ValidEmail");
        }
        if (TextUtils.isEmpty(g_name)){
            giver_name.setError("Please Enter First_Name");
        }
        else if (TextUtils.isEmpty(g_email)){
            giver_email.setError("Please Enter Email");
        }
        else if (!(Patterns.EMAIL_ADDRESS.matcher(g_email).matches())){
            giver_email.setError("Please Enter ValidEmail");
        }
        else
            {

            Toast.makeText(getApplicationContext(), "Your detail has been submitted, We'll contact you soon", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();

        }
    }

}
