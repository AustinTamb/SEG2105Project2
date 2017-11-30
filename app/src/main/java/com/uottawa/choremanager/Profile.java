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
		return (assignedTasks!=null)? assignedTasks.size():0;
	}

	public void setNumberOfTasksCompleted(int num){
		this.numberOfTasksCompleted = num;
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

	public void setAssignedTasks(List<String> assignedTasks) {
		this.assignedTasks = assignedTasks;
	}

	public String getPassword() {
		return password;
	}

	public String getDbId() {
		return dbId;
	}

	public void setDbId(String dbId) {
		this.dbId = dbId;
	}
}