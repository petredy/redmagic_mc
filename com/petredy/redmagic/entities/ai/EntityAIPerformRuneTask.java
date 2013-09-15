package com.petredy.redmagic.entities.ai;

import com.petredy.redmagic.entities.EntitySoulman;

import net.minecraft.entity.ai.EntityAIBase;

public class EntityAIPerformRuneTask extends EntityAIBase{

	private EntitySoulman entity;

	public EntityAIPerformRuneTask(EntitySoulman entity){
		this.entity = entity;
	}
	
	@Override
	public boolean shouldExecute() {
		return this.entity.activeRune != null && this.entity.activeRune.pattern != null && this.entity.activeRune.task != null;
	}
	
	
	public void updateTask() {
		this.entity.activeRune.task.update(entity);
	}
	

}
