package com.uottawa.choremanager;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Austin Tambakopoulos on 11/29/2017.
 */



public class DataBase {
    DatabaseReference dbProfiles, dbTasks;


    public DataBase(){
        dbProfiles = FirebaseDatabase.getInstance().getReference("profiles");
        dbTasks = FirebaseDatabase.getInstance().getReference("tasks");
    }

    public Profile addProfile(String name, Boolean isParent, String password){
        Profile toAdd = new Profile(name, isParent, password);

        String id = dbProfiles.push().getKey();
        toAdd.setId(id);

        DatabaseReference profileRef = dbProfiles.child(id);
        profileRef.child("name").setValue(name);
        profileRef.child(id).child("isParent").setValue(isParent);
        profileRef.child(id).child("password").setValue(password);
        profileRef.child(id).child("tskComp").setValue(0);

        return toAdd;
    }

    public Task addTask(String name, int startDate, String description, int endDate, Profile owner, ArrayList<SubTask> materials){
        Task toAdd = new Task(name, startDate, description, endDate, owner);

        String id = dbTasks.push().getKey();
        toAdd.setId(id);

        DatabaseReference taskRef = dbTasks.child(id);
        taskRef.child("name").setValue(name);
        taskRef.child("description").setValue(description);
        if(startDate != -1)
            taskRef.child("startDate").setValue(startDate);
        if(endDate != -1)
            taskRef.child("endDate").setValue(endDate);

        for(int i = 0; i < materials.size(); i++){
            taskRef.child("materials").push().setValue(materials.get(i));
        }
        assignTask(owner, toAdd);

        return toAdd;
    }

    public void assignTask(Profile profile, Task task){
        dbProfiles.child(profile.getId()).child("tasks").push().setValue(task.getId());
        dbTasks.child(task.getId()).child("owner").setValue(profile.getId());
    }
}
