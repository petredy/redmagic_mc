package com.petredy.redmagic.entities.ai;

import java.util.List;
import java.util.Random;


import com.petredy.redmagic.entities.EntitySoulman;
import com.petredy.redmagic.utils.LogUtils;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.util.Vec3;

public class EntityAIFocus extends EntityAIBase{

	EntitySoulman man;
	public Random rand = new Random();
	
	public EntityAIFocus(EntitySoulman man){
		this.man = man;
	}
	
	@Override
	public boolean shouldExecute() {
		return man.focus == null || (man.focus.getName(man.worldObj) == null);
	}
	
	@Override
	public void startExecuting(){
		if(focusOnItem())return;
		if(focusOnEntity())return;
	}
	
	public void updateTask() {
		if(focusOnItem())return;
		if(focusOnBlock())return;		
	}
	
	public boolean focusOnItem(){
		if(man.getCurrentItemOrArmor(0) != null && !man.soul.memory.knows(man.getCurrentItemOrArmor(0).getUnlocalizedName())){
			man.focus = new FocusTarget(man.getCurrentItemOrArmor(0));
			return true;
		}
		return false;
	}
	
	public boolean focusOnEntity(){
		List<Entity> entities = man.worldObj.getEntitiesWithinAABB(Entity.class, this.man.boundingBox.expand(5.0, 1.0, 5.0));
		for(Entity entity: entities){
			if(!man.soul.memory.knows(EntityList.getEntityString(entity))){
				man.focus = new FocusTarget(entity);
				return true;
			}
		}
		return false;
	}
	
	public boolean focusOnBlock(){
		Vec3 vec = RandomPositionGenerator.findRandomTarget(man, 10, 2);
		Block block = Block.blocksList[man.worldObj.getBlockId((int)man.posX + (int)vec.xCoord, (int)man.posY + (int)vec.yCoord, (int)man.posZ + (int)vec.zCoord)];
		if(block != null  && !man.soul.memory.knows(block.getUnlocalizedName())){
			man.focus = new FocusTarget((int)man.posX + (int)vec.xCoord, (int)man.posY + (int)vec.yCoord, (int)man.posZ + (int)vec.zCoord);
			return true;
		}
		return false;
	}

}
