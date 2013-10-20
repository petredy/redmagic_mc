package com.petredy.redmagic.machines;

import com.petredy.redmagic.api.machines.IMachineHandler;
import com.petredy.redmagic.lib.Machines;
import com.petredy.redmagic.redenergy.RedEnergy;
import com.petredy.redmagic.redvalue.element.Composition;
import com.petredy.redmagic.utils.LogUtils;

public class MachineCharger extends Machine{

	public Composition releaseEnergy;
	public float cooldown = 0.2F;
	public MachineCharger(){
		this.metadata = Machines.CHARGER_METADATA;
		this.size = 1;
		releaseEnergy = Composition.getStandard(2, 2, 2, 2, 0);
	}
	
	public void update(IMachineHandler handler) {
		RedEnergy energy = handler.getEnergyHandler().use(RedEnergy.getFrom(releaseEnergy.copy()));
		if(energy.isEqual(RedEnergy.getFrom(releaseEnergy.copy()))){
			handler.getEnergyHandler().release(handler.getWorld(), new RedEnergy(handler.getWorld().provider.dimensionId, handler.getEnergyHandler().getChunkX(), handler.getEnergyHandler().getChunkZ(), releaseEnergy.copy()));
			handler.setHeat(handler.getHeat() - cooldown);
		}
	}
}
