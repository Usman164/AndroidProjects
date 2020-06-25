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
import android.widget.Toast;

import com.example.usman.dctr_app.Model.Comp_Model;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
//
public class Patient_Complaint_box extends AppCompatActivity {


    Button  img_choose_button, complaint;
    EditText user, email,cnic, bloodGroup,  phone, address;
    Uri filedata;
    ProgressDialog progressDialog;
    FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient__complaint_box);
        img_choose_button = findViewById(R.id.comp_ptnt_btn_img);
        user = findViewById(R.id.comp_ptnt_name);
        bloodGroup=findViewById(R.id.comp_ptnt_bloodGroup);
        cnic=findViewById(R.id.comp_ptnt_cnic);
        email = findViewById(R.id.comp_ptnt_email);
        phone = findViewById(R.id.comp_ptnt_phone);
        address = findViewById(R.id.comp_ptnt_address);
        complaint=findViewById(R.id.comp_btn_Complain);
        complaint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CheckAllValidation();
            }
        });
        progressDialog = new ProgressDialog(this);
        auth = FirebaseAuth.getInstance();
        img_choose_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Choose_image();
            }
        });
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
        String BloodGroup=bloodGroup.getText().toString().trim();
        String CNIC=cnic.getText().toString().trim();
        String userphone = phone.getText().toString().trim();
        String useraddress = address.getText().toString().trim();
        String Complaint= complaint.getText().toString().trim();

        if (TextUtils.isEmpty(userName)){
            user.setError("Please Enter your UserName here");
        }
        else if (TextUtils.isEmpty(userEmail)){
            email.setError("Please Enter your Email here");
        }
        else if (!(Patterns.EMAIL_ADDRESS.matcher(userEmail).matches())){
            email.setError("Please Enter your valid Email");
        }
        else if (TextUtils.isEmpty(BloodGroup)){
            bloodGroup.setError("Please Enter your BloodGroup here");
        }
        else if (TextUtils.isEmpty(CNIC)){
            cnic.setError("Please Enter CNIC ");
        }
        else if (TextUtils.isEmpty(userphone)){
            phone.setError("Please Enter your Phone Number");
        }
        else if (TextUtils.isEmpty(useraddress)){
            address.setError("Please Enter your Address");
        }
        else if (TextUtils.isEmpty(Complaint)){
            complaint.setError("Please Enter your Complaint");
        }
        else  if (filedata == null){
            Toast.makeText(this, "Please choose an Image", Toast.LENGTH_SHORT).show();
        }
        else
            {
                InsertInDataBase(userName, userEmail, BloodGroup, CNIC, userphone, useraddress, Complaint, filedata);
        }
    }
    private void InsertInDataBase(final String userName, final String userEmail, final String BloodGroup, final String CNIC, final String userphone, final String useraddress, final String Complaint, final Uri filedata) {

        progressDialog.setMessage("Please Wait...");
        progressDialog.setCancelable(false);
        progressDialog.show();

                    SendImageInStorage(userName, userEmail, BloodGroup, CNIC, userphone, useraddress, Complaint, filedata);
                    progressDialog.dismiss();
                    Toast.makeText(Patient_Complaint_box.this, "Authentication Complete", Toast.LENGTH_SHORT).show();
    }
    private void SendImageInStorage(final String userName, final String userEmail, final String BloodGroup, final String CNIC, final String userphone, final String useraddress, final String Complaint, final Uri filedata) {

        final StorageReference ref = FirebaseStorage.getInstance().getReference("CompImages/"+auth.getCurrentUser().getUid());
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
                    InsertInRealTimeDatabase(userName, userEmail, BloodGroup, CNIC, userphone, useraddress, Complaint, DownloadedURL);
                }
                else
                {
                    progressDialog.dismiss();
                    Toast.makeText(Patient_Complaint_box.this, "Url Not Generated", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void InsertInRealTimeDatabase(String userName, String userEmail, String BloodGroup, String CNIC, String userphone, String useraddress, String Complaint, Uri downloadedURL) {
        Comp_Model values= new Comp_Model(userName, userEmail, BloodGroup, CNIC, userphone, useraddress, Complaint,downloadedURL.toString());
        FirebaseDatabase.getInstance().getReference("CompTable").child(auth.getCurrentUser().getUid()).setValue(values)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful())
                        {
                            progressDialog.dismiss();
                            Toast.makeText(Patient_Complaint_box.this, " Complaint Registerd", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(Patient_Complaint_box.this, AllDoctors.class));
                        }
                        else
                        {
                            progressDialog.dismiss();
                            Toast.makeText(Patient_Complaint_box.this, "Complaint Not Registerd", Toast.LENGTH_SHORT).show();
                        }
                    }

                });
    }
}
