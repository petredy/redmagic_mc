package redmagic.api.classes;

import net.minecraft.item.ItemStack;

public interface IEducationClass {
	public abstract int getIntelligenceModifier(ItemStack stack);
	public abstract int getStrengthModifier(ItemStack stack);
	public abstract int getCapacityModifier(ItemStack stack);
	public abstract int getIllusionModifier(ItemStack stack);
	public abstract int getResistenceModifier(ItemStack stack);
}
