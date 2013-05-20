package redmagic.addons;

import net.minecraft.src.ModLoader;
import net.minecraftforge.oredict.OreDictionary;
import redmagic.configuration.BankIndex;
import redmagic.core.Logger;
import redmagic.lib.bank.BankData;

public class RailcraftAddon {

	
	public static boolean initialised;
	public static final String modName = "Railcraft";
	
	public static void init(){
		
		if(ModLoader.isModLoaded(modName)){
			Logger.log("Railcraft was found.");
			initialised = true;
		}
		
	}
	
	public static void loadDefault(){
		if(initialised || ModLoader.isModLoaded(modName)){
			/*
			BankData.register(OreDictionary.getOreID("ingotSteel"), 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 2F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(OreDictionary.getOreID("blockSteel"), 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 18F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(OreDictionary.getOreID("dustCharcoal"), 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 0.75F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(OreDictionary.getOreID("dustObsidian"), 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 4F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(OreDictionary.getOreID("dustSaltpeter"), 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 0.5F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(OreDictionary.getOreID("dustSulfur"), 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 0.5F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(OreDictionary.getOreID("oreSaltpeter"), 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 1F, BankIndex.TAX, BankIndex.BUYING);
			BankData.register(OreDictionary.getOreID("oreSulfur"), 0, BankIndex.AMOUNT, BankIndex.TRADEABLE, 1F, BankIndex.TAX, BankIndex.BUYING);
			*/
			
		}
	}
	
}
