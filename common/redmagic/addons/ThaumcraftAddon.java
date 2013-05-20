package redmagic.addons;

import net.minecraft.item.ItemStack;
import net.minecraft.src.ModLoader;
import redmagic.configuration.BankIndex;
import redmagic.core.Logger;
import redmagic.lib.bank.BankData;
import thaumcraft.api.ItemApi;

public class ThaumcraftAddon {
	
	public static boolean initialised;
	public static final String modName = "Thaumcraft";
	
	public static void init(){
		
		if(ModLoader.isModLoaded(modName)){
			Logger.log("Thaumcraft was found.");
			initialised = true;
		}
		
	}
	
	public static void loadDefault(){
		if(initialised || ModLoader.isModLoaded(modName)){
			
			ItemStack shard = ItemApi.getItem("itemShard", 0);
			BankData.register(shard.itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(shard.itemID, 1, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(shard.itemID, 2, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(shard.itemID, 3, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(shard.itemID, 4, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(shard.itemID, 5, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F, BankIndex.TAX, BankIndex.BUYING);

			ItemStack essence = ItemApi.getItem("itemWispEssence", 0);
			BankData.register(essence.itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 50F, BankIndex.TAX, BankIndex.BUYING);
			
			ItemStack ressource = ItemApi.getItem("itemResource", 0);
			BankData.register(ressource.itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(ressource.itemID, 1, BankIndex.AMOUNT, BankIndex.TRADEABLE, 5F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(ressource.itemID, 2, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(ressource.itemID, 3, BankIndex.AMOUNT, BankIndex.TRADEABLE, 5F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(ressource.itemID, 4, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(ressource.itemID, 5, BankIndex.AMOUNT, BankIndex.TRADEABLE, 0.5F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(ressource.itemID, 6, BankIndex.AMOUNT, BankIndex.TRADEABLE, 1.5F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(ressource.itemID, 7, BankIndex.AMOUNT, BankIndex.TRADEABLE, 3F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(ressource.itemID, 8, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(ressource.itemID, 9, BankIndex.AMOUNT, BankIndex.TRADEABLE, 100F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(ressource.itemID, 10, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F, BankIndex.TAX, BankIndex.BUYING);

			ItemStack nugget = ItemApi.getItem("itemNugget", 0);
			BankData.register(nugget.itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10 / 64F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(nugget.itemID, 1, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10 / 64F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(nugget.itemID, 3, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10 / 64F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(nugget.itemID, 4, BankIndex.AMOUNT, BankIndex.TRADEABLE, 20 / 64F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(nugget.itemID, 5, BankIndex.AMOUNT, BankIndex.TRADEABLE, 20 / 64F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(nugget.itemID, 16, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10 / 64F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(nugget.itemID, 17, BankIndex.AMOUNT, BankIndex.TRADEABLE, 1F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(nugget.itemID, 18, BankIndex.AMOUNT, BankIndex.TRADEABLE, 1F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(nugget.itemID, 19, BankIndex.AMOUNT, BankIndex.TRADEABLE, 1F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(nugget.itemID, 20, BankIndex.AMOUNT, BankIndex.TRADEABLE, 1F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(nugget.itemID, 31, BankIndex.AMOUNT, BankIndex.TRADEABLE, 1F, BankIndex.TAX, BankIndex.BUYING);
			
			ItemStack chickenNugget = ItemApi.getItem("itemNuggetChicken", 0);
			BankData.register(chickenNugget.itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10 / 64F, BankIndex.TAX, BankIndex.BUYING);

			ItemStack beefNugget = ItemApi.getItem("itemNuggetBeef", 0);
			BankData.register(beefNugget.itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10 / 64F, BankIndex.TAX, BankIndex.BUYING);

			ItemStack itemNuggetPork = ItemApi.getItem("itemNuggetPork", 0);
			BankData.register(itemNuggetPork.itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10 / 64F, BankIndex.TAX, BankIndex.BUYING);

			ItemStack hole = ItemApi.getItem("itemPortableHole", 0);
			BankData.register(hole.itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 500F, BankIndex.TAX, BankIndex.BUYING);

			ItemStack thaumonomicon = ItemApi.getItem("itemThaumonomicon", 0);
			BankData.register(thaumonomicon.itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 500F, BankIndex.TAX, BankIndex.BUYING);

			ItemStack goggles = ItemApi.getItem("itemGoggles", 0);
			BankData.register(goggles.itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 100F, BankIndex.TAX, BankIndex.BUYING);
			
			//Blocks
			BankData.register(ItemApi.getItem("blockCustomPlant", 0).itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 2F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(ItemApi.getItem("blockCustomPlant", 1).itemID, 1, BankIndex.AMOUNT, BankIndex.TRADEABLE, 50F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(ItemApi.getItem("blockMagicalLog", 0).itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 2F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(ItemApi.getItem("blockMagicalLog", 1).itemID, 1, BankIndex.AMOUNT, BankIndex.TRADEABLE, 20F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(ItemApi.getItem("blockCandle", 0).itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 5F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(ItemApi.getItem("blockCandle", 1).itemID, 1, BankIndex.AMOUNT, BankIndex.TRADEABLE, 5F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(ItemApi.getItem("blockCandle", 2).itemID, 2, BankIndex.AMOUNT, BankIndex.TRADEABLE, 5F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(ItemApi.getItem("blockCandle", 3).itemID, 3, BankIndex.AMOUNT, BankIndex.TRADEABLE, 5F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(ItemApi.getItem("blockCandle", 4).itemID, 4, BankIndex.AMOUNT, BankIndex.TRADEABLE, 5F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(ItemApi.getItem("blockCandle", 5).itemID, 5, BankIndex.AMOUNT, BankIndex.TRADEABLE, 5F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(ItemApi.getItem("blockCandle", 6).itemID, 6, BankIndex.AMOUNT, BankIndex.TRADEABLE, 5F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(ItemApi.getItem("blockCandle", 7).itemID, 7, BankIndex.AMOUNT, BankIndex.TRADEABLE, 5F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(ItemApi.getItem("blockCandle", 8).itemID, 8, BankIndex.AMOUNT, BankIndex.TRADEABLE, 5F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(ItemApi.getItem("blockCandle", 9).itemID, 9, BankIndex.AMOUNT, BankIndex.TRADEABLE, 5F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(ItemApi.getItem("blockCandle", 10).itemID, 10, BankIndex.AMOUNT, BankIndex.TRADEABLE, 5F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(ItemApi.getItem("blockCandle", 11).itemID, 11, BankIndex.AMOUNT, BankIndex.TRADEABLE, 5F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(ItemApi.getItem("blockCandle", 12).itemID, 12, BankIndex.AMOUNT, BankIndex.TRADEABLE, 5F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(ItemApi.getItem("blockCandle", 13).itemID, 13, BankIndex.AMOUNT, BankIndex.TRADEABLE, 5F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(ItemApi.getItem("blockCandle", 14).itemID, 14, BankIndex.AMOUNT, BankIndex.TRADEABLE, 5F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(ItemApi.getItem("blockCandle", 15).itemID, 15, BankIndex.AMOUNT, BankIndex.TRADEABLE, 5F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(ItemApi.getItem("blockMarker", 0).itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 2F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(ItemApi.getItem("blockMarker", 1).itemID, 1, BankIndex.AMOUNT, BankIndex.TRADEABLE, 2F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(ItemApi.getItem("blockMarker", 2).itemID, 2, BankIndex.AMOUNT, BankIndex.TRADEABLE, 2F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(ItemApi.getItem("blockMarker", 3).itemID, 3, BankIndex.AMOUNT, BankIndex.TRADEABLE, 2F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(ItemApi.getItem("blockMarker", 4).itemID, 4, BankIndex.AMOUNT, BankIndex.TRADEABLE, 2F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(ItemApi.getItem("blockMarker", 5).itemID, 5, BankIndex.AMOUNT, BankIndex.TRADEABLE, 2F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(ItemApi.getItem("blockMarker", 6).itemID, 6, BankIndex.AMOUNT, BankIndex.TRADEABLE, 2F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(ItemApi.getItem("blockMarker", 7).itemID, 7, BankIndex.AMOUNT, BankIndex.TRADEABLE, 2F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(ItemApi.getItem("blockMarker", 8).itemID, 8, BankIndex.AMOUNT, BankIndex.TRADEABLE, 2F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(ItemApi.getItem("blockMarker", 9).itemID, 9, BankIndex.AMOUNT, BankIndex.TRADEABLE, 2F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(ItemApi.getItem("blockMarker", 10).itemID, 10, BankIndex.AMOUNT, BankIndex.TRADEABLE, 2F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(ItemApi.getItem("blockMarker", 11).itemID, 11, BankIndex.AMOUNT, BankIndex.TRADEABLE, 2F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(ItemApi.getItem("blockMarker", 12).itemID, 12, BankIndex.AMOUNT, BankIndex.TRADEABLE, 2F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(ItemApi.getItem("blockMarker", 13).itemID, 13, BankIndex.AMOUNT, BankIndex.TRADEABLE, 2F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(ItemApi.getItem("blockMarker", 14).itemID, 14, BankIndex.AMOUNT, BankIndex.TRADEABLE, 2F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(ItemApi.getItem("blockMarker", 15).itemID, 15, BankIndex.AMOUNT, BankIndex.TRADEABLE, 2F, BankIndex.TAX, BankIndex.BUYING);

			
			
		}
	}
}
