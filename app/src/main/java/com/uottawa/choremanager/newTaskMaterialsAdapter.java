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
 * Created by Raymo on 2017-11-30.
 */

public class newTaskMaterialsAdapter extends ArrayAdapter {
    private final Context context;
    //private DataBase dB;
    private final ArrayList<SubTask> subTaskList;

    public newTaskMaterialsAdapter(Context context, ArrayList<SubTask> subTaskList){
        super(context, R.layout.add_task, subTaskList);
        this.context = context;
        this.subTaskList = subTaskList;
        //this.dB = MainActivity.getDB();

    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.subtask_template, parent, false);

        TextView subTaskNameTextField = (TextView) rowView.findViewById(R.id.txtSubTask);
        ImageView personImage = (ImageView) rowView.findViewById(R.id.imgPerson);

        subTaskNameTextField.setText(subTaskList.get(position).getName());


        return(rowView);

    }
}
