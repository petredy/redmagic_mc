package com.petredy.redmagic.items;

import java.util.List;

import com.petredy.redmagic.Redmagic;
import com.petredy.redmagic.api.knowledge.IArtifact;
import com.petredy.redmagic.knowledge.Knowledge;
import com.petredy.redmagic.lib.ItemIndex;
import com.petredy.redmagic.lib.Reference;
import com.petredy.redmagic.utils.ArtifactUtils;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemArtifact extends Item implements IArtifact{

	public ItemArtifact(int par1) {
		super(par1);
		this.setUnlocalizedName(ItemIndex.ARTIFACT_NAME);
		this.setCreativeTab(Redmagic.tabRedmagic);
	}
	
	public void registerIcons(IconRegister iconRegister){
		itemIcon = iconRegister.registerIcon(Reference.MOD_ID + ":" + ItemIndex.ARTIFACT_NAME);
	}
	
	
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
		String name = ArtifactUtils.getKnowledge(par1ItemStack);
		if(name != null){
			par3List.add(name);
		}
	}
	
}
