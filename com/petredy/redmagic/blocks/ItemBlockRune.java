package com.petredy.redmagic.blocks;

import com.petredy.redmagic.lib.BlockIndex;
import com.petredy.redmagic.lib.Runes;
import com.petredy.redmagic.tileentities.TileEntityRune;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class ItemBlockRune extends ItemBlock{
	
	public final static String[] subNames = {
		Runes.RUNE_RED,
		Runes.RUNE_BLUE,
		Runes.RUNE_GREEN
	};

	public ItemBlockRune(int id) {
		super(id);
		this.setHasSubtypes(true);
		this.setUnlocalizedName(BlockIndex.RUNE_NAME);
	}
	
	@Override
	public int getMetadata (int damageValue) {
		return damageValue;
	}
	
	@Override
	public String getUnlocalizedName(ItemStack itemstack) {
		return this.getUnlocalizedName() + "." + subNames[itemstack.getItemDamage()];
	}
	
	@Override
	public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10){
		TileEntity entity = par3World.getBlockTileEntity(par4, par5, par6);
		if(entity instanceof TileEntityRune){
			return true;
		}else{
			return super.onItemUse(par1ItemStack, par2EntityPlayer, par3World, par4, par5, par6, par7, par8, par9, par10);
		}
	}
	
}
