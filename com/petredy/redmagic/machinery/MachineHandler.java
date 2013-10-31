package com.petredy.redmagic.machinery;

import net.minecraftforge.event.ForgeSubscribe;

import com.petredy.redmagic.machines.*;
import com.petredy.redmagic.utils.LogUtils;

public class MachineHandler {

	protected static Class[] machines = new Class[]{
		Machine.class,
		MachineCollector.class,
		MachineContactCooling.class,
		MachineFurnace.class,
		MachineDisintegrator.class,
		MachineCharger.class,
		MachineRefrigerator.class,
		MachineFreezer.class,
		MachineCompactor.class,
		MachineRecycler.class,
		MachineSieve.class,
		MachineCrystalizer.class
	};
	
	public static int getMachineAmount(){
		return machines.length;
	}
	
	public static Machine getMachine(int index){
		try{
			return (Machine) machines[index].newInstance();
		}catch(Exception e){
			LogUtils.log("Couldn't create machine for index " + index);
			return null;
		}
	}
	
}
