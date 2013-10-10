package com.petredy.redmagic.knowledge;

import net.minecraft.item.ItemStack;

public class KnowledgeCrafting extends KnowledgeItem{

	public ItemStack[] recipe;
	
	public KnowledgeCrafting(String name, float progress, int col, int row, Knowledge parent, ItemStack stack, ItemStack[] recipe) {
		super(name, progress, col, row, parent, stack);
		this.recipe = recipe;
	}

}
