package com.petredy.redmagic.utils;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import com.petredy.redmagic.redhole.Hole;

public class RedholeUtils {

	
	public static Hole getHole(ItemStack stack){
		NBTTagCompound data = ItemUtils.getData(stack, "redmagic.modifier");
		return Hole.loadFromNBT(data);
	}
	
	public static void saveHole(ItemStack stack, Hole mod){
		NBTTagCompound data = new NBTTagCompound();
		mod.writeToNBT(data);
		ItemUtils.setData(stack, "redmagic.modifier", data);
	}
	
	
}
