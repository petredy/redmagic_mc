package com.petredy.redmagic.knowledge;

import net.minecraft.item.ItemStack;

public class KnowledgeItem extends Knowledge{

	public ItemStack item;
	
	public KnowledgeItem(String name, float progress, int col, int row, Knowledge parent, ItemStack stack){
		super(name, progress, col, row, parent, null);
		this.item = stack;
	}
	
}
