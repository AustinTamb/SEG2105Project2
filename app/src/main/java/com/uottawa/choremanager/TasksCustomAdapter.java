package com.uottawa.choremanager;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by hayley on 2017-11-28.
 */

public class TasksCustomAdapter extends ArrayAdapter {
    private final Context context;
    private final String[] tasksStringList;
    private DataBase dB;

    public TasksCustomAdapter(Context context, String[] tasksStringList){
        super(context, R.layout.tasks, tasksStringList);
        this.context = context;
        this.tasksStringList = tasksStringList;
        this.dB = MainActivity.getDB();
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.task_template, parent, false);
        final ArrayList<Task> tasksList = dB.getTasks();

        TextView taskNameTextField = (TextView) rowView.findViewById(R.id.txtTaskName);
        ImageView personImage = (ImageView) rowView.findViewById(R.id.imgPerson);




        ImageButton imgTaskButton = (ImageButton) rowView.findViewById(R.id.imgPerson);

        imgTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openTask = new Intent(getContext(), viewTaskActivity.class);
                openTask.putExtra("id", tasksList.get(position).getId().toString());
                context.startActivity(openTask);
            }
        });


        taskNameTextField.setText(tasksList.get(position).getName());

        final CheckBox cbx = (CheckBox) rowView.findViewById(R.id.cbx);
        cbx.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ArrayList<Task> tasksList = dB.getTasks();
                Task x = tasksList.get(position);
                if(!x.getOwnerId().equals(dB.getCurrentUser().getId())){
                    showError("This is not your task to complete!");
                    cbx.setChecked(false);
                } else if(x.getStatus().equals("Active") && x.getStatus().equals("Active")) {
                    tasksList.get(position).setStatus(cbx.isChecked()?"Done":"Error");
                    Profile owner = dB.getProfile(tasksList.get(position).getOwnerId());
                    owner.setNumberOfTasksCompleted(owner.getNumberOfTasksCompleted()+1);
                    cbx.setEnabled(false);
                    //Need code to remove task... causes crash otherwise...
                    //dB.removeTask(x.getId());
                }
            }
        });

        return rowView;
    }


    //Based off: https://developer.android.com/guide/topics/ui/notifiers/toasts.html
    private void showError(String message){
        CharSequence text = message;
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
}
