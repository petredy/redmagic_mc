package com.petredy.redmagic.container.inventory;

import com.petredy.redmagic.utils.InventoryUtils;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public class MultiInventory implements IInventory{

	public IInventory[] inventories;
	public String name;
	
	public MultiInventory(String name, IInventory[] invs){
		this.inventories = invs;
	}
	
	@Override
	public int getSizeInventory() {
		return InventoryUtils.getSizeOfInventories(this.inventories);
	}

	@Override
	public ItemStack getStackInSlot(int i) {
		return InventoryUtils.getStackInSlotFromInventories(i, this.inventories);
	}

	@Override
	public ItemStack decrStackSize(int i, int j) {
		ItemStack stack = InventoryUtils.getStackInSlotFromInventories(i, inventories);
		stack.stackSize -= j;
		if(stack.stackSize <= 0)stack = null;
		InventoryUtils.setStackInSlotFromInventories(i, stack, inventories);
		return stack;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int i) {
		return getStackInSlot(i);
	}

	@Override
	public void setInventorySlotContents(int i, ItemStack itemstack) {
		InventoryUtils.setStackInSlotFromInventories(i, itemstack, inventories);
	}

	@Override
	public String getInvName() {
		return name;
	}

	@Override
	public boolean isInvNameLocalized() {
		return false;
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public void onInventoryChanged() {
		
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer entityplayer) {
		return true;
	}

	@Override
	public void openChest() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void closeChest() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack) {
		return true;
	}

}
