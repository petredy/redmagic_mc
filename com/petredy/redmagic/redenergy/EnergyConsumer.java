package com.petredy.redmagic.redenergy;

import com.petredy.redmagic.utils.LogUtils;

import net.minecraft.util.Vec3;

public class EnergyConsumer {

	public Integer x, y, z, range;
	
	
	public EnergyConsumer(int x, int y, int z, int range){
		this.x = x;
		this.y = y;
		this.z = z;
		LogUtils.log("Create with range: " + range);
		this.range = range;
	}

	@Override
	public String toString(){
		return "Energy Consumer: [" + x + "/" + y + "/" + z + "] with range of " + range; 
	}

	public boolean isInRangeOf(int xCoord, int yCoord, int zCoord) {
		Vec3 me = Vec3.createVectorHelper(x, yCoord, z);
		Vec3 other = Vec3.createVectorHelper(xCoord, yCoord, zCoord);
		if(me.distanceTo(other) <= this.range)return true;
		return false;
	}
	
}
