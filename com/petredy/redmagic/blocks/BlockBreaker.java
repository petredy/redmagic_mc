package com.petredy.redmagic.blocks;

import com.petredy.redmagic.Redmagic;
import com.petredy.redmagic.lib.BlockIndex;
import com.petredy.redmagic.lib.Reference;
import com.petredy.redmagic.redenergy.EnergyMap;
import com.petredy.redmagic.redenergy.RedEnergy;
import com.petredy.redmagic.redvalue.element.Composition;
import com.petredy.redmagic.utils.BlockUtils;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class BlockBreaker extends Block{

	public Icon breaker;
	
	public BlockBreaker(int par1) {
		super(par1, Material.rock);
		this.setUnlocalizedName(BlockIndex.BLOCK_BREAKER_NAME);
		this.setCreativeTab(Redmagic.tabRedmagic);
		this.setHardness(BlockIndex.DEFAULT_HARDNESS);
	}
	
	public void registerIcons(IconRegister iconRegister){
		blockIcon = iconRegister.registerIcon(Reference.MOD_ID + ":" + BlockIndex.BLOCK_BREAKER_NAME + ".side");
		breaker = iconRegister.registerIcon(Reference.MOD_ID + ":" + BlockIndex.BLOCK_BREAKER_NAME);
	}
	
	public Icon getIcon(int par1, int par2)
    {
		if(par1 == par2)return breaker;
        return this.blockIcon;
    }
	
	public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLivingBase, ItemStack par6ItemStack) {
		par1World.setBlockMetadataWithNotify(par2, par3, par4, BlockUtils.getRotation(par1World, par2, par3, par4, par5EntityLivingBase, true).getOpposite().ordinal(), 3);
	}
	
	public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, int par5) {
		int metadata = par1World.getBlockMetadata(par2, par3, par4);
		switch(metadata){
		case 0: 
			destroy(par1World, par2, par3 - 1, par4);
			break;
		case 1: 
			destroy(par1World, par2, par3 + 1, par4);
			break;
		case 2: 
			destroy(par1World, par2, par3, par4 - 1);
			break;
		case 3: 
			destroy(par1World, par2, par3, par4 + 1);
			break;
		case 4: 
			destroy(par1World, par2 - 1, par3, par4);
			break;
		case 5: 
			destroy(par1World, par2 + 1, par3, par4);
			break;
		}
	}
	
	public void destroy(World world, int x, int y, int z){
		int id = world.getBlockId(x, y, z);
		RedEnergy energy = new RedEnergy(world.provider.dimensionId, x / 16, z / 16, Composition.getStandard(0.1f, 0, 0, 0, 0));
		if(id != 0 && Block.blocksList[id].isBlockNormalCube(world, x, y, z) && id != Block.bedrock.blockID && EnergyMap.consumeEnergy(energy.copy()).isEqual(energy.copy())){
			world.destroyBlock(x, y, z, true);
		}
	}
	
	

}
