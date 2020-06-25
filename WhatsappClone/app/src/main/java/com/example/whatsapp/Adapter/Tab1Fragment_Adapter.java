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
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.whatsapp.ClickedInterface;
import com.example.whatsapp.Message_Screen;
import com.example.whatsapp.Models.Register_Model;
import com.example.whatsapp.R;
import com.example.whatsapp.Tabbed_Screen.Tab1Fragment;
import com.google.firebase.database.ValueEventListener;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Tab1Fragment_Adapter extends RecyclerView.Adapter<Tab1Fragment_Adapter.Tab1FragmentViewHolder> {
    private Context ctx;
    private ArrayList<Register_Model> list;
    private ClickedInterface clickedInterface1;

//    public Tab1Fragment_Adapter(Context ctx, ArrayList<Register_Model> list, ClickedInterface activity1) {
//        this.ctx = ctx;
//        this.list = list;
//        this.clickedInterface1=activity1;
//    }

    public Tab1Fragment_Adapter(Context ctx, ArrayList<Register_Model> list) {
        this.ctx = ctx;
        this.list = list;
    }

    @NonNull
    @Override
    public Tab1FragmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(ctx).inflate(R.layout.fragment_one_card_view,parent,false);
        return new Tab1FragmentViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Tab1FragmentViewHolder holder, int position) {
        final Register_Model model= list.get(position);

        holder.name_id.setText(model.getName());
        Picasso.get().load(list.get(position).getImageUrl()).into(holder.imageView);
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(view.getContext(),"click on name: "+ model.getName(),Toast.LENGTH_LONG).show();
                Intent intent= new Intent(ctx, Message_Screen.class);
                intent.putExtra("userID",model.getName());
                intent.putExtra("userImageID",model.getImageUrl());
                ctx.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Tab1FragmentViewHolder extends RecyclerView.ViewHolder{
        CircularImageView imageView;
        TextView name_id,last_text_id,time_id;
        LinearLayout linearLayout;
        public Tab1FragmentViewHolder(@NonNull final View itemView) {
            super(itemView);

            name_id=itemView.findViewById(R.id.batche_number_id);
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
}
