package com.petredy.redmagic.items;

import java.util.List;

import com.petredy.redmagic.Redmagic;
import com.petredy.redmagic.lib.ItemIndex;
import com.petredy.redmagic.utils.TradingUtils;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemTradingCrystal extends Item {

	public ItemTradingCrystal(int par1) {
		super(par1);
		this.setUnlocalizedName(ItemIndex.TRADING_CRYSTAL_NAME);
		this.setCreativeTab(Redmagic.tabRedmagic);
		this.setMaxStackSize(1);
	}
	
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
		if(par1ItemStack.stackTagCompound != null){
			par3List.add("Credits: " + TradingUtils.getMoney(par1ItemStack));
		}
	}

}
