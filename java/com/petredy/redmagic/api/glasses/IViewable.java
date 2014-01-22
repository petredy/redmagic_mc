package com.petredy.redmagic.api.glasses;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public interface IViewable {

	public void view(EntityPlayer player, ItemStack stack);
	
	
}
