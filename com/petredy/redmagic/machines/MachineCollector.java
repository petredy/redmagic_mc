package com.petredy.redmagic.machines;

import com.petredy.redmagic.api.machines.IMachineHandler;
import com.petredy.redmagic.lib.BlockIndex;
import com.petredy.redmagic.lib.Machines;
import com.petredy.redmagic.redenergy.RedEnergy;
import com.petredy.redmagic.redvalue.element.Composition;
import com.petredy.redmagic.utils.LogUtils;

public class MachineCollector extends Machine{

	public static Composition production;
	
	public MachineCollector(){
		this.metadata = Machines.COLLECTOR_METADATA;
		this.size = 1;
		this.production = Composition.getStandard(0.5f, 0.5f, 0.5f, 0.5f, 0.0f);
	}
	
	public void update(IMachineHandler machineHandler) {
		RedEnergy collected = machineHandler.getEnergyHandler().collect(machineHandler.getWorld(), new RedEnergy(machineHandler.getWorld().provider.dimensionId, machineHandler.getEnergyHandler().getChunkX(), machineHandler.getEnergyHandler().getChunkZ(), production.copy()));
		float heatUp = collected.composition.getRedvalue() / 100 * 5;
		machineHandler.setHeat(machineHandler.getHeat() + heatUp);
	}
	
}
