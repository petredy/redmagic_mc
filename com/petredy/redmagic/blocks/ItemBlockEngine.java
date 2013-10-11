/** 
 * Copyright (c) SpaceToad, 2011
 * http://www.mod-buildcraft.com
 * 
 * BuildCraft is distributed under the terms of the Minecraft Mod Public 
 * License 1.0, or MMPL. Please check the contents of the license located in
 * http://www.mod-buildcraft.com/MMPL-1.0.txt
 */

package com.petredy.redmagic.blocks;

import com.petredy.redmagic.lib.BlockIndex;

import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockEngine extends ItemBlock {

	public final static String[] subNames = {
		BlockIndex.ENGINE_RHENIUM_NAME
	};
	
	public ItemBlockEngine(int i) {
		super(i);
		setMaxDamage(0);
		setHasSubtypes(true);
	}

	@Override
	public int getMetadata(int i) {
		return i;
	}

	@Override
	public String getUnlocalizedName(ItemStack itemstack) {
		return this.getUnlocalizedName() + "." + subNames[itemstack.getItemDamage()];
	}
}
