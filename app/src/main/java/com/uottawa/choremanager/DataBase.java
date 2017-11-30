package com.uottawa.choremanager;

import android.provider.ContactsContract;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by Austin Tambakopoulos on 11/29/2017.
 * Based off Lab 5(Firebase) code
 * And Firebase documentation : https://firebase.google.com/docs/database/admin/retrieve-data#section-reading-once
 */



public class DataBase {
    DatabaseReference dbProfiles, dbTasks;
    Map<String, Profile> profiles;
    Map<String, Task> tasks;

    public DataBase(){
        dbProfiles = FirebaseDatabase.getInstance().getReference("profiles");
        dbTasks = FirebaseDatabase.getInstance().getReference("tasks");
        profiles = new HashMap<String, Profile>();
        tasks = new HashMap<String, Task>();

        dbTasks.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                try{

                    System.out.println(dataSnapshot);
                } catch (Exception e){
                    System.err.println(e.getMessage());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });
    }

    public Profile addProfile(String name, Boolean isParent, String password){
        Profile toAdd = new Profile(name, isParent, password);

        String id = dbProfiles.push().getKey();
        toAdd.setId(id);
        dbProfiles.child(id).setValue(toAdd);

        profiles.put(id, toAdd);
        return toAdd;
    }

    public Task addTask(String name, int startDate, String description, int endDate, String ownerId, ArrayList<SubTask> materials){
        Task toAdd = new Task(name, startDate, description, endDate, ownerId);

        String id = dbTasks.push().getKey();
        toAdd.setId(id);
        for(int i = 0; i < materials.size(); i++){
            toAdd.addSubTask(materials.get(i));
        }
        dbTasks.child(id).setValue(toAdd);
        profiles.get(ownerId).addTask(id);
        dbProfiles.child(ownerId).child("tasks").push().setValue(toAdd);
        tasks.put(id, toAdd);
        return toAdd;
    }

    public void assignTask(String profileId, String taskId){
        Task x = tasks.get(taskId);
        String oldOwnerId = x.getOwnerId();

        if(!oldOwnerId.equals("")) {
            profiles.get(x.getOwnerId()).removeTask(taskId);
            dbProfiles.child(oldOwnerId).child("tasks").child(taskId).removeValue();
        }

        profiles.get(profileId).addTask(taskId);
        tasks.get(taskId).setOwner(profileId);

        dbProfiles.child(profileId).child("tasks").push().setValue(taskId);
        dbTasks.child(taskId).child("owner").setValue(profileId);
    }

    public void removeProfile(String profileId){
        Profile x = profiles.get(profileId);
        List<String> y = x.getAssignedTasks();//Gets all tasks assigned to said user

        //Following sets tasks owner to blank
        for(int i = 0; i < y.size(); i++){
            String taskId = y.get(i);
            tasks.get(taskId).setOwner("");
            dbTasks.child(taskId).child("owner").setValue("");
        }

        dbProfiles.child(profileId).removeValue();
        profiles.remove(profileId);
    }

    public void removeTask(String taskId){
        String ownerId = tasks.get(taskId).getOwnerId();
        profiles.get(ownerId).removeTask(taskId);
        dbTasks.child(taskId).removeValue();
        tasks.remove(taskId);
    }

    public Task getTask(String id){
        return tasks.get(id);
    }

    public Profile getProfile(String id){
        return profiles.get(id);
    }


}
