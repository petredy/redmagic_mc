package com.petredy.redmagic.redenergy;

import net.minecraft.nbt.NBTTagCompound;

public class RedEnergy {
	
	public float amount;
	public int x, y, z;
	
	public RedEnergy(){
		amount = 0;
	}
	
	public RedEnergy(int x, int y, int z, float amount){
		this.x = x;
		this.y = y;
		this.z = z;
		this.amount = amount;
	}
	
	public void readFromNBT(NBTTagCompound tag){
		this.amount = tag.getFloat("amount");
		this.x = tag.getInteger("x");
		this.y = tag.getInteger("y");
		this.z = tag.getInteger("z");
	}
	
	public static RedEnergy loadFromNBT(NBTTagCompound tag){
		RedEnergy energy = new RedEnergy();
		energy.readFromNBT(tag);
		return energy;
	}
	
	public void writeToNBT(NBTTagCompound tag){
		tag.setFloat("amount", amount);
		tag.setInteger("x", x);
		tag.setInteger("y", y);
		tag.setInteger("z", z);
	}
}
