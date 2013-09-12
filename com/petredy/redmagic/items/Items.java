package com.petredy.redmagic.items;

import com.petredy.redmagic.lib.ItemIndex;

import net.minecraftforge.common.Configuration;

public class Items {

	public static ItemTradingCrystal trading;
	
	public static void init(){
		trading = new ItemTradingCrystal(ItemIndex.TRADING_CRYSTAL_ID);
	}
	
	public static void config(Configuration config){
		ItemIndex.TRADING_CRYSTAL_ID = config.getItem(ItemIndex.TRADING_CRYSTAL_NAME, ItemIndex.TRADING_CRYSTAL_DEFAULT_ID).getInt(ItemIndex.TRADING_CRYSTAL_DEFAULT_ID);
	}
	
	
}
