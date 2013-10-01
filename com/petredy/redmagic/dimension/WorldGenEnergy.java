package com.petredy.redmagic.dimension;

import java.util.Random;

import com.petredy.redmagic.redenergy.RedEnergy;
import com.petredy.redmagic.redenergy.EnergyMap;
import com.petredy.redmagic.redenergy.Redkey;

import net.minecraft.block.Block;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenEnergy extends WorldGenerator {

	public int numberOfBlocks = 10;
	public float energy = 50;
	
	
	@Override
	public boolean generate(World world, Random random, int i, int j, int k) {
		
		for(int x = -8; x <= 8; x++){
			for(int y = -8; y <= 8; y++){
				for(int z = -8; z <= 8; z++){
					EnergyMap.setEnergy(new RedEnergy(x + i, y + j, z + k, random.nextFloat() * 50));
				}
			}
		}

        return true;
	}

}
