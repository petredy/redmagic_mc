package com.petredy.redmagic.items;

import com.petredy.redmagic.Redmagic;
import com.petredy.redmagic.api.ITribologicalItem;
import com.petredy.redmagic.lib.Reference;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemTribological extends Item implements ITribologicalItem{

	
	public String name;
	public float strength; 
	public static int MAX_DAMAGE = 1000000;
	public static final int DIVISOR = 10000;
	
	public ItemTribological(int par1, String name, float strength) {
		super(par1);
		this.setUnlocalizedName(name);
		this.setCreativeTab(Redmagic.tabRedmagic);
		this.setMaxStackSize(1);
		this.setMaxDamage(MAX_DAMAGE);
		this.strength = strength;
		this.name = name;
	}
	
	public void registerIcons(IconRegister iconRegister){
		this.itemIcon = iconRegister.registerIcon(Reference.MOD_ID + ":" + name);
	}

	@Override
	public int getValue(ItemStack stack) {
		return stack.getItemDamage();
	}

	@Override
	public void setValue(ItemStack stack, int value) {
		stack.setItemDamage(value);
	}

	@Override
	public float getStrength(ItemStack stack) {
		return strength;
	}

}
