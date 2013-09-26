package com.petredy.redmagic.tileentities;

import java.util.Random;

import buildcraft.api.power.IPowerReceptor;
import buildcraft.api.power.PowerHandler;
import buildcraft.api.power.PowerHandler.PowerReceiver;

import com.petredy.redmagic.Redmagic;
import com.petredy.redmagic.redhole.Hole;
import com.petredy.redmagic.soul.SoulInventory;
import com.petredy.redmagic.utils.LogUtils;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

public class TileEntitySoul extends TileEntity implements IPowerReceptor {
	
	public SoulInventory inventory;
	public PowerHandler handler;
	
	public TileEntitySoul(){
		inventory = new SoulInventory();
		handler = new PowerHandler(this, PowerHandler.Type.STORAGE);
		handler.configure(1, 10000, 1, 10000);
	}

	@Override
	public PowerReceiver getPowerReceiver(ForgeDirection side) {
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
	
	public void readFromNBT(NBTTagCompound tag){
		super.readFromNBT(tag);
		this.handler.readFromNBT(tag);
		this.inventory.readFromNBT(tag);
	}
	
	public void writeToNBT(NBTTagCompound tag){
		super.writeToNBT(tag);
		this.handler.writeToNBT(tag);
		this.inventory.writeToNBT(tag);
	}
	
}
