package com.uottawa.choremanager;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;

/**
 * Created by hayley on 2017-11-29.
 */

public class calendarFragment extends Fragment{
    private static final String TAG = "calendarFragment";

    @Nullable
    @Override
    public View onCreateView (LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.calendar, container, false);

        String[] tasksDateList = {"Walk Dog", "Do the Dishes", "Clean Room", "Make Bed", "Take Trash Out"};
        ListView tasksDayListView = (ListView) view.findViewById(R.id.listViewTasksOnDay);
        calendarCustomAdapter calendarAdapter = new calendarCustomAdapter(getActivity().getApplicationContext(), tasksDateList);
        tasksDayListView.setAdapter(calendarAdapter);

        return view;
    }
}
