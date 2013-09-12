package com.petredy.redmagic.utils;

import com.petredy.redmagic.lib.Reference;
import com.petredy.redmagic.trading.TradingData;
import com.petredy.redmagic.trading.TradingManager;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class TradingUtils {

	public static float getMoney(ItemStack crystal) {
		if(crystal.stackTagCompound != null)return crystal.stackTagCompound.getFloat("money");
		return 0;
	}

	public static void setMoney(ItemStack crystal, float money) {
		if(crystal.stackTagCompound == null)crystal.stackTagCompound = new NBTTagCompound();
		crystal.stackTagCompound.setFloat("money", money);
	}

	public static void load(World world){
		TradingData trading = (TradingData) world.loadItemData(TradingData.class, Reference.MOD_ID + ".trading");
		if(trading != null){
			TradingManager.setData(trading.data);
		}else{
			TradingManager.initialise();
		}
	}
	
	public static void save(World world){
		TradingData trading = new TradingData(Reference.MOD_ID + ".trading");
		trading.data = TradingManager.getData();
		trading.markDirty();
		world.setItemData(Reference.MOD_ID + ".trading", trading);
		world.perWorldStorage.saveAllData();
	}

}
