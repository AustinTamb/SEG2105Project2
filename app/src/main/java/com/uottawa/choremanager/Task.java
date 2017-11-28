package com.uottawa.choremanager;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Raymo on 2017-11-27.
 */


public class Task {
    private String name;
    private Date deadline;
    private String description;
	private boolean done;
    private boolean assigned;
    private int reoccurRate;
    private Date reoccurEnd;
    private Profile owner;
    private ArrayList<SubTask> subTaskList;



    public Task(String name, Date deadline,
                String description, boolean assigned,
                int reoccurRate, Date reoccurEnd,
                Profile owner){

        this.name = name;
        this.deadline = deadline;
        this.description = description;
        this.assigned = assigned;
		this.done = false;
        this.reoccurRate = reoccurRate;
        this.reoccurEnd = reoccurEnd;
        this.owner = owner;
        this.subTaskList = new ArrayList<SubTask>();
    }


    public void addSubTask(String subTaskName, boolean done){
        SubTask newSubTask = new SubTask(subTaskName, false);
        subTaskList.add(newSubTask);
    }



    //The following Code was generated by UmpleOnline
    // http://cruise.eecs.uottawa.ca/umpleonline/umple.php?model=171128354777

    //------------------------
    // INTERFACE
    //------------------------
	
	public boolean getDone()
    {
        return done;
    }
	
	public boolean setDone(boolean aDone)
    {
        boolean wasSet = false;
        done = aDone;
        wasSet = true;
        return wasSet;
    }
	
	
    public boolean setName(String aName)
    {
        boolean wasSet = false;
        name = aName;
        wasSet = true;
        return wasSet;
    }

    public boolean setDeadline(Date aDeadline)
    {
        boolean wasSet = false;
        deadline = aDeadline;
        wasSet = true;
        return wasSet;
    }

    public boolean setDescription(String aDescription)
    {
        boolean wasSet = false;
        description = aDescription;
        wasSet = true;
        return wasSet;
    }

    public boolean setAssigned(boolean aAssigned)
    {
        boolean wasSet = false;
        assigned = aAssigned;
        wasSet = true;
        return wasSet;
    }

    public boolean setReoccurRate(int aReoccurRate)
    {
        boolean wasSet = false;
        reoccurRate = aReoccurRate;
        wasSet = true;
        return wasSet;
    }

    public boolean setReoccurEnd(Date aReoccurEnd)
    {
        boolean wasSet = false;
        reoccurEnd = aReoccurEnd;
        wasSet = true;
        return wasSet;
    }

    public boolean setOwner(Profile aOwner)
    {
        boolean wasSet = false;
        owner = aOwner;
        wasSet = true;
        return wasSet;
    }

    public String getName()
    {
        return name;
    }

    public Date getDeadline()
    {
        return deadline;
    }

    public String getDescription()
    {
        return description;
    }

    public boolean getAssigned()
    {
        return assigned;
    }

    public int getReoccurRate()
    {
        return reoccurRate;
    }

    public Date getReoccurEnd()
    {
        return reoccurEnd;
    }

    public Profile getOwner()
    {
        return owner;
    }

    public boolean isAssigned()
    {
        return assigned;
    }

    public String toString()
    {
        return super.toString() + "["+
                "name" + ":" + getName()+ "," +
                "description" + ":" + getDescription()+ "," +
                "assigned" + ":" + getAssigned()+ "," +
                "reoccurRate" + ":" + getReoccurRate()+ "]" + System.getProperties().getProperty("line.separator") +
                "  " + "deadline" + "=" + (getDeadline() != null ? !getDeadline().equals(this)  ? getDeadline().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
                "  " + "reoccurEnd" + "=" + (getReoccurEnd() != null ? !getReoccurEnd().equals(this)  ? getReoccurEnd().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
                "  " + "owner" + "=" + (getOwner() != null ? !getOwner().equals(this)  ? getOwner().toString().replaceAll("  ","    ") : "this" : "null");
    }

    //End Citation
}
