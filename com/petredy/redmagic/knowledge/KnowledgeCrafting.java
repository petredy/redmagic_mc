package com.petredy.redmagic.knowledge;

import net.minecraft.item.ItemStack;

public class KnowledgeCrafting extends KnowledgeItem{

	public ItemStack[] recipe;
	
	public KnowledgeCrafting(String name, float progress, ItemStack stack, ItemStack[] recipe) {
		super(name, progress, stack);
		this.recipe = recipe;
	}

}
