package com.uottawa.choremanager;

import android.app.Application;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Austin Tambakopoulos on 11/29/2017.
 * Based off Lab 5(Firebase) code
 * And Firebase documentation : https://firebase.google.com/docs/database/admin/retrieve-data#section-reading-once
 */



public class DataBase extends Application{
    //DataBase takes advantage of id creator of firebase in
    //order to make it act as a database when searching for a specific
    //Class instance
    private DatabaseReference dbProfiles, dbTasks;
    private Map<String, Profile> profiles;
    private Map<String, Task> tasks;
    private Profile currentUser;

    public DataBase() {
        dbProfiles = FirebaseDatabase.getInstance().getReference("Profile");
        dbTasks = FirebaseDatabase.getInstance().getReference("Task");
        profiles = new HashMap<String, Profile>();
        tasks = new HashMap<String, Task>();

        //Loads Tasks from Firebase on initializations
        dbTasks.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                Log.e("Count ", "" + snapshot.getChildrenCount());
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    Task task = postSnapshot.getValue(Task.class);
                    if (task != null) {
                        tasks.put(task.getId(), task);
                        //Log.e("Get Data", task.getId());
                    }
                }
            }

            //Catch errors related to failures in loading
            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });

        //Pulls data for Profiles from firebase
        dbProfiles.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                Log.e("Count ", "" + snapshot.getChildrenCount());
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    Profile profile = postSnapshot.getValue(Profile.class);
                    if (profile != null) {
                        profiles.put(profile.getId(), profile);
                        //Log.e("Get Data", profile.getId());
                    }
                }
            }

            //Catches error for failure to load data
            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });
    }

    //Method to add profile, does all links between classes for us
    //And returns the profile it create
    public Profile addProfile(String name, Boolean isParent, String password){
        Profile toAdd = new Profile(name, isParent, password);
        String id = dbProfiles.push().getKey();
        toAdd.setId(id);

        profiles.put(id, toAdd);
        dbProfiles.child(id).setValue(toAdd);
        return toAdd;
    }

    //Same purpose as above, except for tasks
    public Task addTask(String name, String startDate, String description, String endDate, String ownerId, ArrayList<SubTask> materials, String status){
        Task toAdd = new Task(name, startDate, description, endDate, ownerId, status);

        String id = dbTasks.push().getKey();
        toAdd.setId(id);
        for(int i = 0; i < materials.size(); i++){
            toAdd.addSubTask(materials.get(i));
        }

        tasks.put(id, toAdd);

        dbTasks.child(id).setValue(toAdd);
        assignTask(ownerId, id);
        return toAdd;
    }

    //Method to assign task to a user
    public void assignTask(String profileId, String taskId){
        Task x = tasks.get(taskId);
        String oldOwnerId = x.getOwnerId();
        removeTaskFromProfile(oldOwnerId, taskId);


        Profile user = profiles.get(profileId);
        user.addTask(taskId);
        tasks.get(taskId).setOwner(profileId);

        dbProfiles.child(profileId).child("assignedTasks").setValue(user.getAssignedTasks());
        dbTasks.child(taskId).child("ownerId").setValue(profileId);
    }

    //Method to remove profile, to be implemented...
    public void removeProfile(String profileId){
        Profile x = profiles.get(profileId);
        List<String> y = x.getAssignedTasks();//Gets all tasks assigned to said user

        //Following sets tasks owner to blank
        for(int i = 0; i < y.size(); i++){
            String taskId = y.get(i);
            tasks.get(taskId).setOwner("");
            dbTasks.child(taskId).child("ownerId").setValue("");
        }

        dbProfiles.child(profileId).removeValue();
        profiles.remove(profileId);
    }

    //Following removes tasks
    public void removeTask(String taskId){
        String ownerId = tasks.get(taskId).getOwnerId();
        profiles.get(ownerId).removeTask(taskId);
        dbProfiles.child(ownerId).child("assignedTasks").child(taskId).removeValue();
        dbTasks.child(taskId).removeValue();
        tasks.remove(taskId);
    }

    public void removeTaskFromProfile(String profileId, String taskId){
        Profile x = getProfile(profileId);
        x.removeTask(taskId);
        Task y = getTask(taskId);
        y.setOwner("");
        dbProfiles.child(profileId).child("assignedTasks").child(taskId).removeValue();
        dbTasks.child(taskId).child("ownerId").setValue("");
    }

    //Getter using id from firebase
    public Task getTask(String id){
        return tasks.get(id);
    }

    //Getter using id from firebase
    public Profile getProfile(String id){
        return profiles.get(id);
    }

    //Getter for Profiles held in an ArrayList, mostly used for ListViews..
    public ArrayList<Profile> getProfiles(){
        ArrayList<Profile> tmp = new ArrayList<Profile>();
        for (Map.Entry<String, Profile> entry : profiles.entrySet())
        {
            tmp.add(entry.getValue());
        }
        return tmp;
    }

    //Same as above, but for tasks.
    public ArrayList<Task> getTasks(){
        ArrayList<Task> tmp = new ArrayList<Task>();
        for (Map.Entry<String, Task> entry : tasks.entrySet())
        {
            tmp.add(entry.getValue());
        }
        return tmp;
    }

    //Getter for current user
    public Profile getCurrentUser(){
        return currentUser;
    }


    //setter for current user
    public void setCurrentUser(Profile user){
        this.currentUser = user;
    }

}
