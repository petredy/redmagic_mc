package redmagic.handlers;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import redmagic.configuration.ItemIndex;
import redmagic.configuration.LogicIndex;
import redmagic.items.ItemManager;
import redmagic.lib.sacrifice.SacrificeChangesType;
import redmagic.lib.sacrifice.SacrificeRegistry;
import redmagic.lib.sacrifice.SacrificeSoul;
import redmagic.recipes.worktable.WorkTableRegistry;

public class RecipeHandler {

	public static void registry(){
		//----------------------------------------------------------------------
		// WorkTable Registry
		
		WorkTableRegistry.register(new ItemStack(Block.dispenser), new ItemStack[]{
			new ItemStack(Block.stone), new ItemStack(Block.stone), new ItemStack(Block.stone),
			new ItemStack(Block.stone), new ItemStack(Block.stone), new ItemStack(Block.stone),
			new ItemStack(Block.stone), new ItemStack(Block.stone), new ItemStack(Block.stone)
		});
		
		WorkTableRegistry.registerShapeless(new ItemStack(ItemManager.soulNectar), new ItemStack[]{
			new ItemStack(ItemManager.soulCrystal), new ItemStack(Item.glassBottle)
		});
		
		
		//----------------------------------------------------------------------
		// Sacrifice Registry
		
		//Soul Sacrificing
		SacrificeRegistry.register(new SacrificeSoul());
		
		
		SacrificeRegistry.register(new SacrificeChangesType(new ItemStack(Block.fenceIron), LogicIndex.SOUL_FILTER));
		SacrificeRegistry.register(new SacrificeChangesType(new ItemStack(Block.furnaceIdle), LogicIndex.SOUL_FURNACE));
		SacrificeRegistry.register(new SacrificeChangesType(new ItemStack(Item.bucketEmpty), LogicIndex.SOUL_STORAGE));
		
	}
	
}
