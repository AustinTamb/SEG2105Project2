package com.uottawa.choremanager;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Austin Tambakopoulos on 2017-11-24.
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
            if(dB.getCurrentUser().isParent()) {
                Intent newProfileIntent = new Intent(getActivity().getApplicationContext(), newProfileActivity.class);
                startActivity(newProfileIntent);
            }else
                showError("You do not have permission to add a profile!");
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

        peopleListView = (ListView) view.findViewById(R.id.listViewPeople);

        updateAdapters();

        ((MainActivity)getActivity()).updateTaskFragment();

        return view;
    }
    //End of citation


    @Override
    public void onResume() {
        super.onResume();
        updateAdapters();
        peopleAdapter.notifyDataSetChanged();
    }

    private void showError(String message){
        Context context = getContext();
        CharSequence text = message;
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    private void updateAdapters(){
        ArrayList<Profile> x = dB.getProfiles();

        String[] profileList = new String[x.size()];
        int i = 0;
        for(Profile tmpP:x){
            profileList[i++] = tmpP.getName();
        }
        peopleAdapter = new PeopleCustomAdapter(getActivity().getApplicationContext(), profileList);
        peopleListView.setAdapter(peopleAdapter);
    }
}
