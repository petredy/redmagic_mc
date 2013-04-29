package redmagic.helpers;

import java.util.Random;

import redmagic.blocks.BlockManager;

import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenDimensionCrack extends WorldGenerator{

	@Override
	public boolean generate(World world, Random random, int x, int y, int z) {
		if(world.isAirBlock(x, y, z)){
			world.setBlock(x, y, z, BlockManager.dimensionCrack.blockID);
		}
		return false;
	}
}