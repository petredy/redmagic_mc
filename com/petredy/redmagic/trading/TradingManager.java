package com.petredy.redmagic.trading;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

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
		TradingItem item = items.get(stack.getUnlocalizedName());
		if(item != null)return item.getPrice();
		return 0;
	}

	public static int getItemAmount(ItemStack stack) {
		TradingItem item = items.get(stack.getUnlocalizedName());
		if(item != null)return item.amount;
		return 0;
	}

	public static NBTTagCompound getData() {
		NBTTagCompound tag = new NBTTagCompound();
		return tag;
	}

	public static boolean addItemAmount(ItemStack stack, int amount) {
		return false;
	}
	
	
}
