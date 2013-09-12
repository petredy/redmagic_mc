package com.petredy.redmagic.trading;

import java.util.LinkedList;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class TradingItem {
	
	public LinkedList<Float> lastPrices = new LinkedList<Float>();
	public ItemStack item;
	public int amount;
	
	public TradingItem(){
		
	}
	
	public TradingItem(ItemStack stack, float price, int amount){
		this.item = stack;
		this.addPrice(price);
		this.amount = amount;
	}
	
	private void addPrice(float price){
		lastPrices.addFirst(price);
		if(lastPrices.size() > 20)lastPrices.removeLast();
	}
	
	public void readFromNBT(NBTTagCompound tag){
		this.item = ItemStack.loadItemStackFromNBT(tag);
		NBTTagList priceList = tag.getTagList("prices");
		for(int i = 0; i < priceList.tagCount(); i++){
			NBTTagCompound priceTag = (NBTTagCompound) priceList.tagAt(i);
			byte index = priceTag.getByte("index");
			lastPrices.add(index, tag.getFloat("price"));
		}
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
		byte index = 0;
		NBTTagList priceList = new NBTTagList();
		for(float price: lastPrices){
			NBTTagCompound priceTag = new NBTTagCompound();
			priceTag.setByte("index", index);
			priceTag.setFloat("price", price);
		}
	}

	public Float getPrice() {
		return lastPrices.getFirst();
	}
}
