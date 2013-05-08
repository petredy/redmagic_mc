package redmagic.items;

import cpw.mods.fml.common.registry.GameRegistry;
import redmagic.configuration.ItemIndex;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraftforge.common.Configuration;

public class ItemManager {
	
	public static ItemRedhole redhole;
	public static ItemToolHammer hammer;
	public static ItemSoulCrystal soulCrystal;
	public static ItemSoulNectar soulNectar;
	public static ItemSoul soul;
	public static ItemWrench wrench;
	public static ItemBankCrystal bankCrystal;
	public static ItemCrafting crafting;
	public static ItemFragment fragment;
	
	public static void init(){
		redhole = new ItemRedhole(ItemIndex.REDHOLE_ID);
		hammer = new ItemToolHammer(ItemIndex.HAMMER_ID);
		soulCrystal = new ItemSoulCrystal(ItemIndex.SOUL_CRYSTAL_ID);
		soulNectar = new ItemSoulNectar(ItemIndex.SOUL_NECTAR_ID);
		soul = new ItemSoul(ItemIndex.SOUL_ID);
		wrench = new ItemWrench(ItemIndex.WRENCH_ID);
		bankCrystal = new ItemBankCrystal(ItemIndex.BANK_CRYSTAL_ID);
		crafting = new ItemCrafting(ItemIndex.CRAFTING_ID);
		fragment = new ItemFragment(ItemIndex.FRAGMENT_ID);
		
		registerRecipes();
		registerSmelting();
	}
	
	public static void registerRecipes(){
		GameRegistry.addRecipe(new ItemStack(hammer), new Object[]{
			"III",
			"ISI",
			" S ",
			'I', Item.ingotIron,
			'S', Item.stick
		});
	}
	
	public static void registerSmelting(){
		FurnaceRecipes.smelting().addSmelting(ItemManager.crafting.itemID, ItemIndex.CRAFTING_SOUL_POWDER_ITEMDAMAGE, new ItemStack(ItemManager.crafting, 1, ItemIndex.CRAFTING_SOUL_INGOT_ITEMDAMAGE), 0.2F);
	}
	
	public static void config(Configuration config){
		ItemIndex.REDHOLE_ID = config.getItem(ItemIndex.REDHOLE_NAME, ItemIndex.REDHOLE_DEFAULT_ID).getInt(ItemIndex.REDHOLE_DEFAULT_ID);
		ItemIndex.HAMMER_ID = config.getItem(ItemIndex.HAMMER_NAME, ItemIndex.HAMMER_DEFAULT_ID).getInt(ItemIndex.HAMMER_DEFAULT_ID);
		ItemIndex.SOUL_CRYSTAL_ID = config.getItem(ItemIndex.SOUL_CRYSTAL_NAME, ItemIndex.SOUL_CRYSTAL_DEFAULT_ID).getInt(ItemIndex.SOUL_CRYSTAL_DEFAULT_ID);
		ItemIndex.SOUL_NECTAR_ID = config.getItem(ItemIndex.SOUL_NECTAR_NAME, ItemIndex.SOUL_NECTAR_DEFAULT_ID).getInt(ItemIndex.SOUL_NECTAR_DEFAULT_ID);
		ItemIndex.SOUL_ID = config.getItem(ItemIndex.SOUL_NAME, ItemIndex.SOUL_DEFAULT_ID).getInt(ItemIndex.SOUL_DEFAULT_ID);
		ItemIndex.WRENCH_ID = config.getItem(ItemIndex.WRENCH_NAME, ItemIndex.WRENCH_DEFAULT_ID).getInt(ItemIndex.WRENCH_DEFAULT_ID);
		ItemIndex.BANK_CRYSTAL_ID = config.getItem(ItemIndex.BANK_CRYSTAL_NAME, ItemIndex.BANK_CRYSTAL_DEFAULT_ID).getInt(ItemIndex.BANK_CRYSTAL_DEFAULT_ID);
		ItemIndex.CRAFTING_ID = config.getItem(ItemIndex.CRAFTING_NAME, ItemIndex.CRAFTING_DEFAULT_ID).getInt(ItemIndex.CRAFTING_DEFAULT_ID);
		ItemIndex.FRAGMENT_ID = config.getItem(ItemIndex.FRAGMENT_NAME, ItemIndex.FRAGMENT_DEFAULT_ID).getInt(ItemIndex.FRAGMENT_DEFAULT_ID);
		
	}
}
