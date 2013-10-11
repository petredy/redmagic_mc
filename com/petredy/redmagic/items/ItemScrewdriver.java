package com.petredy.redmagic.items;

import com.petredy.redmagic.Redmagic;
import com.petredy.redmagic.api.IScrewdriver;
import com.petredy.redmagic.lib.ItemIndex;
import com.petredy.redmagic.lib.Reference;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

public class ItemScrewdriver extends Item implements IScrewdriver{

	public ItemScrewdriver(int par1) {
		super(par1);
		this.setUnlocalizedName(ItemIndex.SCREWDRIVER_NAME);
		this.setCreativeTab(Redmagic.tabRedmagic);
		this.setMaxStackSize(1);
	}
	
	@Override
	public void registerIcons(IconRegister iconRegister){
		this.itemIcon = iconRegister.registerIcon(Reference.MOD_ID + ":" + ItemIndex.SCREWDRIVER_NAME);
	}
	
	public boolean shouldPassSneakingClickToBlock(World par2World, int par4, int par5, int par6)
    {
        return true;
    }
	
	

}
