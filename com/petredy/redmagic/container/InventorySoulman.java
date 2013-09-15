package com.petredy.redmagic.container;

import com.petredy.redmagic.entities.EntitySoulman;

import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class InventorySoulman extends InventoryBasic{

	public ItemStack[] armor = new ItemStack[4];
	public ItemStack current = new ItemStack(Item.swordDiamond);
	public InventorySoulman(int par3) {
		super(EntitySoulman.class.getSimpleName(), false, par3);
	}

	public ItemStack armorItemInSlot(int i) {
		return armor[i];
	}

	public ItemStack getCurrentItem() {
		return current;
	}

}
