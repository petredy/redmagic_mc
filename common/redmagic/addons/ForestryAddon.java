package redmagic.addons;

import java.util.List;

import forestry.api.core.BlockInterface;
import forestry.api.core.ItemInterface;
import net.minecraft.src.ModLoader;
import net.minecraftforge.oredict.OreDictionary;
import redmagic.configuration.BankIndex;
import redmagic.core.Logger;
import redmagic.lib.bank.BankData;

public class ForestryAddon {
	public static boolean initialised;
	public static final String modName = "Forestry";
	
	public static void init(){
		
		if(ModLoader.isModLoaded(modName)){
			Logger.log("Forestry was found.");
			initialised = true;
		}
		
	}
	
	public static void loadDefault(){
		if(initialised || ModLoader.isModLoaded(modName)){
			BankData.register(ItemInterface.getItem("ingotCopper").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 1F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(ItemInterface.getItem("ingotTin").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 1F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(ItemInterface.getItem("ingotBronze").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 1.5F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(ItemInterface.getItem("bucketBiomass").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 5F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(ItemInterface.getItem("gearBronze").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 6F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(ItemInterface.getItem("gearCopper").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 4F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(ItemInterface.getItem("gearTin").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 4F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(ItemInterface.getItem("mulch").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 0.9F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(ItemInterface.getItem("peat").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 2F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(ItemInterface.getItem("bituminousPeat").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 5F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(ItemInterface.getItem("ash").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 0.2F, BankIndex.TAX, BankIndex.BUYING);
			
			BankData.register(ItemInterface.getItem("honeyDrop").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 0.5F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(ItemInterface.getItem("scoop").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 1F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(ItemInterface.getItem("beeswax").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 0.5F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(ItemInterface.getItem("pollen").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 0.4F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(ItemInterface.getItem("propolis").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 0.4F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(ItemInterface.getItem("honeydew").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 0.2F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(ItemInterface.getItem("royalJelly").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 4F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(ItemInterface.getItem("honeyedSlice").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 1F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(ItemInterface.getItem("honeyPot").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 0.8F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(ItemInterface.getItem("refractoryWax").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 0.5F, BankIndex.TAX, BankIndex.BUYING);
			
			BankData.register(ItemInterface.getItem("waxCapsule").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10 / 64F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(ItemInterface.getItem("waxCapsuleWater").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 11 / 64F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(ItemInterface.getItem("waxCapsuleBiomass").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 30 / 64F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(ItemInterface.getItem("waxCapsuleBiofuel").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 0.8F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(ItemInterface.getItem("waxCapsuleOil").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 0.6F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(ItemInterface.getItem("waxCapsuleFuel").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 0.8F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(ItemInterface.getItem("waxCapsuleHoney").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 0.5F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(ItemInterface.getItem("waxCapsuleSeedOil").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 0.4F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(ItemInterface.getItem("waxCapsuleJuice").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 0.3F, BankIndex.TAX, BankIndex.BUYING);
			
			BankData.register(ItemInterface.getItem("refractoryEmpty").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 20 / 64F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(ItemInterface.getItem("refractoryWater").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 22 / 64F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(ItemInterface.getItem("refractoryBiomass").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 0.8F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(ItemInterface.getItem("refractoryBiofuel").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 1.2F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(ItemInterface.getItem("refractoryOil").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 0.8F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(ItemInterface.getItem("refractoryFuel").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 1.2F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(ItemInterface.getItem("refractoryLava").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 5F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(ItemInterface.getItem("refractorySeedOil").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 0.5F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(ItemInterface.getItem("refractoryHoney").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 1F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(ItemInterface.getItem("refractoryJuice").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 0.6F, BankIndex.TAX, BankIndex.BUYING);

			
			BankData.register(ItemInterface.getItem("canWater").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 3 / 16F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(ItemInterface.getItem("canEmpty").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 3 / 16F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(ItemInterface.getItem("canBiomass").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 3 / 16 + 0.4F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(ItemInterface.getItem("canBiofuel").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 3 / 16 + 0.8F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(ItemInterface.getItem("canOil").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 3 / 16 + 0.4F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(ItemInterface.getItem("canFuel").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 3 / 16 + 0.8F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(ItemInterface.getItem("canLava").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 3 / 16 + 2F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(ItemInterface.getItem("canSeedOil").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 3 / 16 + 0.5F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(ItemInterface.getItem("canHoney").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 3 / 16 + 0.4F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(ItemInterface.getItem("canJuice").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 3 / 16 + 0.3F, BankIndex.TAX, BankIndex.BUYING);

		}
	}
	
	public static void addOres(List<Integer> ores) {
		if(initialised || ModLoader.isModLoaded(modName)){
			/*
			ores.add(BlockInterface.getBlock("resource").itemID);
			ores.add(0);
			ores.add(BlockInterface.getBlock("resource").itemID);
			ores.add(1);
			ores.add(BlockInterface.getBlock("resource").itemID);
			ores.add(2);
			*/
		}
		
	}
}
