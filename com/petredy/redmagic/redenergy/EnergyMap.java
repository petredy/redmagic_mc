package com.petredy.redmagic.redenergy;

import java.util.ArrayList;
import java.util.List;

public class EnergyMap {

	public static List<EnergyConsumer> consumers = new ArrayList<EnergyConsumer>();
	
	public static void registerConsumer(int x, int y, int z, int range){
		if(!existsConsumer(x, y, z))consumers.add(new EnergyConsumer(x, y, z, range));
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
			if(consumer.x == xCoord && consumer.y == yCoord && consumer.z == zCoord){
				consumers.remove(consumer);
				break;
			}
		}
	}
	
}
