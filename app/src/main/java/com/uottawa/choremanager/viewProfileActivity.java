package com.uottawa.choremanager;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.CheckBox;
import android.widget.TextView;

/**
 * Created by Raymo on 2017-11-30.
 */

public class viewProfileActivity extends AppCompatActivity{
    DataBase dB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

        String profileId = getIntent().getStringExtra("profileId");
        dB = MainActivity.getDB();
        Profile x = dB.getProfile(profileId);
        Profile currentUser = dB.getCurrentUser();

        TextView name = (TextView) findViewById(R.id.txtName);
        name.setText(x.getName());
        CheckBox isParent = (CheckBox) findViewById(R.id.cbxIsParent);
        isParent.setChecked(x.isParent());
        TextView tskCmp = (TextView) findViewById(R.id.lblTaskCompleted);
        tskCmp.setText("Number of Tasks completed: "+x.getNumberOfTasksCompleted());

        if(currentUser.isParent()){
            System.err.println("Edit profile!");
            //Start activity for edit profile
        }else{
            System.err.println("View profile!");
            //update ui
        }
    }
}
