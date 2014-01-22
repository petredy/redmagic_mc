package com.petredy.redmagic.api;

import net.minecraft.item.ItemStack;

public interface ITribologicalItem {
	
	public int getValue(ItemStack stack);
	
	public void setValue(ItemStack stack, int value);
	
	public float getStrength(ItemStack stack);
	
}
