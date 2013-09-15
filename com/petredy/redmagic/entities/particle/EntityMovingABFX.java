package com.petredy.redmagic.entities.particle;

import net.minecraft.world.World;

public class EntityMovingABFX extends EntityCustomFX{

	public double targetX, targetY, targetZ;
	
	public EntityMovingABFX(World world, double x, double y, double z, double targetX, double targetY, double targetZ) {
		super(world, x, y, z);
		this.targetX = targetX;
		this.targetY = targetY;
		this.targetZ = targetZ;
	}
	
	public double getDistance(){
		return this.getDistance(targetX, targetY, targetZ);
	}
	
	public void onUpdate()
    {
		super.onUpdate();
		double distance = this.getDistance();
		if(distance == 0)distance = 1;
		this.motionX = (this.targetX - this.lastTickPosX) / distance;
		this.motionY = (this.targetY - this.lastTickPosY) / distance;
		this.motionZ = (this.targetZ - this.lastTickPosZ) / distance;
    }

}
