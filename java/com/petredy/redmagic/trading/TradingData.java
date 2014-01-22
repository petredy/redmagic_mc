package com.petredy.redmagic.trading;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.WorldSavedData;

public class TradingData extends WorldSavedData{

	public NBTTagCompound data;
	
	public TradingData(String par1Str) {
		super(par1Str);
	}

	@Override
	public void readFromNBT(NBTTagCompound nbttagcompound) {
		data = (NBTTagCompound) nbttagcompound.getTag("data");
	}

	@Override
	public void writeToNBT(NBTTagCompound nbttagcompound) {
		nbttagcompound.setTag("data", data);
	}

}
