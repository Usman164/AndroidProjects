package com.example.custom_navigation;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class ImageFragmentAdapter extends FragmentStatePagerAdapter {

    public static Integer[] imageSrc=
            {
                    R.drawable.three,
                    R.drawable.two,
                    R.drawable.five,
                    R.drawable.one
            };
//  public static String[] imageSrc=
//            {
//                   "https://www.tnn.com.pk/wp-content/uploads/2017/06/The_Stethoscope_Peru.jpg",
//                    "https://cdn.psychologytoday.com/sites/default/files/styles/article-inline-half/public/field_blog_entry_images/doctor-1461878769jQS.jpg?itok=zeF_yBdz",
//                    "https://phlabs.com/Content/Images/uploaded/doctor-writing-prescription---life-mental-health.jpg",
//                    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTQCCD9ZuFJVk42jkSs5aGoOA4-yatcjYg2UEq4gfIn0Zzn7war&s"
//            };

    public ImageFragmentAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        ImageFragment imageFragment=new ImageFragment();
        Bundle bundle=new Bundle();
        bundle.putString("source", String.valueOf(imageSrc[position]));
        imageFragment.setArguments(bundle);
        return imageFragment;
    }

    @Override
    public int getCount() {
        return imageSrc.length;
    }
}
