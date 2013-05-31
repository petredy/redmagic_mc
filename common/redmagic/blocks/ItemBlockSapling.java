package redmagic.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import redmagic.configuration.BlockIndex;
import redmagic.configuration.Reference;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;

public class ItemBlockSapling extends ItemBlock{
	public final static String[] subNames = {
		BlockIndex.SAPLING_FRAGMENT_NAME,
		BlockIndex.SAPLING_TOKEE_NAME
	};
	

	public ItemBlockSapling(int id) {
		super(id);
		this.setHasSubtypes(true);
		this.setUnlocalizedName(BlockIndex.SAPLING_NAME);
	}
	
	@Override
	public Icon getIconFromDamage(int par1)
    {
		return BlockManager.sapling.getIcon(0,  par1);
    }
	
	@Override
	public int getMetadata (int damageValue) {
		return damageValue;
	}
	
	@Override
	public String getUnlocalizedName(ItemStack itemstack) {
		return this.getUnlocalizedName() + "." + subNames[itemstack.getItemDamage()];
	}
}
