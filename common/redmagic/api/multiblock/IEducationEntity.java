package redmagic.api.multiblock;

import net.minecraft.item.ItemStack;

public interface IEducationEntity extends IMultiEntity{

	public abstract void buildStructure();
	public abstract void destroyStructure();
	public abstract void saveSoul(ItemStack currentItem);
	public abstract ItemStack getSoul();
	public abstract void dropAll();
}
