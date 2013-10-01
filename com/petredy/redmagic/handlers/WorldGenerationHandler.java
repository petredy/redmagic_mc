package com.petredy.redmagic.handlers;

import java.util.Random;

import com.petredy.redmagic.utils.WorldGenerationUtils;


import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;

import cpw.mods.fml.common.IWorldGenerator;

public class WorldGenerationHandler implements IWorldGenerator 
{
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
	{
		if(world.provider.dimensionId != 1 && world.provider.dimensionId != -1){
			WorldGenerationUtils.generateMarbleVeines(world, random, chunkX*16, chunkZ*16);
			WorldGenerationUtils.generateGraniteVeines(world, random, chunkX*16, chunkZ*16);
			WorldGenerationUtils.generateRheniumOre(world, random, chunkX*16, chunkZ*16);
			WorldGenerationUtils.generateCage(world, random, chunkX*16, chunkZ*16);
			WorldGenerationUtils.generateEnergy(world, random, chunkX*16, chunkZ*16);
		}
	}
}

