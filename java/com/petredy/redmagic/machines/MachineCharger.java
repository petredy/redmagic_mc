package com.petredy.redmagic.machines;

import net.minecraft.item.ItemStack;

import com.petredy.redmagic.api.machinery.IMachineHandler;
import com.petredy.redmagic.machinery.Tribological;
import com.petredy.redmagic.items.Items;
import com.petredy.redmagic.lib.Elements;
import com.petredy.redmagic.lib.Machines;
import com.petredy.redmagic.redenergy.RedEnergy;
import com.petredy.redmagic.redvalue.element.Composition;
import com.petredy.redmagic.utils.LogUtils;

public class MachineCharger extends Machine{

	public Composition releaseEnergy;
	public static float LARGE_MACHINE_COOLDOWN = 0.4F;
	public static float MACHINE_COOLDOWN = 0.2f;
	public MachineCharger(){
		this.metadata = Machines.CHARGER_METADATA;
		this.size = 1;
		this.name = Machines.CHARGER_NAME;
		releaseEnergy = Composition.getStandard(200f, 200f, 200f, 200f, 0);
		this.tribological = new Tribological(new ItemStack[]{
				new ItemStack(Items.plateRhenium), new ItemStack(Items.logicStorage), new ItemStack(Items.plateRhenium),
				new ItemStack(Items.filterDevice), new ItemStack(Items.logicCore), new ItemStack(Items.filterDevice),
				new ItemStack(Items.plateRhenium), new ItemStack(Items.filterDevice),  new ItemStack(Items.plateRhenium)
		});
	}
	
	public boolean canPlacedOnSide(int side, int size){
		switch(size){
		case 1: return true;
		case 18: return side == 1;
		default: return false;
		}
	}
	
	public void update(IMachineHandler handler) {
		Float earth = handler.getEnergyHandler().use(Elements.EARTH, 2) * tribological.getStatus() / 100;
		Float air = handler.getEnergyHandler().use(Elements.AIR, 2) * tribological.getStatus() / 100;
		Float water = handler.getEnergyHandler().use(Elements.WATER, 2) * tribological.getStatus() / 100;
		Float fire = handler.getEnergyHandler().use(Elements.FIRE, 2) * tribological.getStatus() / 100;
		handler.getEnergyHandler().release(handler.getWorld(), new RedEnergy(handler.getWorld().provider.dimensionId, handler.getEnergyHandler().getChunkX(), handler.getEnergyHandler().getChunkZ(), Composition.getStandard(earth, air, water, fire, 0)));
		if(earth + air + water + fire > 0){
			if(handler.getHandlerSize() > 1)handler.setHeat(handler.getHeat() - LARGE_MACHINE_COOLDOWN);
			else handler.setHeat(handler.getHeat() - MACHINE_COOLDOWN);
			tribological.damage(1f);
		}
	}
}
