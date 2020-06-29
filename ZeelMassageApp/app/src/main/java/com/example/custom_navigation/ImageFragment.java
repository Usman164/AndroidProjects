package com.example.custom_navigation;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
//import android.view

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;


/**
 * A simple {@link Fragment} subclass.
 */
public class ImageFragment extends Fragment  {
    ImageView imageView;
    Intent intent;
    public ImageFragment() {

        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_image, container, false);
        imageView=view.findViewById(R.id.images);
        String value = getArguments().getString("source");
        int value1=Integer.parseInt(value);
        Picasso.with(getActivity()).load(value1).into(imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId())
                {
                    case R.drawable.five:
                        Toast.makeText(getActivity(), "one", Toast.LENGTH_SHORT).show();
                        intent= new Intent(getActivity(),ImageClickActivity.class);
                        startActivity(intent);
                        break;
                    case R.drawable.three:
                        Toast.makeText(getActivity(),"Activity Two",Toast.LENGTH_SHORT).show();
                        intent= new Intent(getActivity(),ImageClickActivity.class);
                        startActivity(intent);
                        break;
                    case R.drawable.two:
                        Toast.makeText(getActivity(),"Activity Three",Toast.LENGTH_SHORT).show();
                        intent= new Intent(getActivity(),ImageClickActivity.class);
                        startActivity(intent);
                        break;
                    case R.drawable.one:
                        Toast.makeText(getActivity(),"Activity Four",Toast.LENGTH_SHORT).show();
                        intent= new Intent(getActivity(),ImageClickActivity.class);
                        startActivity(intent);
                        break;

                    default:
                        Toast.makeText(getActivity(),"default",Toast.LENGTH_SHORT).show();
                        intent= new Intent(getActivity(),ImageClickActivity.class);
                        intent.putExtra( "url",getArguments().getString("source"));
                        startActivity(intent);
                        break;
                }
            }
        });

        return view;

    }
}
