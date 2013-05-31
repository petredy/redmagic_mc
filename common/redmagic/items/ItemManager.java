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
	public static ItemGlasses glasses;
	public static ItemFlintIronPickaxe pickaxe;
	public static ItemFlintIronShovel shovel;
	public static ItemFlintIronAxe axe;
	public static ItemFlintIronHoe hoe;
	public static ItemBroom broom;
	public static ItemBag bag;
	public static ItemSoulStick stick;
	public static ItemSoulAxe soulAxe;
	public static ItemScroll scroll;
	
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
		glasses = new ItemGlasses(ItemIndex.ARMOR_SOUL_ID, 0, 0);
		pickaxe = new ItemFlintIronPickaxe(ItemIndex.PICKAXE_ID);
		shovel = new ItemFlintIronShovel(ItemIndex.SHOVEL_ID);
		axe = new ItemFlintIronAxe(ItemIndex.AXE_ID);
		hoe = new ItemFlintIronHoe(ItemIndex.HOE_ID);
		broom = new ItemBroom(ItemIndex.BROOM_ID);
		bag = new ItemBag(ItemIndex.BAG_ID);
		stick = new ItemSoulStick(ItemIndex.STICK_ID);
		soulAxe = new ItemSoulAxe(ItemIndex.SOUL_AXE_ID);
		scroll = new ItemScroll(ItemIndex.SCROLL_ID);
		
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
		
		GameRegistry.addShapelessRecipe(new ItemStack(scroll), new Object[]{
			new ItemStack(Item.paper), new ItemStack(Item.paper), new ItemStack(Item.paper), new ItemStack(Item.paper)
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
		ItemIndex.ARMOR_SOUL_ID = config.getItem(ItemIndex.ARMOR_SOUL_NAME, ItemIndex.ARMOR_SOUL_DEFAULT_ID).getInt(ItemIndex.ARMOR_SOUL_DEFAULT_ID);
		ItemIndex.PICKAXE_ID = config.getItem(ItemIndex.PICKAXE_NAME, ItemIndex.PICKAXE_DEFAULT_ID).getInt(ItemIndex.PICKAXE_DEFAULT_ID);
		ItemIndex.SHOVEL_ID = config.getItem(ItemIndex.SHOVEL_NAME, ItemIndex.SHOVEL_DEFAULT_ID).getInt(ItemIndex.SHOVEL_DEFAULT_ID);
		ItemIndex.AXE_ID = config.getItem(ItemIndex.AXE_NAME, ItemIndex.AXE_DEFAULT_ID).getInt(ItemIndex.AXE_DEFAULT_ID);
		ItemIndex.HOE_ID = config.getItem(ItemIndex.HOE_NAME, ItemIndex.HOE_DEFAULT_ID).getInt(ItemIndex.HOE_DEFAULT_ID);
		ItemIndex.BROOM_ID = config.getItem(ItemIndex.BROOM_NAME, ItemIndex.BROOM_DEFAULT_ID).getInt(ItemIndex.BROOM_DEFAULT_ID);
		ItemIndex.BAG_ID = config.getItem(ItemIndex.BAG_NAME, ItemIndex.BAG_DEFAULT_ID).getInt(ItemIndex.BAG_DEFAULT_ID);
		ItemIndex.STICK_ID = config.getItem(ItemIndex.STICK_NAME, ItemIndex.STICK_DEFAULT_ID).getInt(ItemIndex.STICK_DEFAULT_ID);
		ItemIndex.SOUL_AXE_ID = config.getItem(ItemIndex.SOUL_AXE_NAME, ItemIndex.SOUL_AXE_DEFAULT_ID).getInt(ItemIndex.SOUL_AXE_DEFAULT_ID);
		ItemIndex.SCROLL_ID = config.getItem(ItemIndex.SCROLL_NAME, ItemIndex.SCROLL_DEFAULT_ID).getInt(ItemIndex.SCROLL_DEFAULT_ID);
		
	}
}
