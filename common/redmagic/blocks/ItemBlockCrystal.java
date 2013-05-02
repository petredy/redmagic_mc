package redmagic.blocks;

import redmagic.configuration.BlockIndex;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockCrystal extends ItemBlock{
	public static String[] subNames = new String[]{
			BlockIndex.CRYSTAL_INTELLIGENCE_NAME,
			BlockIndex.CRYSTAL_STRENGTH_NAME,
			BlockIndex.CRYSTAL_CAPACITY_NAME,
			BlockIndex.CRYSTAL_ILLUSION_NAME
		};

	public ItemBlockCrystal(int id) {
		super(id);
		this.setHasSubtypes(true);
		this.setUnlocalizedName(BlockIndex.CRYSTAL_NAME);
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
