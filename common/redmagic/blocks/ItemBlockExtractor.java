package redmagic.blocks;

import redmagic.configuration.BlockIndex;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockExtractor extends ItemBlock{
	public final static String[] subNames = {
		BlockIndex.EXTRACTOR_BASIC_NAME,
		BlockIndex.EXTRACTOR_COLLECTOR_NAME
	};

	public ItemBlockExtractor(int id) {
		super(id);
		this.setHasSubtypes(true);
		this.setUnlocalizedName(BlockIndex.EXTRACTOR_NAME);
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
