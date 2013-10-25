package com.petredy.redmagic.machines;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.petredy.redmagic.Redmagic;
import com.petredy.redmagic.api.machines.IMachineHandler;
import com.petredy.redmagic.lib.Guis;
import com.petredy.redmagic.lib.Machines;
import com.petredy.redmagic.utils.InventoryUtils;
import com.petredy.redmagic.utils.LogUtils;

public class MachineFreezer extends Machine{

	public InventoryBasic inventory;
	public ItemStack item;
	
	public int tick;
	public int neededTicks = 333;
	
	public MachineFreezer(){
		this.metadata = Machines.FREEZER_METADATA;
		this.size = 1;
		this.inventory = new InventoryBasic(Machines.FREEZER_NAME, false, 2);
	}
	
	
	public void update(IMachineHandler handler) {
		if(item != null){
			if(tick < neededTicks){
				tick++;
				handler.setHeat(handler.getHeat() - (getCooling(item) / neededTicks));
			}else{
				tick = 0;
				item = null;
			}
		}else{
			if(inventory.getStackInSlot(0) != null && getCooling(inventory.getStackInSlot(0)) > 0){
				item = inventory.getStackInSlot(0).copy();
				item.stackSize = 1;
				inventory.decrStackSize(0, 1);
			}else if(inventory.getStackInSlot(0) == null){
				switch(this.side){
				case 0: transferItemFrom(handler.getWorld(), handler.getXCoord(), handler.getYCoord() - 1, handler.getZCoord());
				case 1: transferItemFrom(handler.getWorld(), handler.getXCoord(), handler.getYCoord() + 1, handler.getZCoord());
				case 2: transferItemFrom(handler.getWorld(), handler.getXCoord(), handler.getYCoord(), handler.getZCoord() - 1);
				case 3: transferItemFrom(handler.getWorld(), handler.getXCoord(), handler.getYCoord(), handler.getZCoord() + 1);
				case 4: transferItemFrom(handler.getWorld(), handler.getXCoord() - 1, handler.getYCoord(), handler.getZCoord());
				case 5: transferItemFrom(handler.getWorld(), handler.getXCoord() + 1, handler.getYCoord() - 1, handler.getZCoord());
				}
			}
		}
	}
	
	public void transferItemFrom(World world, int x, int y, int z) {
		TileEntity entity = world.getBlockTileEntity(x, y, z);
		if(entity instanceof IInventory){
			IInventory inv = (IInventory)entity;
			for(int i = 0; i < inv.getSizeInventory(); i++){
				ItemStack slot = inv.getStackInSlot(i);
				if(slot != null && getCooling(slot) > 0){
					this.inventory.setInventorySlotContents(0, slot.copy());
					inv.setInventorySlotContents(i, null);
					break;
				}
			}
		}
	}


	public void activate(IMachineHandler handler, EntityPlayer player, float offX, float offY, float offZ) {
		player.openGui(Redmagic.instance, Guis.FREEZER, player.worldObj, handler.getXCoord(), handler.getYCoord(), handler.getZCoord());
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
	
	public float getCooling(ItemStack stack){
		if(stack.isItemEqual(new ItemStack(Block.ice)))return 250F;
		return 0;
	}
}
