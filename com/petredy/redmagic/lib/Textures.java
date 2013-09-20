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
	public static final ResourceLocation TRADING_CHEST = ResourceLocationUtils.getResourceLocation(TEXTURE_PATH + "blocks/" + BlockIndex.TRADING_CHEST_NAME + ".png");
	public static final ResourceLocation CAGE = ResourceLocationUtils.getResourceLocation(TEXTURE_PATH + "blocks/" + BlockIndex.CAGE_NAME + ".png");
	public static final ResourceLocation SOUL_CHEST = ResourceLocationUtils.getResourceLocation(TEXTURE_PATH + "blocks/" + BlockIndex.SOUL_CHEST_NAME + ".png");
	
	//Guis
	public static final ResourceLocation TRADING_CHEST_GUI = ResourceLocationUtils.getResourceLocation(GUI_PATH + BlockIndex.TRADING_CHEST_NAME + ".png");
	public static final ResourceLocation ALL_ITEMS = ResourceLocationUtils.getResourceLocation(GUI_PATH + "allitems.png"); 
	
	//Glasses
	public static final String GLASSES = TEXTURE_PATH + "glasses/glasses.png";
	public static final ResourceLocation OVERLAY = ResourceLocationUtils.getResourceLocation(TEXTURE_PATH + "glasses/overlay.png");
	public static final ResourceLocation DANGER_OVERLAY = ResourceLocationUtils.getResourceLocation(TEXTURE_PATH + "glasses/overlay.danger.png");
	public static final ResourceLocation MODUS_OVERLAY = ResourceLocationUtils.getResourceLocation(TEXTURE_PATH + "glasses/overlay.modus.png");
	
	
	//Entities
	public static final ResourceLocation SOUL = ResourceLocationUtils.getResourceLocation(TEXTURE_PATH + "entities/soul.png");;
	
	//Particles 
	public static final ResourceLocation STAR_FX = ResourceLocationUtils.getResourceLocation(TEXTURE_PATH + "entities/particles/star.png");
	
	
}
