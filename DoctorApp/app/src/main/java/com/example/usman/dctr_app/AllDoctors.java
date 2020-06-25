package com.example.usman.dctr_app;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.usman.dctr_app.Adapters.D_Adapter;
import com.example.usman.dctr_app.Model.Dctr_model;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class AllDoctors extends Fragment {
    D_Adapter d_adapter;
    ArrayList<Dctr_model> list;
    RecyclerView recyclerView;
    LinearLayoutManager manager;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    public AllDoctors() {
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AllDoctors.
     */

    public static AllDoctors newInstance(String param1, String param2) {
        AllDoctors fragment = new AllDoctors();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.fragment_all_doctors, container, false);
        recyclerView = v.findViewById(R.id.Doctor_recycler_View);
        manager= new LinearLayoutManager(getActivity());
        list = new ArrayList<>();
        recyclerView.setLayoutManager(manager);

        FetchData();
        return v;
    }
    private void FetchData()
    {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        reference.child("DctrTable")
                .addValueEventListener(new ValueEventListener() {
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
                            list.add(new Dctr_model(Name,Email,Password,Phone,Spec,Rating,Gender,Image));
                        }
                        d_adapter = new D_Adapter(getActivity(),list);
                          recyclerView.setAdapter(d_adapter);
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Toast.makeText(getActivity(),"databaseError", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
