package com.petredy.redmagic.soul;

import net.minecraft.item.ItemStack;

public class MemoryItem extends Memory {

	public ItemStack item;
	
	public MemoryItem(String name, ItemStack stack) {
		super(name);
		this.item = stack;
	}

}
