package com.petredy.redmagic.api.redenergy;

import net.minecraft.world.World;

public interface IEnergyHandler {

	public float collect(World world, float amount, int chunkX, int chunkZ);
	
	public float release(World world, float amount, int chunkX, int chunkZ);
	
	public float use(float amount);
	
	public float store(float store);
	
	public float getStoredEnergy();
	
	public int getChunkX();
	
	public int getChunkZ();
	
}
