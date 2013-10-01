package com.petredy.redmagic.entities.ai;

import com.petredy.redmagic.entities.EntitySoulman;
import com.petredy.redmagic.soul.Memory;
import com.petredy.redmagic.soul.MemoryBlock;
import com.petredy.redmagic.soul.MemoryEntity;
import com.petredy.redmagic.soul.MemoryItem;
import com.petredy.redmagic.utils.LogUtils;

import net.minecraft.entity.ai.EntityAIBase;

public class EntityAILearn extends EntityAIBase{

	EntitySoulman man;
	Memory mem;
	public float progress = 0.0F;
	public float interest = 1.0F;
	
	public EntityAILearn(EntitySoulman man){
		this.man = man;
	}
	
	@Override
	public boolean shouldExecute() {
		return man.focus != null && man.focus.getName(man.worldObj) != null;
	}
	
	@Override
	public void startExecuting(){
		this.getMemory();
	}
	
	@Override
	public void updateTask() {
		this.watch();
		this.collectData();
		if(interest <= 0){
			this.resetTask();
		}
		if(mem.isWellKnown()){
			this.resetTask();
		}
	}

	private void getMemory() {
		mem = man.soul.memory.get(this.man.focus.getName(man.worldObj));
		if(mem == null){
			if(this.man.focus.isBlock()){
				mem = new MemoryBlock(this.man.focus.getName(man.worldObj), this.man.focus.block.x, this.man.focus.block.y, this.man.focus.block.z);
			}else if(this.man.focus.isEntity()){
				mem = new MemoryEntity(this.man.focus.getName(man.worldObj), this.man.focus.entity);
			}else if(this.man.focus.isItem()){
				mem = new MemoryItem(this.man.focus.getName(man.worldObj), this.man.getCurrentItemOrArmor(0));
			}
		}
	}

	private boolean isInRange(double x, double y, double z) {
		return man.getDistance(x, y, z) < 2;
	}

	public void resetTask() {
		this.man.focus = null;
		interest = 1.0F;
		this.man.soul.memory.learn(mem);
	}
	
	private void collectData() {
		if(man.focus.isEntity() && isInRange(man.focus.entity.posX, man.focus.entity.posY, man.focus.entity.posZ)){
			if(man.focus.entity.isDead){
				interest -= 1.0f;
			}
			mem.addProgress(0.05f);
		}else if(man.focus.isBlock() && isInRange(man.focus.block.x, man.focus.block.y, man.focus.block.z)){
			if(man.worldObj.getBlockId(this.man.focus.block.x, this.man.focus.block.y, this.man.focus.block.z) == 0){
				interest -= 1.0f;
			}
			mem.addProgress(0.05f);
		}else if(man.focus.isItem()){
			if(man.getCurrentItemOrArmor(0) == null){
				interest -= 1.0f;
			}
			mem.addProgress(0.05f);
		}
		LogUtils.log(interest + "/" + mem.progress);
	}

	private void watch() {
		if(this.man.focus.isEntity()){
			if(!this.man.getNavigator().tryMoveToEntityLiving(this.man.focus.entity, 0.4f)){
				interest -= 0.01F;
			}
		}else if(this.man.focus.isBlock()){
			this.man.getLookHelper().setLookPosition(man.focus.block.x, man.focus.block.y, man.focus.block.z, 0, 50);
			if(!this.man.getNavigator().tryMoveToXYZ(this.man.focus.block.x, this.man.focus.block.y, this.man.focus.block.z, 0.4F)){
				interest -= 0.01F;
			}
		}else if(this.man.focus.isItem()){
			this.man.getLookHelper().setLookPosition(man.posX, man.posY, man.posZ, 5, 55);
		}
	}

}
