package com.petredy.redmagic.items;

import com.petredy.redmagic.Redmagic;
import com.petredy.redmagic.lib.ItemIndex;
import com.petredy.redmagic.lib.Reference;
import com.petredy.redmagic.network.PacketHandler;
import com.petredy.redmagic.redenergy.EnergyMap;
import com.petredy.redmagic.redenergy.RedEnergy;
import com.petredy.redmagic.redenergy.Redkey;
import com.petredy.redmagic.utils.LogUtils;

import cpw.mods.fml.common.network.PacketDispatcher;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemRedmeter extends Item{

	public ItemRedmeter(int par1) {
		super(par1);
		this.setUnlocalizedName(ItemIndex.REDMETER_NAME);
		this.setCreativeTab(Redmagic.tabRedmagic);
		this.setMaxStackSize(1);
	}
	
	public void registerIcons(IconRegister iconRegister){
		this.itemIcon = iconRegister.registerIcon(Reference.MOD_ID + ":" + ItemIndex.REDMETER_NAME);
	}
	
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
		if(!par2World.isRemote){
			RedEnergy en = EnergyMap.getEnergy(Redkey.get(par2World.provider.dimensionId, (int)(par3EntityPlayer.posX / 16), (int)(par3EntityPlayer.posZ / 16)));
			if(en != null){
				par3EntityPlayer.addChatMessage("Chunk: " + (int)(par3EntityPlayer.posX / 16) + "/" + (int)(par3EntityPlayer.posZ / 16) + " | red: " + en.amount);
			}else{
				par3EntityPlayer.addChatMessage("Chunk: " + (int)(par3EntityPlayer.posX / 16) + "/" + (int)(par3EntityPlayer.posZ / 16) + " | no red here");
			}
		}
		return par1ItemStack;
    }

}
