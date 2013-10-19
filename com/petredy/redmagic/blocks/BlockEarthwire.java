package com.petredy.redmagic.blocks;

import java.util.List;
import java.util.Random;

import com.petredy.redmagic.Redmagic;
import com.petredy.redmagic.api.IScrewdriver;
import com.petredy.redmagic.items.Items;
import com.petredy.redmagic.lib.BlockIndex;
import com.petredy.redmagic.lib.Reference;
import com.petredy.redmagic.lib.Rendering;
import com.petredy.redmagic.lib.Sounds;
import com.petredy.redmagic.redenergy.EnergyMap;
import com.petredy.redmagic.tileentities.TileEntityCage;
import com.petredy.redmagic.tileentities.TileEntityEarthwire;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class BlockEarthwire extends BlockContainer{

	public BlockEarthwire(int par1) {
		super(par1, Material.rock);
		this.setUnlocalizedName(BlockIndex.EARTHWIRE_NAME);
		this.setCreativeTab(Redmagic.tabRedmagic);
		this.setHardness(BlockIndex.DEFAULT_HARDNESS);
		
	}
	
	public void registerIcons(IconRegister iconRegister){
		this.blockIcon = iconRegister.registerIcon(Reference.MOD_ID + ":" + BlockIndex.EARTHWIRE_NAME);
	}	

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntityEarthwire();
	}
	
	public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
    {
		ItemStack current = par5EntityPlayer.getCurrentEquippedItem();
		if(current != null && current.getItem() instanceof IScrewdriver){
			if(EnergyMap.existsConsumer(par2, par3, par4)){
				par5EntityPlayer.addChatMessage("EnergyConsumer registered");
			}
		}
        return false;
    }


}
