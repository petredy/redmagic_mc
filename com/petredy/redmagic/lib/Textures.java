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
	public static final ResourceLocation ENGINE = ResourceLocationUtils.getResourceLocation("models/engine/engine.png");
	public static final ResourceLocation TRADING_CHEST = ResourceLocationUtils.getResourceLocation(TEXTURE_PATH + "blocks/" + BlockIndex.TRADING_CHEST_NAME + ".png");
	public static final ResourceLocation CAGE = ResourceLocationUtils.getResourceLocation(TEXTURE_PATH + "blocks/" + BlockIndex.CAGE_NAME + ".png");
	
	//Guis
	public static final ResourceLocation TRADING_CHEST_GUI = ResourceLocationUtils.getResourceLocation(GUI_PATH + BlockIndex.TRADING_CHEST_NAME + ".png");
	public static final ResourceLocation ALL_ITEMS = ResourceLocationUtils.getResourceLocation(GUI_PATH + "allitems.png"); 
	public static final ResourceLocation PLAYER_INVENTORY = ResourceLocationUtils.getResourceLocation(GUI_PATH + "player.inventory.png");
	public static final ResourceLocation MACHINE = ResourceLocationUtils.getResourceLocation(GUI_PATH + "machine.png");
	public static final ResourceLocation FURNACE = ResourceLocationUtils.getResourceLocation(GUI_PATH + "furnace.png");
	public static final ResourceLocation DEINTEGRATOR = ResourceLocationUtils.getResourceLocation(GUI_PATH + "deintegrator.png");

	//Glasses
	public static final String GLASSES = TEXTURE_PATH + "glasses/glasses.png";
	public static final ResourceLocation OVERLAY = ResourceLocationUtils.getResourceLocation(TEXTURE_PATH + "glasses/overlay.png");
	public static final ResourceLocation DANGER_OVERLAY = ResourceLocationUtils.getResourceLocation(TEXTURE_PATH + "glasses/overlay.danger.png");
	public static final ResourceLocation MODUS_OVERLAY = ResourceLocationUtils.getResourceLocation(TEXTURE_PATH + "glasses/overlay.modus.png");
	
	
	//Entities
	public static final ResourceLocation SOUL = ResourceLocationUtils.getResourceLocation(TEXTURE_PATH + "entities/soul.png");;
	
	
	//Particles 
	public static final ResourceLocation STAR_FX = ResourceLocationUtils.getResourceLocation(TEXTURE_PATH + "entities/particles/star.png");
	public static final ResourceLocation HOLE_FX = ResourceLocationUtils.getResourceLocation(TEXTURE_PATH + "entities/particles/hole.png");
	public static final ResourceLocation SOUL_FX = ResourceLocationUtils.getResourceLocation(TEXTURE_PATH + "entities/particles/soul.png");
	public static final Object RING_FX = ResourceLocationUtils.getResourceLocation(TEXTURE_PATH + "entities/particles/ring.png");
	
	
	//Soulman
	public static final ResourceLocation SOULMAN = ResourceLocationUtils.getResourceLocation(TEXTURE_PATH + "entities/soulman.png");
	public static final ResourceLocation SOULMAN_0 = ResourceLocationUtils.getResourceLocation(TEXTURE_PATH + "entities/soulman_0.png");
	public static final ResourceLocation SOULMAN_1 = ResourceLocationUtils.getResourceLocation(TEXTURE_PATH + "entities/soulman_1.png");
	public static final ResourceLocation SOULMAN_2 = ResourceLocationUtils.getResourceLocation(TEXTURE_PATH + "entities/soulman_2.png");
	
	
	
	
	
}
