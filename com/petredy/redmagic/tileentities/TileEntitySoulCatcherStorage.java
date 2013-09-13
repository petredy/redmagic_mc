package com.petredy.redmagic.tileentities;

import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import buildcraft.api.power.PowerHandler;
import buildcraft.api.power.PowerHandler.PowerReceiver;

import com.petredy.redmagic.structures.soulcatcher.StructureSoulCatcher;
import com.petredy.redmagic.utils.LogUtils;
import com.petredy.redmagic.utils.WorldSavedDataUtils;

public class TileEntitySoulCatcherStorage extends TileEntitySoulCatcher {

	public PowerHandler handler;
	public TileEntitySoulCatcherStorage(){
		handler = new PowerHandler(this, PowerHandler.Type.MACHINE);
		handler.configure(50, 1000, 100, 15000);
		
	}
	
	/**
	 * IPowerReceptor
	 */
	
	@Override
	public PowerReceiver getPowerReceiver(ForgeDirection side) {
		return handler.getPowerReceiver();
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
