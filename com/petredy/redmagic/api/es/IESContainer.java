package com.petredy.redmagic.api.es;

import net.minecraft.item.ItemStack;

import com.petredy.redmagic.es.Environment;

public interface IESContainer {

	public Environment getEnvironment(ItemStack stack);
	
	public void setEnvironment(ItemStack stack, Environment environment);
	
	public float getMaxEnergy(ItemStack stack);
	
}
