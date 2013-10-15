package com.petredy.redmagic.blocks;

import java.util.Random;

import com.petredy.redmagic.Redmagic;
import com.petredy.redmagic.api.machines.IMachineItem;
import com.petredy.redmagic.items.ItemMachine;
import com.petredy.redmagic.items.ItemScrewdriver;
import com.petredy.redmagic.lib.BlockIndex;
import com.petredy.redmagic.lib.Reference;
import com.petredy.redmagic.tileentities.TileEntityMachine;
import com.petredy.redmagic.tileentities.TileEntityStructure;
import com.petredy.redmagic.utils.LogUtils;
import com.petredy.redmagic.utils.StructureUtils;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockRedStructure extends BlockContainer{

	public Icon inactive, active, border;
	public Icon[] icons = new Icon[ItemMachine.subNames.length];
	
	protected BlockRedStructure(int par1) {
		super(par1, Material.iron);
		this.setUnlocalizedName(BlockIndex.RED_STRUCTURE_NAME);
		this.setCreativeTab(Redmagic.tabRedmagic);
		this.setHardness(BlockIndex.DEFAULT_HARDNESS);
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntityStructure();
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister) {
		this.inactive = this.blockIcon = par1IconRegister.registerIcon(Reference.MOD_ID + ":" + BlockIndex.RED_STRUCTURE_NAME + ".inactive");
		this.active = par1IconRegister.registerIcon(Reference.MOD_ID + ":" + BlockIndex.RED_STRUCTURE_NAME + ".active");
		this.border = par1IconRegister.registerIcon(Reference.MOD_ID + ":" + BlockIndex.RED_STRUCTURE_NAME + ".border.active");
		for(int i = 0; i < ItemMachine.subNames.length; i++){
			icons[i] = par1IconRegister.registerIcon(Reference.MOD_ID + ":" + BlockIndex.MACHINE_NAME + "." + ItemMachine.subNames[i]);
		}
	}
	
	public Icon getBlockTexture(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
    {
		TileEntityStructure entity = (TileEntityStructure) par1IBlockAccess.getBlockTileEntity(par2, par3, par4);
		if(entity != null && entity.hasStructure()){
			int position = entity.position;
			if(position > 17 && par5 != 0 && par5 != 1)return this.border;
			if(entity.machine >= 0){
				if(position == 4)return this.icons[entity.machine];
				if(position == 10)return this.icons[entity.machine];
				if(position == 12)return this.icons[entity.machine];
				if(position == 14)return this.icons[entity.machine];
				if(position == 16)return this.icons[entity.machine];
				if(position == 22)return this.icons[entity.machine];
			}
			return this.active;
		}
        return this.getIcon(par5, par1IBlockAccess.getBlockMetadata(par2, par3, par4));
    }
	
	public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9){
		TileEntityStructure entity = (TileEntityStructure) par1World.getBlockTileEntity(par2, par3, par4);
		if(entity != null){
			ItemStack current = par5EntityPlayer.getCurrentEquippedItem();
			if(current != null){
				if(current.getItem() instanceof ItemScrewdriver){
					if(par5EntityPlayer.isSneaking()){
						entity.removeMachine(par5EntityPlayer, par7, par8, par9);
						return true;
					}
				}else if(current.getItem() instanceof IMachineItem){
					if(entity.setMachine(((IMachineItem)current.getItem()).getMetadata(current), par5EntityPlayer)){
						par5EntityPlayer.inventory.decrStackSize(par5EntityPlayer.inventory.currentItem, 1);
						return true;
					}
				}
			}
			entity.activate(par5EntityPlayer, par7, par8, par9);
			return true;
		}
        return false;
    }
	
	public void breakBlock(World par1World, int par2, int par3, int par4, int par5, int par6)
    {
		TileEntityStructure entity = (TileEntityStructure) par1World.getBlockTileEntity(par2, par3, par4);
		if(entity != null){
			if(entity.hasStructure())entity.onBreak();
			entity.destroy();
		}
		super.breakBlock(par1World, par2, par3, par4, par5, par6);
    }
	
	public void onBlockAdded(World par1World, int par2, int par3, int par4) {
		TileEntityStructure entity = (TileEntityStructure) par1World.getBlockTileEntity(par2, par3, par4);
		if(entity != null && !par1World.isRemote){
			entity.build();
		}
	}
	
	public void randomDisplayTick(World par1World, int par2, int par3, int par4, Random par5Random) {
		TileEntityStructure machineBlock = (TileEntityStructure) par1World.getBlockTileEntity(par2, par3, par4);
		if(machineBlock != null && machineBlock.hasStructure()){
			machineBlock.onDisplayTick(par5Random);
		}
	}
	
	public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, int par5) {
		TileEntityStructure machineBlock = (TileEntityStructure)par1World.getBlockTileEntity(par2, par3, par4);
		if(machineBlock != null && machineBlock.hasStructure()){
			machineBlock.onNeighborChange(par5);
		}
	}

}
