package com.petredy.redmagic.redvalue;

import com.petredy.redmagic.redvalue.element.Composition;

import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class RedvalueItem {
	
	protected Composition composition = Composition.getStandard(1, 1, 1, 1, 1);
	protected float size;
	public ItemStack stack;
	
	
	public RedvalueItem(ItemStack stack){
		this.stack = stack;
	}
	
	public float getValue(){
		return getComposition().getRedvalue();
	}
	
	public float getSize(){
		return stack != null ? (stack.getItem() instanceof ItemBlock ? 1 : 0.1f) : 0;
	}
	
	public boolean matches(ItemStack item) {
		return item != null && item.isItemEqual(stack);
	}
	
	public Composition getComposition(){
		return composition;
	}
}
