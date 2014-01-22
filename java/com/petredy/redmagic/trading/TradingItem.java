package com.petredy.redmagic.trading;

import java.util.LinkedList;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class TradingItem implements Comparable<TradingItem>{
	
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
	
	@Override
	public String toString(){
		return "TradingItem: " + item + " | " + price + " | " + amount;
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

	@Override
	public int compareTo(TradingItem item) {
		if(item.amount > this.amount)return 1;
		if(this.amount > item.amount)return -1;
		if(this.price > item.price)return 1;
		if(this.price < item.price)return -1;
		return 0;
	}
}
