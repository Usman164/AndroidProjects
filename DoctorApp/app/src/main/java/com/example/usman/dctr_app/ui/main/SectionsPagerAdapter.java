package com.example.usman.dctr_app.ui.main;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.usman.dctr_app.AllDoctors;
import com.example.usman.dctr_app.R;
import com.example.usman.dctr_app.Top_Ratted_Doctor;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_text_1, R.string.tab_text_2};
    private final Context mContext;

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position)
    {
        switch (position)
        {
            case 0:
                {
                AllDoctors allDoctors = new AllDoctors();
                return allDoctors;
            }
            case 1:
            {
                Top_Ratted_Doctor top_ratted_doctor  = new Top_Ratted_Doctor();
                return top_ratted_doctor;
            }
            default:
                {
                    return null;
                }
        }
    }


    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        // Show 2 total pages.
        return 2;
    }
}