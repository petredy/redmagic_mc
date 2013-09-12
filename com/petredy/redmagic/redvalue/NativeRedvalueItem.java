package com.petredy.redmagic.redvalue;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.ItemStack;

public class NativeRedvalueItem extends RedvalueItem{

	public NativeRedvalueItem(ItemStack stack) {
		super(stack);
	}
	
	public NativeRedvalueItem(ItemStack stack, float value) {
		this(stack);
		this.value = value;
	}
	
	@Override
	public float getValue(){
		return this.value;
	}
	
	
}
