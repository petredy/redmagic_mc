package redmagic.blocks;

import redmagic.configuration.BlockIndex;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockEntity extends ItemBlock{
	public final static String[] subNames = {
		BlockIndex.ENTITY_PIG_NAME
	};

	public ItemBlockEntity(int id) {
		super(id);
		this.setHasSubtypes(true);
		this.setUnlocalizedName(BlockIndex.MOLD_NAME);
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
