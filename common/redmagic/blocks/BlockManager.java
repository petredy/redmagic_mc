package redmagic.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import redmagic.configuration.BlockIndex;
import net.minecraft.block.Block;
import net.minecraftforge.common.Configuration;

public class BlockManager {
	
	public static Block machine;
	public static Block pipe;
	public static Block dimensionCrack;
	public static Block workTable;
	public static Block soulCrystalOre;
	public static Block soulTrap;
	public static Block education;
	public static Block crystalizer;
	public static Block lens;
	public static Block collector;
	public static Block soulJuice;
	public static Block crystal;
	public static Block juicer;
	public static Block essence;
	
	public static void init(){
		machine = new BlockMachine(BlockIndex.MACHINE_ID);
		pipe = new BlockPipe(BlockIndex.PIPE_ID);
		dimensionCrack = new BlockDimensionCrack(BlockIndex.DIMENSION_CRACK_ID);
		workTable = new BlockWorkTable(BlockIndex.WORK_TABLE_ID);
		soulCrystalOre = new BlockSoulCrystalOre(BlockIndex.SOUL_CRYSTAL_ORE_ID);
		soulTrap = new BlockSoulTrap(BlockIndex.SOUL_TRAP_ID);
		education = new BlockEducation(BlockIndex.EDUCATION_ID);
		crystalizer = new BlockCrystalizer(BlockIndex.CRYSTALIZER_ID);
		lens = new BlockLens(BlockIndex.LENS_ID);
		collector = new BlockCollector(BlockIndex.COLLECTOR_ID);
		soulJuice = new BlockSoulJuice(BlockIndex.SOUL_JUICE_ID);
		crystal = new BlockCrystal(BlockIndex.CRYSTAL_ID);
		juicer = new BlockSoulJuicer(BlockIndex.JUICER_ID);
		essence = new BlockLiquidEssence(BlockIndex.LIQUID_ESSENCE_ID);
		
		GameRegistry.registerBlock(essence, BlockIndex.LIQUID_ESSENCE_NAME);
		GameRegistry.registerBlock(juicer, BlockIndex.JUICER_NAME);
		GameRegistry.registerBlock(crystal, ItemBlockCrystal.class, BlockIndex.CRYSTAL_NAME);
		GameRegistry.registerBlock(soulJuice, BlockIndex.SOUL_JUICE_NAME);
		GameRegistry.registerBlock(collector, BlockIndex.COLLECTOR_NAME);
		GameRegistry.registerBlock(lens, BlockIndex.LENS_NAME);
		GameRegistry.registerBlock(crystalizer, BlockIndex.CRYSTALIZER_NAME);
		GameRegistry.registerBlock(education, ItemBlockEducation.class, BlockIndex.EDUCATION_NAME);
		GameRegistry.registerBlock(soulTrap, BlockIndex.SOUL_TRAP_NAME);
		GameRegistry.registerBlock(soulCrystalOre, BlockIndex.SOUL_CRYSTAL_ORE_NAME);
		GameRegistry.registerBlock(workTable, BlockIndex.WORK_TABLE_NAME);
		GameRegistry.registerBlock(dimensionCrack, BlockIndex.DIMENSION_CRACK_NAME);
		GameRegistry.registerBlock(pipe, BlockIndex.PIPE_NAME);
		GameRegistry.registerBlock(machine, ItemBlockMachine.class, BlockIndex.MACHINE_NAME);
		registerRecipes();
	}
	
	public static void registerRecipes(){
		
	}
	
	public static void config(Configuration config){
		BlockIndex.MACHINE_ID = config.getBlock(BlockIndex.MACHINE_NAME, BlockIndex.MACHINE_DEFAULT_ID).getInt(BlockIndex.MACHINE_DEFAULT_ID);
		BlockIndex.PIPE_ID = config.getBlock(BlockIndex.PIPE_NAME, BlockIndex.PIPE_DEFAULT_ID).getInt(BlockIndex.PIPE_DEFAULT_ID);
		BlockIndex.DIMENSION_CRACK_ID = config.getBlock(BlockIndex.DIMENSION_CRACK_NAME, BlockIndex.DIMENSION_CRACK_DEFAULT_ID).getInt(BlockIndex.DIMENSION_CRACK_DEFAULT_ID);
		BlockIndex.WORK_TABLE_ID = config.getBlock(BlockIndex.WORK_TABLE_NAME, BlockIndex.WORK_TABLE_DEFAULT_ID).getInt(BlockIndex.WORK_TABLE_DEFAULT_ID);
		BlockIndex.SOUL_CRYSTAL_ORE_ID = config.getBlock(BlockIndex.SOUL_CRYSTAL_ORE_NAME, BlockIndex.SOUL_CRYSTAL_ORE_DEFAULT_ID).getInt(BlockIndex.SOUL_CRYSTAL_ORE_DEFAULT_ID);
		BlockIndex.SOUL_TRAP_ID = config.getBlock(BlockIndex.SOUL_TRAP_NAME, BlockIndex.SOUL_TRAP_DEFAULT_ID).getInt(BlockIndex.SOUL_TRAP_DEFAULT_ID);
		BlockIndex.EDUCATION_ID = config.getBlock(BlockIndex.EDUCATION_NAME, BlockIndex.EDUCATION_DEFAULT_ID).getInt(BlockIndex.EDUCATION_DEFAULT_ID);
		BlockIndex.CRYSTALIZER_ID = config.getBlock(BlockIndex.CRYSTALIZER_NAME, BlockIndex.CRYSTALIZER_DEFAULT_ID).getInt(BlockIndex.CRYSTALIZER_DEFAULT_ID);
		BlockIndex.LENS_ID = config.getBlock(BlockIndex.LENS_NAME, BlockIndex.LENS_DEFAULT_ID).getInt(BlockIndex.LENS_DEFAULT_ID);
		BlockIndex.COLLECTOR_ID = config.getBlock(BlockIndex.COLLECTOR_NAME, BlockIndex.COLLECTOR_DEFAULT_ID).getInt(BlockIndex.COLLECTOR_DEFAULT_ID);
		BlockIndex.SOUL_JUICE_ID = config.getBlock(BlockIndex.SOUL_JUICE_NAME, BlockIndex.SOUL_JUICE_DEFAULT_ID).getInt(BlockIndex.SOUL_JUICE_DEFAULT_ID);
		BlockIndex.CRYSTAL_ID = config.getBlock(BlockIndex.CRYSTAL_NAME, BlockIndex.CRYSTAL_DEFAULT_ID).getInt(BlockIndex.CRYSTAL_DEFAULT_ID);
		BlockIndex.JUICER_ID = config.getBlock(BlockIndex.JUICER_NAME, BlockIndex.JUICER_DEFAULT_ID).getInt(BlockIndex.JUICER_DEFAULT_ID);
		BlockIndex.LIQUID_ESSENCE_ID = config.getBlock(BlockIndex.LIQUID_ESSENCE_NAME, BlockIndex.LIQUID_ESSENCE_DEFAULT_ID).getInt(BlockIndex.LIQUID_ESSENCE_DEFAULT_ID);
		
	}
	
}
