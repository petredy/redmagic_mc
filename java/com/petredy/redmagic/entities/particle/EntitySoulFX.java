package com.petredy.redmagic.entities.particle;

import com.petredy.redmagic.lib.Textures;

import net.minecraft.world.World;

public class EntitySoulFX extends EntityCustomFX{

	public EntitySoulFX(World world, double x, double y, double z) {
		super(world, x, y, z, Textures.SOUL_FX);
		this.particleScale = 10;
		this.particleMaxAge = 15;
	}

}
