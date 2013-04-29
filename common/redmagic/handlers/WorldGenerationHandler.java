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
		switch (world.provider.dimensionId)
		{
			case -1: generateNether(world, random, chunkX*16, chunkZ*16);
			case 0: generateSurface(world, random, chunkX*16, chunkZ*16);
		}
	}



		private void generateSurface(World world, Random random, int blockX, int blockZ) 
		{
			WorldGenerationHelper.generateDimensionCracks(world, random, blockX, blockZ);
			WorldGenerationHelper.generateSoulCrystalOre(world, random, blockX, blockZ);
		}

		private void generateNether(World world, Random random, int blockX, int blockZ) 
		{

		}
}

