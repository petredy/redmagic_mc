package redmagic.helpers;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenMinable;

import redmagic.blocks.BlockManager;
import redmagic.configuration.LogicIndex;
import redmagic.core.Logger;

public class WorldGenerationHelper {
	
	public static void generateDimensionCracks(World world, Random random, int blockX, int blockZ){
		for(int a = 0; a < LogicIndex.DIMENSION_CRACK_DEPOSIT_AMOUNT; a++){
			int Xcoord = blockX + random.nextInt(16);
			int Ycoord = 30 + random.nextInt(64);
			int Zcoord = blockZ + random.nextInt(16);

			if(random.nextInt(100) < LogicIndex.DIMENSION_CRACK_RARITY)(new WorldGenDimensionCrack()).generate(world, random, Xcoord, Ycoord, Zcoord);
		}	
	}
	
	public static void generateSoulCrystalOre(World world, Random random, int blockX, int blockZ){
		for(int a = 0; a < LogicIndex.ORE_SOULCRYSTAL_CHUNK_DEPOSITS; a++){
			int Xcoord = blockX + random.nextInt(16);
			int Ycoord = random.nextInt(50);
			int Zcoord = blockZ + random.nextInt(16);
			
			(new WorldGenMinable(BlockManager.soulCrystalOre.blockID, LogicIndex.ORE_SOULCRYSTAL_DEPOSIT_AMOUNT)).generate(world, random, Xcoord, Ycoord, Zcoord);
		}
		
	}

}
