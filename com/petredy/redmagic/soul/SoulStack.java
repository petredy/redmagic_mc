package com.petredy.redmagic.soul;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class SoulStack {

	public ItemStack stack;
	public int amount;
	
	public SoulStack(){
		
	}
	
	public SoulStack(ItemStack stack, int amount){
		this.stack = stack;
		this.amount = amount;
	}
	
	public void readFromNBT(NBTTagCompound tag){
		this.stack = ItemStack.loadItemStackFromNBT(tag);
		this.amount = tag.getInteger("amount");
	}
	
	public static SoulStack loadFromNBT(NBTTagCompound tag){
		SoulStack stack = new SoulStack();
		stack.readFromNBT(tag);
		return stack;
	}
	
	public void writeToNBT(NBTTagCompound tag){
		if(stack != null)stack.writeToNBT(tag);
		amount = tag.getInteger("amount");
	}
}
