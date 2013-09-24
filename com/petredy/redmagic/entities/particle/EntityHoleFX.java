package com.petredy.redmagic.entities.particle;

import org.lwjgl.opengl.GL11;

import com.petredy.redmagic.lib.Textures;
import com.petredy.redmagic.utils.LogUtils;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.World;

public class EntityHoleFX extends EntityCustomFX{

	public String color;
	
	public EntityHoleFX(World world, double x, double y, double z, String color) {
		super(world, x, y, z);
		this.texture = Textures.HOLE_FX;
		this.particleScale = 10;
		this.particleMaxAge = 5;
		this.color = color;
		String[] parts = color.split("#");
		if(parts.length == 3){
			this.particleRed = Float.parseFloat(parts[0]);
			this.particleBlue = Float.parseFloat(parts[1]);
			this.particleGreen = Float.parseFloat(parts[2]);
		}
	}

}
