package redmagic.addons;

import java.util.List;

import ic2.api.item.Items;
import net.minecraft.src.ModLoader;
import redmagic.api.bank.BankData;
import redmagic.configuration.BankIndex;
import redmagic.core.Logger;
import redmagic.items.ItemManager;

public class IndustrialCraftAddon {
	public static boolean initialised;
	public static final String modName = "IC2";
	
	public static void init(){
		
		if(ModLoader.isModLoaded(modName)){
			Logger.log("IC2 was found.");
			initialised = true;
		}
		
	}
	
	public static void loadDefault(){
		if(initialised || ModLoader.isModLoaded(modName)){
			BankData.register(Items.getItem("rubberWood").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 1F, BankIndex.TAX, BankIndex.BUYING);
			//TODO
			BankData.register(Items.getItem("rubberSapling").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 15F, BankIndex.TAX + 0.1F, BankIndex.BUYING);
			
			BankData.register(Items.getItem("reinforcedStone").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 6 / 8F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(Items.getItem("reinforcedGlass").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 12 / 7 + 3.5F, BankIndex.TAX, BankIndex.BUYING);
		
			BankData.register(Items.getItem("rubber").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 0.5F, BankIndex.TAX, BankIndex.BUYING);

			
			BankData.register(Items.getItem("uraniumDrop").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 150F, BankIndex.TAX, BankIndex.BUYING);

			BankData.register(Items.getItem("coalDust").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 0.8F, BankIndex.TAX, BankIndex.BUYING);
			
			BankData.register(Items.getItem("refinedIronIngot").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 1.1F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(Items.getItem("copperIngot").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 0.7F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(Items.getItem("tinIngot").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 0.8F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(Items.getItem("bronzeIngot").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 0.85F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(Items.getItem("uraniumIngot").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 150F, BankIndex.TAX, BankIndex.BUYING);

			BankData.register(Items.getItem("cell").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 0.8F / 4F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(Items.getItem("lavaCell").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 5F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(Items.getItem("coalfuelCell").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 1.6F / 4F + 0.5F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(Items.getItem("biofuelCell").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 1.4F / 4F + 0.5F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(Items.getItem("waterCell").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 1F / 4F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(Items.getItem("electrolyzedWaterCell").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 2F / 4F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(Items.getItem("fuelCan").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 0.8F * 7F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(Items.getItem("filledFuelCan").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 0.8F * 7F + 6 * (1.4F / 4F + 0.5F), BankIndex.TAX, BankIndex.BUYING);
			BankData.register(Items.getItem("tinCan").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 0.8F * 5 / 4F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(Items.getItem("filledTinCan").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 0.8F * 5 / 4F + 10F / 64F, BankIndex.TAX, BankIndex.BUYING);
			
			BankData.register(Items.getItem("scrap").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 1 / 64F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(Items.getItem("scrapBox").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 9 / 64F, BankIndex.TAX, BankIndex.BUYING);
			
			BankData.register(Items.getItem("plantBall").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 0.4F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(Items.getItem("compressedPlantBall").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 0.6F, BankIndex.TAX, BankIndex.BUYING);
			
			BankData.register(Items.getItem("electronicCircuit").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 7.8F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(Items.getItem("advancedCircuit").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 13F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(Items.getItem("advancedAlloy").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 6F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(Items.getItem("carbonPlate").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 8F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(Items.getItem("matter").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 11F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(Items.getItem("iridiumPlate").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 201F, BankIndex.TAX, BankIndex.BUYING);
			
		}
	}

	public static void addOres(List<Integer> ores) {
		if(initialised || ModLoader.isModLoaded(modName)){
			ores.add(Items.getItem("copperOre").itemID);
			ores.add(0);
			ores.add(Items.getItem("tinOre").itemID);
			ores.add(0);
			ores.add(Items.getItem("uraniumOre").itemID);
			ores.add(0);
		}
	}
		
}
