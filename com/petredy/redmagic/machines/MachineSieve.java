package com.petredy.redmagic.machines;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import com.petredy.redmagic.Redmagic;
import com.petredy.redmagic.api.machines.IMachineHandler;
import com.petredy.redmagic.lib.Guis;
import com.petredy.redmagic.lib.Machines;
import com.petredy.redmagic.utils.InventoryUtils;

public class MachineSieve extends Machine{

	
	public InventoryBasic inventory;
	public ItemStack item;
	
	public MachineSieve(){
		metadata = Machines.SIEVE_METADATA;
		size = 1;
		this.inventory = new InventoryBasic(Machines.SIEVE_NAME, false, 18);
	}
	
	public void activate(IMachineHandler handler, EntityPlayer player, float offX, float offY, float offZ) {
		player.openGui(Redmagic.instance, Guis.SIEVE, player.worldObj, handler.getXCoord(), handler.getYCoord(), handler.getZCoord());
	}
	
	public void remove(IMachineHandler handler) {
		if(!handler.getWorld().isRemote){
			InventoryUtils.dropInventory(inventory, handler.getWorld(), handler.getXCoord(), handler.getYCoord(), handler.getZCoord());
			if(item != null)InventoryUtils.dropItemStack(item, handler.getWorld(), handler.getXCoord(), handler.getYCoord(), handler.getZCoord());
		}
		super.remove(handler);
	}
	
	public void readFromNBT(NBTTagCompound tag){
		super.readFromNBT(tag);
		this.item = ItemStack.loadItemStackFromNBT(tag);
		InventoryUtils.readFromNBT(inventory, tag);
	}
	
	public void writeToNBT(NBTTagCompound tag){
		super.writeToNBT(tag);
		if(item != null)item.writeToNBT(tag);
		InventoryUtils.writeToNBT(inventory, tag);
	}
	
}
