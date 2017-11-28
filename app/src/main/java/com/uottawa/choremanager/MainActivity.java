package com.uottawa.choremanager;

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
import android.widget.AdapterView;
import android.widget.ListView;


//taken from tutorial https://www.youtube.com/watch?v=bNpWGI_hGGg
public class MainActivity extends AppCompatActivity {
    private SectionPageAdapter mSectionPageAdapter;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSectionPageAdapter = new SectionPageAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager)findViewById(R.id.container);
        setupViewPager(mViewPager);

        TabLayout tabLayout = (TabLayout)findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        //String[] taskList = {"Hayley", "Raymond"};
        //ListView listView = (ListView) findViewById(R.id.taskList);
        //TasksCustomAdapter adapter = new TasksCustomAdapter(this, taskList);
        //listView.setAdapter(adapter);

        //String[] peopleList = {"Hayley", "Raymond"};
        //ListView peoplelistView = (ListView) findViewById(R.id.peopleList);
        //PeopleCustomAdapter peopleadapter = new PeopleCustomAdapter(this, peopleList);
        //listView.setAdapter(adapter);
    }
    private void setupViewPager(ViewPager viewPager){
        SectionPageAdapter adapter = new SectionPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new shoppingFragment(), "Shopping");
        adapter.addFragment(new tasksFragment(), "Tasks");
        adapter.addFragment(new peopleFragment(), "People");
        viewPager.setAdapter(adapter);
    }
}

//End citation
