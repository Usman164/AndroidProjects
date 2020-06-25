package com.example.whatsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.whatsapp.Tabbed_Screen.Tabbed_Screen;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class User_Login_Screen extends AppCompatActivity {
Button register_BTN,login_BTN;
EditText email,password;
FirebaseAuth auth;
ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user__login__screen);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        login_BTN=findViewById(R.id.user_login_page_button);
        email=findViewById(R.id.user_login_email);
        password=findViewById(R.id.user_login_password);
        register_BTN=findViewById(R.id.user_signup_button);
        progressDialog=new ProgressDialog(this);
        auth= FirebaseAuth.getInstance();
        register_BTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(User_Login_Screen.this,Register_Screen.class));
            }
        });
        login_BTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CheckValidation();
            }
        });
    }

    private void CheckValidation() {
        String Email = email.getText().toString().trim();
        String Password = password.getText().toString().trim();

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
            Login_Authentication(Email, Password);
        }
    }

    private void Login_Authentication(String email, String password) {
        progressDialog.setMessage("Please wait...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful())
                {
                    startActivity(new Intent(User_Login_Screen.this, Tabbed_Screen.class));
                    finish();
                }
                else
                {
                    progressDialog.dismiss();
                    Toast.makeText(User_Login_Screen.this, "Not Authentication", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}