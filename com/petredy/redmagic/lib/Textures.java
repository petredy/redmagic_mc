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
	public static final ResourceLocation EFFECT_NOISE = ResourceLocationUtils.getResourceLocation(TEXTURE_PATH + "effects/noise.png");
	public static final ResourceLocation TRADING_CHEST = ResourceLocationUtils.getResourceLocation(TEXTURE_PATH + "blocks/" + BlockIndex.TRADING_CHEST_NAME + ".png");
	public static final ResourceLocation CAGE = ResourceLocationUtils.getResourceLocation(TEXTURE_PATH + "blocks/" + BlockIndex.CAGE_NAME + ".png");

	//Engine
	public static final ResourceLocation ENGINE_CHAMBER = ResourceLocationUtils.getResourceLocation("models/engine/chamber.png");
	public static final ResourceLocation ENGINE_TRUNK = ResourceLocationUtils.getResourceLocation("models/engine/trunk.png");
	public static final ResourceLocation ENGINE_BASE = ResourceLocationUtils.getResourceLocation("models/engine/base.png");
	
	//Guis
	public static final ResourceLocation GUI_3x3 = ResourceLocationUtils.getResourceLocation(GUI_PATH + "default_3x3.png");
	public static final ResourceLocation GUI_2x3x3 = ResourceLocationUtils.getResourceLocation(GUI_PATH + "default_2x3x3.png");
	public static final ResourceLocation TRADING_CHEST_GUI = ResourceLocationUtils.getResourceLocation(GUI_PATH + BlockIndex.TRADING_CHEST_NAME + ".png");
	public static final ResourceLocation ALL_ITEMS = ResourceLocationUtils.getResourceLocation(GUI_PATH + "allitems.png"); 
	public static final ResourceLocation PLAYER_INVENTORY = ResourceLocationUtils.getResourceLocation(GUI_PATH + "player.inventory.png");
	public static final ResourceLocation MACHINE = ResourceLocationUtils.getResourceLocation(GUI_PATH + "machine.png");
	public static final ResourceLocation FURNACE = ResourceLocationUtils.getResourceLocation(GUI_PATH + "furnace.png");
	public static final ResourceLocation DEINTEGRATOR = ResourceLocationUtils.getResourceLocation(GUI_PATH + "deintegrator.png");
	public static final ResourceLocation COMPACTOR = ResourceLocationUtils.getResourceLocation(GUI_PATH + "compactor.png");
	public static final ResourceLocation REDMETER = ResourceLocationUtils.getResourceLocation(GUI_PATH + "redmeter.png");
	
	//Symbols
	public static final ResourceLocation SYMBOLS = ResourceLocationUtils.getResourceLocation(TEXTURE_PATH + "symbols/symbols.png");
	public static final ResourceLocation KNOWLEDGE_BASIC = ResourceLocationUtils.getResourceLocation(TEXTURE_PATH + "symbols/knowledge.basic.png");
	
	//Glasses
	public static final String GLASSES = TEXTURE_PATH + "glasses/glasses.png";
	public static final ResourceLocation OVERLAY = ResourceLocationUtils.getResourceLocation(TEXTURE_PATH + "glasses/overlay.png");
	public static final ResourceLocation DANGER_OVERLAY = ResourceLocationUtils.getResourceLocation(TEXTURE_PATH + "glasses/overlay.danger.png");
	public static final ResourceLocation MODUS_OVERLAY = ResourceLocationUtils.getResourceLocation(TEXTURE_PATH + "glasses/overlay.modus.png");
	
	//Particles 
	public static final ResourceLocation STAR_FX = ResourceLocationUtils.getResourceLocation(TEXTURE_PATH + "entities/particles/star.png");
	public static final ResourceLocation HOLE_FX = ResourceLocationUtils.getResourceLocation(TEXTURE_PATH + "entities/particles/hole.png");
	public static final ResourceLocation SOUL_FX = ResourceLocationUtils.getResourceLocation(TEXTURE_PATH + "entities/particles/soul.png");
	public static final ResourceLocation RING_FX = ResourceLocationUtils.getResourceLocation(TEXTURE_PATH + "entities/particles/ring.png");
	
	
	
	
	
	
	

	
	
	
	
	
}
