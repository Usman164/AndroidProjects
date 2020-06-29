package com.example.custom_navigation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class Login_Page extends AppCompatActivity {
    Button login_back,Log_in;
    EditText email,password;
    String Email ;
    String Password ;
    TextView contactUS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login__page);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Log_in=findViewById(R.id.log_in_id);
        email=findViewById(R.id.login_email);
        password=findViewById(R.id.login_password);
        contactUS=findViewById(R.id.contact_us);
        login_back=findViewById(R.id.login_backward);
        login_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Login_Page.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        Log_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckAllValidation();
            }
        });
        contactUS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent(Intent.ACTION_VIEW);
                sendIntent.setData(Uri.parse("sms:"+ 1133));
                sendIntent.putExtra("sms_body", "Its an Emergency");
                startActivity(sendIntent);
            }
        });
    }

    private void CheckAllValidation() {
        Email = email.getText().toString().trim();
        Password = password.getText().toString().trim();
        if (TextUtils.isEmpty(Email)){
            email.setError("Please Enter Email");
        }
        else if (!(Patterns.EMAIL_ADDRESS.matcher(Email).matches())){
            email.setError("Please Enter ValidEmail");
        }
        else if (TextUtils.isEmpty(Password)){
            password.setError("Please Enter Password");
        }
        else {
            Toast.makeText(getApplicationContext(), "Data Recorded", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Login_Page.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent=new Intent(Login_Page.this,MainActivity.class);
        startActivity(intent);
        finish();
        super.onBackPressed();
    }

}