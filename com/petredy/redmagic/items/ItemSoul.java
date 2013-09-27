package com.petredy.redmagic.items;

import com.petredy.redmagic.Redmagic;
import com.petredy.redmagic.entities.EntitySoulman;
import com.petredy.redmagic.lib.ItemIndex;
import com.petredy.redmagic.lib.Reference;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemSoul extends Item{

	public ItemSoul(int par1) {
		super(par1);
		this.setUnlocalizedName(ItemIndex.SOUL_NAME);
		this.setCreativeTab(Redmagic.tabRedmagic);
		this.setMaxStackSize(1);
	}
	
	public void registerIcons(IconRegister iconRegister){
		this.itemIcon = iconRegister.registerIcon(Reference.MOD_ID + ":" + ItemIndex.SOUL_NAME);
	}
	
	public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10)
    {
		EntitySoulman man = new EntitySoulman(par3World);
		man.setPosition(par4 + 0.5, par5 + 1, par6 + 0.5);
		if(!par3World.isRemote)par3World.spawnEntityInWorld(man);
		if(!par2EntityPlayer.capabilities.isCreativeMode)par2EntityPlayer.inventory.setInventorySlotContents(par2EntityPlayer.inventory.currentItem, null);
		return true;
    }

}
