package redmagic.helpers;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenMinable;

import redmagic.blocks.BlockManager;
import redmagic.configuration.BlockIndex;
import redmagic.configuration.LogicIndex;

public class WorldGenerationHelper {
	
	public static void generateRheniumOre(World world, Random random, int blockX, int blockZ) {
		for(int a = 0; a < LogicIndex.RHENIUMORE_DEPOSITS; a++){
			int Xcoord = blockX + random.nextInt(16);
			int Ycoord = random.nextInt(10);
			int Zcoord = blockZ + random.nextInt(16);
			
			(new WorldGenMinable(BlockManager.rheniumOre.blockID, LogicIndex.RHENIUMORE_DEPOSIT_AMOUNT)).generate(world, random, Xcoord, Ycoord, Zcoord);
		}
		
	}

	public static void generateMarbleVeines(World world, Random random, int blockX, int blockZ) {
		for(int a = 0; a < LogicIndex.MARBLE_DEPOSITS; a++){
			int Xcoord = blockX + random.nextInt(16);
			int Ycoord = random.nextInt(256);
			int Zcoord = blockZ + random.nextInt(16);
			
			(new WorldGenMinable(BlockManager.decoration.blockID, BlockIndex.MARBLE_METADATA, LogicIndex.MARBLE_DEPOSIT_AMOUNT, Block.stone.blockID)).generate(world, random, Xcoord, Ycoord, Zcoord);
		}
	}

	public static void generateGraniteVeines(World world, Random random, int blockX, int blockZ) {
		for(int a = 0; a < LogicIndex.GRANITE_DEPOSITS; a++){
			int Xcoord = blockX + random.nextInt(16);
			int Ycoord = random.nextInt(256);
			int Zcoord = blockZ + random.nextInt(16);
			
			(new WorldGenMinable(BlockManager.decoration.blockID, BlockIndex.GRANITE_METADATA, LogicIndex.GRANITE_DEPOSIT_AMOUNT, Block.stone.blockID)).generate(world, random, Xcoord, Ycoord, Zcoord);
		}
	}

}
