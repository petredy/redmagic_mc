package com.petredy.redmagic.items;

import com.petredy.redmagic.Redmagic;
import com.petredy.redmagic.api.knowledge.IArtifact;
import com.petredy.redmagic.lib.ItemIndex;
import com.petredy.redmagic.lib.Reference;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;

public class ItemArtifact extends Item implements IArtifact{

	public ItemArtifact(int par1) {
		super(par1);
		this.setUnlocalizedName(ItemIndex.ARTIFACT_NAME);
		this.setCreativeTab(Redmagic.tabRedmagic);
	}
	
	public void registerIcons(IconRegister iconRegister){
		itemIcon = iconRegister.registerIcon(Reference.MOD_ID + ":" + ItemIndex.ARTIFACT_NAME);
	}
	
}
