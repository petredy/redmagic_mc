package com.petredy.redmagic.structure;

import java.util.HashMap;

import com.petredy.redmagic.network.PacketHandler;
import com.petredy.redmagic.network.PacketStructureSync;
import com.petredy.redmagic.utils.LogUtils;

import cpw.mods.fml.common.network.PacketDispatcher;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;

public class StructureManager {
	public static HashMap<Integer, Structure> structures = new HashMap<Integer, Structure>();
	
	public static String TOKEN = "redmagic.structure.";
	
	
	public static int add(World world, Structure structure){
		if(structure.id == 0){
			int id = world.getUniqueDataId(TOKEN);
			if(id <= 0)world.getUniqueDataId(TOKEN);
			structure.id = id;
		}
		structure.setWorld(world);
		structures.put(structure.id, structure);
		return structure.id;
	}
	
	
	public static Structure get(int id){
		return structures.get(id);
	}
	
	public static void readFromNBT(World world, NBTTagCompound tag){
		NBTTagList list = tag.getTagList("structures");
		for(int i = 0; i< list.tagCount(); i++){
			NBTTagCompound structureData = (NBTTagCompound) list.tagAt(i);
			Structure structure = new Structure();
			structure.readFromNBT(structureData);
			structure.setWorld(world);
			structures.put(structure.id, structure);
		}
	}
	
	public static void writeToNBT(NBTTagCompound tag){
		NBTTagList list = new NBTTagList();
		for(Structure structure: structures.values()){
			if(structure != null && structure.id > 0){
				NBTTagCompound data = new NBTTagCompound();
				structure.writeToNBT(data);
				list.appendTag(data);
			}
		}
		tag.setTag("structures", list);
	}
}
