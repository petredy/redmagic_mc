package redmagic.handlers;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import redmagic.blocks.BlockManager;
import redmagic.configuration.BlockIndex;
import redmagic.configuration.ItemIndex;
import redmagic.configuration.LogicIndex;
import redmagic.items.ItemManager;
import redmagic.lib.sacrifice.SacrificeChangesType;
import redmagic.lib.sacrifice.SacrificeRegistry;
import redmagic.lib.sacrifice.SacrificeSoul;
import redmagic.lib.sacrifice.SacrificeSoulFragment;
import redmagic.recipes.tree.TreeCraftingRegistry;
import redmagic.recipes.worktable.WorkTableRegistry;

public class RecipeHandler {

	public static void registry(){
		//----------------------------------------------------------------------
		// WorkTable Registry
		
		WorkTableRegistry.registerShapeless(new ItemStack(ItemManager.crafting.itemID, 2, ItemIndex.CRAFTING_SOUL_POWDER_ITEMDAMAGE), new ItemStack[]{
			new ItemStack(ItemManager.soulCrystal)
		});
		
		WorkTableRegistry.registerShapeless(new ItemStack(ItemManager.crafting.itemID, 1, ItemIndex.CRAFTING_REDSTONE_SHARD_ITEMDAMAGE), new ItemStack[]{
			new ItemStack(Block.blockRedstone)
		});
		
		WorkTableRegistry.registerShapeless(new ItemStack(ItemManager.crafting.itemID, 1, ItemIndex.CRAFTING_FLINT_IRON_INGOT_ITEMDAMAGE), new ItemStack[]{
			new ItemStack(Item.flint), new ItemStack(Item.ingotIron)
		});
		
		WorkTableRegistry.registerShapeless(new ItemStack(BlockManager.sapling.blockID, 1, BlockIndex.SAPLING_FRAGMENT_METADATA), new ItemStack[]{
			new ItemStack(Block.sapling), new ItemStack(ItemManager.soulNectar)
		});
		
		WorkTableRegistry.register(new ItemStack(BlockManager.sapling.blockID, 1, BlockIndex.SAPLING_TOKEE_METADATA), new ItemStack[]{
			 new ItemStack(Item.ingotIron), new ItemStack(Item.ingotIron), new ItemStack(Item.ingotIron),
			 new ItemStack(Item.ingotIron), new ItemStack(Block.sapling), new ItemStack(Item.ingotIron),
			 new ItemStack(Item.ingotIron), new ItemStack(Item.ingotIron), new ItemStack(Item.ingotIron)
		});
		
		WorkTableRegistry.registerShapeless(new ItemStack(ItemManager.broom), new ItemStack[]{
			new ItemStack(Item.stick), new ItemStack(Item.wheat)
		});
		
		WorkTableRegistry.register(new ItemStack(ItemManager.crafting.itemID, 1, ItemIndex.CRAFTING_REDSTONE_LOGIC_CORE_ITEMDAMAGE), new ItemStack[]{
			new ItemStack(Item.redstone), new ItemStack(ItemManager.crafting.itemID, 1, ItemIndex.CRAFTING_REDSTONE_SHARD_ITEMDAMAGE), new ItemStack(Item.redstone),
			new ItemStack(ItemManager.crafting.itemID, 1, ItemIndex.CRAFTING_REDSTONE_SHARD_ITEMDAMAGE), new ItemStack(ItemManager.soulCrystal), new ItemStack(ItemManager.crafting.itemID, 1, ItemIndex.CRAFTING_REDSTONE_SHARD_ITEMDAMAGE),
			new ItemStack(Item.redstone), new ItemStack(ItemManager.crafting.itemID, 1, ItemIndex.CRAFTING_REDSTONE_SHARD_ITEMDAMAGE), new ItemStack(Item.redstone)
		});
		
		WorkTableRegistry.registerShapeless(new ItemStack(ItemManager.soulNectar), new ItemStack[]{
			new ItemStack(ItemManager.crafting, 1, ItemIndex.CRAFTING_SOUL_POWDER_ITEMDAMAGE), new ItemStack(Item.glassBottle)
		});
		
		WorkTableRegistry.register(new ItemStack(ItemManager.redhole), new ItemStack[]{
			new ItemStack(Item.redstone), new ItemStack(Item.redstone), new ItemStack(Item.redstone),
			new ItemStack(Item.redstone), new ItemStack(Item.diamond), new ItemStack(Item.redstone),
			new ItemStack(Item.redstone), new ItemStack(Item.redstone), new ItemStack(Item.redstone)
		});
		
		WorkTableRegistry.register(new ItemStack(ItemManager.wrench), new ItemStack[]{
			new ItemStack(ItemManager.crafting, 1, ItemIndex.CRAFTING_FLINT_IRON_INGOT_ITEMDAMAGE), null, new ItemStack(ItemManager.crafting, 1, ItemIndex.CRAFTING_FLINT_IRON_INGOT_ITEMDAMAGE),
			new ItemStack(ItemManager.crafting, 1, ItemIndex.CRAFTING_FLINT_IRON_INGOT_ITEMDAMAGE), new ItemStack(ItemManager.crafting, 1, ItemIndex.CRAFTING_FLINT_IRON_INGOT_ITEMDAMAGE), new ItemStack(ItemManager.crafting, 1, ItemIndex.CRAFTING_FLINT_IRON_INGOT_ITEMDAMAGE),
			null, new ItemStack(ItemManager.crafting, 1, ItemIndex.CRAFTING_FLINT_IRON_INGOT_ITEMDAMAGE), null
		});
		
		WorkTableRegistry.register(new ItemStack(ItemManager.bankCrystal), new ItemStack[]{
			new ItemStack(Item.dyePowder, 1, 4), new ItemStack(Item.dyePowder, 1, 4), new ItemStack(Item.dyePowder, 1, 4),
			new ItemStack(Item.dyePowder, 1, 4), new ItemStack(ItemManager.crafting, 1, ItemIndex.CRAFTING_REDSTONE_LOGIC_CORE_ITEMDAMAGE), new ItemStack(Item.dyePowder, 1, 4),
			new ItemStack(Item.dyePowder, 1, 4), new ItemStack(Item.dyePowder, 1, 4), new ItemStack(Item.dyePowder, 1, 4)
		});
		
		WorkTableRegistry.register(new ItemStack(BlockManager.pipe, 16), new ItemStack[]{
			new ItemStack(ItemManager.crafting, 1, ItemIndex.CRAFTING_SOUL_INGOT_ITEMDAMAGE), new ItemStack(ItemManager.crafting, 1, ItemIndex.CRAFTING_FLINT_IRON_INGOT_ITEMDAMAGE), new ItemStack(ItemManager.crafting, 1, ItemIndex.CRAFTING_SOUL_INGOT_ITEMDAMAGE),
			new ItemStack(ItemManager.crafting, 1, ItemIndex.CRAFTING_FLINT_IRON_INGOT_ITEMDAMAGE), new ItemStack(Item.flint), new ItemStack(ItemManager.crafting, 1, ItemIndex.CRAFTING_FLINT_IRON_INGOT_ITEMDAMAGE),
			new ItemStack(ItemManager.crafting, 1, ItemIndex.CRAFTING_SOUL_INGOT_ITEMDAMAGE), new ItemStack(ItemManager.crafting, 1, ItemIndex.CRAFTING_FLINT_IRON_INGOT_ITEMDAMAGE), new ItemStack(ItemManager.crafting, 1, ItemIndex.CRAFTING_SOUL_INGOT_ITEMDAMAGE)
		});
		
		WorkTableRegistry.register(new ItemStack(BlockManager.extractor, 4, BlockIndex.EXTRACTOR_BASIC_METADATA), new ItemStack[]{
			new ItemStack(Block.stone), new ItemStack(ItemManager.crafting, 1, ItemIndex.CRAFTING_FLINT_IRON_INGOT_ITEMDAMAGE), new ItemStack(Block.stone),
			new ItemStack(ItemManager.crafting, 1, ItemIndex.CRAFTING_FLINT_IRON_INGOT_ITEMDAMAGE), new ItemStack(Block.chest), new ItemStack(ItemManager.crafting, 1, ItemIndex.CRAFTING_FLINT_IRON_INGOT_ITEMDAMAGE),
			new ItemStack(Block.stone), new ItemStack(ItemManager.crafting, 1, ItemIndex.CRAFTING_FLINT_IRON_INGOT_ITEMDAMAGE), new ItemStack(Block.stone)
		});
		
		WorkTableRegistry.register(new ItemStack(BlockManager.extractor, 1, BlockIndex.EXTRACTOR_COLLECTOR_METADATA), new ItemStack[]{
			new ItemStack(Block.stone), new ItemStack(ItemManager.crafting, 1, ItemIndex.CRAFTING_FLINT_IRON_INGOT_ITEMDAMAGE), new ItemStack(Block.stone),
			new ItemStack(ItemManager.crafting, 1, ItemIndex.CRAFTING_FLINT_IRON_INGOT_ITEMDAMAGE), new ItemStack(Block.pistonBase), new ItemStack(ItemManager.crafting, 1, ItemIndex.CRAFTING_FLINT_IRON_INGOT_ITEMDAMAGE),
			new ItemStack(Block.stone), new ItemStack(ItemManager.crafting, 1, ItemIndex.CRAFTING_FLINT_IRON_INGOT_ITEMDAMAGE), new ItemStack(Block.stone)
		});
		
		WorkTableRegistry.register(new ItemStack(BlockManager.collector), new ItemStack[]{
			new ItemStack(ItemManager.crafting, 1, ItemIndex.CRAFTING_FLINT_IRON_INGOT_ITEMDAMAGE), new ItemStack(ItemManager.crafting, 1, ItemIndex.CRAFTING_REDSTONE_LOGIC_CORE_ITEMDAMAGE), new ItemStack(ItemManager.crafting, 1, ItemIndex.CRAFTING_FLINT_IRON_INGOT_ITEMDAMAGE),
			new ItemStack(ItemManager.crafting, 1, ItemIndex.CRAFTING_REDSTONE_LOGIC_CORE_ITEMDAMAGE), new ItemStack(ItemManager.soulCrystal), new ItemStack(ItemManager.crafting, 1, ItemIndex.CRAFTING_REDSTONE_LOGIC_CORE_ITEMDAMAGE),
			new ItemStack(ItemManager.crafting, 1, ItemIndex.CRAFTING_FLINT_IRON_INGOT_ITEMDAMAGE), new ItemStack(ItemManager.crafting, 1, ItemIndex.CRAFTING_REDSTONE_LOGIC_CORE_ITEMDAMAGE), new ItemStack(ItemManager.crafting, 1, ItemIndex.CRAFTING_FLINT_IRON_INGOT_ITEMDAMAGE),
		});
		
		WorkTableRegistry.register(new ItemStack(BlockManager.crystal, 1, BlockIndex.CRYSTAL_INTELLIGENCE_METADATA), new ItemStack[]{
			new ItemStack(ItemManager.crafting, 1, ItemIndex.CRAFTING_SOUL_INGOT_ITEMDAMAGE), new ItemStack(ItemManager.crafting, 1, ItemIndex.CRAFTING_SOUL_INGOT_ITEMDAMAGE), new ItemStack(ItemManager.crafting, 1, ItemIndex.CRAFTING_SOUL_INGOT_ITEMDAMAGE),
			new ItemStack(ItemManager.crafting, 1, ItemIndex.CRAFTING_SOUL_INGOT_ITEMDAMAGE), new ItemStack(ItemManager.fragment, 1, ItemIndex.FRAGMENT_INTELLIGENCE_ITEMDAMAGE), new ItemStack(ItemManager.crafting, 1, ItemIndex.CRAFTING_SOUL_INGOT_ITEMDAMAGE),
			new ItemStack(ItemManager.crafting, 1, ItemIndex.CRAFTING_SOUL_INGOT_ITEMDAMAGE), new ItemStack(ItemManager.crafting, 1, ItemIndex.CRAFTING_SOUL_INGOT_ITEMDAMAGE), new ItemStack(ItemManager.crafting, 1, ItemIndex.CRAFTING_SOUL_INGOT_ITEMDAMAGE)
		});
		
		
		WorkTableRegistry.register(new ItemStack(BlockManager.crystal, 1, BlockIndex.CRYSTAL_STRENGTH_METADATA), new ItemStack[]{
			new ItemStack(ItemManager.crafting, 1, ItemIndex.CRAFTING_SOUL_INGOT_ITEMDAMAGE), new ItemStack(ItemManager.crafting, 1, ItemIndex.CRAFTING_SOUL_INGOT_ITEMDAMAGE), new ItemStack(ItemManager.crafting, 1, ItemIndex.CRAFTING_SOUL_INGOT_ITEMDAMAGE),
			new ItemStack(ItemManager.crafting, 1, ItemIndex.CRAFTING_SOUL_INGOT_ITEMDAMAGE), new ItemStack(ItemManager.fragment, 1, ItemIndex.FRAGMENT_STRENGTH_ITEMDAMAGE), new ItemStack(ItemManager.crafting, 1, ItemIndex.CRAFTING_SOUL_INGOT_ITEMDAMAGE),
			new ItemStack(ItemManager.crafting, 1, ItemIndex.CRAFTING_SOUL_INGOT_ITEMDAMAGE), new ItemStack(ItemManager.crafting, 1, ItemIndex.CRAFTING_SOUL_INGOT_ITEMDAMAGE), new ItemStack(ItemManager.crafting, 1, ItemIndex.CRAFTING_SOUL_INGOT_ITEMDAMAGE)
		});
		
		WorkTableRegistry.register(new ItemStack(BlockManager.crystal, 1, BlockIndex.CRYSTAL_CAPACITY_METADATA), new ItemStack[]{
			new ItemStack(ItemManager.crafting, 1, ItemIndex.CRAFTING_SOUL_INGOT_ITEMDAMAGE), new ItemStack(ItemManager.crafting, 1, ItemIndex.CRAFTING_SOUL_INGOT_ITEMDAMAGE), new ItemStack(ItemManager.crafting, 1, ItemIndex.CRAFTING_SOUL_INGOT_ITEMDAMAGE),
			new ItemStack(ItemManager.crafting, 1, ItemIndex.CRAFTING_SOUL_INGOT_ITEMDAMAGE), new ItemStack(ItemManager.fragment, 1, ItemIndex.FRAGMENT_CAPACITY_ITEMDAMAGE), new ItemStack(ItemManager.crafting, 1, ItemIndex.CRAFTING_SOUL_INGOT_ITEMDAMAGE),
			new ItemStack(ItemManager.crafting, 1, ItemIndex.CRAFTING_SOUL_INGOT_ITEMDAMAGE), new ItemStack(ItemManager.crafting, 1, ItemIndex.CRAFTING_SOUL_INGOT_ITEMDAMAGE), new ItemStack(ItemManager.crafting, 1, ItemIndex.CRAFTING_SOUL_INGOT_ITEMDAMAGE)
		});
		
		WorkTableRegistry.register(new ItemStack(BlockManager.crystal, 1, BlockIndex.CRYSTAL_ILLUSION_METADATA), new ItemStack[]{
			new ItemStack(ItemManager.crafting, 1, ItemIndex.CRAFTING_SOUL_INGOT_ITEMDAMAGE), new ItemStack(ItemManager.crafting, 1, ItemIndex.CRAFTING_SOUL_INGOT_ITEMDAMAGE), new ItemStack(ItemManager.crafting, 1, ItemIndex.CRAFTING_SOUL_INGOT_ITEMDAMAGE),
			new ItemStack(ItemManager.crafting, 1, ItemIndex.CRAFTING_SOUL_INGOT_ITEMDAMAGE), new ItemStack(ItemManager.fragment, 1, ItemIndex.FRAGMENT_ILLUSION_ITEMDAMAGE), new ItemStack(ItemManager.crafting, 1, ItemIndex.CRAFTING_SOUL_INGOT_ITEMDAMAGE),
			new ItemStack(ItemManager.crafting, 1, ItemIndex.CRAFTING_SOUL_INGOT_ITEMDAMAGE), new ItemStack(ItemManager.crafting, 1, ItemIndex.CRAFTING_SOUL_INGOT_ITEMDAMAGE), new ItemStack(ItemManager.crafting, 1, ItemIndex.CRAFTING_SOUL_INGOT_ITEMDAMAGE)
		});
		
		WorkTableRegistry.register(new ItemStack(BlockManager.bank), new ItemStack[]{
			new ItemStack(ItemManager.crafting, 1, ItemIndex.CRAFTING_SOUL_INGOT_ITEMDAMAGE), new ItemStack(ItemManager.crafting, 1, ItemIndex.CRAFTING_SOUL_INGOT_ITEMDAMAGE), new ItemStack(ItemManager.crafting, 1, ItemIndex.CRAFTING_SOUL_INGOT_ITEMDAMAGE),
			new ItemStack(Item.paper), new ItemStack(Block.chest), new ItemStack(Item.paper),
			new ItemStack(Block.planks), new ItemStack(Block.planks), new ItemStack(Block.planks) 
		});
		
		WorkTableRegistry.register(new ItemStack(BlockManager.mold), new ItemStack[]{
			new ItemStack(ItemManager.crafting, 1, ItemIndex.CRAFTING_SOUL_INGOT_ITEMDAMAGE), new ItemStack(ItemManager.crafting, 1, ItemIndex.CRAFTING_SOUL_INGOT_ITEMDAMAGE), new ItemStack(ItemManager.crafting, 1, ItemIndex.CRAFTING_SOUL_INGOT_ITEMDAMAGE),
			new ItemStack(ItemManager.crafting, 1, ItemIndex.CRAFTING_SOUL_INGOT_ITEMDAMAGE), null, new ItemStack(ItemManager.crafting, 1, ItemIndex.CRAFTING_SOUL_INGOT_ITEMDAMAGE),
			new ItemStack(ItemManager.crafting, 1, ItemIndex.CRAFTING_SOUL_INGOT_ITEMDAMAGE), new ItemStack(ItemManager.crafting, 1, ItemIndex.CRAFTING_SOUL_INGOT_ITEMDAMAGE), new ItemStack(ItemManager.crafting, 1, ItemIndex.CRAFTING_SOUL_INGOT_ITEMDAMAGE),
		});
		
		WorkTableRegistry.register(new ItemStack(ItemManager.glasses), new ItemStack[]{
			new ItemStack(ItemManager.crafting, 1, ItemIndex.CRAFTING_SOUL_POWDER_ITEMDAMAGE), new ItemStack(ItemManager.crafting, 1, ItemIndex.CRAFTING_SOUL_POWDER_ITEMDAMAGE), new ItemStack(ItemManager.crafting, 1, ItemIndex.CRAFTING_SOUL_POWDER_ITEMDAMAGE),
			new ItemStack(Item.stick), new ItemStack(Block.thinGlass), new ItemStack(Item.stick),
			new ItemStack(Item.stick), null, new ItemStack(Item.stick)
		});
		
		WorkTableRegistry.register(new ItemStack(ItemManager.pickaxe), new ItemStack[]{
			new ItemStack(ItemManager.crafting, 1, ItemIndex.CRAFTING_FLINT_IRON_INGOT_ITEMDAMAGE), new ItemStack(ItemManager.crafting, 1, ItemIndex.CRAFTING_FLINT_IRON_INGOT_ITEMDAMAGE), new ItemStack(ItemManager.crafting, 1, ItemIndex.CRAFTING_FLINT_IRON_INGOT_ITEMDAMAGE),
			null, new ItemStack(Item.stick), null,
			null, new ItemStack(Item.stick), null
		});
		
		WorkTableRegistry.register(new ItemStack(ItemManager.shovel), new ItemStack[]{
			null, new ItemStack(ItemManager.crafting, 1, ItemIndex.CRAFTING_FLINT_IRON_INGOT_ITEMDAMAGE), null,
			null, new ItemStack(Item.stick), null,
			null, new ItemStack(Item.stick), null
		});
		
		WorkTableRegistry.register(new ItemStack(ItemManager.axe), new ItemStack[]{
			new ItemStack(ItemManager.crafting, 1, ItemIndex.CRAFTING_FLINT_IRON_INGOT_ITEMDAMAGE), new ItemStack(ItemManager.crafting, 1, ItemIndex.CRAFTING_FLINT_IRON_INGOT_ITEMDAMAGE), null,
			new ItemStack(ItemManager.crafting, 1, ItemIndex.CRAFTING_FLINT_IRON_INGOT_ITEMDAMAGE), new ItemStack(Item.stick), null,
			null, new ItemStack(Item.stick), null
		});
		
		WorkTableRegistry.register(new ItemStack(ItemManager.hoe), new ItemStack[]{
			new ItemStack(ItemManager.crafting, 1, ItemIndex.CRAFTING_FLINT_IRON_INGOT_ITEMDAMAGE), new ItemStack(ItemManager.crafting, 1, ItemIndex.CRAFTING_FLINT_IRON_INGOT_ITEMDAMAGE), null,
			null, new ItemStack(Item.stick), null,
			null, new ItemStack(Item.stick), null
		});
		
		
		//----------------------------------------------------------------------
		//TreeCrafting Registry
		
		TreeCraftingRegistry.registerShapeless(new ItemStack(ItemManager.crafting, 4, ItemIndex.CRAFTING_REDSTONE_SHARD_ITEMDAMAGE), new ItemStack[]{
			new ItemStack(Block.blockRedstone)
		}, 10, 10, 10, 30, 15);
				
		
		//----------------------------------------------------------------------
		// Sacrifice Registry
		
		//Soul Sacrificing
		SacrificeRegistry.register(new SacrificeSoul());
		SacrificeRegistry.register(new SacrificeSoulFragment());
		
		SacrificeRegistry.register(new SacrificeChangesType(new ItemStack(Block.fenceIron), LogicIndex.SOUL_FILTER));
		SacrificeRegistry.register(new SacrificeChangesType(new ItemStack(Block.furnaceIdle), LogicIndex.SOUL_FURNACE));
		SacrificeRegistry.register(new SacrificeChangesType(new ItemStack(Item.bucketEmpty), LogicIndex.SOUL_STORAGE));
		SacrificeRegistry.register(new SacrificeChangesType(new ItemStack(Block.workbench), LogicIndex.SOUL_CRAFTING));
		
	}
	
}
