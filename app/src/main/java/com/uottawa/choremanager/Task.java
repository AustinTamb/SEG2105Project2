package com.uottawa.choremanager;

import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Raymo on 2017-11-27.
 */


public class Task {
    private String name;
    private int startDate;
    private String description;
	private boolean done;
    private int endDate;
    private String ownerId;
    private List<SubTask> subTaskList;
    private String id;
    private String status;



    public Task(String name, int startDate,
                String description, int endDate,
                String ownerId, String status){

        this.name = name;
        this.startDate = startDate;
        this.description = description;
        this.done = false;
        this.endDate = endDate;
        this.ownerId = ownerId;
        this.subTaskList = new ArrayList<SubTask>();
        this.status = status;
    }


    public Task(){
        //do something
    }

    public void addSubTask(String subTaskName){
        SubTask newSubTask = new SubTask(subTaskName, false);
        subTaskList.add(newSubTask);
    }

    //Returns the requested SubTask or null if the SubTask does not exist
    public SubTask getSubTask(String name){
        for(SubTask s: subTaskList){
            if(name.equals(s)){ return(s); }
        }
        return(null);
    }



    //Removes the specified SubTask
    public void removeSubTask(String subTaskName, boolean done){
        SubTask newSubTask = new SubTask(subTaskName, false);
        subTaskList.remove(newSubTask);

        SubTask toBeRemoved;
        //True if the SubTask is found

        for(SubTask s: subTaskList){
            if(name.equals(s)){
                toBeRemoved = s;
                subTaskList.remove(s);
                break;
            }
        }

    }



    //The following Code was generated by UmpleOnline
    // http://cruise.eecs.uottawa.ca/umpleonline/umple.php?model=171128354777
    //Rewritten by Austin Tambakopoulos
    //------------------------
    // INTERFACE
    //------------------------
	
	public boolean getDone()
    {
        return done;
    }
	
	public boolean setDone(boolean aDone)
    {
        this.done = aDone;
        return true;
    }
	
	public boolean setId(String id){
        this.id = id;
        return true;
    }

    public boolean setName(String aName)
    {
        this.name = aName;
        return true;
    }

    public boolean setStartDate(int startDate)
    {
        this.startDate = startDate;
        return true;
    }

    public boolean setDescription(String aDescription)
    {
        this.description = aDescription;
        return true;
    }

    public boolean setEndDate(int endDate)
    {
        this.endDate = endDate;
        return true;
    }

    public boolean setOwner(String aOwnerId)
    {
        ownerId = aOwnerId;
        return true;
    }

    public String getName()
    {
        return name;
    }

    public int getEndDate()
    {
        return endDate;
    }

    public String getDescription() { return description; }

    public int getStartDate()
    {
        return startDate;
    }

    public String getOwnerId()
    {
        return ownerId;
    }

    public String getId(){
	    return id;
	}

    public List<SubTask> getSubTasks(){
        return subTaskList;
    }


    public void setSubTask(ArrayList<SubTask> subTask){
        this.subTaskList = subTask;
    }
    public void addSubTask(SubTask toAdd){
        subTaskList.add(toAdd);
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String toString()
    {
        return ("["+
                "name" + ":" + getName()+ "," +
                "id" + ":" + getId()+ "," +
                "description" + ":" + getDescription() +
                "]" +
                System.getProperties().getProperty("line.separator") +
                "startDate" + ":" + getStartDate()+ "," +
                "endDate" + ":" + getEndDate()+ "," +
                System.getProperties().getProperty("line.separator") +
                " " + "owner" + "=" + (getOwnerId() != null ? !getOwnerId().equals(this)  ? getOwnerId().toString().replaceAll("  ","    ") : "this" : "null"));
    }

    //Generated methods:

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public List<SubTask> getSubTaskList() {
        return subTaskList;
    }

    public void setSubTaskList(List<SubTask> subTaskList) {
        this.subTaskList = subTaskList;
    }

    //End Citation
}
