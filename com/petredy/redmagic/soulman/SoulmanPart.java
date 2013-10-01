package com.petredy.redmagic.soulman;

import com.petredy.redmagic.lib.Textures;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.util.ResourceLocation;

public class SoulmanPart {

	public int texture;
	
	public SoulmanPart(int texture){
		this.texture = texture;
	}
	
	@SideOnly(Side.CLIENT)
	public ResourceLocation getTexture(){
		switch(texture){
		default:
			return Textures.SOULMAN;
		}
	}
	
}
