package com.petredy.redmagic.handlers;


import java.util.ArrayList;
import java.util.List;

import com.petredy.redmagic.Redmagic;
import com.petredy.redmagic.lib.Comments;
import com.petredy.redmagic.lib.Configs;
import com.petredy.redmagic.lib.Keys;
import com.petredy.redmagic.lib.Reference;
import com.petredy.redmagic.utils.GlassesUtils;

import cpw.mods.fml.common.registry.EntityRegistry;

import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.Property;

public class ConfigHandler {
	public static void config(Configuration config){
		
		
		/***
		 * ------------------------------------------------------------------------
	     * Keys
		 * ------------------------------------------------------------------------
		 */
		
		Property key = config.get("general", Keys.KEY_EXTRA_NAME, Keys.KEY_EXTRA_DEFAULT);
		key.comment = Comments.KEY_EXTRA;
		Keys.KEY_EXTRA_ID = key.getInt(Keys.KEY_EXTRA_DEFAULT);
		
		Redmagic.proxy.setKeyBinding(Keys.KEY_EXTRA_NAME, Keys.KEY_EXTRA_ID);
		
		//.........................................................................
		
		Property sleeprop = config.get("general", "sleep", Reference.SLEEP);
		sleeprop.comment = Comments.SLEEP;
		Reference.SLEEP = sleeprop.getBoolean(Reference.SLEEP);
	
		
		Configs.GLASSES_ORE_DIC = config.get("glasses", "ores", GlassesUtils.getOres(), "Always write id and metadata for your ore like the default values.").getIntList();
		
	}
}
