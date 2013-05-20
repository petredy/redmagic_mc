package redmagic.handlers;


import redmagic.Redmagic;
import redmagic.blocks.BlockManager;
import redmagic.configuration.BankIndex;
import redmagic.configuration.Comments;
import redmagic.configuration.LogicIndex;
import redmagic.configuration.Reference;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.Property;

public class ConfigHandler {
	public static void config(Configuration config){
		//------------------------------------------------------------------------
		// Bank
		//------------------------------------------------------------------------
		
		BankIndex.TAX = Float.parseFloat(config.get("general", "Tax", BankIndex.TAX_DEFAULT).getString());
		BankIndex.TAX_CHANGE = Float.parseFloat(config.get("general", "TaxChange", BankIndex.TAX_CHANGE_DEFAULT).getString());
	
		
		//------------------------------------------------------------------------
		// Glasses
		//------------------------------------------------------------------------
		
		LogicIndex.GLASSES_ORE_DIC = config.get("glasses", "ores", new int[]{
				14, 0,
				15, 0,
				16, 0,
				21, 0,
				56, 0,
				73, 0,
				74, 0,
				129, 0,
				153, 0,
				BlockManager.soulCrystalOre.blockID, 0
		}, "Always write id and metadata for your ore like the default values.").getIntList();
		
		
		//------------------------------------------------------------------------
		// Keys
		//------------------------------------------------------------------------

		Property key = config.get("general", Reference.KEY_EXTRA_NAME, Reference.KEY_EXTRA_DEFAULT);
		key.comment = Comments.KEY_EXTRA;
		Reference.KEY_EXTRA_ID = key.getInt(Reference.KEY_EXTRA_DEFAULT);
		
		Redmagic.proxy.setKeyBinding(Reference.KEY_EXTRA_NAME, Reference.KEY_EXTRA_ID);
	}
}
