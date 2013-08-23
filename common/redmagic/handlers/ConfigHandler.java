package redmagic.handlers;


import java.util.ArrayList;
import java.util.List;

import redmagic.Redmagic;
import redmagic.blocks.BlockManager;
import redmagic.configuration.Comments;
import redmagic.configuration.LogicIndex;
import redmagic.configuration.Reference;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.Property;

public class ConfigHandler {
	public static void config(Configuration config){
		
		
		/***
		 * ------------------------------------------------------------------------
	     * Keys
		 * ------------------------------------------------------------------------
		 */
		Property key = config.get("general", Reference.KEY_EXTRA_NAME, Reference.KEY_EXTRA_DEFAULT);
		key.comment = Comments.KEY_EXTRA;
		Reference.KEY_EXTRA_ID = key.getInt(Reference.KEY_EXTRA_DEFAULT);
		
		Redmagic.proxy.setKeyBinding(Reference.KEY_EXTRA_NAME, Reference.KEY_EXTRA_ID);
		
		//.........................................................................
		
	}
}
