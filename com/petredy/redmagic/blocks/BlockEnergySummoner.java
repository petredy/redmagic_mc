package com.petredy.redmagic.blocks;

import com.petredy.redmagic.Redmagic;
import com.petredy.redmagic.lib.BlockIndex;
import com.petredy.redmagic.lib.Reference;
import com.petredy.redmagic.tileentities.TileEntityEnergySummoner;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockEnergySummoner extends BlockContainer{

	protected BlockEnergySummoner(int par1) {
		super(par1, Material.piston);
		this.setUnlocalizedName(BlockIndex.ENERGY_SUMMONER_NAME);
		this.setCreativeTab(Redmagic.tabRedmagic);
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntityEnergySummoner();
	}

	public void registerIcons(IconRegister iconRegister){
		this.blockIcon = iconRegister.registerIcon(Reference.MOD_ID + ":" + BlockIndex.ENERGY_SUMMONER_NAME);
	}
	
}
