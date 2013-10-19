package com.petredy.redmagic.api.redenergy;

import com.petredy.redmagic.redenergy.RedEnergy;

import net.minecraft.world.World;

public interface IEnergyHandler {

	public RedEnergy collect(World world, RedEnergy energy);
	
	public RedEnergy release(World world, RedEnergy energy);
	
	public RedEnergy use(RedEnergy energy);
	
	public float use(String element, float value);
	
	public RedEnergy store(RedEnergy energy);
	
	public float store(String element, float store);
	
	public RedEnergy getStoredEnergy();
	
	public int getChunkX();
	
	public int getChunkZ();
	
}
