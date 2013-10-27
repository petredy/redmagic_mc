package com.petredy.redmagic.blocks;

import com.petredy.redmagic.Redmagic;
import com.petredy.redmagic.es.EnvironmentSphere;
import com.petredy.redmagic.lib.BlockIndex;
import com.petredy.redmagic.lib.Guis;
import com.petredy.redmagic.lib.Reference;
import com.petredy.redmagic.tileentities.TileEntityESLoader;
import com.petredy.redmagic.utils.LogUtils;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockESLoader extends BlockContainer{

	protected BlockESLoader(int par1) {
		super(par1, Material.piston);
		this.setUnlocalizedName(BlockIndex.ES_LOADER_NAME);
		this.setCreativeTab(Redmagic.tabRedmagic);
		this.setHardness(BlockIndex.DEFAULT_HARDNESS);
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntityESLoader();
	}
	
	public void registerIcons(IconRegister iconRegister){
		blockIcon = iconRegister.registerIcon(Reference.MOD_ID + ":" + BlockIndex.ES_LOADER_NAME);
	}
	
	public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
    {
		TileEntityESLoader loader = (TileEntityESLoader) par1World.getBlockTileEntity(par2, par3, par4);
		if(loader != null){
			if(par5EntityPlayer.isSneaking()){
				loader.toggle();
			}else{
				par5EntityPlayer.openGui(Redmagic.instance, Guis.ES_LOADER, par1World, par2, par3, par4);
			}
			return true;
		}
        return false;
    }
	
	

}
