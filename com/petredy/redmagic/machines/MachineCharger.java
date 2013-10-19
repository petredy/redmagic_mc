package com.petredy.redmagic.machines;

import com.petredy.redmagic.api.machines.IMachineHandler;
import com.petredy.redmagic.lib.Machines;
import com.petredy.redmagic.redenergy.RedEnergy;
import com.petredy.redmagic.redvalue.element.Composition;

public class MachineCharger extends Machine{

	public Composition releaseEnergy = Composition.getStandard(2, 2, 2, 2, 2);
	public float cooldown = 0.2F;
	public MachineCharger(){
		this.metadata = Machines.CHARGER_METADATA;
		this.size = 1;
	}
	
	public void update(IMachineHandler handler) {
		if(handler.getEnergyHandler().use(new RedEnergy(0, 0, 0, releaseEnergy)).composition.isEqual(releaseEnergy)){
			handler.getEnergyHandler().release(handler.getWorld(), new RedEnergy(handler.getWorld().provider.dimensionId, handler.getEnergyHandler().getChunkX(), handler.getEnergyHandler().getChunkZ(), releaseEnergy));
			handler.setHeat(handler.getHeat() - cooldown);
		}
	}
}
