package com.petredy.redmagic.blocks;

import com.petredy.redmagic.Redmagic;
import com.petredy.redmagic.lib.BlockIndex;
import com.petredy.redmagic.lib.Reference;
import com.petredy.redmagic.redenergy.EnergyMap;
import com.petredy.redmagic.redenergy.RedEnergy;
import com.petredy.redmagic.redvalue.element.Composition;
import com.petredy.redmagic.utils.BlockUtils;
import com.petredy.redmagic.utils.LogUtils;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.world.World;

public class BlockRedTransformator extends Block{

	public static Composition PRODUCTION = Composition.getStandard(0.1f, 0.1f, 0.1f, 0.1f, 0.1f);
	
	public BlockRedTransformator(int par1) {
		super(par1, Material.piston);
		this.setUnlocalizedName(BlockIndex.RED_TRANSFORMATOR_NAME);
		this.setCreativeTab(Redmagic.tabRedmagic);
		this.setHardness(50F);
	}
	
	public void registerIcons(IconRegister iconRegister){
		blockIcon = iconRegister.registerIcon(Reference.MOD_ID + ":" + BlockIndex.RED_TRANSFORMATOR_NAME);
	}
	
	public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, int par5) {
		if(!par1World.isRemote && par5 == Block.pistonExtension.blockID){
			for(int i = 0; i < 6; i++){
				if(BlockUtils.isPunchedByPistionOnSide(par1World, par2, par3, par4, i)){
					this.releaseEnergy(par1World.provider.dimensionId, par2 / 16, par4 / 16);
				}
			}
		}
	}

	private void releaseEnergy(int dimension, int x, int z) {
		EnergyMap.releaseEnergy(new RedEnergy(dimension, x, z, PRODUCTION));
	}

	
	
}
