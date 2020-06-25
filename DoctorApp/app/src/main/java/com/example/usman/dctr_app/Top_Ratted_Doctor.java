package com.example.usman.dctr_app;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.usman.dctr_app.Adapters.D_Adapter;
import com.example.usman.dctr_app.Model.Dctr_model;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Top_Ratted_Doctor extends Fragment {

    D_Adapter adapter;
    ArrayList<Dctr_model> list;
    RecyclerView recyclerView;
    LinearLayoutManager manager;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_top__ratted__doctor, container, false);
        recyclerView = v.findViewById(R.id.top_Ratted);
        manager = new LinearLayoutManager(getActivity());
        list = new ArrayList<>();
        manager.setReverseLayout(true);
        manager.setStackFromEnd(true);
        recyclerView.setLayoutManager(manager);
        adapter = new D_Adapter(getActivity(),list);
        recyclerView.setAdapter(adapter);




        Query query1 = FirebaseDatabase.getInstance().getReference("DctrTable")
                .orderByChild("d_Rating");
        query1.addListenerForSingleValueEvent(valueEventListener);
        return v;
    }
    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            for (DataSnapshot snapshot: dataSnapshot.getChildren()){

                String Name =snapshot.child("name").getValue().toString();
                String Email =snapshot.child("email").getValue().toString();
                String Password =snapshot.child("password").getValue().toString();
                String Gender =snapshot.child("gender").getValue().toString();
                String Phone =snapshot.child("phone").getValue().toString();
                String Image =snapshot.child("imageURL").getValue().toString();
                String Spec=snapshot.child("spec").getValue().toString();
                String Rating=snapshot.child("d_Rating").getValue().toString();


                list.add(new Dctr_model(Name,Email,Password,Phone,Spec,Rating,Gender ,Image));
            }
            adapter.notifyDataSetChanged();
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };


}
