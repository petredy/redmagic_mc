package com.petredy.redmagic.api.machines;

import net.minecraft.world.World;

import com.petredy.redmagic.api.redenergy.IEnergyHandler;
import com.petredy.redmagic.machines.Machine;

public interface IMachineHandler {

	public Machine getMachineOnSide(int i);
	
	public Machine getMachine(int metadata);
	
	public IEnergyHandler getEnergyHandler();
	
	public float getHeat();
	
	public int getSize();
	
	public void setHeat(float heat);
	
	public World getWorld();
	
	public int getXCoord();
	public int getYCoord();
	public int getZCoord();
}
