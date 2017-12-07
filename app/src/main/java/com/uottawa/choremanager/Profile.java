package com.uottawa.choremanager;

import java.util.ArrayList;
import java.util.List;

public class Profile{
	private boolean isParent;
	private String name;
	private String password;
	private int numberOfTasksCompleted;
	private List<String> assignedTasks;
	private String id;
	private int numberOfCurrentTasks;

	public Profile(){

	}

	public Profile(String name, boolean isParent, String password/*Add image*/){
		this.isParent = isParent;
		this.name = name;
		this.password = password;
		this.numberOfTasksCompleted = 0;
		this.assignedTasks = new ArrayList<String>();
		this.numberOfCurrentTasks = 0;
	}

	public void addTask(String id){
		if(assignedTasks== null){
			this.assignedTasks = new ArrayList<String>();
		}
		assignedTasks.add(id);
		numberOfCurrentTasks++;
	}

	public void removeTask(String id){
		if(assignedTasks != null) {
			assignedTasks.remove(id);
			numberOfCurrentTasks--;
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
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public List<String> getAssignedTasks(){
		return assignedTasks;
	}
	public Boolean validatePassword(String passEnt){
		return(password.equals(passEnt));
	}

	public void setAssignedTasks(ArrayList<String> assignedTasks) {
		this.assignedTasks = assignedTasks;
	}

	public String getPassword() {
		return password;
	}

	public int getNumberOfCurrentTasks(){
		return numberOfCurrentTasks;
	}

	public void setNumberOfCurrentTasks(int nOCT){
		numberOfCurrentTasks = nOCT;
	}
}