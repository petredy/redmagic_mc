package com.petredy.redmagic.items;

import com.petredy.redmagic.Redmagic;
import com.petredy.redmagic.lib.ItemIndex;
import com.petredy.redmagic.lib.Reference;
import com.petredy.redmagic.redenergy.EnergyMap;
import com.petredy.redmagic.redenergy.RedEnergy;
import com.petredy.redmagic.redenergy.Redkey;
import com.petredy.redmagic.utils.LogUtils;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemRedgauge extends Item{

	public ItemRedgauge(int par1) {
		super(par1);
		this.setUnlocalizedName(ItemIndex.REDGAUGE_NAME);
		this.setCreativeTab(Redmagic.tabRedmagic);
		this.setMaxStackSize(1);
	}
	
	public void registerIcons(IconRegister iconRegister){
		this.itemIcon = iconRegister.registerIcon(Reference.MOD_ID + ":" + ItemIndex.REDGAUGE_NAME);
	}
	
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
		if(!par2World.isRemote){
			RedEnergy en = EnergyMap.getEnergy(Redkey.get(par2World.provider.dimensionId, par3EntityPlayer.chunkCoordX, par3EntityPlayer.chunkCoordZ));
			if(en != null){
				par3EntityPlayer.addChatMessage("Chunk: " + par3EntityPlayer.chunkCoordX + "/" + par3EntityPlayer.chunkCoordZ + " | red: " + en.amount);
			}else{
				par3EntityPlayer.addChatMessage("Chunk: " + par3EntityPlayer.chunkCoordX + "/" + par3EntityPlayer.chunkCoordZ + " | no red here");
			}
		}
		return par1ItemStack;
    }

}
