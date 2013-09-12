package com.petredy.redmagic.trading;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

import com.petredy.redmagic.redvalue.RedvalueDictionary;
import com.petredy.redmagic.redvalue.RedvalueItem;

public class TradingManager {
	
	private static HashMap<String, TradingItem>items = new HashMap<String, TradingItem>();
	
	public static void register(ItemStack stack, float price, int amount){
		items.put(stack.getUnlocalizedName(), new TradingItem(stack, price, amount));
	}
	
	public static void initialise(){
		List<RedvalueItem> list = RedvalueDictionary.getAllItems();
		for(RedvalueItem item: list){
			register(item.stack, item.getValue(), 0);
		}
	}
	
	public static Collection<TradingItem> getAllItems(){
		return items.values();
	}

	public static float getItemPrice(ItemStack stack) {
		if(stack == null)return 0;
		TradingItem item = items.get(stack.getUnlocalizedName());
		if(item != null)return item.getPrice();
		return 0;
	}

	public static int getItemAmount(ItemStack stack) {
		if(stack == null)return 0;
		TradingItem item = items.get(stack.getUnlocalizedName());
		if(item != null)return item.amount;
		return 0;
	}
	
	public static void readFromNBT(NBTTagCompound tag){
		NBTTagList list = tag.getTagList("items");
		for(int i = 0; i < list.tagCount(); i++){
			TradingItem item = TradingItem.loadFromNBT((NBTTagCompound) list.tagAt(i));
			items.put(item.item.getUnlocalizedName(), item);
		}
	}
	
	public static void writeToNBT(NBTTagCompound tag){
		NBTTagList list = new NBTTagList();
		for(TradingItem item: items.values()){
			NBTTagCompound itemTag = new NBTTagCompound();
			item.writeToNBT(itemTag);
			list.appendTag(itemTag);
		}
		tag.setTag("items", list);
	}

	public static NBTTagCompound getData() {
		NBTTagCompound tag = new NBTTagCompound();
		writeToNBT(tag);
		return tag;
	}
	
	public static void setData(NBTTagCompound tag){
		readFromNBT(tag);
	}

	public static boolean addItemAmount(ItemStack stack, int amount) {
		if(stack == null)return false;
		TradingItem item = items.get(stack.getUnlocalizedName());
		if(item != null){
			item.amount += amount;
			return true;
		}
		return false;
	}

	public static boolean removeItemAmount(ItemStack stack, int amount) {
		if(stack == null)return false;
		TradingItem item = items.get(stack.getUnlocalizedName());
		if(item != null && item.amount >= amount){
			item.amount -= amount;
			return true;
		}
		return false;
	}
	
	
}
