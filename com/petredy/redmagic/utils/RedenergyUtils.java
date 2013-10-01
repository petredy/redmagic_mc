package com.petredy.redmagic.utils;

import com.petredy.redmagic.network.PacketEnergyMapSync;
import com.petredy.redmagic.network.PacketHandler;
import com.petredy.redmagic.redenergy.EnergyMap;

import cpw.mods.fml.common.network.PacketDispatcher;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class RedenergyUtils {

	public static void load(World world){
		if(!EnergyMap.loaded){
			NBTTagCompound energyTag = WorldSavedDataUtils.loadData(world, "redmagic.energy");
			if(energyTag != null){
				LogUtils.log("Load EnergyMap");
				EnergyMap.loaded = true;
				EnergyMap.readFromNBT(energyTag);
				//PacketDispatcher.sendPacketToAllPlayers(PacketHandler.populatePacket(new PacketEnergyMapSync(energyTag)));
			}
		}
	}
	
	public static void save(World world){
		NBTTagCompound energyTag = new NBTTagCompound();
		LogUtils.log("Save EnergyMap");
		EnergyMap.writeToNBT(energyTag);
		WorldSavedDataUtils.saveData(world, "redmagic.energy", energyTag);
	}
	
	
}
