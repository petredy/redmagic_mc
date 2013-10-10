package com.petredy.redmagic.handlers;


import java.util.ArrayList;
import java.util.List;

import com.petredy.redmagic.Redmagic;
import com.petredy.redmagic.blocks.Blocks;
import com.petredy.redmagic.items.Items;
import com.petredy.redmagic.knowledge.Knowledge;
import com.petredy.redmagic.knowledge.KnowledgeCrafting;
import com.petredy.redmagic.knowledge.KnowledgeItem;
import com.petredy.redmagic.knowledge.KnowledgeSystem;
import com.petredy.redmagic.lib.Comments;
import com.petredy.redmagic.lib.Configs;
import com.petredy.redmagic.lib.ItemIndex;
import com.petredy.redmagic.lib.Keys;
import com.petredy.redmagic.lib.KnowledgeIndex;
import com.petredy.redmagic.lib.Reference;
import com.petredy.redmagic.lib.Textures;
import com.petredy.redmagic.utils.GlassesUtils;

import cpw.mods.fml.common.registry.EntityRegistry;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
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
	}

	public static void postConfig(Configuration config){
		Configs.GLASSES_ORE_DIC = config.get("glasses", "ores", GlassesUtils.getOres(), "Always write id and metadata for your ore like the default values.").getIntList();
		
		
		configKnowledge(config);
	}
	
	private static void configKnowledge(Configuration config) {
		KnowledgeSystem.addKnowledge(new KnowledgeItem(KnowledgeIndex.ORE, 100, 10, 0, null, new ItemStack(Blocks.oreRhenium)));
		KnowledgeSystem.addKnowledge(new Knowledge(KnowledgeIndex.ENERGY, 100, 8, 0, null, Textures.KNOWLEDGE_BASIC));
		
		KnowledgeSystem.addKnowledge(new KnowledgeCrafting(KnowledgeIndex.CRAFTER, 100, 10, 2, KnowledgeSystem.getKnowledge(KnowledgeIndex.ORE), new ItemStack(Blocks.crafter), new ItemStack[]{
			new ItemStack(Items.crafting, 1, ItemIndex.CRAFTING_INGOT_RHENIUM_METADATA), new ItemStack(Items.crafting, 1, ItemIndex.CRAFTING_INGOT_RHENIUM_METADATA), new ItemStack(Items.crafting, 1, ItemIndex.CRAFTING_INGOT_RHENIUM_METADATA), 
			new ItemStack(Items.crafting, 1, ItemIndex.CRAFTING_INGOT_RHENIUM_METADATA), new ItemStack(Block.workbench), new ItemStack(Items.crafting, 1, ItemIndex.CRAFTING_INGOT_RHENIUM_METADATA), 
			new ItemStack(Items.crafting, 1, ItemIndex.CRAFTING_INGOT_RHENIUM_METADATA), new ItemStack(Items.crafting, 1, ItemIndex.CRAFTING_INGOT_RHENIUM_METADATA), new ItemStack(Items.crafting, 1, ItemIndex.CRAFTING_INGOT_RHENIUM_METADATA)
		}));
	}
}
