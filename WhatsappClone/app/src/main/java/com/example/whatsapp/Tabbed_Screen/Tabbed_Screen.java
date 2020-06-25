package com.example.whatsapp.Tabbed_Screen;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.whatsapp.On_Touch_Listener;
import com.example.whatsapp.R;
import com.example.whatsapp.User_Login_Screen;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GestureDetectorCompat;
import androidx.viewpager.widget.ViewPager;

public class Tabbed_Screen extends AppCompatActivity implements GestureDetector.OnGestureListener{
    private TabAdapter adapter;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    private static final String TAG="Swipe Position";
    private float x1,x2,y1,y2;
    private static int MIN_DISTANCE=150;
    private GestureDetector gestureDetector;
    private GestureDetectorCompat gestureDetectorCompat=null;

    Toolbar toolbar;
    public static final int number=1000;
    FirebaseAuth auth;
    ImageView camera;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabbed__screen);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        textView=findViewById(R.id.textViewMain);
        this.gestureDetector=new GestureDetector(Tabbed_Screen.this,this);
        camera= findViewById(R.id.camera_id_tabbed_screen);
        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(MediaStore.INTENT_ACTION_STILL_IMAGE_CAMERA);
                startActivity(intent);
            }
        });
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        adapter = new TabAdapter(getSupportFragmentManager());
        adapter.addFragment(new Tab1Fragment(), "Chats");
        adapter.addFragment(new Tab2Fragment(), "Status");
        adapter.addFragment(new Tab3Fragment(), "Calls");
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        On_Touch_Listener gestureListener=new On_Touch_Listener();
        gestureListener.setTabbed_screen(this);
        gestureDetectorCompat= new GestureDetectorCompat(this,gestureListener);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        gestureDetectorCompat.onTouchEvent(event);
        return true;
    }
    public void displayMSG(String message)
    {
        textView.setText(message);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        switch (requestCode)
        {
            case (number):
                if (resultCode == Activity.RESULT_OK) {
                    Uri contactData = data.getData();
                    Cursor phone = getContentResolver().query(contactData, null, null, null, null);
                    if (phone.moveToFirst()) {
                        String contactNumberName = phone.getString(phone.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                        // Todo something when contact number selected
                        Toast.makeText(this, contactNumberName, Toast.LENGTH_SHORT).show();
//                        textView.setText("Name: " + contactNumberName);
                    }
                }
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.new_group:
                Toast.makeText(this, "New Group", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.new_broadcast:
                Toast.makeText(this, "New Brodcast", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.labels:
                Toast.makeText(this, "Labels", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.whatsapp_web:
                Toast.makeText(this, "Whatsapp Web", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.starred_messages:
                Toast.makeText(this, "Starred message", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.setting:
                Toast.makeText(this, "Setting", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.search_bar_icon:
                Intent intent=new Intent(Intent.ACTION_PICK,ContactsContract.Contacts.CONTENT_URI);
                startActivityForResult(intent,number);
                return true;
            case R.id.logout:
                auth=FirebaseAuth.getInstance();
                if (auth != null)
                {
                    auth.signOut();
                    startActivity(new Intent(Tabbed_Screen.this, User_Login_Screen.class));
                    finishAffinity();
                    finish();
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.setting_icon,menu);
        return true;
    }

    @Override
    public boolean onDown(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {
    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        return false;
    }
}

