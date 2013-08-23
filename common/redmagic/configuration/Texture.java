package redmagic.configuration;

import redmagic.helpers.ResourceLocationHelper;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.util.ResourceLocation;

public class Texture {
	public static final String TEXTURE_PATH = "textures/";
	
	public static final String GUI_PATH = TEXTURE_PATH + "gui/";
	public static final String PARTICLE_PATH = TEXTURE_PATH + "particles/";
	
	public static final ResourceLocation VANILLA_BLOCK_TEXTURE_SHEET = TextureMap.field_110575_b;
    public static final ResourceLocation VANILLA_ITEM_TEXTURE_SHEET = TextureMap.field_110576_c;
	
	
	
	//Particles
	public static final ResourceLocation STAR_FX = ResourceLocationHelper.getResourceLocation(PARTICLE_PATH + "star.png");
	
	
	
	
}
