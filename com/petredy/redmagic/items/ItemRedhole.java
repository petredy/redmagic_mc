package com.petredy.redmagic.items;

import com.petredy.redmagic.Redmagic;
import com.petredy.redmagic.dimension.TeleporterOverworld;
import com.petredy.redmagic.dimension.TeleporterSoul;
import com.petredy.redmagic.lib.Dimensions;
import com.petredy.redmagic.lib.ItemIndex;
import com.petredy.redmagic.lib.Reference;
import com.petredy.redmagic.utils.ItemUtils;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.Teleporter;
import net.minecraft.world.World;

public class ItemRedhole extends Item{

	public ItemRedhole(int id){
		super(id);
		this.setUnlocalizedName(ItemIndex.REDHOLE_NAME);
		this.setCreativeTab(Redmagic.tabRedmagic);
	}
	
	public void registerIcons(IconRegister iconRegister){
		this.itemIcon = iconRegister.registerIcon(Reference.MOD_ID + ":" + ItemIndex.REDHOLE_NAME);
	}
	
	
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
		if(!par2World.isRemote){
			if(par2World.getTotalWorldTime() - ItemUtils.getLong(par1ItemStack, "time") > 100){
				EntityPlayerMP player = (EntityPlayerMP) par3EntityPlayer;
	        	MinecraftServer mServer = MinecraftServer.getServer();
	        	if(player.isSneaking()){
	        		player.mcServer.getConfigurationManager().transferPlayerToDimension(player, 0, new TeleporterOverworld(mServer.worldServerForDimension(0)));
	        	}else{
	        		player.mcServer.getConfigurationManager().transferPlayerToDimension(player, Dimensions.DIMENSION_ID, new TeleporterSoul(mServer.worldServerForDimension(Dimensions.DIMENSION_ID)));
	        	}
	        	ItemUtils.setLong(par1ItemStack, "time", par2World.getTotalWorldTime());
			}
		}
        return par1ItemStack;
    }
	
}
