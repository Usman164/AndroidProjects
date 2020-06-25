package com.example.usman.dctr_app;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Dctr_Login extends AppCompatActivity {
    Button  button2, login;
    EditText email, password;

    ProgressDialog progressDialog;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dctr__login);

        button2 = findViewById(R.id.signup_button);
        login = findViewById(R.id.login_button);
        email = findViewById(R.id.login_email);
        password = findViewById(R.id.login_password);

        progressDialog = new ProgressDialog(this);
        auth = FirebaseAuth.getInstance();

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Dctr_Login.this, Dctr_Sign_Up_Page.class);
                startActivity(intent);
                finish();
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CheckValidation();
            }
        });
    }
    private void CheckValidation()
    {
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

    private void Login_Authentication(String email, String password)
    {
        progressDialog.setMessage("Please wait...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            progressDialog.dismiss();
//                            startActivity(new Intent(Dctr_Login.this, MainActivity.class));
                            Toast.makeText(Dctr_Login.this, "Can Be Approved By Admin", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            progressDialog.dismiss();
                            Toast.makeText(Dctr_Login.this, "Not Authenticated", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
    public void Sign_In_Ptnt(View view)
    {
        startActivity(new Intent(Dctr_Login.this, Patient_Login.class));
        finish();
    }
}
