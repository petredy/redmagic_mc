package com.petredy.redmagic.machines;

import com.petredy.redmagic.api.machines.IMachineHandler;
import com.petredy.redmagic.lib.BlockIndex;
import com.petredy.redmagic.lib.Machines;
import com.petredy.redmagic.utils.LogUtils;

public class MachineCollector extends Machine{

	public static final float production = 0.1f;
	
	public MachineCollector(){
		this.metadata = Machines.COLLECTOR_METADATA;
	}
	
	public void update(IMachineHandler machineHandler) {
		float collected = machineHandler.getEnergyHandler().collect(machineHandler.getWorld(), production, machineHandler.getEnergyHandler().getChunkX(), machineHandler.getEnergyHandler().getChunkZ());
		float heatUp = collected / 100 * 5;
		machineHandler.setHeat(machineHandler.getHeat() + heatUp);
	}
	
}
