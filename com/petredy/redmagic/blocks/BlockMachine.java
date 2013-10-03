package com.petredy.redmagic.blocks;

import java.util.List;

import com.petredy.redmagic.Redmagic;
import com.petredy.redmagic.api.machines.IMachineItem;
import com.petredy.redmagic.items.ItemMachine;
import com.petredy.redmagic.lib.BlockIndex;
import com.petredy.redmagic.lib.Guis;
import com.petredy.redmagic.lib.Reference;
import com.petredy.redmagic.tileentities.TileEntityMachine;
import com.petredy.redmagic.utils.LogUtils;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockMachine extends BlockContainer{

	public Icon[] icons = new Icon[ItemMachine.subNames.length];
	
	protected BlockMachine(int par1) {
		super(par1, Material.piston);
		this.setUnlocalizedName(BlockIndex.MACHINE_NAME);
		this.setCreativeTab(Redmagic.tabRedmagic);
	}
	
	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntityMachine();
	}
	
	public void registerIcons(IconRegister iconRegister){
		for(int i = 0; i < ItemMachine.subNames.length; i++){
			icons[i] = iconRegister.registerIcon(Reference.MOD_ID + ":" + BlockIndex.MACHINE_NAME + "." + ItemMachine.subNames[i]);
		}
	}
	
	public Icon getIcon(int side, int metadata){
		return icons[0];
	}
	
	public Icon getBlockTexture(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
    {
		TileEntityMachine machine = (TileEntityMachine) par1IBlockAccess.getBlockTileEntity(par2, par3, par4);
		if(machine != null){
			return icons[machine.machines[par5] != null ? machine.machines[par5].getMetadata() : 0];
		}else{
			return this.getIcon(par5, par1IBlockAccess.getBlockMetadata(par2, par3, par4));
		}
    }
	
	public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLivingBase, ItemStack par6ItemStack) {
		TileEntityMachine machine = (TileEntityMachine) par1World.getBlockTileEntity(par2, par3, par4);
		if(machine != null){
			
		}
	}
	
	public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
    {
		TileEntityMachine machineBlock = (TileEntityMachine) par1World.getBlockTileEntity(par2, par3, par4);
		if(machineBlock != null){
			ItemStack current = par5EntityPlayer.getCurrentEquippedItem();
			if(current != null && current.getItem() instanceof IMachineItem){
				if(machineBlock.setMachine(((IMachineItem)current.getItem()).getMetadata(current), par6, par5EntityPlayer)){
					par5EntityPlayer.inventory.decrStackSize(par5EntityPlayer.inventory.currentItem, 1);
					return true;
				}
			}else{
				if(machineBlock.activate(par5EntityPlayer, par6, par7, par8, par9))return true;
				else {
					par5EntityPlayer.openGui(Redmagic.instance, Guis.MACHINE, par1World, par2, par3, par4);
					return true;
				}
			}
		}
        return false;
    }
	
}
