package com.example.whatsapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

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

public class Message_Adapter extends RecyclerView.Adapter<Message_Adapter.Tab1FragmentViewHolder> {
    private Context ctx;
    private ArrayList<Chat_Messages_Model> chat_list;
    private String imageurl;
    public static final int MSG_TYPE_LEFT=0;
    public static final int MSG_TYPE_RIGHT=1;
    FirebaseUser fuser;


//    public Tab1Fragment_Adapter(Context ctx, ArrayList<Register_Model> list, ClickedInterface activity1) {
//        this.ctx = ctx;
//        this.list = list;
//        this.clickedInterface1=activity1;
//    }

    public Message_Adapter(Context ctx, ArrayList<Chat_Messages_Model> chat_list, String imageurl) {
        this.ctx = ctx;
        this.chat_list = chat_list;
        this.imageurl=imageurl;
    }

    @NonNull
    @Override
    public Message_Adapter.Tab1FragmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(ctx).inflate(R.layout.fragment_one_card_view,parent,false);
        return new Message_Adapter.Tab1FragmentViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Message_Adapter.Tab1FragmentViewHolder holder, int position) {
    }

    @Override
    public int getItemCount() {
        return chat_list.size();
    }

    public class Tab1FragmentViewHolder extends RecyclerView.ViewHolder{
        CircularImageView imageView;
        TextView showMessage,last_text_id,time_id;
        LinearLayout linearLayout;
        public Tab1FragmentViewHolder(@NonNull final View itemView) {
            super(itemView);

            showMessage=itemView.findViewById(R.id.show_message);
            imageView=itemView.findViewById(R.id.circularImage_id);
            linearLayout=itemView.findViewById(R.id.linearlayout_id);
//            linearLayout.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Toast.makeText(view.getContext(), "dsfe", Toast.LENGTH_SHORT).show();
//                }
//            });
//            last_text_id=itemView.findViewById(R.id.last_text_id);
//            time_id=itemView.findViewById(R.id.batche_time_id);
//            name_id.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    clickedInterface1.OnItemClicked(getAdapterPosition());
//                }
//            });
//            time_id.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    clickedInterface1.OnItemClicked(getAdapterPosition());
//                }
//            });
//            last_text_id.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    clickedInterface1.OnItemClicked(getAdapterPosition());
//                }
//            });


        }
    }

    @Override
    public int getItemViewType(int position) {
        fuser= FirebaseAuth.getInstance().getCurrentUser();
        if(chat_list.get(position).getSender().equals(fuser.getUid()))
        {
            return MSG_TYPE_RIGHT;
        }
        else
        {
            return MSG_TYPE_LEFT;
        }
    }
}