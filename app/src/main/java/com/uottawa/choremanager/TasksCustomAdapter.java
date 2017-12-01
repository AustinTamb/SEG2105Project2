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

import java.util.ArrayList;

/**
 * Created by hayley on 2017-11-28.
 */

public class TasksCustomAdapter extends ArrayAdapter {
    private final Context context;
    private final ArrayList<Task> tasksList;
    private DataBase dB;

    public TasksCustomAdapter(Context context, ArrayList<Task> tasksList){
        super(context, R.layout.tasks, tasksList);
        this.context = context;
        this.tasksList = tasksList;
        this.dB = MainActivity.getDB();
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.task_template, parent, false);

        TextView taskNameTextField = (TextView) rowView.findViewById(R.id.txtTaskName);
        ImageView personImage = (ImageView) rowView.findViewById(R.id.imgPerson);
        final CheckBox cbx = (CheckBox) rowView.findViewById(R.id.cbx);
        cbx.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Task x = tasksList.get(position);
                if(x.getStatus().equals("Active") && x.getStatus().equals("Active")) {
                    tasksList.get(position).setStatus(cbx.isChecked()?"Done":"Error");
                    Profile owner = dB.getProfile(tasksList.get(position).getOwnerId());
                    owner.setNumberOfTasksCompleted(owner.getNumberOfTasksCompleted()+1);
                    cbx.setEnabled(false);
                }
            }
        });

        ImageButton imgTaskButton = (ImageButton) rowView.findViewById(R.id.imgPerson);

        imgTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openTask = new Intent(getContext(), viewTaskActivity.class);
                context.startActivity(openTask);
            }
        });

        taskNameTextField.setText(tasksList.get(position).getName());


        return rowView;
    }
}
