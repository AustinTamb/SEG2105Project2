package com.uottawa.choremanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.SpinnerAdapter;

import java.util.ArrayList;

/**
 * Created by Raymo on 2017-11-30.
 */

public class spinnerCustomAdapter extends ArrayAdapter {

    private Context context;
    private ArrayList<String> profileIdList;


    public spinnerCustomAdapter(Context context, ArrayList<String> profileIdList){
        super(context, R.layout.add_task, profileIdList);
        this.context = context;
        this.profileIdList = profileIdList;
    }

    public View getDropDownView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.task_template, parent, false);

        return rowView;
    }
}
