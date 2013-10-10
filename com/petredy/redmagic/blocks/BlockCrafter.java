package com.petredy.redmagic.blocks;

import com.petredy.redmagic.Redmagic;
import com.petredy.redmagic.lib.BlockIndex;
import com.petredy.redmagic.lib.Guis;
import com.petredy.redmagic.tileentities.TileEntityCrafter;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockCrafter extends BlockContainer {

	
	
	protected BlockCrafter(int par1) {
		super(par1, Material.wood);
		this.setUnlocalizedName(BlockIndex.CRAFTER_NAME);
		this.setCreativeTab(Redmagic.tabRedmagic);
		this.setHardness(BlockIndex.DEFAULT_HARDNESS);
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntityCrafter();
	}
	
	public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9){
		if(par5EntityPlayer.isSneaking())return false;
		
		par5EntityPlayer.openGui(Redmagic.instance, Guis.CRAFTER, par1World, par2, par3, par4);
		return true;
	}
	
	public void breakBlock(World par1World, int par2, int par3, int par4, int par5, int par6){
		super.breakBlock(par1World, par2, par3, par4, par5, par6);
	}

}
