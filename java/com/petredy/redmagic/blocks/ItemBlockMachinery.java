package com.petredy.redmagic.blocks;

import com.petredy.redmagic.lib.BlockIndex;

import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockMachinery extends ItemBlock{
	
	public final static String[] subNames = {
		BlockIndex.MACHINERY_CORE_NAME,
		BlockIndex.MACHINERY_DRIVE_NAME,
		BlockIndex.MACHINERY_HULL_NAME
	};

	public ItemBlockMachinery(int id) {
		super(id);
		this.setHasSubtypes(true);
		this.setUnlocalizedName(BlockIndex.MACHINERY_NAME);
	}
	
	@Override
	public int getMetadata (int damageValue) {
		return damageValue;
	}
	
	@Override
	public String getUnlocalizedName(ItemStack itemstack) {
		return this.getUnlocalizedName() + "." + subNames[itemstack.getItemDamage()];
	}
	
}
