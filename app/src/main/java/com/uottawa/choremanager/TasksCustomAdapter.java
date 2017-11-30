package com.uottawa.choremanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by hayley on 2017-11-28.
 */

public class TasksCustomAdapter extends ArrayAdapter {
    private final Context context;
    private final ArrayList<Task> tasksList;

    public TasksCustomAdapter(Context context, ArrayList<Task> tasksList){
        super(context, R.layout.tasks, tasksList);
        this.context = context;
        this.tasksList = tasksList;
    }
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.task_template, parent, false);

        TextView taskNameTextField = (TextView) rowView.findViewById(R.id.txtTaskName);
        ImageView personImage = (ImageView) rowView.findViewById(R.id.imgPerson);
        CheckBox cbx = (CheckBox) rowView.findViewById(R.id.cbx);

        taskNameTextField.setText(tasksList.get(position).getName());


        return rowView;
    }
}
