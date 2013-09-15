package com.petredy.redmagic.tasks;

import java.util.HashMap;

import net.minecraft.nbt.NBTTagCompound;

import com.petredy.redmagic.entities.EntitySoulman;

public class Task {

	protected static HashMap<String, Task>tasks = new HashMap<String, Task>();
	
	public static void addTask(Task task){
		tasks.put(task.name, task);
	}
	
	public static Task getTaskForPattern(String name) {
		return tasks.get(name);
	}
	
	public EntitySoulman soulman;
	public String name;
	
	public Task(){
		
	}
	
	public void update(EntitySoulman soulman){
		
	};
	
	public void writeToNBT(NBTTagCompound tag){
		
	}
	
	public void readFromNBT(NBTTagCompound tag){
		
	}

	
	
}
