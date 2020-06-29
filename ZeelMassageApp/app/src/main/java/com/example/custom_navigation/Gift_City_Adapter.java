package com.example.custom_navigation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Gift_City_Adapter extends RecyclerView.Adapter<Gift_City_Adapter.CityViewHolder>{
    List<String> names;
    private Context cxt ;
    private RecyclerViewClickedInterface recyclerViewClickedInterface;
    TextView namerecyclerview;

    public Gift_City_Adapter(List<String> nameList, Context cxt, RecyclerViewClickedInterface recyclerViewClickedInterface) {
        this.names = nameList;
        this.cxt = cxt;
        this.recyclerViewClickedInterface=recyclerViewClickedInterface;
    }

    @NonNull
    @Override
    public Gift_City_Adapter.CityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(cxt.getApplicationContext()).inflate(R.layout.gift_city_items,parent,false);
        return new CityViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Gift_City_Adapter.CityViewHolder holder, int position) {
        holder.name.setText(names.get(position));

    }

    @Override
    public int getItemCount() {
        return names.size();
    }

    public class CityViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        public CityViewHolder(@NonNull View itemView) {
            super(itemView);
            name=(TextView) itemView.findViewById(R.id.nameText);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    recyclerViewClickedInterface.OnItemClicked(getAdapterPosition());
                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    recyclerViewClickedInterface.OnLongItemClicked(getAdapterPosition());
                    return false;
                }
            });
        }
    }
    public interface OnNoteListener
    {
        void onNoteClick(int position);
    }
}
