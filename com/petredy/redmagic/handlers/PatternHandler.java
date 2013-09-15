package com.petredy.redmagic.handlers;

import com.petredy.redmagic.lib.Patterns;
import com.petredy.redmagic.rune.Pattern;
import com.petredy.redmagic.tasks.Task;
import com.petredy.redmagic.tasks.TaskStay;

public class PatternHandler {

	public static void init(){
		
		Pattern.register(Patterns.STOP, Pattern.create(new int[]{
			0,-1,-1,0,
			-1,0,0,-1,
			-1,0,0,-1,
			0,-1,-1,0
		}, true));
		
		Task.addTask(new TaskStay());
		
	}
	
}
