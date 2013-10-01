package com.petredy.redmagic.redenergy;

import net.minecraft.nbt.NBTTagCompound;

public class RedEnergy {
	
	public float amount;
	public int x, z, dimension;
	
	public RedEnergy(){
		amount = 0;
	}
	
	public RedEnergy(int dimension, int x, int z, float amount){
		this.dimension = dimension;
		this.x = x;
		this.z = z;
		this.amount = amount;
	}
	
	public void readFromNBT(NBTTagCompound tag){
		this.amount = tag.getFloat("amount");
		this.dimension = tag.getInteger("dimension");
		this.x = tag.getInteger("x");
		this.z = tag.getInteger("z");
	}
	
	public static RedEnergy loadFromNBT(NBTTagCompound tag){
		RedEnergy energy = new RedEnergy();
		energy.readFromNBT(tag);
		return energy;
	}
	
	public void writeToNBT(NBTTagCompound tag){
		tag.setFloat("amount", amount);
		tag.setInteger("dimension", dimension);
		tag.setInteger("x", x);
		tag.setInteger("z", z);
	}
}
