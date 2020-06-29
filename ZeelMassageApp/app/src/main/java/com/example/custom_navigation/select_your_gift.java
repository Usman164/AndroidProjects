package com.example.custom_navigation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.jar.Attributes;

public class select_your_gift extends AppCompatActivity implements RecyclerViewClickedInterface{
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    List<String> names;
    Button locationBtn;
    private Gift_City_Adapter gift_city_adapter;
//    private String names[]={"ali","usman","adeel","sheraz","zain","mana","talha","ali","usman","adeel","sheraz","zain","mana","talha"
//    ,"ali","usman","adeel","sheraz","zain","mana","talha","ali","usman","adeel","sheraz","zain","mana","talha"
//    ,"ali","usman","adeel","sheraz","zain","mana","talha","ali","usman","adeel","sheraz","zain","mana","talha"};
    TextView NameText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_your_gift);
        names=new ArrayList<>();
        names.add("Lahore");
        names.add("Karachi");
        names.add("Islamabad");
        names.add("Murree");
        names.add("Bahawalpure");
        names.add("Sahiwal");
        names.add("Sargodha");
        names.add("Patoki");
        names.add("Sheikhopura");
        names.add("Lahore");
        names.add("Karachi");
        names.add("Islamabad");
        names.add("Murree");
        names.add("Bahawalpure");
        names.add("Sahiwal");
        names.add("Sargodha");
        names.add("Patoki");
        names.add("Sheikhopura");
        names.add("Lahore");
        names.add("Karachi");
        names.add("Islamabad");
        names.add("Murree");
        names.add("Bahawalpure");
        names.add("Sahiwal");
        names.add("Sargodha");
        names.add("Patoki");
        names.add("Sheikhopura");

        recyclerView= (RecyclerView)findViewById(R.id.my_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        gift_city_adapter=new Gift_City_Adapter(names,this,this);
        recyclerView.setAdapter(gift_city_adapter);

    }

    @Override
    public void OnItemClicked(final int position) {
//        Toast.makeText(this,names.get(position),Toast.LENGTH_SHORT).show();
        Intent intent;
        intent = new Intent(getApplicationContext(),Gift_Cards.class);
        String value=names.get(position);
        intent.putExtra("name",value);
        startActivity(intent);
        finish();
    }

    @Override
    public void OnLongItemClicked(int position) {
        Toast.makeText(this,"movie removed",Toast.LENGTH_SHORT).show();
    }
}
