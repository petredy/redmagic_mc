package com.petredy.redmagic.api.machines;

import com.petredy.redmagic.machinery.Tribological;

import net.minecraft.item.ItemStack;

public interface IMachineItem {

	public int getMetadata(ItemStack item);
	
	public Tribological getTribological(ItemStack item);
	
	public void setTribological(ItemStack item, Tribological tribological);
}
