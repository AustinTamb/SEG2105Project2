package com.uottawa.choremanager;

import java.util.ArrayList;
import java.util.Date;
import java.util.Calendar;

public class Profile{
	private boolean isParent;
	private String name;
	private String password;
	private ArrayList<Task> assignedTasks;

	public Task createTask(String name, Date startDate, Boolean recur, int recurRate, Date endDate, Profile owner){
		

		if(owner!=null){
			//set task owner to owner
			//add task to owners assignedTasks
		}

		return null;
	}

	public Profile(String name, boolean isParent, String password/*Add image*/){
		this.isParent = isParent;
		this.name = name;
		this.password = password;
	}

	public void takeTask(Task toTake){
		if(!toTake.getDone()){
			Profile oldOwner = toTake.getOwner();
			if(oldOwner != null)
				oldOwner.removeTask(toTake);
			
			assignedTasks.add(toTake);
			toTake.setOwner(this);
		}

	}

	public void removeTask(Task toTake){
		if(assignedTasks.contains(toTake)){
			assignedTasks.remove(toTake);
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

	public void setPassword(String password){
		this.password = password;
	}

	public String getPassword(){
		return password;
	}

}