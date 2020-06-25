package com.example.usman.dctr_app.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.usman.dctr_app.Model.Dctr_model;
import com.example.usman.dctr_app.Indiviual_Doctor;
import com.example.usman.dctr_app.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class D_Adapter extends RecyclerView.Adapter<D_Adapter.D_ViewHolder> {


        private Context ctx;
        private ArrayList<Dctr_model> list;

        public D_Adapter(Context ctx, ArrayList<Dctr_model> list) {
            this.ctx = ctx;
            this.list = list;

        }

        @Override
        public D_ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(ctx).inflate(R.layout.doctor_card, parent, false);
            return new D_ViewHolder(v);
        }

        @Override
        public void onBindViewHolder(D_ViewHolder holder, int position) {
            final Dctr_model model = list.get(position);

            holder.D_ratingbar.setRating(model.getD_Rating());
            holder.D_spec.setText(model.getSpec());
            holder.D_Name.setText(model.getName());


            String Url = model.getImageURL();

            Picasso.with(ctx).load(Url).fit().into(holder.D_img);

            holder.singdoc.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(ctx, Indiviual_Doctor.class);
                    i.putExtra("name", model.getName());
                    i.putExtra("imageURL", model.getImageURL());
                    i.putExtra("d_Rating", model.getD_Rating());
                    i.putExtra("spec", model.getSpec());
                    ctx.startActivity(i);
                }
            });

        }


        @Override
        public int getItemCount() {
            return list.size();
        }

        public class D_ViewHolder extends RecyclerView.ViewHolder {
            TextView D_Name, D_spec;
            ImageView D_img;
            RatingBar D_ratingbar;
            LinearLayout singdoc;

            public D_ViewHolder(View itemView) {
                super(itemView);
                D_Name = itemView.findViewById(R.id.D_Name);
                D_spec = itemView.findViewById(R.id.D_spec);
                D_img = itemView.findViewById(R.id.D_img);
                D_ratingbar = itemView.findViewById(R.id.ratingBar);

               singdoc = itemView.findViewById(R.id.singledoct);


            }


        }
}