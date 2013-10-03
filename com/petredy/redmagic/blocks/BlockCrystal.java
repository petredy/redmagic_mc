package com.petredy.redmagic.blocks;

import java.util.List;
import java.util.Random;

import com.petredy.redmagic.Redmagic;
import com.petredy.redmagic.lib.BlockIndex;
import com.petredy.redmagic.lib.Reference;
import com.petredy.redmagic.tileentities.TileEntityCrystal;
import com.petredy.redmagic.utils.BlockUtils;
import com.petredy.redmagic.utils.LogUtils;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

public class BlockCrystal extends BlockContainer{

	public Icon[] icons = new Icon[ItemBlockCrystal.subNames.length];
	
	public BlockCrystal(int par1) {
		super(par1, Material.rock);
		this.setHardness(100F);
		this.setUnlocalizedName(BlockIndex.CRYSTAL_NAME);
		this.setCreativeTab(Redmagic.tabRedmagic);
		this.setHardness(BlockIndex.DEFAULT_HARDNESS);
	}
	
	public void registerIcons(IconRegister iconRegister){
		for(int i = 0; i < ItemBlockCrystal.subNames.length; i++){
			icons[i] = iconRegister.registerIcon(Reference.MOD_ID + ":" + BlockIndex.CRYSTAL_NAME  + "_" + i);
		}
	}
	
	public Icon getIcon(int side, int metadata){
		return icons[metadata];
	}
	
	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntityCrystal();
	}
	
	public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLivingBase, ItemStack par6ItemStack) {
		TileEntityCrystal crystal = (TileEntityCrystal)par1World.getBlockTileEntity(par2, par3, par4);
		if(crystal != null){
			crystal.direction = ForgeDirection.getOrientation(ForgeDirection.OPPOSITES[BlockUtils.forgeDirectionToInt(BlockUtils.getRotation(par1World, par2, par3, par4, par5EntityLivingBase, true))]);
			if(crystal.blockMetadata == BlockIndex.CRYSTAL_SMALL_METADATA)crystal.range = 20;
			if(crystal.blockMetadata == BlockIndex.CRYSTAL_MEDIUM_METADATA)crystal.range = 60;
			if(crystal.blockMetadata == BlockIndex.CRYSTAL_LARGE_METADATA)crystal.range = 100;
		}
	}
	
	public int idDropped(int par1, Random par2Random, int par3)
    {
        return this.blockID;
    }
	
	public int damageDropped(int par1)
    {
        return BlockIndex.CRYSTAL_LEFTOVER_METADATA;
    }
	
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(int par1, CreativeTabs tab, List subItems) {
		for (int i = 0; i < ItemBlockCrystal.subNames.length; i++) {
			subItems.add(new ItemStack(this, 1, i));
		}
	}
	
}
