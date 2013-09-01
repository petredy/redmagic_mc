package redmagic.handlers;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import redmagic.items.ItemManager;
import redmagic.lib.crafting.CraftingManager;
import redmagic.lib.talent.Talent;

public class CraftingHandler {
	public static void init(){
		CraftingManager.addShapedRecipe(new ItemStack(ItemManager.redhole), Talent.life, new ItemStack[]{
			new ItemStack(Item.redstone), new ItemStack(Item.redstone), new ItemStack(Item.redstone),
			new ItemStack(Item.redstone), new ItemStack(Item.diamond), new ItemStack(Item.redstone),
			new ItemStack(Item.redstone), new ItemStack(Item.redstone), new ItemStack(Item.redstone)
		});
	}
}
