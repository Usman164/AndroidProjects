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

import com.example.usman.dctr_app.Model.Ptnt_Model;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

     public class Patient_Sign_Up extends AppCompatActivity {
         Button ptnt_login ,img_choose_button, register, ptnt_sign_up;
         EditText user, email, password, confirmpassword, phone, address;
         RadioGroup gender;
         RadioButton genderbutton;

         Uri filedata;
         ProgressDialog progressDialog;
         FirebaseAuth auth;

         @Override
         protected void onCreate(Bundle savedInstanceState) {
             super.onCreate(savedInstanceState);
             setContentView(R.layout.activity_patient__sign__up);
             img_choose_button = findViewById(R.id.ptnt_btn_img);
             register = findViewById(R.id.ptnt_register);
             user = findViewById(R.id.ptnt_name);
             email = findViewById(R.id.ptnt_email);
             password = findViewById(R.id.ptnt_password);
             confirmpassword = findViewById(R.id.ptnt_confirmpassword);
             phone = findViewById(R.id.ptnt_phone);
             address = findViewById(R.id.ptnt_address);
             gender = findViewById(R.id.ptnt_gender_group);
             ptnt_login= findViewById( R.id.ptnt_Sign_in);
             ptnt_login.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View view) {
                     startActivity(new Intent(Patient_Sign_Up.this, Patient_Login.class));
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

             ptnt_sign_up=findViewById(R.id.Sign_Up_Dctr);
             ptnt_sign_up.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View view) {
                     Sign_Up_Dctr();
                 }
             });
         }
         private void Sign_Up_Dctr() {
             startActivity(new Intent(Patient_Sign_Up.this, Dctr_Sign_Up_Page.class));
         }
         private void Choose_image() {
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
         private void CheckAllValidation() {
             String userName = user.getText().toString().trim();
             String userEmail = email.getText().toString().trim();
             String userPass = password.getText().toString().trim();
             String userCon = confirmpassword.getText().toString().trim();
             String userphone = phone.getText().toString().trim();
             String useraddress = address.getText().toString().trim();

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
             else  if (filedata == null){
                 Toast.makeText(this, "Please choose an Image", Toast.LENGTH_SHORT).show();
             }
             else {
                 int selectedID = gender.getCheckedRadioButtonId();
                 genderbutton = findViewById(selectedID);
                 String userGender = genderbutton.getText().toString();

                 InsertInDataBase(userName, userEmail, userPass, userCon, userphone, useraddress, userGender, filedata);
             }
         }
         private void InsertInDataBase(final String userName, final String userEmail, final String userPass, final String userCon, final String userphone, final String useraddress, final String userGender, final Uri filedata) {

             progressDialog.setMessage("Please Wait...");
             progressDialog.setCancelable(false);
             progressDialog.show();
             auth.createUserWithEmailAndPassword(userEmail,userPass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                 @Override
                 public void onComplete(@NonNull Task<AuthResult> task) {
                     if (task.isSuccessful())
                     {
                         SendImageInStorage(userName, userEmail, userPass, userCon, userphone, useraddress, userGender, filedata);
                     }
                     else
                     {
                         progressDialog.dismiss();
                         Toast.makeText(Patient_Sign_Up.this, "Authentication Not Complete", Toast.LENGTH_SHORT).show();
                     }
                 }
             });
         }
         private void SendImageInStorage(final String userName, final String userEmail, final String userPass, final String userCon, final String userphone, final String useraddress, final String userGender, final Uri filedata) {

             final StorageReference ref = FirebaseStorage.getInstance().getReference("PtntImages/"+auth.getCurrentUser().getUid());
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
                         InsertInRealTimeDatabase(userName, userEmail, userPass, userCon, userphone, useraddress, userGender, DownloadedURL);
                     }
                     else
                     {
                         progressDialog.dismiss();
                         Toast.makeText(Patient_Sign_Up.this, "Url Not Generated", Toast.LENGTH_SHORT).show();
                     }
                 }
             });
         }
         private void InsertInRealTimeDatabase(String userName, String userEmail, String userPass, String userCon, String userphone, String useraddress,  String userGender, Uri downloadedURL) {
             Ptnt_Model values= new Ptnt_Model(userName,userEmail,userPass,userphone,userGender,downloadedURL.toString());
             FirebaseDatabase.getInstance().getReference("PtntTable").child(auth.getCurrentUser().getUid()).setValue(values)
                     .addOnCompleteListener(new OnCompleteListener<Void>() {
                         @Override
                         public void onComplete(@NonNull Task<Void> task) {
                             if (task.isSuccessful())
                             {
                                 progressDialog.dismiss();
                                 Toast.makeText(Patient_Sign_Up.this, "User Creates", Toast.LENGTH_SHORT).show();
                                 startActivity(new Intent(Patient_Sign_Up.this, Dctr_Login.class));
                             }
                             else
                             {
                                 progressDialog.dismiss();
                                 Toast.makeText(Patient_Sign_Up.this, "User Not Creates", Toast.LENGTH_SHORT).show();
                             }
                         }

                     });
         }
     }
