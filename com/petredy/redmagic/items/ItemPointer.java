package com.petredy.redmagic.items;

import com.petredy.redmagic.Redmagic;
import com.petredy.redmagic.entities.EntitySoulman;
import com.petredy.redmagic.entities.ai.FocusTarget;
import com.petredy.redmagic.lib.ItemIndex;
import com.petredy.redmagic.lib.Reference;
import com.petredy.redmagic.utils.ItemUtils;
import com.petredy.redmagic.utils.LogUtils;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemPointer extends Item{

	public ItemPointer(int par1) {
		super(par1);
		this.setUnlocalizedName(ItemIndex.POINTER_NAME);
		this.setCreativeTab(Redmagic.tabRedmagic);
	}
	
	public void registerIcons(IconRegister iconRegister){
		this.itemIcon = iconRegister.registerIcon(Reference.MOD_ID + ":" + ItemIndex.POINTER_NAME);
	}
	
	
	public boolean itemInteractionForEntity(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, EntityLivingBase par3EntityLivingBase)
    {
		if(par3EntityLivingBase instanceof EntitySoulman){
			ItemUtils.setInteger(par2EntityPlayer.getCurrentEquippedItem(), "pointerEntity", par3EntityLivingBase.entityId);
        	return true;
		}
		return false;
    }
	
	public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10)
    {
		EntitySoulman man = (EntitySoulman) par3World.getEntityByID(ItemUtils.getInteger(par1ItemStack, "pointerEntity"));
		if(man != null)man.focus = new FocusTarget(par4, par5, par6);
        return true;
    }
	

}
