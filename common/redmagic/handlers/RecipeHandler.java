package redmagic.handlers;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import redmagic.configuration.ItemIndex;
import redmagic.items.ItemManager;
import redmagic.recipes.worktable.WorkTableRegistry;
import redmagic.taming.TamingRegistry;

public class RecipeHandler {

	public static void registry(){
		WorkTableRegistry.register(new ItemStack(Block.dispenser), new ItemStack[]{
			new ItemStack(Block.stone), new ItemStack(Block.stone), new ItemStack(Block.stone),
			new ItemStack(Block.stone), new ItemStack(Block.stone), new ItemStack(Block.stone),
			new ItemStack(Block.stone), new ItemStack(Block.stone), new ItemStack(Block.stone)
		});
		
		WorkTableRegistry.registerShapeless(new ItemStack(ItemManager.soulNectar), new ItemStack[]{
			new ItemStack(ItemManager.soulCrystal), new ItemStack(Item.glassBottle)
		});
		
		TamingRegistry.registerProcess(new ItemStack(ItemManager.machine, 1,ItemIndex.MACHINE_SOUL_FILTER_ITEMDAMAGE),new ItemStack[]{
			new ItemStack(Item.bucketWater), new ItemStack(Item.bucketLava), new ItemStack(Item.glassBottle), new ItemStack(Item.bowlEmpty)
		}, 3);
	}
	
}
