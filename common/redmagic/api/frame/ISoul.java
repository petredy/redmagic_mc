package redmagic.api.frame;

import net.minecraft.item.ItemStack;

public interface ISoul {
	
	public abstract void setIntelligence(ItemStack stack, int intelligence);
	public abstract int getIntelligence(ItemStack stack);
	
	public abstract void setStrength(ItemStack stack, int strength);
	public abstract int getStrength(ItemStack stack);
	
	public abstract void setCapacity(ItemStack stack, int capacity);
	public abstract int getCapacity(ItemStack stack);
	
	public abstract void setIllusion(ItemStack stack, int illusion);
	public abstract int getIllusion(ItemStack stack);
	
	public abstract void setSatisfaction(ItemStack stack, int satisfaction);
	public abstract int getSatisfaction(ItemStack stack);
	
	public abstract void setType(ItemStack stack, int type);
	public abstract int getType(ItemStack stack);
}
