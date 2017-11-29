package com.uottawa.choremanager;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

/**
 * Created by Raymo on 2017-11-24.
 */

public class tasksFragment extends Fragment {
    //taken from tutorial https://www.youtube.com/watch?v=bNpWGI_hGGg

    private static final String TAG = "tasksFragment";

    @Nullable
    @Override
    public View onCreateView (LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tasks, container, false);

        String[] taskList = {"Hayley", "Raymond"};
        ListView tasksListView = (ListView) view.findViewById(R.id.listViewPeople);
        TasksCustomAdapter tasksAdapter = new TasksCustomAdapter(getActivity().getApplicationContext(), taskList);
        tasksListView.setAdapter(tasksAdapter);
        System.out.println("Successfully Created People view");

        return view;
    }
    //End of citation
}
