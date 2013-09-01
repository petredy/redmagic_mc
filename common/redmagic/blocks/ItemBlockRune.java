package redmagic.blocks;

import redmagic.configuration.BlockIndex;
import redmagic.helpers.LogHelper;
import redmagic.tileentities.TileEntityRune;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class ItemBlockRune extends ItemBlock{
	
	public final static String[] subNames = {
		BlockIndex.RUNE_TABLE,
		BlockIndex.RUNE_RED,
		BlockIndex.RUNE_BLUE,
		BlockIndex.RUNE_GREEN
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
		if(par1ItemStack.getItemDamage() == BlockIndex.RUNE_TABLE_METADATA){
			return super.onItemUse(par1ItemStack, par2EntityPlayer, par3World, par4, par5, par6, par7, par8, par9, par10);
		}
		return false;
	}
	
}
