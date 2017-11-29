package com.uottawa.choremanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by hayley on 2017-11-29.
 */

public class calendarCustomAdapter extends ArrayAdapter {
        private final Context context;
        private final String[] tasksDate;

        public calendarCustomAdapter(Context context, String[] tasksDateList){
            super(context, R.layout.calendar_template, tasksDateList);
            this.context = context;
            this.tasksDate = tasksDateList;
        }
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View rowView = inflater.inflate(R.layout.calendar_template, parent, false);

            TextView taskNameTextField = (TextView) rowView.findViewById(R.id.txtTaskName);

            taskNameTextField.setText(tasksDate[position]);

            return rowView;
        }
    }
