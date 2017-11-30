package com.uottawa.choremanager;

import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class newTaskActivity extends AppCompatActivity {
    private ArrayList<SubTask> subTasks;
    private ArrayList<String> names;
    private ArrayAdapter<String> aA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_task);
        names = new ArrayList<String>();
        final Spinner profiles = findViewById(R.id.spnProfiles);
        //Get array of Profiles, loop through and getNames->spinnerOption

        final Button button = findViewById(R.id.btnAddTask);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String startDate = ((TextView)findViewById(R.id.txtStartDate)).getText().toString();
                String endDate = ((TextView)findViewById(R.id.txtEndDate)).getText().toString();
                String name = ((TextView)findViewById(R.id.txtTaskName)).getText().toString();
                String description = ((TextView)findViewById(R.id.txtNotes)).getText().toString();

            }
        });
        final Button addButton = findViewById(R.id.btnAdd);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String mToAdd = ((TextView)findViewById(R.id.txtSTName)).getText().toString();
                if(!mToAdd.equals("")) {
                    subTasks.add(new SubTask(mToAdd, false));
                    names.add(mToAdd);

                }
            }
        });
    }

}
