package com.example.whatsapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.whatsapp.ClickedInterface;
import com.example.whatsapp.Message_Screen;
import com.example.whatsapp.Models.Chat_Messages_Model;
import com.example.whatsapp.Models.Register_Model;
import com.example.whatsapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class Message_Adapter extends RecyclerView.Adapter<Message_Adapter.Tab1FragmentViewHolder> {
    private Context ctx;
    private List<Chat_Messages_Model> mChat;
    private String imageurl;
    public static final int MSG_TYPE_LEFT=0;
    public static final int MSG_TYPE_RIGHT=1;
    FirebaseUser fuser;

//    public Message_Adapter(Context ctx,List<Chat_Messages_Model> mChat, String imageurl) {
//        this.ctx = ctx;
//        this.mChat = mChat;
//        this.imageurl=imageurl;
//    }
    public Message_Adapter(Context ctx,List<Chat_Messages_Model> mChat) {
        this.ctx = ctx;
        this.mChat = mChat;
        this.imageurl=imageurl;
    }

    @NonNull
    @Override
    public Message_Adapter.Tab1FragmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (viewType == MSG_TYPE_RIGHT)
        {
            View v = LayoutInflater.from(ctx).inflate(R.layout.chat_item_right,parent,false);
            return new Message_Adapter.Tab1FragmentViewHolder(v);
        }
        else
        {
            View v = LayoutInflater.from(ctx).inflate(R.layout.chat_item_left,parent,false);
            return new Message_Adapter.Tab1FragmentViewHolder(v);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull Message_Adapter.Tab1FragmentViewHolder holder, int position) {

        Chat_Messages_Model chat=mChat.get(position);
        holder.showMessage.setText(chat.getMessage());
//        if (imageurl.equals("default")){
//            holder.profile_imageView.setImageResource(R.mipmap.ic_launcher);
//        }
//        else{
//            Glide.with(ctx).load(imageurl).into(holder.profile_imageView);
//        }

    }

    @Override
    public int getItemCount() {
        return mChat.size();
    }

    public class Tab1FragmentViewHolder extends RecyclerView.ViewHolder{
        CircularImageView profile_imageView;
        TextView showMessage,last_text_id,time_id;
        LinearLayout linearLayout;
        public Tab1FragmentViewHolder(@NonNull final View itemView) {
            super(itemView);

            showMessage=itemView.findViewById(R.id.show_message);
            profile_imageView=itemView.findViewById(R.id.chat_item_profil_image_right);
        }
    }
    @Override
    public int getItemViewType(int position) {
        fuser= FirebaseAuth.getInstance().getCurrentUser();
        if(mChat.get(position).getSender().equals(fuser.getUid()))
        {
            return MSG_TYPE_RIGHT;
        }
        else
        {
            Toast.makeText(ctx, "left msg", Toast.LENGTH_SHORT).show();
            return MSG_TYPE_LEFT;
        }
    }
}

















//
//    public void readMessages(final String myid, final String userid){
//        mChat= new ArrayList<>();
//        reference= FirebaseDatabase.getInstance().getReference();
//
//        reference.child("Chats").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                for (DataSnapshot snapshot : dataSnapshot.getChildren())
//                {
//                    String sender = dataSnapshot.child("sender").getValue().toString();
//                    String receiver = dataSnapshot.child("receiver").getValue().toString();
//                    String message = dataSnapshot.child("message").getValue().toString();
//                    Chat_Messages_Model chat= new Chat_Messages_Model(sender,receiver,message);
////                    Chat_Messages_Model chat=snapshot.getValue(Chat_Messages_Model.class);
//                    if (chat.getReceiver().equals(myid)  &&  chat.getSender().equals(userid)
//                            || chat.getReceiver().equals(userid)  &&  chat.getSender().equals(myid))
//                    {
//                        mChat.add(new Chat_Messages_Model(sender,receiver,message));
////                        chat.add(new Register_Model(Name,Email,Pass,Phone,Gender,ImageURL));
////                        mChat.add(chat);
//                    }
////                    message_adapter = new Message_Adapter(Message_Screen.this,mChat,imageUrl);
//                    message_adapter = new Message_Adapter(Message_Screen.this,mChat);
//                    recyclerView.setAdapter(message_adapter);
//                }
//            }
