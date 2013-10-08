package com.petredy.redmagic.knowledge;

import net.minecraft.item.ItemStack;

public class KnowledgeItem extends Knowledge{

	public ItemStack item;
	
	public KnowledgeItem(String name, float progress, ItemStack stack){
		super(name, progress);
		this.item = stack;
	}
	
}
