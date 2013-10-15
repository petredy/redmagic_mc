package com.petredy.redmagic.utils;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

import com.petredy.redmagic.network.PacketHandler;
import com.petredy.redmagic.network.PacketStructureSync;
import com.petredy.redmagic.structure.Structure;

import cpw.mods.fml.common.network.PacketDispatcher;

public class StructureUtils {

	public static Structure loadStructure(World world, int id){
		Structure structure = new Structure();
		structure.setWorld(world);
		NBTTagCompound tag = WorldSavedDataUtils.loadData(world, getTokenPrefix() + id);
		if(tag == null)return null;
		structure.readFromNBT(tag);
		return structure;
	}
	
	public static String getTokenPrefix(){
		return "redmagic.structure.";
	}
	
	public static void saveStructure(World world, Structure structure){
		if(structure != null){
			NBTTagCompound tag = new NBTTagCompound();
			structure.writeToNBT(tag);
			WorldSavedDataUtils.saveData(world, getTokenPrefix() + structure.id, tag);
		}
	}
	
}
