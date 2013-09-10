package com.petredy.redmagic.blocks;

import com.petredy.redmagic.Redmagic;
import com.petredy.redmagic.lib.BlockIndex;
import com.petredy.redmagic.tileentities.TileEntityEngine;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockBasicEngine extends BlockContainer{

	protected BlockBasicEngine(int par1) {
		super(par1, Material.piston);
		this.setCreativeTab(Redmagic.tabRedmagic);
		this.setUnlocalizedName(BlockIndex.ENGINE_NAME);
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntityEngine();
	}
	
	

}
