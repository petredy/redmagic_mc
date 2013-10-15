package com.petredy.redmagic.items;

import java.util.List;

import com.petredy.redmagic.Redmagic;
import com.petredy.redmagic.lib.ItemIndex;
import com.petredy.redmagic.lib.Reference;
import com.petredy.redmagic.utils.LogUtils;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;

public class ItemCrafting extends Item {

	public String[] subNames = new String[]{
		ItemIndex.CRAFTING_INGOT_RHENIUM_NAME,
		ItemIndex.CRAFTING_NUGGET_RHENIUM_NAME,
		ItemIndex.CRAFTING_LOGIC_CORE_NAME,
		ItemIndex.CRAFTING_LOGIC_STORAGE_NAME,
		ItemIndex.CRAFTING_ENERGY_CONDENSER_NAME,
		ItemIndex.CRAFTING_PLATE_RHENIUM_NAME,
		ItemIndex.CRAFTING_CONCENTRATED_GRANITE_NAME
	};
	
	public Icon[] icons = new Icon[subNames.length];
	
	public ItemCrafting(int par1) {
		super(par1);
		this.setCreativeTab(Redmagic.tabRedmagic);
		this.setUnlocalizedName(ItemIndex.CRAFTING_NAME);
		this.setHasSubtypes(true);
	}

	@Override
	public void registerIcons(IconRegister iconRegister){
		for(int i = 0; i < icons.length; i++){
			icons[i] = iconRegister.registerIcon(Reference.MOD_ID + ":" + subNames[i]);
		}
	}
	
	public Icon getIconFromDamage(int par1)
    {
        return icons[par1];
    }
	
	@Override
	public void getSubItems(int par1, CreativeTabs par2CreativeTabs, List par3List)
    {
		for(int i = 0; i < subNames.length; i++){
			par3List.add(new ItemStack(par1, 1, i));
		}
    }
	
	public String getUnlocalizedName(ItemStack par1ItemStack)
    {
		return this.getUnlocalizedName() + "." + subNames[par1ItemStack.getItemDamage()];
    }
	
	
	
}
