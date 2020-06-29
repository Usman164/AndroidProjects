package com.example.custom_navigation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    NavigationView navigationView;
    TextView cliked;
    ImageView imageView;
    ViewPager viewPager;
    ImageFragmentAdapter imageFragmentAdapter;
    Timer timer;
    Boolean a;
    int current_posstion=0,custom_positon=0,dotCount=0,alert_dialoge_delay=5000;
    int stop_alert=2;
    LinearLayout dotslayout;
    Button massageBTN,knowledgeBTN;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        DrawerLayout drawerLayout=findViewById(R.id.drawerlayout);
        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.drawer_open,R.string.drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView=findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        imageView=findViewById(R.id.images);
        dotslayout=findViewById(R.id.dotcontainer);
        viewPager=findViewById(R.id.pager);
        cliked=findViewById(R.id.image_cliked);
        imageFragmentAdapter=new ImageFragmentAdapter(getSupportFragmentManager());
        viewPager.setAdapter(imageFragmentAdapter);
        massageBTN=findViewById(R.id.massage_btn);
        massageBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,MassageAppointment.class);
                startActivity(intent);
            }
        });
        prepareDot(custom_positon++);
        createSlideShow();
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
            @Override
            public void onPageSelected(int position)
            {
                if (custom_positon >3)
                {
                    custom_positon=0;
                }
                prepareDot(custom_positon++);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ShowDialouge();
            }
        },alert_dialoge_delay);
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawerLayout=findViewById(R.id.drawerlayout    );
        if (drawerLayout.isDrawerOpen(GravityCompat.START))
        {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else
        {
            super.onBackPressed();
        }
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id==R.id.setting)
        {

            Toast.makeText(getApplicationContext(),"Setting",Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
       getMenuInflater().inflate(R.menu.drawer_setting,menu);
       return true;
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id =menuItem.getItemId();
        if (id==R.id.nav_profile)
        {
            Toast.makeText(this,"Profile",Toast.LENGTH_SHORT).show();
            return true;
        }
        else if (id==R.id.nav_account)
        {
           Intent intent=new Intent(MainActivity.this,Login_Page.class);
           startActivity(intent);
            return true;
        }
        else if (id==R.id.nav_help_center)
        {
            LayoutInflater inflaterhelp=LayoutInflater.from(this);
            View view=inflaterhelp.inflate(R.layout.alert_help,null);
            final AlertDialog alertDialog=new AlertDialog.Builder(this).setView(view).create();
            Button sms_chat=view.findViewById(R.id.btn_sms_live_chat);
            sms_chat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getApplicationContext(), "Live Chat", Toast.LENGTH_SHORT).show();
                    alertDialog.cancel();
                }
            });
            Button faqs=view.findViewById(R.id.btn_faqs);
            faqs.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getApplicationContext(), "FAQS", Toast.LENGTH_SHORT).show();
                    alertDialog.cancel();
                }
            });
            Button about=view.findViewById(R.id.btn_about);
            about.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getApplicationContext(), "ABOUT", Toast.LENGTH_SHORT).show();
                    alertDialog.cancel();
                }
            });
            alertDialog.show();
            alertDialog.setCancelable(false);
            return true;
        }
        else if (id==R.id.nav_promo_code)
        {
            LayoutInflater inflaterpromo=LayoutInflater.from(this);
            View view=inflaterpromo.inflate(R.layout.alert_promo,null);
            final AlertDialog alertDialog=new AlertDialog.Builder(this).setView(view).create();
            Button enter=view.findViewById(R.id.promo_enter_btn);
            enter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getApplicationContext(), "Code Enter", Toast.LENGTH_SHORT).show();
                    alertDialog.cancel();
                }
            });
            Button cancel=view.findViewById(R.id.promo_cancel_btn);
            cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getApplicationContext(), "Code Cancel", Toast.LENGTH_SHORT).show();
                    alertDialog.cancel();
                }
            });
            alertDialog.show();
            alertDialog.setCancelable(false);

            return true;
        }
        else if (id==R.id.nav_Join_member)
        {
            setContentView(R.layout.activity_join__membership);
            Button cross_member=findViewById(R.id.close_membership_view);
            cross_member.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);
                    stop_alert=1;
                    finish();
                }
            });
            Button zipCode=findViewById(R.id.btn_membership);
            zipCode.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getApplicationContext(), "ZipCode", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);
                    stop_alert=1;
                    finish();

                }
            });
//            alertDialog.show();
//            alertDialog.setCancelable(false);

            return true;
        }
        else if (id==R.id.nav_gift_card)
        {
            Intent intent=new Intent(MainActivity.this,Gift_Cards.class);
            startActivity(intent);
            return true;
        }
        else if (id==R.id.nav_store)
        {
            Toast.makeText(this, "Store", Toast.LENGTH_SHORT).show();
            return true;
        }
        else if (id==R.id.nav_logOut)
        {
            LayoutInflater inflaterpromo=LayoutInflater.from(this);
            View view=inflaterpromo.inflate(R.layout.alert_logout,null);
            final AlertDialog alertDialog=new AlertDialog.Builder(this).setView(view).create();
            Button yes=view.findViewById(R.id.btn_logOut_yes);
            yes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                    System.exit(0);
                }
            });
            Button no=view.findViewById(R.id.btn_logOut_no);
            no.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getApplicationContext(), "Cancel Exit", Toast.LENGTH_SHORT).show();
                    alertDialog.cancel();
                }
            });
            alertDialog.show();
            alertDialog.setCancelable(false);
            return true;
        }
            Toast.makeText(getApplicationContext(), "No item Selected", Toast.LENGTH_SHORT).show();
            DrawerLayout drawerLayout = findViewById(R.id.drawerlayout);
            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
    }
    public void createSlideShow()
    {
        final Handler handler=new Handler();
        final Runnable runnable=new Runnable() {
            @Override
            public void run() {
                if (current_posstion==Integer.MAX_VALUE)
                {
                    Toast.makeText(getApplicationContext(), "inside if slide show", Toast.LENGTH_SHORT).show();
                    current_posstion=0;
                    viewPager.setCurrentItem(current_posstion++,true);
                }
            }
        };
        timer=new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(runnable);

            }
        },1,1);
    }
    public void prepareDot(int currentSlidePosstion)
    {
        if (dotslayout.getChildCount()>0)
            dotslayout.removeAllViews();
        ImageView dots[]=new ImageView[4];

        for (int i=0;i<4;i++)
        {
            dots[i]=new ImageView(this);
            if (i==currentSlidePosstion)
            {
                dotCount++;
//                Toast.makeText(getApplicationContext(), dotCount+"  "+custom_positon, Toast.LENGTH_SHORT).show();
                dots[i].setImageDrawable(ContextCompat.getDrawable(this,R.drawable.active_dot));
            }
            else
            {
//                Toast.makeText(getApplicationContext(), dotCount+"  "+custom_positon, Toast.LENGTH_SHORT).show();
                dots[i].setImageDrawable(ContextCompat.getDrawable(this,R.drawable.inactive_dot));
            }
            LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(4,0,4,0);
            dotslayout.addView(dots[i],layoutParams);
        }
    }
    public void ShowDialouge()
    {

        LayoutInflater inflater=LayoutInflater.from(this);
        View view=inflater.inflate(R.layout.alert_dialoge,null);
        Toast.makeText(this, a+"ad", Toast.LENGTH_SHORT).show();
        final AlertDialog alertDialog=new AlertDialog.Builder(this).setView(view).create();
        if (stop_alert==1)
        {
            Toast.makeText(this, a+"ad", Toast.LENGTH_SHORT).show();
            alertDialog.dismiss();
        }
        else
        {
            a=true;
            knowledgeBTN=view.findViewById(R.id.btn_knowledge);
            knowledgeBTN.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    alertDialog.dismiss();
                }
            });
            alertDialog.show();
            alertDialog.setCancelable(false);
        }
    }
}
