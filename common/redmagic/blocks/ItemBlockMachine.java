package redmagic.blocks;

import redmagic.configuration.BlockIndex;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockMachine extends ItemBlock{
	public final static String[] subNames = {
		BlockIndex.MACHINE_FRAME_NAME,
		BlockIndex.MACHINE_FILTER_NAME,
		BlockIndex.MACHINE_FURNACE_NAME,
		BlockIndex.MACHINE_STORAGE_NAME
	};

	public ItemBlockMachine(int id) {
		super(id);
		this.setHasSubtypes(true);
		this.setUnlocalizedName(BlockIndex.MACHINE_NAME);
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
