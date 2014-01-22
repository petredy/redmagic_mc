package com.petredy.redmagic.blocks;

import com.petredy.redmagic.Redmagic;
import com.petredy.redmagic.lib.BlockIndex;
import com.petredy.redmagic.lib.Reference;
import com.petredy.redmagic.tileentities.TileEntityGenerator;
import com.petredy.redmagic.utils.BlockUtils;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockGenerator extends BlockContainer{

	public Icon front, runFront, top, runTop;
	
	protected BlockGenerator(int par1) {
		super(par1, Material.piston);
		this.setUnlocalizedName(BlockIndex.GENERATOR_NAME);
		this.setCreativeTab(Redmagic.tabRedmagic);
		this.setHardness(BlockIndex.DEFAULT_HARDNESS);
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntityGenerator();
	}
	
	public void registerIcons(IconRegister iconRegister){
		blockIcon = iconRegister.registerIcon(Reference.MOD_ID + ":" + BlockIndex.GENERATOR_NAME);
		runFront = iconRegister.registerIcon(Reference.MOD_ID + ":" + BlockIndex.GENERATOR_NAME + ".front.active");
		front = iconRegister.registerIcon(Reference.MOD_ID + ":" + BlockIndex.GENERATOR_NAME + ".front");
		runTop = iconRegister.registerIcon(Reference.MOD_ID + ":" + BlockIndex.GENERATOR_NAME + ".top.active");
		top = iconRegister.registerIcon(Reference.MOD_ID + ":" + BlockIndex.GENERATOR_NAME + ".top");
	}
	
	public Icon getBlockTexture(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
    {
		int metadata = par1IBlockAccess.getBlockMetadata(par2, par3, par4);
		TileEntityGenerator generator = (TileEntityGenerator) par1IBlockAccess.getBlockTileEntity(par2, par3, par4);
		if(generator != null && generator.run){
			if(par5 == 1)return runTop;
			if(par5 == metadata)return runFront;
		}
        return this.getIcon(par5, metadata);
    }
	
	public Icon getIcon(int par1, int par2)
    {
		if(par1 == 1)return top;
		if(par1 == par2)return front;
        return this.blockIcon;
    }
	
	public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLivingBase, ItemStack par6ItemStack) {
		par1World.setBlockMetadataWithNotify(par2, par3, par4, BlockUtils.getRotation(par1World, par2, par3, par4, par5EntityLivingBase, false).ordinal(), 3);
	}

	public void breakBlock(World par1World, int par2, int par3, int par4, int par5, int par6)
    {
		TileEntityGenerator generator = (TileEntityGenerator) par1World.getBlockTileEntity(par2, par3, par4);
		if(generator != null)generator.delete();
		super.breakBlock(par1World, par2, par3, par4, par5, par6);
    }
}
