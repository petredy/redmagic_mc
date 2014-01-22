package com.petredy.redmagic.utils;

import java.util.Random;

import com.petredy.redmagic.blocks.Blocks;
import com.petredy.redmagic.lib.BlockIndex;
import com.petredy.redmagic.lib.Configs;
import com.petredy.redmagic.lib.RedEnergyIndex;
import com.petredy.redmagic.redenergy.EnergyMap;
import com.petredy.redmagic.redenergy.RedEnergy;
import com.petredy.redmagic.redvalue.element.Composition;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenMinable;

public class WorldGenerationUtils {
	
	public static void generateRheniumOre(World world, Random random, int blockX, int blockZ) {
		for(int a = 0; a < Configs.RHENIUMORE_DEPOSITS; a++){
			int Xcoord = blockX + random.nextInt(16);
			int Ycoord = random.nextInt(30);
			int Zcoord = blockZ + random.nextInt(16);
			
			(new WorldGenMinable(Blocks.oreRhenium.blockID, Configs.RHENIUMORE_DEPOSIT_AMOUNT)).generate(world, random, Xcoord, Ycoord, Zcoord);
		}
		
	}

	public static void generateMarbleVeines(World world, Random random, int blockX, int blockZ) {
		for(int a = 0; a < Configs.MARBLE_DEPOSITS; a++){
			int Xcoord = blockX + random.nextInt(16);
			int Ycoord = random.nextInt(256);
			int Zcoord = blockZ + random.nextInt(16);
			
			(new WorldGenMinable(Blocks.decoration.blockID, BlockIndex.MARBLE_METADATA, Configs.MARBLE_DEPOSIT_AMOUNT, Block.stone.blockID)).generate(world, random, Xcoord, Ycoord, Zcoord);
		}
	}

	public static void generateGraniteVeines(World world, Random random, int blockX, int blockZ) {
		for(int a = 0; a < Configs.GRANITE_DEPOSITS; a++){
			int Xcoord = blockX + random.nextInt(16);
			int Ycoord = random.nextInt(256);
			int Zcoord = blockZ + random.nextInt(16);
			
			(new WorldGenMinable(Blocks.decoration.blockID, BlockIndex.GRANITE_METADATA, Configs.GRANITE_DEPOSIT_AMOUNT, Block.stone.blockID)).generate(world, random, Xcoord, Ycoord, Zcoord);
		}
	}

	public static void generateCage(World world, Random random, int blockX, int blockZ) {
		int Xcoord = blockX + random.nextInt(16);
		int Ycoord = 4 + random.nextInt(16);
		int Zcoord = blockZ + random.nextInt(16);
		if(random.nextFloat() < 0.2 && world.getBlockId(Xcoord, Ycoord, Zcoord) == Block.stone.blockID){
			world.setBlock(Xcoord, Ycoord, Zcoord, Blocks.cage.blockID);
		}
	}
	
	public static void generateEnergy(World world, Random random, int chunkX, int chunkZ) {
		float earth = RedEnergyIndex.chunk_min_energy + random.nextFloat() * (RedEnergyIndex.chunk_energy - RedEnergyIndex.chunk_min_energy);
		float air = RedEnergyIndex.chunk_min_energy + random.nextFloat() * (RedEnergyIndex.chunk_energy - RedEnergyIndex.chunk_min_energy);
		float water = RedEnergyIndex.chunk_min_energy + random.nextFloat() * (RedEnergyIndex.chunk_energy - RedEnergyIndex.chunk_min_energy);
		float fire = RedEnergyIndex.chunk_min_energy + random.nextFloat() * (RedEnergyIndex.chunk_energy - RedEnergyIndex.chunk_min_energy);
		float theVoid = RedEnergyIndex.chunk_min_void + random.nextFloat() * (RedEnergyIndex.chunk_void - RedEnergyIndex.chunk_min_void);
		EnergyMap.setEnergy(new RedEnergy(world.provider.dimensionId, chunkX, chunkZ, Composition.getStandard(earth, air, water, fire, theVoid)));
	}

}
