package com.petredy.redmagic.api.machinery;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import com.petredy.redmagic.api.redenergy.IEnergyHandler;
import com.petredy.redmagic.machines.Machine;
import com.petredy.redmagic.utils.BlockUtils.VirtualBlock;

public interface IMachineHandler {

	public Machine getMachineOnSide(int i);
	
	public IEnergyHandler getEnergyHandler();
	
	public boolean addMachine(int metadata, int side, EntityPlayer player);
	
	public boolean removeMachine(int side, EntityPlayer player);
	
	public float getHeat();
	
	public int getUsedSize();
	
	public int getHandlerSize();
	
	public void setHeat(float heat);
	
	public World getWorld();
	
	public int getXCoord();
	public int getYCoord();
	public int getZCoord();
	
	public VirtualBlock getBlockInfrontMachineOnSide(int side);
}
