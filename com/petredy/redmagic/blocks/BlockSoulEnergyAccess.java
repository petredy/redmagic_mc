package com.petredy.redmagic.blocks;

import com.petredy.redmagic.Redmagic;
import com.petredy.redmagic.lib.BlockIndex;
import com.petredy.redmagic.lib.Reference;
import com.petredy.redmagic.tileentities.TileEntitySoulEnergyAccess;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockSoulEnergyAccess extends BlockContainer{

	protected BlockSoulEnergyAccess(int par1) {
		super(par1, Material.piston);
		this.setCreativeTab(Redmagic.tabRedmagic);
		this.setUnlocalizedName(BlockIndex.SOUL_ENERGY_ACCESS_NAME);
		this.setHardness(BlockIndex.DEFAULT_HARDNESS);
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntitySoulEnergyAccess();
	}
	
	public void registerIcons(IconRegister iconRegister){
		this.blockIcon = iconRegister.registerIcon(Reference.MOD_ID + ":" + BlockIndex.SOUL_ENERGY_ACCESS_NAME);
	}

}
