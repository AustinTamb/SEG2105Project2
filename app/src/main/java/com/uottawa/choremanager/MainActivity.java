/* CITATIONS
* Got Firebase related code from : https://www.firebase.com/docs/android/quickstart.html
*
*
* */

package com.uottawa.choremanager;

import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import java.util.ArrayList;


//taken from tutorial https://www.youtube.com/watch?v=bNpWGI_hGGg
public class MainActivity extends AppCompatActivity {
    private SectionPageAdapter mSectionPageAdapter;
    private ViewPager mViewPager;
    static public DataBase dB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Following is uses to populate database and test stuff...
        ArrayList<SubTask> z = new ArrayList<SubTask>();
        dB = new DataBase();
        Profile x = dB.addProfile("Austin", true,"Test123");
        Profile kh = dB.addProfile("Kevin Hart", true,"Test123");

        z.add(new SubTask("Cloth", false));
        z.add(new SubTask("Bucket", false));
        z.add(new SubTask("Water", false));

        Task y = dB.addTask("Wash Car", 10, "wash it..", 20, x.getId(), z, "Active");
        Task b = dB.addTask("Pet Car", 10, "pet it..", 20, x.getId(), z, "Active");

        dB.assignTask(x.getId(), y.getId());
        dB.assignTask(x.getId(), b.getId());
        dB.setCurrentUser(x);

        Task p = dB.addTask("Wash Stapler", 10, "washittt", 20, kh.getId(), z, "Active");
        Task k = dB.addTask("Say hi to Raymond", 10, "hi raymond", 20, kh.getId(), z, "Active");

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
