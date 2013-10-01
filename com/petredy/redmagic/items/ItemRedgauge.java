package com.petredy.redmagic.items;

import com.petredy.redmagic.Redmagic;
import com.petredy.redmagic.lib.ItemIndex;
import com.petredy.redmagic.redenergy.EnergyMap;
import com.petredy.redmagic.redenergy.RedEnergy;
import com.petredy.redmagic.redenergy.Redkey;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemRedgauge extends Item{

	public ItemRedgauge(int par1) {
		super(par1);
		this.setUnlocalizedName(ItemIndex.REDGAUGE_NAME);
		this.setCreativeTab(Redmagic.tabRedmagic);
		this.setMaxStackSize(1);
	}
	
	public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10)
    {
		RedEnergy en = EnergyMap.getEnergy(Redkey.get(par4, par5, par6));
		if(en != null){
			par2EntityPlayer.addChatMessage("red: " + en.amount);
		}else{
			par2EntityPlayer.addChatMessage("no red here");
		}
        return true;
    }
	
	

}
