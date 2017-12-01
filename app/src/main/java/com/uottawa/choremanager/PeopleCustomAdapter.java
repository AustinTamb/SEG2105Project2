package com.uottawa.choremanager;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by hayley on 2017-11-28.
 */

public class PeopleCustomAdapter extends ArrayAdapter {
    private final Context context;
    private final String[] people;
    private DataBase dB;

    public PeopleCustomAdapter(Context context, String[] peopleList){
        super(context, R.layout.people_template, peopleList);
        this.context = context;
        this.people = peopleList;
    }
    public View getView(int position, View convertView, ViewGroup parent) {
        dB = MainActivity.getDB();
        ArrayList<Profile> x = dB.getProfiles();
        Profile person = x.get(position);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.people_template, parent, false);

        TextView personNameTextField = (TextView) rowView.findViewById(R.id.txtName);
        TextView numTasksTextField = (TextView) rowView.findViewById(R.id.txtNumTasks);
        TextView nextTaskTextField = (TextView) rowView.findViewById(R.id.txtNextTask);
        ImageView personImage = (ImageView) rowView.findViewById(R.id.imgPerson);

        personNameTextField.setText(person.getName());
        numTasksTextField.setText("Number of Tasks: " + person.getAssignedTasks().size());
        String currentTask;
        try {
            currentTask = dB.getTask(person.getAssignedTasks().get(0)).getName();
        } catch (Exception e){
            currentTask = "N/A";
        }
        personImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openProfile = new Intent(getContext(), viewProfileActivity.class);
                context.startActivity(openProfile);
            }
        });


        nextTaskTextField.setText("Current Tasks: " + currentTask);
        return rowView;
    }
}
