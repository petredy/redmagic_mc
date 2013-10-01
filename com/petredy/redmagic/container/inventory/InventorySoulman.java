package com.petredy.redmagic.container.inventory;

import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.ItemStack;

public class InventorySoulman extends InventoryBasic {

	public ItemStack[] equip = new ItemStack[5];
	
	public InventorySoulman() {
		super("soulman.inventory", false, 6);
	}

	public ItemStack armorItemInSlot(int i) {
		return equip[i];
	}

	public ItemStack getCurrentItem() {
		return equip[0];
	}

}
