package com.uottawa.choremanager;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Switch;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Raymo on 2017-11-24.
 */

public class tasksFragment extends Fragment {
    //taken from tutorial https://www.youtube.com/watch?v=bNpWGI_hGGg

    private ImageButton btnNewTask;
    private Switch swtOnlyShow;
    private DataBase dB;
    private static final String TAG = "tasksFragment";
    private boolean onlyShowMyTasks = false;
    private Profile currentUser;
    private TasksCustomAdapter tasksAdapter;
    private MaterialsCustomAdapter subTasksAdapter;


    //This nested class is used to control what happens when btnNewTask is clicked
    public class NewTaskOnClickListener implements View.OnClickListener{
        public void onClick(View v) {
            Intent newTaskIntent = new Intent(getActivity().getApplicationContext(), newTaskActivity.class);
            startActivity(newTaskIntent);
        }
    }
    public class AddSubTaskOnClickListener implements View.OnClickListener{
        public void onClick(View v){
            Intent newAddSubTaskIntent = new Intent(getActivity().getApplicationContext(), newTaskActivity.class);
            startActivity(newAddSubTaskIntent);
        }
    }

    @Nullable
    @Override
    public View onCreateView (LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        System.out.println("tasksFragment OnCreateView has been called");
        View view = inflater.inflate(R.layout.tasks, container, false);
        btnNewTask = (ImageButton) view.findViewById(R.id.imgNewTask);
        btnNewTask.setOnClickListener(new NewTaskOnClickListener());

        dB = MainActivity.getDB();
        ArrayList<Profile> listOfProfiles = dB.getProfiles();
        System.out.println("Amount of profiles from taskFragment"
                + listOfProfiles.size());


        ArrayList<Task> listOfTasks = new ArrayList<Task>();

        //REMOVE ME
        int amountOfTasks = 0;

        for(Profile p: listOfProfiles){
            List aProfilesListOfTasks = p.getAssignedTasks();
            if(aProfilesListOfTasks != null) {
                amountOfTasks = amountOfTasks + aProfilesListOfTasks.size();
                for (int j = 0; j < aProfilesListOfTasks.size(); j++) {
                    String aTaskId = (String) aProfilesListOfTasks.get(j);
                    listOfTasks.add(dB.getTask(aTaskId));
                }
            }
        }

        System.out.println("THE TASK LIST SIZE:" + listOfTasks.size());
        System.out.println("FIRST ELEMENT IN LIST OF TASKS:" + listOfTasks.get(0));


        String[] taskList = new String[listOfTasks.size()];
        for (int i = 0; i < listOfTasks.size(); i++) {
            taskList[i] = listOfTasks.get(i).getName();
            System.out.println("ELEMENT IN LIST OF TASKS at position: " + i + " " + listOfTasks.get(0) );

        }
        ArrayList<SubTask> mats = new ArrayList<SubTask>();

        for(int i = 0; i < listOfTasks.size(); i++){
            mats.addAll(listOfTasks.get(i).getSubTasks());
        }

        //Fills the tasks List View
        ListView tasksListView = (ListView) view.findViewById(R.id.listViewTasks);
        tasksAdapter = new TasksCustomAdapter(getActivity().getApplicationContext(), taskList);
        tasksListView.setAdapter(tasksAdapter);

        //Fills the materials List View
        ListView subTasksListView = (ListView) view.findViewById(R.id.listViewMaterials);
        subTasksAdapter = new MaterialsCustomAdapter(getActivity().getApplicationContext(), listOfTasks);
        subTasksListView.setAdapter(subTasksAdapter);


        //Handles the switch

        //NEEDS TO BE FIXED
        /*
        swtOnlyShow = (Switch) view.findViewById(R.id.swtShowMyTasks);
        swtOnlyShow.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b == true){

                    //Updates the tasks listview
                    onlyShowMyTasks = true;
                    currentUser = dB.getCurrentUser();
                    listOfTasks.clear();
                    for(Task t: dB.getTasks()){
                        if(t.getOwnerId().equals(currentUser.getId())){
                            listOfTasks.add(t);
                        }
                    }
                    tasksAdapter.notifyDataSetChanged();

                }else{

                    //Updates the tasks listview
                    onlyShowMyTasks = false;
                    listOfTasks.clear();
                    for(Task t: dB.getTasks()){
                        listOfTasks.add(t);
                    }
                    tasksAdapter.notifyDataSetChanged();
                }
            }
        });
        */
        //((MainActivity)getActivity()).update();



        return view;
    }



    //End of citation

    @Override
    public void onResume() {
        super.onResume();
        tasksAdapter.notifyDataSetChanged();
        subTasksAdapter.notifyDataSetChanged();

    }

}
