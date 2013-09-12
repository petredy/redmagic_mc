package com.petredy.redmagic.utils;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class TradingUtils {

	public static float getMoney(ItemStack crystal) {
		if(crystal.stackTagCompound != null)return crystal.stackTagCompound.getFloat("money");
		return 0;
	}

	public static void setMoney(ItemStack crystal, float money) {
		if(crystal.stackTagCompound == null)crystal.stackTagCompound = new NBTTagCompound();
		crystal.stackTagCompound.setFloat("money", money);
	}


}
