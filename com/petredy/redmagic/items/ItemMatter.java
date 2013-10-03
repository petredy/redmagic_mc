package com.petredy.redmagic.items;

import java.util.List;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;

import com.petredy.redmagic.Redmagic;
import com.petredy.redmagic.lib.ItemIndex;
import com.petredy.redmagic.lib.Machines;
import com.petredy.redmagic.lib.Reference;

public class ItemMatter extends Item{

	public final static String[] subNames = {
		ItemIndex.MATTER_LEFTOVER_NAME,
		ItemIndex.MATTER_INSTABLE_NAME,
		ItemIndex.MATTER_STABLE_NAME
	};
	
	public Icon[] icons = new Icon[subNames.length];
	
	public ItemMatter(int id){
		super(id);
		this.setUnlocalizedName(ItemIndex.MATTER_NAME);
		this.setCreativeTab(Redmagic.tabRedmagic);
		this.setHasSubtypes(true);
	}
	
	public void registerIcons(IconRegister iconRegister){
		for(int i = 0; i < subNames.length; i++){
			icons[i] = iconRegister.registerIcon(Reference.MOD_ID + ":" + ItemIndex.MATTER_NAME + "." + ItemMachine.subNames[i]);
		}
	}
	
	public Icon getIconFromDamage(int metadata){
		metadata = Math.max(1, Math.min(subNames.length - 1, metadata));
		return icons[metadata];
	}
	
	public void getSubItems(int par1, CreativeTabs par2CreativeTabs, List par3List)
    {
		for(int i = 0; i <subNames.length; i++){
			par3List.add(new ItemStack(par1, 1, i));
		}
    }
	
	@Override
	public String getUnlocalizedName(ItemStack itemstack) {
		return this.getUnlocalizedName() + "." + subNames[itemstack.getItemDamage()];
	}
}
