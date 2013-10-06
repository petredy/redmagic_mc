/**
 * Copyright (c) SpaceToad, 2011 http://www.mod-buildcraft.com
 *
 * BuildCraft is distributed under the terms of the Minecraft Mod Public License
 * 1.0, or MMPL. Please check the contents of the license located in
 * http://www.mod-buildcraft.com/MMPL-1.0.txt
 */
package com.petredy.redmagic.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import java.util.Random;

import com.petredy.redmagic.Redmagic;
import com.petredy.redmagic.lib.BlockIndex;
import com.petredy.redmagic.lib.Rendering;
import com.petredy.redmagic.tileentities.TileEntityEngine;
import com.petredy.redmagic.tileentities.TileEntityEngineRhenium;
import com.petredy.redmagic.utils.BlockUtils;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

public class BlockEngine extends BlockContainer {


	public BlockEngine(int i) {
		super(i, Material.iron);

		setHardness(0.5F);
		setCreativeTab(Redmagic.tabRedmagic);
		setUnlocalizedName(BlockIndex.ENGINE_NAME);
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister) {
		
	}

	@Override
	public int getRenderType() {
		return Rendering.ENGINE_ID;
	}

	@Override
	public TileEntity createTileEntity(World world, int metadata) {
		switch(metadata){
		
		case 0: return new TileEntityEngineRhenium();
		default: return null;
		}
	}



	@Override
	public int damageDropped(int i) {
		return i;
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return null;
	}
	
	public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLivingBase, ItemStack par6ItemStack) {
		ForgeDirection side = BlockUtils.getRotation(par1World, par2, par3, par4, par5EntityLivingBase, true);
		TileEntityEngine engine = (TileEntityEngine) par1World.getBlockTileEntity(par2, par3, par4);
		if(engine != null)engine.side = side;
	}
}
