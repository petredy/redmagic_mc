package com.petredy.redmagic.machines;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;

import com.petredy.redmagic.api.machinery.IMachineHandler;
import com.petredy.redmagic.items.Items;
import com.petredy.redmagic.lib.BlockIndex;
import com.petredy.redmagic.lib.Machines;
import com.petredy.redmagic.lib.Reference;
import com.petredy.redmagic.machinery.Tribological;
import com.petredy.redmagic.redenergy.RedEnergy;
import com.petredy.redmagic.redvalue.element.Composition;
import com.petredy.redmagic.utils.LogUtils;

public class MachineCollector extends Machine{

	public static Composition production;
	public static float MODIFIER_LARGE_MACHINE_PRODUCTION = 1.5f;
	public static float MODIFIER_LARGE_MACHINE_HEAT = 0.8f;
	
	public MachineCollector(){
		this.metadata = Machines.COLLECTOR_METADATA;
		this.size = 1;
		this.name = Machines.COLLECTOR_NAME;
		this.production = Composition.getStandard(20f, 20f, 20f, 20f, 0.0f);
		this.tribological = new Tribological(new ItemStack[]{
			new ItemStack(Items.plateRhenium), new ItemStack(Items.logicStorage), new ItemStack(Items.plateRhenium),
			new ItemStack(Items.logicStorage), new ItemStack(Items.logicCore), new ItemStack(Items.logicStorage),
			new ItemStack(Items.plateRhenium), new ItemStack(Items.logicStorage), new ItemStack(Items.plateRhenium)
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
		Composition comp = production.copy();
		comp.multiply((float)tribological.getStatus() / 100f);
		if(handler.getHandlerSize() > 1)comp.multiply(1 + MODIFIER_LARGE_MACHINE_PRODUCTION);
		RedEnergy collected = handler.getEnergyHandler().collect(handler.getWorld(), new RedEnergy(handler.getWorld().provider.dimensionId, handler.getEnergyHandler().getChunkX(), handler.getEnergyHandler().getChunkZ(), comp));
		if(collected.composition.getRedvalue() > 0){
			tribological.damage(1f);
		}
		float heatUp = collected.composition.getRedvalue() / 100 * 5;
		heatUp *= MODIFIER_LARGE_MACHINE_HEAT;
		handler.setHeat(handler.getHeat() + heatUp);
	}
	
}
