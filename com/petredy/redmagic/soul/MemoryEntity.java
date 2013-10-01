package com.petredy.redmagic.soul;

import net.minecraft.entity.Entity;

public class MemoryEntity extends Memory{

	public Entity entity;
	public boolean canMove;
	public boolean lives;
	
	public MemoryEntity(String name, Entity entity) {
		super(name);
		this.entity = entity;
	}

}
