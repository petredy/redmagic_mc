package com.petredy.redmagic.entities.ai;

import com.petredy.redmagic.entities.EntitySoul;
import com.petredy.redmagic.tileentities.TileEntitySoulBase;

import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.ChunkCoordinates;

public class EntityAIStayAtBase extends EntityAIBase {

	private EntitySoul soul;
	private ChunkCoordinates base = null;

	public EntityAIStayAtBase(EntitySoul soul){
		this.soul = soul;
	}
	
	@Override
	public boolean shouldExecute() {
		return (base != null && soul.worldObj.getBlockTileEntity(base.posX, base.posY, base.posZ) instanceof TileEntitySoulBase && soul.getDistance(base.posX, base.posY, base.posZ) < 2) || findClosestBase() != null;
	}

	private ChunkCoordinates findClosestBase() {
		for(int i = -10; i < 11; i++){
			for(int j = -10; j < 11; j++){
				for(int k = -10; k < 11; k++){
					if(soul.worldObj.getBlockTileEntity((int)soul.posX + i, (int)soul.posY + j, (int)soul.posZ + k) instanceof TileEntitySoulBase){
						base = new ChunkCoordinates((int)soul.posX + i, (int)soul.posY + j, (int)soul.posZ + k);
						return base;
					}
				}
			}
		}
		return null;
	}
	
	public void updateTask() {
		this.soul.getNavigator().tryMoveToXYZ(base.posX, base.posY, base.posZ, 0.3);
	}

}
