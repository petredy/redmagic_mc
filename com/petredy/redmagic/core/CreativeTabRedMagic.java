package com.petredy.redmagic.core;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;

public class CreativeTabRedMagic extends CreativeTabs{

	public CreativeTabRedMagic(int par1, String par2Str) {
		super(par1, par2Str);
	}
	
	public int getTabIconItemIndex() {
        return Block.beacon.blockID;
    }
	
	

}