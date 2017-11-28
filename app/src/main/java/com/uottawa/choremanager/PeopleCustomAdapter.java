package com.uottawa.choremanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by hayley on 2017-11-28.
 */

public class PeopleCustomAdapter extends ArrayAdapter {
    private final Context context;
    private final String[] people;

    public PeopleCustomAdapter(Context context, String[] peopleList){
        super(context, R.layout.people2, peopleList);
        this.context = context;
        this.people = peopleList;
    }
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.people2, parent, false);

        TextView personNameTextField = (TextView) rowView.findViewById(R.id.txtName);
        TextView numTasksTextField = (TextView) rowView.findViewById(R.id.txtNumTasks);
        TextView nextTaskTextField = (TextView) rowView.findViewById(R.id.txtNewTask);
        ImageView personImage = (ImageView) rowView.findViewById(R.id.imgPerson);

        personNameTextField.setText(people[position]);
        numTasksTextField.setText(people[position] + ":2");
        nextTaskTextField.setText(people[position] + " : Wash car");

        return rowView;
    }
}
