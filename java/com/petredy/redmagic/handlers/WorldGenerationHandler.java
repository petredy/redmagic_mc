package com.petredy.redmagic.handlers;

import java.util.Random;



import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;

import cpw.mods.fml.common.IWorldGenerator;

public class WorldGenerationHandler implements IWorldGenerator 
{
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
	{
		if(world.provider.dimensionId != 1 && world.provider.dimensionId != -1){
			
		}
	}
}

