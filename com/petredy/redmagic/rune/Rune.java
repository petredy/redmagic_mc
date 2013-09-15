package com.petredy.redmagic.rune;

import com.petredy.redmagic.tasks.Task;
import com.petredy.redmagic.utils.LogUtils;

public class Rune {

	public int x, y, z;
	public Pattern pattern;
	public Task task;
	
	public Rune(int x, int y, int z){
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public void setPattern(String name){
		this.pattern = Pattern.getPattern(name);
		this.task = Task.getTaskForPattern(name);
		LogUtils.log(this.task);
	}
	
}
