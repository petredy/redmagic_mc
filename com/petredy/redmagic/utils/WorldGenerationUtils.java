package com.petredy.redmagic.utils;

import java.util.Random;

import com.petredy.redmagic.blocks.Blocks;
import com.petredy.redmagic.lib.BlockIndex;
import com.petredy.redmagic.lib.Configs;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenMinable;

public class WorldGenerationUtils {
	
	public static void generateRheniumOre(World world, Random random, int blockX, int blockZ) {
		for(int a = 0; a < Configs.RHENIUMORE_DEPOSITS; a++){
			int Xcoord = blockX + random.nextInt(16);
			int Ycoord = random.nextInt(10);
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

}
