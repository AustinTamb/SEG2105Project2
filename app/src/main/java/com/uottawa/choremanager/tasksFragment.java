package com.uottawa.choremanager;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Button;
import android.widget.ImageButton;

import static java.sql.DriverManager.println;

/**
 * Created by Raymo on 2017-11-24.
 */

public class tasksFragment extends Fragment {
    //taken from tutorial https://www.youtube.com/watch?v=bNpWGI_hGGg

    private ImageButton btnNewTask;

    private static final String TAG = "tasksFragment";


    //This nested class is used to control what happens when btnNewTask is clicked
    public class NewTaskOnClickListener implements View.OnClickListener{
        public void onClick(View v) {
            Intent newTaskIntent = new Intent(getActivity().getApplicationContext(), newTaskActivity.class);
            startActivity(newTaskIntent);
        }
    }

    @Nullable
    @Override
    public View onCreateView (LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tasks, container, false);
        btnNewTask = (ImageButton) view.findViewById(R.id.btnNewTask);
        btnNewTask.setOnClickListener(new NewTaskOnClickListener());


        String[] taskList = {"Hayley", "Raymond"};
        ListView tasksListView = (ListView) view.findViewById(R.id.listViewPeople);
        TasksCustomAdapter tasksAdapter = new TasksCustomAdapter(getActivity().getApplicationContext(), taskList);
        tasksListView.setAdapter(tasksAdapter);
        System.out.println("Successfully Created People view");

        return view;
    }



    //End of citation
}
