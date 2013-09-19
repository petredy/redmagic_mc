package com.petredy.redmagic.blocks;

import com.petredy.redmagic.lib.BlockIndex;

import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockCrystal extends ItemBlock{
	
	public final static String[] subNames = {
		BlockIndex.CRYSTAL_SMALL_NAME,
		BlockIndex.CRYSTAL_MEDIUM_NAME,
		BlockIndex.CRYSTAL_LARGE_NAME,
		BlockIndex.CRYSTAL_LEFTOVER_NAME
	};

	public ItemBlockCrystal(int id) {
		super(id);
		this.setHasSubtypes(true);
		this.setUnlocalizedName(BlockIndex.CRYSTAL_NAME);
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
