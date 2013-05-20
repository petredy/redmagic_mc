package redmagic.addons;

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
			BankData.register(Items.getItem("copperOre").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 0.8F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(Items.getItem("tinOre").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 0.8F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(Items.getItem("uraniumOre").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 0.8F, BankIndex.TAX, BankIndex.BUYING);
			
			BankData.register(Items.getItem("rubberWood").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 0.5F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(Items.getItem("rubberLeaves").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 0.4F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(Items.getItem("rubberSapling").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 0.6F, BankIndex.TAX, BankIndex.BUYING);
			
			BankData.register(Items.getItem("reinforcedStone").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(Items.getItem("reinforcedGlass").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 8F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(Items.getItem("reinforcedDoor").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 12F, BankIndex.TAX, BankIndex.BUYING);

			BankData.register(Items.getItem("bronzeBlock").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(Items.getItem("copperBlock").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(Items.getItem("tinBlock").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(Items.getItem("uraniumBlock").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 20F, BankIndex.TAX, BankIndex.BUYING);
			
			BankData.register(Items.getItem("generator").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 30F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(Items.getItem("geothermalGenerator").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 40F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(Items.getItem("waterMill").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 40F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(Items.getItem("solarPanel").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 40F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(Items.getItem("windMill").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 40F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(Items.getItem("nuclearReactor").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 60F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(Items.getItem("reactorChamber").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 50F, BankIndex.TAX, BankIndex.BUYING);

			BankData.register(Items.getItem("batBox").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 20F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(Items.getItem("mfeUnit").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 500F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(Items.getItem("mfsUnit").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 1000F, BankIndex.TAX, BankIndex.BUYING);
			
			BankData.register(Items.getItem("lvTransformer").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 20F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(Items.getItem("mvTransformer").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 50F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(Items.getItem("hvTransformer").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 100F, BankIndex.TAX, BankIndex.BUYING);
			
			BankData.register(Items.getItem("machine").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(Items.getItem("advancedMachine").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 100F, BankIndex.TAX, BankIndex.BUYING);

			BankData.register(Items.getItem("ironFurnace").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(Items.getItem("electroFurnace").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(Items.getItem("macerator").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(Items.getItem("extractor").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(Items.getItem("compressor").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(Items.getItem("canner").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(Items.getItem("miner").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(Items.getItem("pump").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(Items.getItem("electrolyzer").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(Items.getItem("recycler").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(Items.getItem("inductionFurnace").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(Items.getItem("massFabricator").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(Items.getItem("terraformer").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(Items.getItem("teleporter").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(Items.getItem("teslaCoil").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(Items.getItem("luminator").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(Items.getItem("activeLuminator").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F, BankIndex.TAX, BankIndex.BUYING);
			
			BankData.register(Items.getItem("miningPipe").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F, BankIndex.TAX, BankIndex.BUYING);
			
			BankData.register(Items.getItem("personalSafe").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(Items.getItem("tradeOMat").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(Items.getItem("energyOMat").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F, BankIndex.TAX, BankIndex.BUYING);

			BankData.register(Items.getItem("industrialTnt").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(Items.getItem("nuke").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(Items.getItem("dynamiteStick").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F, BankIndex.TAX, BankIndex.BUYING);

			
			
			BankData.register(Items.getItem("resin").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(Items.getItem("rubber").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F, BankIndex.TAX, BankIndex.BUYING);

			
			BankData.register(Items.getItem("uraniumDrop").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F, BankIndex.TAX, BankIndex.BUYING);

			BankData.register(Items.getItem("bronzeDust").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(Items.getItem("clayDust").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(Items.getItem("coalDust").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(Items.getItem("copperDust").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(Items.getItem("goldDust").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(Items.getItem("ironDust").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(Items.getItem("silverDust").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(Items.getItem("smallIronDust").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(Items.getItem("tinDust").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(Items.getItem("hydratedCoalDust").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F, BankIndex.TAX, BankIndex.BUYING);
			
			BankData.register(Items.getItem("refinedIronIngot").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(Items.getItem("copperIngot").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(Items.getItem("tinIngot").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(Items.getItem("bronzeIngot").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(Items.getItem("mixedMetalIngot").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(Items.getItem("uraniumIngot").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F, BankIndex.TAX, BankIndex.BUYING);

			BankData.register(Items.getItem("treetap").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(Items.getItem("wrench").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(Items.getItem("cutter").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(Items.getItem("constructionFoamSprayer").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(Items.getItem("bronzePickaxe").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(Items.getItem("bronzeAxe").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(Items.getItem("bronzeSword").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(Items.getItem("bronzeShovel").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(Items.getItem("bronzeHoe").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F, BankIndex.TAX, BankIndex.BUYING);
			
			BankData.register(Items.getItem("miningDrill").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(Items.getItem("diamondDrill").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(Items.getItem("chainsaw").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(Items.getItem("electricWrench").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(Items.getItem("electricTreetap").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(Items.getItem("miningLaser").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(Items.getItem("ecMeter").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(Items.getItem("odScanner").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(Items.getItem("ovScanner").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(Items.getItem("frequencyTransmitter").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(Items.getItem("nanoSaber").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F, BankIndex.TAX, BankIndex.BUYING);
			
			BankData.register(Items.getItem("bronzeHelmet").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(Items.getItem("bronzeChestplate").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(Items.getItem("bronzeLeggings").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(Items.getItem("bronzeBoots").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(Items.getItem("compositeArmor").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(Items.getItem("nanoHelmet").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(Items.getItem("nanoBodyarmor").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(Items.getItem("nanoLeggings").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(Items.getItem("nanoBoots").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(Items.getItem("quantumHelmet").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(Items.getItem("quantumBodyarmor").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(Items.getItem("quantumLeggings").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(Items.getItem("quantumBoots").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(Items.getItem("jetpack").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(Items.getItem("electricJetpack").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(Items.getItem("batPack").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(Items.getItem("lapPack").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(Items.getItem("cfPack").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(Items.getItem("solarHelmet").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(Items.getItem("staticBoots").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F, BankIndex.TAX, BankIndex.BUYING);
			
			BankData.register(Items.getItem("reBattery").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(Items.getItem("chargedReBattery").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(Items.getItem("energyCrystal").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(Items.getItem("lapotronCrystal").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(Items.getItem("suBattery").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(Items.getItem("copperCableItem").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(Items.getItem("insulatedCopperCableItem").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(Items.getItem("goldCableItem").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(Items.getItem("insulatedGoldCableItem").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(Items.getItem("doubleInsulatedGoldCableItem").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(Items.getItem("ironCableItem").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(Items.getItem("insulatedIronCableItem").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(Items.getItem("doubleInsulatedIronCableItem").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(Items.getItem("trippleInsulatedIronCableItem").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(Items.getItem("glassFiberCableItem").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(Items.getItem("tinCableItem").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(Items.getItem("detectorCableItem").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(Items.getItem("splitterCableItem").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F, BankIndex.TAX, BankIndex.BUYING);
			
			BankData.register(Items.getItem("cell").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(Items.getItem("lavaCell").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(Items.getItem("hydratedCoalCell").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(Items.getItem("bioCell").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(Items.getItem("coalfuelCell").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(Items.getItem("biofuelCell").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(Items.getItem("waterCell").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(Items.getItem("electrolyzedWaterCell").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(Items.getItem("fuelCan").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(Items.getItem("filledFuelCan").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(Items.getItem("tinCan").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(Items.getItem("filledTinCan").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F, BankIndex.TAX, BankIndex.BUYING);
			/*
			BankData.register(Items.getItem("uraniumCell").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(Items.getItem("coolingCell").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(Items.getItem("depletedIsotopeCell").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(Items.getItem("reEnrichedUraniumCell").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(Items.getItem("nearDepletedUraniumCell").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(Items.getItem("integratedReactorPlating").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(Items.getItem("integratedHeatDisperser").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F, BankIndex.TAX, BankIndex.BUYING);
			*/
			BankData.register(Items.getItem("coalBall").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(Items.getItem("compressedCoalBall").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(Items.getItem("coalChunk").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(Items.getItem("industrialDiamond").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F, BankIndex.TAX, BankIndex.BUYING);

			BankData.register(Items.getItem("scrap").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(Items.getItem("scrapBox").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F, BankIndex.TAX, BankIndex.BUYING);
			
			BankData.register(Items.getItem("hydratedCoalClump").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(Items.getItem("plantBall").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(Items.getItem("compressedPlantBall").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(Items.getItem("dynamite").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(Items.getItem("stickyDynamite").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F, BankIndex.TAX, BankIndex.BUYING);
			
			BankData.register(Items.getItem("electronicCircuit").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(Items.getItem("advancedCircuit").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(Items.getItem("advancedAlloy").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(Items.getItem("carbonFiber").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(Items.getItem("carbonMesh").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(Items.getItem("carbonPlate").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(Items.getItem("matter").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(Items.getItem("iridiumOre").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(Items.getItem("iridiumPlate").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F, BankIndex.TAX, BankIndex.BUYING);
			
			BankData.register(Items.getItem("overclockerUpgrade").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(Items.getItem("transformerUpgrade").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(Items.getItem("energyStorageUpgrade").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(Items.getItem("overclockerUpgrade").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(Items.getItem("overclockerUpgrade").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(Items.getItem("overclockerUpgrade").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(Items.getItem("overclockerUpgrade").itemID, 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 10F, BankIndex.TAX, BankIndex.BUYING);

		}
	}
		
}
