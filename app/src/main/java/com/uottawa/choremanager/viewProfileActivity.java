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

        //Updates UML with data:
        TextView name = (TextView) findViewById(R.id.txtName);
        name.setText(x.getName());
        CheckBox isParent = (CheckBox) findViewById(R.id.cbxIsParent);
        isParent.setChecked(x.isParent());
        isParent.setEnabled(false);//no point in them clicking this if it doesn't edit...
        TextView tskCmp = (TextView) findViewById(R.id.lblTaskCompleted);
        tskCmp.setText("Number of Tasks completed: "+x.getNumberOfTasksCompleted());

        //Add on click listener for edit profile button
        if(currentUser.isParent()){//Checks if parent
            //
            System.err.println("Edit profile!");
            //Start activity for edit profile
        }
    }
}
