package com.uottawa.choremanager;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MaterialsCustomAdapter extends ArrayAdapter {
    private final Context context;
    private final ArrayList<Task> tasksList;
    private final ArrayList<SubTask> subTaskList;
    private DataBase dB;
    private ImageButton imgTaskButton;

    public MaterialsCustomAdapter(Context context, ArrayList<Task> tasksList, ArrayList<SubTask> subTaskList){
        super(context, R.layout.tasks, subTaskList);
        this.context = context;
        this.tasksList = tasksList;
        this.dB = MainActivity.getDB();
        this.subTaskList = subTaskList;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.subtask_template, parent, false);

        TextView subTaskNameTextField = (TextView) rowView.findViewById(R.id.txtSubTask);
        ImageView personImage = (ImageView) rowView.findViewById(R.id.imgPerson);
        final CheckBox cbx = (CheckBox) rowView.findViewById(R.id.cbx);
        cbx.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Task x = tasksList.get(position);
                if(x.getStatus().equals("Active")) {
                    tasksList.get(position).setStatus(cbx.isChecked()? "Done":"Error");
                    Profile owner = dB.getProfile(tasksList.get(position).getOwnerId());
                    owner.setNumberOfTasksCompleted(owner.getNumberOfTasksCompleted()+1);
                    cbx.setEnabled(false);
                }
            }
        });

        //Put in Constructor?
        //Fills subTaskList
        try {

            if (subTaskList.size() != 0) {
                subTaskNameTextField.setText(subTaskList.get(position).getName());
            }
        }catch(NullPointerException e){

        }

        return rowView;
    }

}
