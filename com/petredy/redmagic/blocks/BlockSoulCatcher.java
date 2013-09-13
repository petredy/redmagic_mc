package com.petredy.redmagic.blocks;

import buildcraft.api.power.PowerHandler.PowerReceiver;

import com.petredy.redmagic.Redmagic;
import com.petredy.redmagic.lib.BlockIndex;
import com.petredy.redmagic.lib.Reference;
import com.petredy.redmagic.structures.soulcatcher.StructureSoulCatcher;
import com.petredy.redmagic.tileentities.TileEntitySoulCatcher;
import com.petredy.redmagic.utils.LogUtils;
import com.petredy.redmagic.utils.WorldSavedDataUtils;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

public class BlockSoulCatcher extends BlockContainer{

	protected Icon active, inactive;
	
	protected BlockSoulCatcher(int par1) {
		super(par1, Material.piston);
		this.setUnlocalizedName(BlockIndex.SOUL_CATCHER_NAME);
		this.setCreativeTab(Redmagic.tabRedmagic);
		this.setHardness(1.0f);
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntitySoulCatcher();
	}
	
	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase living, ItemStack stack) {
		TileEntitySoulCatcher entity = (TileEntitySoulCatcher) world.getBlockTileEntity(x, y, z);
		if(!world.isRemote)entity.buildOrFindStructure();
	}
	
	public void breakBlock(World world, int x, int y, int z, int par5, int par6){
		TileEntitySoulCatcher entity = (TileEntitySoulCatcher) world.getBlockTileEntity(x, y, z);
		if(entity != null && !world.isRemote)entity.breakLayer();
		super.breakBlock(world, x, y, z, par5, par6);
	}
	
	public void registerIcons(IconRegister iconRegister){
		this.blockIcon = inactive = iconRegister.registerIcon(Reference.MOD_ID + ":" + BlockIndex.SOUL_CATCHER_NAME + ".inactive");
		active = iconRegister.registerIcon(Reference.MOD_ID + ":" + BlockIndex.SOUL_CATCHER_NAME + ".active");
	}
	
	public Icon getBlockTexture(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
    {
		TileEntitySoulCatcher entity = (TileEntitySoulCatcher) par1IBlockAccess.getBlockTileEntity(par2, par3, par4);
		if(entity != null && entity.id >= 0)return active;
		return inactive;
    }
	
	public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9){
		TileEntitySoulCatcher entity = (TileEntitySoulCatcher) par1World.getBlockTileEntity(par2, par3, par4);
		if(entity != null){
			LogUtils.log(entity.id);
			if(entity.getPowerReceiver(ForgeDirection.UNKNOWN) != null){
				PowerReceiver receiver = StructureSoulCatcher.loadFromNBT(WorldSavedDataUtils.loadData(par1World, StructureSoulCatcher.TOKEN_PREFIX + entity.id)).getNextPowerReceiver(par1World);
				if(receiver != null)LogUtils.log(receiver.getEnergyStored());
			}
		}
        return false;
    }

}
