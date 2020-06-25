package com.example.usman.dctr_app;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.usman.dctr_app.Model.Dctr_model;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.Random;

public class Dctr_Sign_Up_Page extends AppCompatActivity
{
    Button dctr_login ,img_choose_button, register, dctr_Sign_Up, button2;
    EditText user, email, password, confirmpassword, phone, address, specialty;
    RadioGroup gender;
    RadioButton genderbutton;

    Uri filedata;
    ProgressDialog progressDialog;
    FirebaseAuth auth;

    Random rand;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dctr__sign__up__page);
        img_choose_button = findViewById(R.id.btn_img);
        register = findViewById(R.id.register_dctr);
        user = findViewById(R.id.dctr_name);
        email = findViewById(R.id.dctr_email);
        password = findViewById(R.id.dctr_password);
        confirmpassword = findViewById(R.id.dctr_confirmpassword);
        phone = findViewById(R.id.dctr_phone);
        address = findViewById(R.id.dctr_address);
        specialty = findViewById(R.id.dctr_specialty);
        gender = findViewById(R.id.gender_group);
        dctr_login= findViewById( R.id.Dctr_Login);
        dctr_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Dctr_Sign_Up_Page.this, Dctr_Login.class));
            }
        });

        progressDialog = new ProgressDialog(this);
        auth = FirebaseAuth.getInstance();

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CheckAllValidation();
            }
        });
        img_choose_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Choose_image();
            }
        });

        dctr_Sign_Up= findViewById(R.id.Sign_Up_Ptnt);
        dctr_Sign_Up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Sign_Up_Ptnt();
            }
        });


    }

    private void Sign_Up_Ptnt()
    {
        startActivity(new Intent(Dctr_Sign_Up_Page.this, Patient_Sign_Up.class));
    }

    private void Choose_image()
    {
        Intent imageChose = new Intent();
        imageChose.setType("image/*");
        imageChose.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(imageChose,01);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==01 && resultCode==RESULT_OK && data!=null && data.getData()!=null)
        {
            filedata=data.getData();

        }
    }

    private void CheckAllValidation()
    {
        String userName = user.getText().toString().trim();
        String userEmail = email.getText().toString().trim();
        String userPass = password.getText().toString().trim();
        String userCon = confirmpassword.getText().toString().trim();
        String userphone = phone.getText().toString().trim();
        String useraddress = address.getText().toString().trim();
        String userSpecialty=specialty.getText().toString().trim();

        rand=new Random();
        int num=rand.nextInt(5)+1;
        String D_rating=Integer.toString(num);

        if (TextUtils.isEmpty(userName)){
            user.setError("Please Enter your UserName here");
        }
        else if (TextUtils.isEmpty(userEmail)){
            email.setError("Please Enter your Email here");
        }
        else if (!(Patterns.EMAIL_ADDRESS.matcher(userEmail).matches())){
            email.setError("Please Enter your valid Email");
        }
        else if (TextUtils.isEmpty(userPass)){
            password.setError("Please Enter your Password here");
        }
        else if (TextUtils.isEmpty(userCon)){
            confirmpassword.setError("Please Re-Enter Password");
        }
        else if (!(userPass.equals(userCon))){
            Toast.makeText(this, "Password not Matched", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(userphone)){
            phone.setError("Please Enter your Phone Number");
        }
        else if (TextUtils.isEmpty(useraddress)){
            address.setError("Please Enter your Address");
        }
        else if (TextUtils.isEmpty(userSpecialty)){
            address.setError("Please Enter your Specialty");
        }
        else  if (filedata == null){
            Toast.makeText(this, "Please choose an Image", Toast.LENGTH_SHORT).show();
        }
        else {
            int selectedID = gender.getCheckedRadioButtonId();
            genderbutton = findViewById(selectedID);
            String userGender = genderbutton.getText().toString();

            InsertInDataBase(userName, userEmail, userPass, userCon, userphone, useraddress, userSpecialty,D_rating, userGender, filedata);
        }
    }

    private void InsertInDataBase(final String userName, final String userEmail, final String userPass, final String userCon, final String userphone, final String useraddress, final String userSpecialty,final String D_rating, final String userGender, final Uri filedata) {

        progressDialog.setMessage("Please Wait...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        auth.createUserWithEmailAndPassword(userEmail,userPass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful())
                {
                    SendImageInStorage(userName, userEmail, userPass, userCon, userphone, useraddress, userSpecialty,D_rating, userGender, filedata);
                }
                else
                {
                    progressDialog.dismiss();
                    Toast.makeText(Dctr_Sign_Up_Page.this, "Authentication Not Complete", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void SendImageInStorage(final String userName, final String userEmail, final String userPass, final String userCon, final String userphone, final String useraddress, final String userSpecialty,final String D_rating, final String userGender, final Uri filedata) {

        final StorageReference ref = FirebaseStorage.getInstance().getReference("DctrImages/"+auth.getCurrentUser().getUid());
        ref.putFile(filedata).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
            @Override
            public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {

                if (!task.isSuccessful())
                {
                       throw task.getException();
                }
                return ref.getDownloadUrl();
            }
        }).addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {
                if (task.isSuccessful())
                {
                    Uri DownloadedURL= task.getResult();
                    InsertInRealTimeDatabase(userName, userEmail, userPass, userCon, userphone, useraddress, userSpecialty,D_rating, userGender, DownloadedURL);
                }
                else
                {
                    progressDialog.dismiss();
                    Toast.makeText(Dctr_Sign_Up_Page.this, "Url Not Generated", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void InsertInRealTimeDatabase(String userName, String userEmail, String userPass, String userCon, String userphone, String useraddress, String userSpecialty, String D_rating, String userGender, Uri downloadedURL) {
        Dctr_model values= new Dctr_model(userName,userEmail,userPass,userphone,userSpecialty , D_rating,userGender,downloadedURL.toString());
        FirebaseDatabase.getInstance().getReference("DctrTable").child(auth.getCurrentUser().getUid()).setValue(values)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful())
                        {
                            progressDialog.dismiss();
                            Toast.makeText(Dctr_Sign_Up_Page.this, "User Creates", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(Dctr_Sign_Up_Page.this, Dctr_Login.class));
                        }
                        else
                        {
                            progressDialog.dismiss();
                            Toast.makeText(Dctr_Sign_Up_Page.this, "User Not Creates", Toast.LENGTH_SHORT).show();
                        }
                    }

                });
    }

}
