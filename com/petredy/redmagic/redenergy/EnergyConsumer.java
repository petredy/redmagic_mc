package com.petredy.redmagic.redenergy;

import net.minecraft.util.Vec3;

public class EnergyConsumer {

	public int x, y, z, range;
	
	
	public EnergyConsumer(int x, int y, int z, int range){
		this.x = x;
		this.y = y;
		this.z = z;
		this.range = range;
	}


	public boolean isInRangeOf(int xCoord, int yCoord, int zCoord) {
		Vec3 me = Vec3.createVectorHelper(x, yCoord, z);
		Vec3 other = Vec3.createVectorHelper(xCoord, yCoord, zCoord);
		if(me.distanceTo(other) <= range)return true;
		return false;
	}
	
}
