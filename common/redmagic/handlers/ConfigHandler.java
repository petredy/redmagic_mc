package redmagic.handlers;


import redmagic.configuration.BankIndex;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.Property;

public class ConfigHandler {
	public static void config(Configuration config){
		BankIndex.TAX = Float.parseFloat(config.get("general", "Tax", BankIndex.TAX_DEFAULT).getString());
		BankIndex.TAX_CHANGE = Float.parseFloat(config.get("general", "TaxChange", BankIndex.TAX_CHANGE_DEFAULT).getString());
	}
}
