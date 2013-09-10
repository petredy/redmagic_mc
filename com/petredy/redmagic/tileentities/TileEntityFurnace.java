package com.petredy.redmagic.tileentities;

import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import buildcraft.api.power.IPowerReceptor;
import buildcraft.api.power.PowerHandler;
import buildcraft.api.power.PowerHandler.PowerReceiver;

import com.petredy.redmagic.lib.BlockIndex;

public class TileEntityFurnace extends TileEntityInventory implements IPowerReceptor{

	public PowerHandler handler;
	
	public TileEntityFurnace() {
		super(BlockIndex.FURNACE_NAME, 2);
		this.handler = new PowerHandler(this, PowerHandler.Type.MACHINE);
		this.handler.configure(5, 100, 5, 1500);
	}

	@Override
	public PowerReceiver getPowerReceiver(ForgeDirection side) {
		if(this.handler.getEnergyStored() < this.handler.getMaxEnergyStored()){
			return this.handler.getPowerReceiver();
		}
		return null;
	}

	@Override
	public void doWork(PowerHandler workProvider) {
		workProvider.update();
	}

	@Override
	public World getWorld() {
		return worldObj;
	}

}
