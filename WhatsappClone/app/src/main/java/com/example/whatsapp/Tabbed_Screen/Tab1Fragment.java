package com.example.whatsapp.Tabbed_Screen;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.whatsapp.Adapter.Tab1Fragment_Adapter;
import com.example.whatsapp.ClickedInterface;
import com.example.whatsapp.Message_Screen;
import com.example.whatsapp.Models.Register_Model;
import com.example.whatsapp.Models.Tab1Fragment_Model;
import com.example.whatsapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Tab1Fragment extends Fragment  {

    private static final String TAG="Swipe Position";
    private float x1,x2,y1,y2;
    private static int MIN_DISTANCE=150;
//    private GestureDetector gestureDetector;

    FragmentActivity c=getActivity();
    Tab1Fragment_Adapter adapter1;
    ArrayList<Register_Model> list;
    RecyclerView recyclerView;
    LinearLayoutManager manager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_one, container, false);
        recyclerView= view.findViewById(R.id.fragment1recycler_id);
       manager=new LinearLayoutManager(c);
        list = new ArrayList<>();
        recyclerView.setLayoutManager(manager);

//        this.gestureDetector=new GestureDetector(getContext(),this);

        FetchData();
        return  view;
    }
//    public boolean onTouchEvent(MotionEvent touchEvent){
//        switch(touchEvent.getAction()){
//            case MotionEvent.ACTION_DOWN:
//                x1 = touchEvent.getX();
//                y1 = touchEvent.getY();
//                break;
//            case MotionEvent.ACTION_UP:
//                x2 = touchEvent.getX();
//                y2 = touchEvent.getY();
//                if(x1< x2){
//                    Toast.makeText(c, "Swipe left", Toast.LENGTH_SHORT).show();
//            }else if(x1 >x2){
//                    Toast.makeText(c, "Swipe Right", Toast.LENGTH_SHORT).show();
//            }
//            break;
//        }
//        return false;
//    }
    private void FetchData()
    {
        final FirebaseUser firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
        final DatabaseReference reference= FirebaseDatabase.getInstance().getReference();
        reference.child("UserTable")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot dataSnapshot : snapshot.getChildren())
                        {
                            String Name = dataSnapshot.child("name").getValue().toString();
                            String Email = dataSnapshot.child("email").getValue().toString();
                            String Pass = dataSnapshot.child("pass").getValue().toString();
                            String Phone = dataSnapshot.child("phone").getValue().toString();
                            String Gender = dataSnapshot.child("gender").getValue().toString();
                            String ImageURL = dataSnapshot.child("imageUrl").getValue().toString();
                            Register_Model user= new Register_Model(Name,Email,Pass,Phone,Gender,ImageURL);
                            assert user!=null;
                            assert firebaseUser!=null;
                            if (!user.getEmail().equals(firebaseUser.getEmail()))
                            {
                                list.add(new Register_Model(Name,Email,Pass,Phone,Gender,ImageURL));
                            }
//                            list.add(new Register_Model(Name,Email,Pass,Phone,Gender,ImageURL));
//                            list.add(user);
                        }
                        adapter1= new Tab1Fragment_Adapter(getActivity(),list);
                        recyclerView.setAdapter(adapter1);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }

}
