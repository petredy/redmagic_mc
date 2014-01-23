package com.petredy.redmagic.core;

import com.petredy.redmagic.items.Items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class CreativeTabRedMagic extends CreativeTabs{

	public CreativeTabRedMagic(int par1, String par2Str) {
		super(par1, par2Str);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Item getTabIconItem() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
