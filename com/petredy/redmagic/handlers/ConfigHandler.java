package com.petredy.redmagic.handlers;


import java.util.ArrayList;
import java.util.List;

import com.petredy.redmagic.Redmagic;
import com.petredy.redmagic.lib.Comments;
import com.petredy.redmagic.lib.Keys;

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
		/*
		Property keyMenu = config.get("general", Reference.KEY_TALENT_MENU_NAME, Reference.KEY_TALENT_MENU_DEFAULT);
		keyMenu.comment = Comments.KEY_TALENT_MENU;
		Reference.KEY_TALENT_MENU_ID = keyMenu.getInt(Reference.KEY_TALENT_MENU_DEFAULT);
		
		Redmagic.proxy.setKeyBinding(Reference.KEY_TALENT_MENU_NAME, Reference.KEY_TALENT_MENU_ID);
		*/
		//.........................................................................
		
	}
}
