package com.petredy.redmagic.items;

import com.petredy.redmagic.Redmagic;
import com.petredy.redmagic.lib.ItemIndex;
import com.petredy.redmagic.lib.Reference;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;

public class ItemSoul extends Item{
	
	
	public ItemSoul(int id){
		super(id);
		this.setCreativeTab(Redmagic.tabRedmagic);
		this.setUnlocalizedName(ItemIndex.SOUL_NAME);
	}
	
	public void registerIcons(IconRegister iconRegister){
		this.itemIcon = iconRegister.registerIcon(Reference.MOD_ID + ":" + ItemIndex.SOUL_NAME);
	}

}
