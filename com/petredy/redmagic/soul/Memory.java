package com.petredy.redmagic.soul;

public class Memory {

	public String name;
	public float progress;
	
	
	public Memory(String name) {
		this.name = name;
		this.progress = 0.0F;
		
	}
	
	public boolean isWellKnown(){
		return progress >= 1.0F;
	}
	
	public void addProgress(Float value){
		this.progress += value;
	}
}
