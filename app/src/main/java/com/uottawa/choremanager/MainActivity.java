/* CITATIONS
* Got Firebase related code from : https://www.firebase.com/docs/android/quickstart.html
*
*
* */

package com.uottawa.choremanager;

import android.app.Application;
import android.provider.Settings;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


//taken from tutorial https://www.youtube.com/watch?v=bNpWGI_hGGg
public class MainActivity extends AppCompatActivity {
    private SectionPageAdapter mSectionPageAdapter;
    private ViewPager mViewPager;
    static public DataBase dB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<SubTask> z = new ArrayList<SubTask>();
        dB = new DataBase();
        Profile x = dB.addProfile("Austin", true,"Test123");

        z.add(new SubTask("Cloth", false));
        z.add(new SubTask("Bucket", false));
        z.add(new SubTask("Water", false));

        Task y = dB.addTask("Wash Car", 10, "wash it..", 20, x.getId(), z, "Active");

        dB.setCurrentUser(x);
       // dB.removeProfile(x);
        //profiles.remove(x);

        mSectionPageAdapter = new SectionPageAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager)findViewById(R.id.container);
        setupViewPager(mViewPager);

        TabLayout tabLayout = (TabLayout)findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

    }
    private void setupViewPager(ViewPager viewPager){
        SectionPageAdapter adapter = new SectionPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new calendarFragment(), "Calendar");
        adapter.addFragment(new tasksFragment(), "Tasks");
        adapter.addFragment(new peopleFragment(), "People");
        viewPager.setAdapter(adapter);
    }

    static public DataBase getDB(){
        return dB;
    }
}

//End citation
