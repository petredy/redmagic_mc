package com.petredy.redmagic.forge.wrapper;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class TileEntityWrapper extends TileEntity{

	public World getWorld(){
		return this.field_145850_b;
	}
	
	public int getX(){
		return this.field_145851_c;
	}
	
	public int getY(){
		return this.field_145848_d;
	}
	
	public int getZ(){
		return this.field_145849_e;
	}
	
	public void readFromNBT(NBTTagCompound tag){
		super.func_145841_b(tag);
	}
	
	public void writeToNBT(NBTTagCompound tag){
		super.func_145839_a(tag);
	}
	
	
	
}
