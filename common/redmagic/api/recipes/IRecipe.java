package redmagic.api.recipes;

import net.minecraft.item.ItemStack;

public interface IRecipe {
	public abstract ItemStack getOutput();
	public abstract ItemStack[] getInput();
}
