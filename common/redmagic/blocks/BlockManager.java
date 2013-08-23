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
	
	
	public static void init(){
		decoration = new BlockDecoration(BlockIndex.DECORATION_ID);
		
		registerRecipes();
	}
	
	public static void registerRecipes(){
		
	}
	
	public static void config(Configuration config){
		BlockIndex.DECORATION_ID = config.getItem(BlockIndex.DECORATION_NAME, BlockIndex.DECORATION_DEFAULT_ID).getInt(BlockIndex.DECORATION_DEFAULT_ID);
		LogHelper.log(BlockIndex.DECORATION_ID);
	}
	
}
