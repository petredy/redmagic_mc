package com.petredy.redmagic.utils;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

import com.petredy.redmagic.network.PacketHandler;
import com.petredy.redmagic.network.PacketStructureSync;
import com.petredy.redmagic.structure.Structure;
import com.petredy.redmagic.structure.StructureManager;

import cpw.mods.fml.common.network.PacketDispatcher;

public class StructureUtils {

	public static void loadStructures(World world){
		NBTTagCompound tag = WorldSavedDataUtils.loadData(world, getToken());
		if(tag != null){
			LogUtils.log("Load structures");
			StructureManager.readFromNBT(world, tag);
		}
	}
	
	public static String getToken(){
		return "redmagic.structures";
	}
	
	public static void saveStructure(World world){
		NBTTagCompound tag = new NBTTagCompound();
		StructureManager.writeToNBT(tag);
		LogUtils.log("Save structures");
		LogUtils.log(tag);
		WorldSavedDataUtils.saveData(world, getToken(), tag);
	}
	
}
