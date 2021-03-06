package com.example.whatsapp;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.whatsapp.Adapter.Message_Adapter;
import com.example.whatsapp.Models.Chat_Messages_Model;
import com.example.whatsapp.Models.Register_Model;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Message_Screen extends AppCompatActivity {

    CircularImageView profile_imageView;
    TextView userName;
    FirebaseUser fuser;
    DatabaseReference reference;
    Intent intent;
    ImageButton btn_sndText;
    EditText textMessage;
    Message_Adapter message_adapter;
    List<Chat_Messages_Model> mChat;
    RecyclerView recyclerView;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.setting_icon,menu);
        return true;
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
            case R.id.setting_menu:
                Toast.makeText(this, "Setting", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.search_bar_icon:
                Toast.makeText(this, "Search Bar", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.logout:
                Toast.makeText(this, "Logout", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    Toolbar message_toolbar;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message__screen);

        message_toolbar= (Toolbar) findViewById(R.id.toolbar_message_screen);
        setSupportActionBar(message_toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        message_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        profile_imageView=findViewById(R.id.profile_image_message_screen);
        userName=findViewById(R.id.message_screen_user_id);
        btn_sndText=findViewById(R.id.send_message_btn_id);
        textMessage=findViewById(R.id.text_message_id);


        recyclerView=findViewById(R.id.message_recycler_id);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);


        intent=getIntent();
        final String userid=intent.getStringExtra("userID");
        final String imageID=intent.getStringExtra("userImageID");
        userName.setText(userid);
        Glide.with(Message_Screen.this).load(imageID).into(profile_imageView);


        fuser= FirebaseAuth.getInstance().getCurrentUser();
        reference= FirebaseDatabase.getInstance().getReference("name").child(userid);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Register_Model user = snapshot.getValue(Register_Model.class);
//                readMessages(fuser.getUid(),userid, user.getImageUrl());
                readMessages(fuser.getUid(),userid);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        btn_sndText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msg=textMessage.getText().toString();
                if (!msg.equals(""))
                {
                    sendMessage(fuser.getUid(),userid,msg);
                }
                else
                {
                    Toast.makeText(Message_Screen.this, "You can't send blank message...", Toast.LENGTH_SHORT).show();
                }
                textMessage.setText("");
            }
        });
    }
    public void sendMessage(String sender,String receiver,String message)
    {
        DatabaseReference reference=FirebaseDatabase.getInstance().getReference();
        HashMap<String, Object> hashMap=new HashMap<>();

        hashMap.put("sender",sender);
        hashMap.put("receiver",receiver);
        hashMap.put("message",message);

        reference.child("Chats").push().setValue(hashMap);

    }

    public void readMessages(final String myid, final String userid){
        mChat= new ArrayList<>();
        reference= FirebaseDatabase.getInstance().getReference("Chats");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mChat.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren())
                {
                    Chat_Messages_Model chat=snapshot.getValue(Chat_Messages_Model.class);
                    if (chat.getReceiver().equals(myid)  &&  chat.getSender().equals(userid)
                    || chat.getReceiver().equals(userid)  &&  chat.getSender().equals(myid))
                    {
                        mChat.add(chat);
                    }
//                    message_adapter = new Message_Adapter(Message_Screen.this,mChat,imageUrl);
                    message_adapter = new Message_Adapter(Message_Screen.this,mChat);
                    recyclerView.setAdapter(message_adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}