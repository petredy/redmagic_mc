package com.petredy.redmagic.handlers;


import java.util.ArrayList;
import java.util.List;

import com.petredy.redmagic.Redmagic;
import com.petredy.redmagic.blocks.Blocks;
import com.petredy.redmagic.items.Items;
import com.petredy.redmagic.lib.Comments;
import com.petredy.redmagic.lib.Configs;
import com.petredy.redmagic.lib.ItemIndex;
import com.petredy.redmagic.lib.Keys;
import com.petredy.redmagic.lib.Reference;
import com.petredy.redmagic.lib.Textures;

import cpw.mods.fml.common.registry.EntityRegistry;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

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
	}

	public static void postConfig(Configuration config){
		
	}
}
