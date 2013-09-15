package com.petredy.redmagic.entities.ai;

import com.petredy.redmagic.entities.EntitySoulman;
import com.petredy.redmagic.rune.Rune;
import com.petredy.redmagic.tileentities.TileEntityRune;
import com.petredy.redmagic.utils.LogUtils;

import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.util.Vec3;

public class EntityAISearchRune extends EntityAIBase{

	public EntitySoulman entity;
	public int x, y, z;
	
	public EntityAISearchRune(EntitySoulman entity){
		this.entity = entity;
	}
	
	@Override
	public boolean shouldExecute() {
		return entity.activeRune == null;
	}
	
	@Override
	public void updateTask(){
		Vec3 vec = RandomPositionGenerator.findRandomTarget(entity, 3, 1);
		LogUtils.log(vec);
		entity.getLookHelper().setLookPosition(vec.xCoord, vec.yCoord, vec.zCoord, 0F, 0F);
		if(isRune((int)vec.xCoord, (int)vec.yCoord, (int)vec.zCoord)){
			String name = ((TileEntityRune)entity.worldObj.getBlockTileEntity((int)vec.xCoord, (int)vec.yCoord, (int)vec.zCoord)).getPatternName();
			if(name != null){
				entity.activeRune = new Rune((int)vec.xCoord, (int)vec.yCoord, (int)vec.zCoord);
				entity.activeRune.setPattern(name);
			}
		}
	}
	
	public boolean isRune(int x, int y, int z){
		if(entity.worldObj.getBlockTileEntity(x, y, z) instanceof TileEntityRune)return true;
		return false;
	}

}
