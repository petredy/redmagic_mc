package redmagic.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import redmagic.configuration.BlockIndex;
import redmagic.helpers.LogHelper;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.Configuration;

public class BlockManager {
	
	public static Block decoration;
	public static Block rune;
	public static Block rheniumOre;
	public static Block altar;
	public static Block construction;
	
	public static void init(){
		decoration = new BlockDecoration(BlockIndex.DECORATION_ID);
		rune = new BlockRune(BlockIndex.RUNE_ID);
		rheniumOre = new BlockRheniumOre(BlockIndex.RHENIUM_ID);
		altar = new BlockAltar(BlockIndex.ALTAR_ID);
		construction = new BlockConstructionTable(BlockIndex.CONSTRUCTION_TABLE_ID);

		GameRegistry.registerBlock(decoration, ItemBlockDecoration.class, BlockIndex.DECORATION_NAME);
		GameRegistry.registerBlock(rune, ItemBlockRune.class, BlockIndex.RUNE_NAME);
		GameRegistry.registerBlock(rheniumOre, BlockIndex.RHENIUM_NAME);
		GameRegistry.registerBlock(altar, BlockIndex.ALTAR_NAME);
		GameRegistry.registerBlock(construction, BlockIndex.CONSTRUCTION_TABLE_NAME);
		
		registerRecipes();
	}
	
	public static void registerRecipes(){
		
		// Marble Bricks
		GameRegistry.addShapelessRecipe(new ItemStack(decoration, 4, BlockIndex.MARBLE_BRICKS_METADATA), new Object[]{
			new ItemStack(decoration, 1, BlockIndex.MARBLE_METADATA),
			new ItemStack(decoration, 1, BlockIndex.MARBLE_METADATA),
			new ItemStack(decoration, 1, BlockIndex.MARBLE_METADATA),
			new ItemStack(decoration, 1, BlockIndex.MARBLE_METADATA)
		});
		
	}
	
	public static void config(Configuration config){
		BlockIndex.DECORATION_ID = config.getBlock(BlockIndex.DECORATION_NAME, BlockIndex.DECORATION_DEFAULT_ID).getInt(BlockIndex.DECORATION_DEFAULT_ID);
		BlockIndex.RUNE_ID = config.getBlock(BlockIndex.RUNE_NAME, BlockIndex.RUNE_DEFAULT_ID).getInt(BlockIndex.RUNE_DEFAULT_ID);
		BlockIndex.RHENIUM_ID = config.getBlock(BlockIndex.RHENIUM_NAME, BlockIndex.RHENIUM_DEFAULT_ID).getInt(BlockIndex.RHENIUM_DEFAULT_ID);
		BlockIndex.ALTAR_ID = config.getBlock(BlockIndex.ALTAR_NAME, BlockIndex.ALTAR_DEFAULT_ID).getInt(BlockIndex.ALTAR_DEFAULT_ID);
		BlockIndex.CONSTRUCTION_TABLE_ID = config.getBlock(BlockIndex.CONSTRUCTION_TABLE_NAME, BlockIndex.CONSTRUCTION_TABLE_DEFAULT_ID).getInt(BlockIndex.CONSTRUCTION_TABLE_DEFAULT_ID);
	}
	
}
