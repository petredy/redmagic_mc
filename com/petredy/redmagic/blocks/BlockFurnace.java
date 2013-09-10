package com.petredy.redmagic.blocks;

import com.petredy.redmagic.Redmagic;
import com.petredy.redmagic.lib.BlockIndex;
import com.petredy.redmagic.tileentities.TileEntityFurnace;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockFurnace extends BlockContainer{

	protected BlockFurnace(int par1) {
		super(par1, Material.piston);
		this.setCreativeTab(Redmagic.tabRedmagic);
		this.setUnlocalizedName(BlockIndex.FURNACE_NAME);
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntityFurnace();
	}
	
	public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9){
		TileEntityFurnace furnace = (TileEntityFurnace) par1World.getBlockTileEntity(par2, par3, par4);
		par5EntityPlayer.addChatMessage(((Float)furnace.handler.getEnergyStored()).toString());
		return false;
	}

}
