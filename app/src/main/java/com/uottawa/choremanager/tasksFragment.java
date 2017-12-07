package com.uottawa.choremanager;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;


/**
 * Created by Raymo on 2017-11-24.
 */

public class tasksFragment extends Fragment implements Runnable{
    //taken from tutorial https://www.youtube.com/watch?v=bNpWGI_hGGg

    private ImageButton btnNewTask;
    private DataBase dB;
    private static final String TAG = "tasksFragment";
    private TasksCustomAdapter tasksAdapter;
    private MaterialsCustomAdapter subTasksAdapter;
    private TasksCustomAdapter myTasksAdapter;
    private MaterialsCustomAdapter mySubTasksAdapter;
    private String[] taskList;
    private String[] myTaskList;
    private ArrayList<SubTask> myMats;
    private ArrayList<SubTask> mats;
    private ArrayList<Task> listOfTasks;


    //This nested class is used to control what happens when btnNewTask is clicked
    public class NewTaskOnClickListener implements View.OnClickListener{
        public void onClick(View v) {
            if(dB.getCurrentUser().isParent()) {
                Intent newTaskIntent = new Intent(getActivity().getApplicationContext(), newTaskActivity.class);
                startActivity(newTaskIntent);
            }else
                showError("You do not have the permission to add tasks!");
        }
    }


    @Nullable
    @Override
    public View onCreateView (final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tasks, container, false);
        btnNewTask = (ImageButton) view.findViewById(R.id.imgNewTask);
        btnNewTask.setOnClickListener(new NewTaskOnClickListener());

        dB = MainActivity.getDB();

        listOfTasks = dB.getTasks();
        mats = new ArrayList<SubTask>();
        myMats = new ArrayList<SubTask>();
        updateAdapters();
        final Switch abc = (Switch) view.findViewById(R.id.swtShowMyTasks);
        boolean showMyTasks = abc.isChecked();

        //Fills the tasks List View
        final ListView tasksListView = (ListView) view.findViewById(R.id.listViewTasks);
        tasksAdapter = new TasksCustomAdapter(getActivity().getApplicationContext(), taskList);


        myTasksAdapter = new TasksCustomAdapter(getActivity().getApplicationContext(), myTaskList);
        mySubTasksAdapter = new MaterialsCustomAdapter(getActivity().getApplicationContext(), listOfTasks, myMats);

        //Fills the materials List View
        final ListView subTasksListView = (ListView) view.findViewById(R.id.listViewMaterials);
        subTasksAdapter = new MaterialsCustomAdapter(getActivity().getApplicationContext(), listOfTasks, mats);

        if(showMyTasks) {
            tasksListView.setAdapter(myTasksAdapter);
            subTasksListView.setAdapter(mySubTasksAdapter);
        }else{
            tasksListView.setAdapter(tasksAdapter);
            subTasksListView.setAdapter(subTasksAdapter);
        }

        //((MainActivity)getActivity()).update();
        abc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateAdapters();
                if(abc.isChecked()) {
                    tasksListView.setAdapter(myTasksAdapter);
                    subTasksListView.setAdapter(mySubTasksAdapter);
                }else{
                    tasksListView.setAdapter(tasksAdapter);
                    subTasksListView.setAdapter(subTasksAdapter);
                }
            }
        });

        return view;
    }



    //End of citation

    @Override
    public void onResume() {
        super.onResume();
        updateAdapters();
        tasksAdapter.notifyDataSetChanged();
        subTasksAdapter.notifyDataSetChanged();
    }

    //updates adapters
    @Override
    public void run(){
        try {
            updateAdapters();
            sleep(1000);
        }catch(Exception e){
            System.err.println("Clock error...");
        }
    }

    public void updateAdapters(){
            List<String> tasks = dB.getCurrentUser().getAssignedTasks();
            if(tasks!=null) {
                myTaskList = new String[tasks.size()];
                int i = 0;
                for (String id : tasks) {
                    myTaskList[i++] = dB.getTask(id).getName();
                    if (dB.getTask(id).getSubTasks() != null)
                        myMats.addAll(dB.getTask(id).getSubTasks());
                }
            }

        taskList = new String[listOfTasks.size()];
        for (int i = 0; i < listOfTasks.size(); i++) {
            taskList[i] = listOfTasks.get(i).getName();
            if (listOfTasks.get(i).getSubTasks() != null)
                mats.addAll(listOfTasks.get(i).getSubTasks());
        }
    }

    private void showError(String message){
        Context context = getContext();
        CharSequence text = message;
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

}
