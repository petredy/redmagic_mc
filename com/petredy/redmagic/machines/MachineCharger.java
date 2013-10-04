package com.petredy.redmagic.machines;

import com.petredy.redmagic.api.machines.IMachineHandler;
import com.petredy.redmagic.lib.Machines;

public class MachineCharger extends Machine{

	public float releaseEnergy = 0.2F;
	public float cooldown = 0.2F;
	public MachineCharger(){
		this.metadata = Machines.CHARGER_METADATA;
		this.size = 1;
	}
	
	public void update(IMachineHandler machineHandler) {
		if(machineHandler.getEnergyHandler().use(releaseEnergy) == releaseEnergy){
			machineHandler.getEnergyHandler().release(machineHandler.getWorld(), releaseEnergy, machineHandler.getEnergyHandler().getChunkX(), machineHandler.getEnergyHandler().getChunkZ());
			machineHandler.setHeat(machineHandler.getHeat() - cooldown);
		}
	}
}
