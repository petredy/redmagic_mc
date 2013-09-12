package com.petredy.redmagic.redvalue;

import net.minecraft.item.ItemStack;

public class RedvalueItem {
	
	protected float value;
	public ItemStack stack;
	
	
	public RedvalueItem(ItemStack stack){
		this.stack = stack;
	}
	
	public float getValue(){
		return 0;
	}
	
	public boolean matches(ItemStack item) {
		return item != null && item.isItemEqual(stack);
	}
}
