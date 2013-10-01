package com.petredy.redmagic.dimension;

import net.minecraft.world.World;
import net.minecraft.world.biome.WorldChunkManager;

public class ChunkManagerSoul extends WorldChunkManager {

	public ChunkManagerSoul(World par1World)
    {
		super(par1World);
    }
	
	
	public float[] getRainfall(float[] par1ArrayOfFloat, int par2, int par3, int par4, int par5)
    {
		return new float[par1ArrayOfFloat.length];
    }
}
