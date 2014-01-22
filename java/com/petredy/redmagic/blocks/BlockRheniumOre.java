package com.petredy.redmagic.blocks;

import java.util.Random;

import com.petredy.redmagic.Redmagic;
import com.petredy.redmagic.items.Items;
import com.petredy.redmagic.lib.BlockIndex;
import com.petredy.redmagic.lib.ItemIndex;
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

	public int quantityDropped(Random par1Random)
    {
        return 3 + par1Random.nextInt(5);
    }
	
	public int idDropped(int par1, Random par2Random, int par3)
    {
        return Items.crafting.itemID;
    }
	
	public int damageDropped(int par1)
    {
        return ItemIndex.CRAFTING_NUGGET_RHENIUM_METADATA;
    }
	
}
