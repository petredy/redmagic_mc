package redmagic.items;

import cpw.mods.fml.common.registry.GameRegistry;
import redmagic.configuration.ItemIndex;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraftforge.common.Configuration;

public class ItemManager {
	
	public static ItemRedhole redhole;
	public static ItemArtifact artifact;
	public static ItemConstructionTool constructionTool;
	
	public static void init(){
		redhole = new ItemRedhole(ItemIndex.REDHOLE_ID);
		artifact = new ItemArtifact(ItemIndex.ARTIFACT_ID);
		constructionTool = new ItemConstructionTool(ItemIndex.CONSTRUCTION_TOOL_ID);
		
		registerRecipes();
		registerSmelting();
	}
	
	public static void registerRecipes(){
		
	}
	
	public static void registerSmelting(){
		
	}
	
	public static void config(Configuration config){
		ItemIndex.REDHOLE_ID = config.getItem(ItemIndex.REDHOLE_NAME, ItemIndex.REDHOLE_DEFAULT_ID).getInt(ItemIndex.REDHOLE_DEFAULT_ID);
		ItemIndex.ARTIFACT_ID = config.getItem(ItemIndex.ARTIFACT_NAME, ItemIndex.ARTIFACT_DEFAULT_ID).getInt(ItemIndex.ARTIFACT_DEFAULT_ID);
		ItemIndex.CONSTRUCTION_TOOL_ID = config.getItem(ItemIndex.CONSTRUCTION_TOOL_NAME, ItemIndex.CONSTRUCTION_TOOL_DEFAULT_ID).getInt(ItemIndex.CONSTRUCTION_TOOL_DEFAULT_ID);
		
	}
}
