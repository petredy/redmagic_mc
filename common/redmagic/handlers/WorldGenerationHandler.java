package redmagic.handlers;

import java.util.Random;

import redmagic.helpers.WorldGenerationHelper;

import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;

import cpw.mods.fml.common.IWorldGenerator;

public class WorldGenerationHandler implements IWorldGenerator 
{
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
	{
		if(world.provider.dimensionId != 1 && world.provider.dimensionId != -1){
			WorldGenerationHelper.generateMarbleVeines(world, random, chunkX*16, chunkZ*16);
			WorldGenerationHelper.generateGraniteVeines(world, random, chunkX*16, chunkZ*16);
			WorldGenerationHelper.generateRheniumOre(world, random, chunkX*16, chunkZ*16);
		}
	}
}

