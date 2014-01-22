package com.petredy.redmagic.utils;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.WorldSavedData;

public class WorldSavedDataUtils {

	
	public static int createUniqueToken(World world, String prefix){
		return world.getUniqueDataId(prefix);
	}
	
	public static void saveData(World world, String token, NBTTagCompound data){
		CustomWorldSavedData worldData = new CustomWorldSavedData(token);
		worldData.data = data;
		worldData.markDirty();
		world.setItemData(token, worldData);
		world.perWorldStorage.saveAllData();
	}
	
	public static NBTTagCompound loadData(World world, String token){
		CustomWorldSavedData worldData = (CustomWorldSavedData) world.loadItemData(CustomWorldSavedData.class, token);
		return worldData != null ? worldData.data : null;
	}
	
	
	public static class CustomWorldSavedData extends WorldSavedData{

		public NBTTagCompound data;
		
		public CustomWorldSavedData(String par1Str) {
			super(par1Str);
		}

		@Override
		public void readFromNBT(NBTTagCompound nbttagcompound) {
			data = nbttagcompound.getCompoundTag("data");
		}

		@Override
		public void writeToNBT(NBTTagCompound nbttagcompound) {
			nbttagcompound.setTag("data", data);
		}
		
		
	}
}
