package com.petredy.redmagic.machinery;

import com.petredy.redmagic.api.ITribologicalItem;
import com.petredy.redmagic.items.ItemTribological;
import com.petredy.redmagic.utils.InventoryUtils;
import com.petredy.redmagic.utils.LogUtils;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class Tribological {

	public IInventory inventory;
	
	public Tribological(ItemStack[] inv){
		this.inventory = InventoryUtils.itemStacksToInventory(inv,"redmagic.machine.tribological.inventory");
	}
	
	public Tribological(){
		this.inventory = new InventoryBasic("redmagic.machine.tribological.inventory", false, 9);
	}
	
	public void damage(float damage){
		for(int i = 0; i < inventory.getSizeInventory(); i++){
			ItemStack item = inventory.getStackInSlot(i);
			if(item != null){
				int value = ((ITribologicalItem)item.getItem()).getValue(item);
				float dmg = damage * ((ITribologicalItem)item.getItem()).getStrength(item);
				((ITribologicalItem)item.getItem()).setValue(item, (int) (value + dmg));
				if(((ITribologicalItem)item.getItem()).getValue(item) > ItemTribological.MAX_DAMAGE)((ITribologicalItem)item.getItem()).setValue(item, ItemTribological.MAX_DAMAGE);
			}
		}
	}
	
	public int getStatus(){
		int amount = 0;
		for(int i = 0; i < inventory.getSizeInventory(); i++){
			ItemStack item = inventory.getStackInSlot(i);
			if(item != null){
				amount += (ItemTribological.MAX_DAMAGE - ((ITribologicalItem)item.getItem()).getValue(item)) / ItemTribological.DIVISOR;
			}else{
				return 0;
			}
		}
		return amount / 9;
	}
	
	public void readFromNBT(NBTTagCompound tag){
		InventoryUtils.readFromNBT(inventory, tag);
	}
	
	public static Tribological loadFromNBT(NBTTagCompound tag){
		Tribological tribological = new Tribological();
		if(tag != null){
			tribological.readFromNBT(tag);
		}
		return tribological;
	}
	
	public void writeToNBT(NBTTagCompound tag){
		InventoryUtils.writeToNBT(inventory, tag);
	}

	public String toString(){
		return super.toString() + " @Status: " + this.getStatus();
	}
	
}
