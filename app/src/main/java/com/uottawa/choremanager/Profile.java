package com.uottawa.choremanager;

import java.util.ArrayList;
import java.util.Date;
import java.util.Calendar;
import java.util.List;

public class Profile{
	private boolean isParent;
	private String name;
	private String password;
	private int numberOfTasksCompleted;
	private List<String> assignedTasks;
	private String dbId;

	public Task createTask(String name, Date startDate, Boolean recur, int recurRate, Date endDate, Profile owner){
		

		if(owner!=null){
			//set task owner to owner
			//add task to owners assignedTasks
		}

		return null;
	}
	public Profile(){

	}

	public Profile(String name, boolean isParent, String password/*Add image*/){
		this.isParent = isParent;
		this.name = name;
		this.password = password;
		this.numberOfTasksCompleted = 0;
		this.assignedTasks = new ArrayList<String>();
	}

	public void addTask(String id){
		assignedTasks.add(id);
	}

	public void removeTask(String id){
		if(assignedTasks.contains(id)){
			assignedTasks.remove(id);
		}
	}

	public void setName(String newName){
		this.name = newName;
	}

	public String getName(){
		return name;
	}

	public void setParent(Boolean isParent){
		this.isParent = isParent;
	}

	public Boolean isParent(){
		return isParent;
	}

	public int getNumberOfCurrentTasks(){
		return assignedTasks.size();
	}


	public int getNumberOfTasksCompleted(){
		return numberOfTasksCompleted;
	}
	public void setPassword(String password){
		this.password = password;
	}

	public void setId(String id){
		this.dbId = id;
	}

	public String getId(){
		return dbId;
	}

	public List<String> getAssignedTasks(){
		return assignedTasks;
	}
	public Boolean validatePassword(String passEnt){
		return(password == passEnt);
	}
}