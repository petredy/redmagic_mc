package redmagic.blocks;

import redmagic.configuration.BlockIndex;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockEducation extends ItemBlock{
	public final static String[] subNames = {
		BlockIndex.EDUCATION_BASIC_NAME,
		BlockIndex.EDUCATION_RESISTENCE_NAME
	};

	public ItemBlockEducation(int id) {
		super(id);
		this.setHasSubtypes(true);
		this.setUnlocalizedName(BlockIndex.EDUCATION_NAME);
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
