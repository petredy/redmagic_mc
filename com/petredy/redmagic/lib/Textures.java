package com.petredy.redmagic.lib;

import com.petredy.redmagic.utils.ResourceLocationUtils;

import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.util.ResourceLocation;

public class Textures {
	public static final ResourceLocation VANILLA_BLOCK_TEXTURE_SHEET = TextureMap.locationBlocksTexture;
	public static final ResourceLocation VANILLA_ITEM_TEXTURE_SHEET = TextureMap.locationItemsTexture;
	
	public static final String TEXTURE_PATH = "textures/";
	public static final String GUI_PATH = TEXTURE_PATH + "gui/";
	
	//Renders
	public static final ResourceLocation ENGINE = ResourceLocationUtils.getResourceLocation("models/engine/engine.png");
	public static final ResourceLocation TRADING_CHEST = ResourceLocationUtils.getResourceLocation(TEXTURE_PATH + "blocks/trading_chest.png");
	
	//Guis
	public static final ResourceLocation TRADING_CHEST_GUI = ResourceLocationUtils.getResourceLocation(GUI_PATH + "trading_chest.png");
	public static final ResourceLocation ALL_ITEMS = ResourceLocationUtils.getResourceLocation(GUI_PATH + "allitems.png"); 
			
}
