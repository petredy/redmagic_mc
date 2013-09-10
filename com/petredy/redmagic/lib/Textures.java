package com.petredy.redmagic.lib;

import com.petredy.redmagic.utils.ResourceLocationUtils;

import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.util.ResourceLocation;

public class Textures {
	public static final ResourceLocation VANILLA_BLOCK_TEXTURE_SHEET = TextureMap.locationBlocksTexture;
	public static final ResourceLocation VANILLA_ITEM_TEXTURE_SHEET = TextureMap.locationItemsTexture;
	
	public static final String TEXTURE_PATH = "textures/";
	
	//Renders
	public static final ResourceLocation ENGINE = ResourceLocationUtils.getResourceLocation(Reference.ASSETS + Reference.MOD_ID + "models/engine/engine.png");
			
}
