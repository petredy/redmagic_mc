package com.petredy.redmagic.items;

import com.petredy.redmagic.Redmagic;
import com.petredy.redmagic.lib.ItemIndex;
import com.petredy.redmagic.lib.Reference;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemSieve extends Item{

	public ItemSieve(int par1) {
		super(par1);
		this.setUnlocalizedName(ItemIndex.SIEVE_NAME);
		this.setCreativeTab(Redmagic.tabRedmagic);
		this.setMaxStackSize(1);
		this.setHasSubtypes(true);
		this.setMaxDamage(100);
	}
	
	public void registerIcons(IconRegister iconRegister){
		itemIcon = iconRegister.registerIcon(Reference.MOD_ID + ":" + ItemIndex.SIEVE_NAME);
	}
	
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
		if(par3EntityPlayer.inventory.consumeInventoryItem(Block.gravel.blockID)){
			par3EntityPlayer.inventory.addItemStackToInventory(new ItemStack(Item.flint));
		}
        return par1ItemStack;
    }
	
	

}
