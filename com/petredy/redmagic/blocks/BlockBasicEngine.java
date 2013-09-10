package com.petredy.redmagic.blocks;

import com.petredy.redmagic.Redmagic;
import com.petredy.redmagic.lib.BlockIndex;
import com.petredy.redmagic.lib.Rendering;
import com.petredy.redmagic.tileentities.TileEntityEngine;
import com.petredy.redmagic.utils.BlockUtils;
import com.petredy.redmagic.utils.LogUtils;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
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
	
	@Override
	public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLivingBase, ItemStack par6ItemStack) {
		if(par5EntityLivingBase instanceof EntityPlayer){
			TileEntityEngine engine = (TileEntityEngine)par1World.getBlockTileEntity(par2, par3, par4);
			engine.side = BlockUtils.getRotation(par1World, par2, par3, par4, par5EntityLivingBase);
		}
	}
	
	public boolean renderAsNormalBlock(){
        return false;
    }
	
	public int getRenderType(){
        return Rendering.ENGINE_ID;
    }
	
	public boolean isOpaqueCube(){
        return false;
    }
	
	public static boolean isNormalCube(int par0)
    {
		return false;
    }
	

}
