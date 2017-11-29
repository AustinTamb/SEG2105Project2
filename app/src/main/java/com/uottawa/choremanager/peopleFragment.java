package com.uottawa.choremanager;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;

/**
 * Created by Raymo on 2017-11-24.
 */

public class peopleFragment extends Fragment {
    //taken from https://www.youtube.com/watch?v=bNpWGI_hGGg

    private ImageButton btnNewProfile;

    private static final String TAG = "peopleFragment";

    //This nested class is used to control what happens when btnNewTask is clicked
    public class NewProfileOnClickListener implements View.OnClickListener{
        public void onClick(View v) {
            Intent newProfileIntent = new Intent(getActivity().getApplicationContext(), newProfileActivity.class);
            startActivity(newProfileIntent);
        }
    }

    @Nullable
    @Override
    public View onCreateView (LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.people, container, false);
        btnNewProfile = (ImageButton) view.findViewById(R.id.imgNewPerson);
        btnNewProfile.setOnClickListener(new NewProfileOnClickListener());

        String[] taskList = {"Hayley", "Raymond"};
        ListView peopleListView = (ListView) view.findViewById(R.id.listViewPeople);
        PeopleCustomAdapter peopleAdapter = new PeopleCustomAdapter(getActivity().getApplicationContext(), taskList);
        peopleListView.setAdapter(peopleAdapter);
        System.out.println("Successfully Created People view");
        return view;
    }
    //End of citation
}
