package com.petredy.redmagic.redenergy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.petredy.redmagic.utils.LogUtils;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class EnergyMap {

	public static boolean loaded = false;
	
	public static List<EnergyConsumer> consumers = new ArrayList<EnergyConsumer>();
	public static HashMap<Redkey, RedEnergy> energy = new HashMap<Redkey, RedEnergy>();
	
	public static void registerConsumer(int x, int y, int z, int range){
		if(!existsConsumer(x, y, z))consumers.add(new EnergyConsumer(x, y, z, range));
		else overwriteConsumer(new EnergyConsumer(x, y, z, range));
	}
	
	private static void overwriteConsumer(EnergyConsumer energyConsumer) {
		for(EnergyConsumer consumer: consumers){
			if(consumer.x == energyConsumer.x && consumer.y == energyConsumer.y && consumer.z == energyConsumer.z)consumer = energyConsumer;
		}
	}

	public static boolean existsConsumer(int x, int y, int z){
		for(EnergyConsumer consumer: consumers){
			if(consumer.x == x && consumer.y == y && consumer.z == z)return true;
		}
		return false;
	}
	
	public static List<EnergyConsumer> getConsumerInNearOf(int x, int y, int z){
		List<EnergyConsumer> rtn = new ArrayList<EnergyConsumer>();
 		for(EnergyConsumer consumer: consumers){
			if(consumer.isInRangeOf(x, y, z))rtn.add(consumer);
		}
 		return rtn;
	}

	public static void removeConsumer(int xCoord, int yCoord, int zCoord) {
		for(EnergyConsumer consumer: consumers){
			if(consumer.x == xCoord && consumer.z == zCoord){
				consumers.remove(consumer);
				break;
			}
		}
	}
	
	public static void addEnergy(RedEnergy energy){
		RedEnergy en = getEnergy(Redkey.get(energy.dimension, energy.x, energy.z));
		en.amount += energy.amount;
		setEnergy(en);
	}
	
	public static RedEnergy getEnergy(Redkey key){
		return EnergyMap.energy.get(key);
	}
	
	public static void setEnergy(RedEnergy energy) {
		EnergyMap.energy.put(Redkey.get(energy.dimension, energy.x,  energy.z), energy);
	}
	
	
	public static void readFromNBT(NBTTagCompound tag){
		NBTTagList list = tag.getTagList("energy");
		if(list != null && list.tagCount() > 0){
			for(int i = 0; i < list.tagCount(); i++){
				NBTTagCompound energyTag = (NBTTagCompound) list.tagAt(i);
				RedEnergy en = RedEnergy.loadFromNBT(energyTag);
				energy.put(Redkey.get(en.dimension, en.x, en.z), en);
			}
		}
	}
	
	public static void writeToNBT(NBTTagCompound tag){
		NBTTagList list = new NBTTagList();
		long count = 0;
		try{
			for(RedEnergy en: energy.values()){
				if(en != null && en.amount > 0){
					NBTTagCompound energyTag = new NBTTagCompound();
					en.writeToNBT(energyTag);
					list.appendTag(energyTag);
					count++;
				}
			}
		}catch(Exception e){
			LogUtils.log("Error by saving EnergyMap:");
			LogUtils.log("Saved "  + count + " of " + energy.size() + " chunks.");
			//e.printStackTrace();
		}
		tag.setTag("energy", list);
	}

	
}
