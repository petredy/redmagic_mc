package redmagic.items;

import redmagic.configuration.ItemIndex;
import net.minecraftforge.common.Configuration;

public class ItemManager {
	
	public static ItemRedhole redhole;
	public static ItemToolHammer hammer;
	public static ItemSoulCrystal soulCrystal;
	public static ItemSoulNectar soulNectar;
	public static ItemSoul soul;
	public static ItemWrench wrench;
	public static ItemCrystal crystal;
	
	public static void init(){
		redhole = new ItemRedhole(ItemIndex.REDHOLE_ID);
		hammer = new ItemToolHammer(ItemIndex.HAMMER_ID);
		soulCrystal = new ItemSoulCrystal(ItemIndex.SOUL_CRYSTAL_ID);
		soulNectar = new ItemSoulNectar(ItemIndex.SOUL_NECTAR_ID);
		soul = new ItemSoul(ItemIndex.SOUL_ID);
		wrench = new ItemWrench(ItemIndex.WRENCH_ID);
		crystal = new ItemCrystal(ItemIndex.CRYSTAL_ID);
		
		registerRecipes();
	}
	
	public static void registerRecipes(){
		
		
	}
	
	public static void config(Configuration config){
		ItemIndex.REDHOLE_ID = config.getItem(ItemIndex.REDHOLE_NAME, ItemIndex.REDHOLE_DEFAULT_ID).getInt(ItemIndex.REDHOLE_DEFAULT_ID);
		ItemIndex.HAMMER_ID = config.getItem(ItemIndex.HAMMER_NAME, ItemIndex.HAMMER_DEFAULT_ID).getInt(ItemIndex.HAMMER_DEFAULT_ID);
		ItemIndex.SOUL_CRYSTAL_ID = config.getItem(ItemIndex.SOUL_CRYSTAL_NAME, ItemIndex.SOUL_CRYSTAL_DEFAULT_ID).getInt(ItemIndex.SOUL_CRYSTAL_DEFAULT_ID);
		ItemIndex.SOUL_NECTAR_ID = config.getItem(ItemIndex.SOUL_NECTAR_NAME, ItemIndex.SOUL_NECTAR_DEFAULT_ID).getInt(ItemIndex.SOUL_NECTAR_DEFAULT_ID);
		ItemIndex.SOUL_ID = config.getItem(ItemIndex.SOUL_NAME, ItemIndex.SOUL_DEFAULT_ID).getInt(ItemIndex.SOUL_DEFAULT_ID);
		ItemIndex.WRENCH_ID = config.getItem(ItemIndex.WRENCH_NAME, ItemIndex.WRENCH_DEFAULT_ID).getInt(ItemIndex.WRENCH_DEFAULT_ID);
		ItemIndex.CRYSTAL_ID = config.getItem(ItemIndex.CRYSTAL_NAME, ItemIndex.CRYSTAL_DEFAULT_ID).getInt(ItemIndex.CRYSTAL_DEFAULT_ID);
		
	}
}
