package com.petredy.redmagic.machines;

import com.petredy.redmagic.api.machines.IMachineHandler;
import com.petredy.redmagic.lib.Elements;
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
		releaseEnergy = Composition.getStandard(0.5f, 0.5f, 0.5f, 0.5f, 0);
	}
	
	public void update(IMachineHandler handler) {
		Float earth = handler.getEnergyHandler().use(Elements.EARTH, 2);
		Float air = handler.getEnergyHandler().use(Elements.AIR, 2);
		Float water = handler.getEnergyHandler().use(Elements.WATER, 2);
		Float fire = handler.getEnergyHandler().use(Elements.FIRE, 2);
		handler.getEnergyHandler().release(handler.getWorld(), new RedEnergy(handler.getWorld().provider.dimensionId, handler.getEnergyHandler().getChunkX(), handler.getEnergyHandler().getChunkZ(), Composition.getStandard(earth, air, water, fire, 0)));
		if(earth + air + water + fire > 0){
			handler.setHeat(handler.getHeat() - cooldown);
		}
	}
}
