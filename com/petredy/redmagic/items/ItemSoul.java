package com.petredy.redmagic.items;

import com.petredy.redmagic.Redmagic;
import com.petredy.redmagic.entities.EntitySoul;
import com.petredy.redmagic.lib.ItemIndex;
import com.petredy.redmagic.lib.Reference;
import com.petredy.redmagic.utils.LogUtils;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class ItemSoul extends Item{
	
	
	public ItemSoul(int id){
		super(id);
		this.setCreativeTab(Redmagic.tabRedmagic);
		this.setUnlocalizedName(ItemIndex.SOUL_NAME);
	}
	
	public void registerIcons(IconRegister iconRegister){
		this.itemIcon = iconRegister.registerIcon(Reference.MOD_ID + ":" + ItemIndex.SOUL_NAME);
	}

	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
		MovingObjectPosition location = this.getMovingObjectPositionFromPlayer(par2World, par3EntityPlayer, true);
		if(location != null && !par2World.isRemote){
			EntitySoul soul = new EntitySoul(par2World);
			soul.setPosition(location.blockX + 0.5, location.blockY + 1, location.blockZ + 0.5);
			//par2World.spawnEntityInWorld(soul);
			LogUtils.log("spawn");
		}
		return par1ItemStack;
    }
	
}
