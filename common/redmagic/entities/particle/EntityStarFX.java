package redmagic.entities.particle;

import redmagic.configuration.Texture;
import net.minecraft.world.World;

public class EntityStarFX extends EntityMovingABFX{

	public EntityStarFX(World world, double x, double y, double z, double targetX, double targetY, double targetZ) {
		super(world, x, y, z, targetX, targetY, targetZ);
		this.resourceLocation = Texture.STAR_FX;
		this.particleScale = 1;
		this.particleMaxAge = (int) (this.getDistance());
	}

}
