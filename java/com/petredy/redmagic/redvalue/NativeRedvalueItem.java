package com.petredy.redmagic.redvalue;

import java.util.ArrayList;
import java.util.List;

import com.petredy.redmagic.redvalue.element.Composition;
import com.petredy.redmagic.redvalue.element.Element;

import net.minecraft.item.ItemStack;

public class NativeRedvalueItem extends RedvalueItem{

	public NativeRedvalueItem(ItemStack stack) {
		super(stack);
	}
	
	public NativeRedvalueItem(ItemStack stack, Composition comp) {
		this(stack);
		this.composition = comp;
	}
	
	public List<Element> getElements(){
		return new ArrayList<Element>();
	}

	
	@Override
	public String toString(){
		return "NativeRedvalueItem: " + stack + "@" + getComposition();
	}
	
}
