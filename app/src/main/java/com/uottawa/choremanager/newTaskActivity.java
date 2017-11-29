package com.uottawa.choremanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;

public class newTaskActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_task);

        final Spinner profiles = findViewById(R.id.spnProfiles);
        //Get array of Profiles, loop through and getNames->spinnerOption

        final Button button = findViewById(R.id.btnAddTask);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String startDate, endDate;
                // Code here executes on main thread after user presses button
            }
        });
    }


}
