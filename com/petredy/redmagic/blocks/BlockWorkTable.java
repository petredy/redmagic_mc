package com.petredy.redmagic.blocks;

import com.petredy.redmagic.Redmagic;
import com.petredy.redmagic.lib.BlockIndex;
import com.petredy.redmagic.lib.Guis;
import com.petredy.redmagic.lib.Reference;
import com.petredy.redmagic.tileentities.TileEntityWorkTable;
import com.petredy.redmagic.utils.BlockUtils;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

public class BlockWorkTable extends BlockContainer{

	public Icon top, side, bottom;
	
	protected BlockWorkTable(int par1) {
		super(par1, Material.wood);
		this.setUnlocalizedName(BlockIndex.WORK_TABLE_NAME);
		this.setCreativeTab(Redmagic.tabRedmagic);
		this.setHardness(BlockIndex.DEFAULT_HARDNESS);
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntityWorkTable();
	}
	
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase living, ItemStack par6ItemStack) {
		if(living instanceof EntityPlayer){
			ForgeDirection side = BlockUtils.getRotation(world, x, y, z, (EntityPlayer)living);
			TileEntityWorkTable entity = (TileEntityWorkTable) world.getBlockTileEntity(x, y, z);
			if(entity != null)entity.side = side;
		}
	}
	
	public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9){
        par5EntityPlayer.openGui(Redmagic.instance, Guis.WORK_TABLE, par1World, par2, par3, par4);
		return true;
    }
	
	public void registerIcons(IconRegister iconRegister){
		top = iconRegister.registerIcon(Reference.MOD_ID + ":" + BlockIndex.WORK_TABLE_NAME + ".top");
		bottom = iconRegister.registerIcon(Reference.MOD_ID + ":" + BlockIndex.WORK_TABLE_NAME + ".bottom");
		side = iconRegister.registerIcon(Reference.MOD_ID + ":" + BlockIndex.WORK_TABLE_NAME + ".side");
	}
	
	@Override
	public Icon getIcon(int side, int metadata){
		switch(side){
		case 1: return top;
		case 0: return bottom;
		default: return this.side;
		}
	}

}
