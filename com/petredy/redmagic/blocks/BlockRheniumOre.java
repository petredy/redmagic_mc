package com.petredy.redmagic.blocks;

import com.petredy.redmagic.Redmagic;
import com.petredy.redmagic.lib.BlockIndex;
import com.petredy.redmagic.lib.Reference;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;

public class BlockRheniumOre extends Block{

	public BlockRheniumOre(int par1) {
		super(par1, Material.rock);
		this.setUnlocalizedName(BlockIndex.RHENIUM_ORE_NAME);
		this.setHardness(BlockIndex.DEFAULT_HARDNESS);
		this.setCreativeTab(Redmagic.tabRedmagic);
	}
	
	@Override
	public void registerIcons(IconRegister iconRegister){
		this.blockIcon = iconRegister.registerIcon(Reference.MOD_ID + ":" + BlockIndex.RHENIUM_ORE_NAME);
	}

}
