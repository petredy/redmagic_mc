package com.petredy.redmagic.trading;

import java.util.LinkedList;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class TradingItem {
	
	public float price;
	public ItemStack item;
	public int amount;
	
	public TradingItem(){
		
	}
	
	public TradingItem(ItemStack stack, float price, int amount){
		this.item = stack;
		this.price = price;
		this.amount = amount;
	}
	
	public void readFromNBT(NBTTagCompound tag){
		this.item = ItemStack.loadItemStackFromNBT(tag);
		this.price = tag.getFloat("price");
		this.amount = tag.getInteger("amount");
	}
	
	public static TradingItem loadFromNBT(NBTTagCompound tag){
		TradingItem item = new TradingItem();
		item.readFromNBT(tag);
		return item;
	}
	
	public void writeToNBT(NBTTagCompound tag){
		if(this.item != null)this.item.writeToNBT(tag);
		tag.setInteger("amount", amount);
		tag.setFloat("price", price);
	}

	public Float getPrice() {
		return price;
	}
}
