package com.example.whatsapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.whatsapp.Models.Register_Model;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class Register_Screen extends AppCompatActivity {
    Button register,userLogin,userImage;
    EditText userName,userEmail,userPassword,userConPassword,userPhone,userAddress;
    RadioGroup userGender;
    RadioButton genderbutton;
    Uri fileData;
    ProgressDialog progressDialog;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register__screen);


        userImage = findViewById(R.id.btn_img);
        register = findViewById(R.id.register_user);
        userName = findViewById(R.id.user_name);
        userEmail = findViewById(R.id.user_email);
        userPassword = findViewById(R.id.user_password);
        userConPassword = findViewById(R.id.user_confirmpassword);
        userPhone = findViewById(R.id.user_phone);
        userAddress = findViewById(R.id.user_address);
        userGender = findViewById(R.id.user_gender_group);

        progressDialog=new ProgressDialog(this);
        auth=FirebaseAuth.getInstance();

        userLogin= findViewById( R.id.user_Login);
        userLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Register_Screen.this,User_Login_Screen.class);
                startActivity(intent);
                finish();
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CheckAllValidation();
            }
        });
        userImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Choose_Image();
            }
        });
    }

    private void Choose_Image() {
        Intent imagePick=new Intent();
        imagePick.setType("image/*");
        imagePick.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(imagePick,01);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode== RESULT_OK &&  requestCode==01 )
        {
            fileData=data.getData();

        }
    }

    private void CheckAllValidation() {
        String user_Name = userName.getText().toString().trim();
        String user_Email = userEmail.getText().toString().trim();
        String user_Pass = userPassword.getText().toString().trim();
        String user_Con = userConPassword.getText().toString().trim();
        String user_phone = userPhone.getText().toString().trim();
        String user_address = userAddress.getText().toString().trim();

        if (TextUtils.isEmpty(user_Name)){
            userName.setError("Please Enter your UserName here");
        }
        else if (TextUtils.isEmpty(user_Email)){
            userEmail.setError("Please Enter your Email here");
        }
        else if (!(Patterns.EMAIL_ADDRESS.matcher(user_Email).matches())){
            userEmail.setError("Please Enter your valid Email");
        }
        else if (TextUtils.isEmpty(user_Pass)){
            userPassword.setError("Please Enter your Password here");
        }
        else if (TextUtils.isEmpty(user_Con)){
            userConPassword.setError("Please Re-Enter Password");
        }
        else if (!(user_Pass.equals(user_Con))){
            Toast.makeText(this, "Password not Matched", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(user_phone)){
            userPhone.setError("Please Enter your Phone Number");
        }
        else if (TextUtils.isEmpty(user_address)){
            userAddress.setError("Please Enter your Address");
        }
        else  if (fileData == null){
            Toast.makeText(this, "Please choose an Image", Toast.LENGTH_SHORT).show();
        }
        else {
            int selectedID = userGender.getCheckedRadioButtonId();
            genderbutton = findViewById(selectedID);
            String userGender = genderbutton.getText().toString();
            InsertInDataBase(user_Name, user_Email, user_Pass, user_Con, user_phone, user_address, userGender, fileData);
        }
    }

    private void InsertInDataBase(final String userName, final String userEmail, final String userPass, final String userCon, final String userphone, final String useraddress, final String userGender, final Uri fileData) {
        progressDialog.setMessage("Please wait...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        auth.createUserWithEmailAndPassword(userEmail,userPass).addOnCompleteListener(this,
                new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful())
                {
                    SendImageInStorage(userName, userEmail, userPass, userCon, userphone, useraddress, userGender, fileData);
                }
                else {
                    progressDialog.dismiss();
                    Toast.makeText(Register_Screen.this, "Authentication Not Complete", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void SendImageInStorage(final String userName, final String userEmail, final String userPass, final String userCon, final String userphone, final String useraddress, final String userGender, final Uri fileData)
    {
        final StorageReference reference= FirebaseStorage.getInstance().getReference("UserImages/"+auth.getCurrentUser().getUid());
        reference.putFile(fileData).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
            @Override
            public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                if (!task.isSuccessful())
                {
                    throw task.getException();
                }
                return reference.getDownloadUrl();
            }
        }).addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {
                if (task.isSuccessful())
                {
                    Uri downloadURL= task.getResult();
                    InsertInRealTimeDatabase(userName, userEmail, userPass, userCon, userphone, useraddress, userGender, downloadURL);
                }
                else {
                    progressDialog.dismiss();
                    Toast.makeText(Register_Screen.this, "URL Not Generated", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void InsertInRealTimeDatabase(String userName, String userEmail, String userPass, String userCon, String userphone, String useraddress, String userGender, Uri downloadURL) {
        Register_Model values= new Register_Model(userName,userEmail,userPass,userphone,userGender,downloadURL.toString());
        FirebaseDatabase.getInstance().getReference("UserTable").child(auth.getCurrentUser().getUid()).setValue(values)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful())
                        {
                            progressDialog.dismiss();
                            Toast.makeText(Register_Screen.this, "User Created", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(Register_Screen.this,User_Login_Screen.class));
                            finish();
                        }
                        else {
                            progressDialog.dismiss();
                            Toast.makeText(Register_Screen.this, "User Not Created", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }


}