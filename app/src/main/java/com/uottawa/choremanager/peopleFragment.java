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

import java.util.ArrayList;

/**
 * Created by Raymo on 2017-11-24.
 */

public class peopleFragment extends Fragment {
    //taken from https://www.youtube.com/watch?v=bNpWGI_hGGg

    private ImageButton btnNewProfile;
    private DataBase dB;
    private static final String TAG = "peopleFragment";
    //Following two made private by random guy on Github rather than declared in body https://github.com/michaelsam94
    private PeopleCustomAdapter peopleAdapter;
    private ListView peopleListView;

    //This nested class is used to control what happens when btnNewTask is clicked
    public class NewProfileOnClickListener implements View.OnClickListener {
        public void onClick(View v) {
            Intent newProfileIntent = new Intent(getActivity().getApplicationContext(), newProfileActivity.class);
            startActivity(newProfileIntent);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.people, container, false);
        btnNewProfile = (ImageButton) view.findViewById(R.id.imgNewPerson);
        btnNewProfile.setOnClickListener(new NewProfileOnClickListener());

        dB = MainActivity.getDB();
        ArrayList<Profile> x = dB.getProfiles();

        String[] profileList = new String[x.size()];
        for (int i = 0; i < x.size(); i++) {
            profileList[i] = x.get(i).getName();

        }

        peopleListView = (ListView) view.findViewById(R.id.listViewPeople);
        peopleAdapter = new PeopleCustomAdapter(getActivity().getApplicationContext(), profileList);
        peopleListView.setAdapter(peopleAdapter);

        ((MainActivity)getActivity()).updateTaskFragment();

        return view;
    }
    //End of citation


    @Override
    public void onResume() {
        super.onResume();
        peopleAdapter.notifyDataSetChanged();
    }
}
