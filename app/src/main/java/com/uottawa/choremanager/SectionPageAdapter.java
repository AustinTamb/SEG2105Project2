package com.uottawa.choremanager;


//taken from tutorial https://www.youtube.com/watch?v=bNpWGI_hGGg
//https://stackoverflow.com/questions/18088076/update-fragment-from-viewpager
//https://stackoverflow.com/questions/19744261/how-to-update-refresh-fragment-in-viewpager-by-main-activity-programmatically
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Raymo on 2017-11-24.
 */

public class SectionPageAdapter extends FragmentStatePagerAdapter {
    private final List<Fragment> mFragmentList = new ArrayList<>();
    private final List<String> mFragmentTitleList = new ArrayList<>();

    public void addFragment(Fragment fragment, String title){
        mFragmentList.add(fragment);
        mFragmentTitleList.add(title);
    }

    public SectionPageAdapter(FragmentManager fm){
        super(fm);
    }


    @Override
    public CharSequence getPageTitle(int position){
        return mFragmentTitleList.get(position);
    }
    @Override
    public Fragment getItem(int position){
        return mFragmentList.get(position);
    }
    @Override
    public int getCount(){
        return mFragmentList.size();
    }


    @Override
    public int getItemPosition(Object object) {
        // POSITION_NONE makes it possible to reload the PagerAdapter
        return POSITION_NONE;
    }

    //End Citation
}
