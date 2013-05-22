package redmagic.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import redmagic.configuration.BlockIndex;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.Configuration;

public class BlockManager {
	
	public static Block machine;
	public static Block pipe;
	public static Block dimensionCrack;
	public static Block workTable;
	public static Block soulCrystalOre;
	public static Block soulTrap;
	public static Block extractor;
	public static Block collector;
	public static Block crystal;
	public static Block essence;
	public static Block bank;
	public static Block mold;
	public static Block sapling;
	public static Block wood;
	public static Block leaves;
	
	public static void init(){
		//machine = new BlockMachine(BlockIndex.MACHINE_ID);
		pipe = new BlockPipe(BlockIndex.PIPE_ID);
		dimensionCrack = new BlockDimensionCrack(BlockIndex.DIMENSION_CRACK_ID);
		workTable = new BlockWorkTable(BlockIndex.WORK_TABLE_ID);
		soulCrystalOre = new BlockSoulCrystalOre(BlockIndex.SOUL_CRYSTAL_ORE_ID);
		//soulTrap = new BlockSoulTrap(BlockIndex.SOUL_TRAP_ID);
		extractor = new BlockExtractor(BlockIndex.EXTRACTOR_ID);
		collector = new BlockCollector(BlockIndex.COLLECTOR_ID);
		crystal = new BlockCrystal(BlockIndex.CRYSTAL_ID);
		essence = new BlockLiquidEssence(BlockIndex.LIQUID_ESSENCE_ID);
		bank = new BlockBank(BlockIndex.BANK_ID);
		mold = new BlockSoulMold(BlockIndex.MOLD_ID);
		sapling = new BlockSoulSapling(BlockIndex.SAPLING_ID);
		wood = new BlockSoulWood(BlockIndex.WOOD_ID);
		leaves = new BlockSoulLeaves(BlockIndex.LEAVES_ID);
		
		GameRegistry.registerBlock(leaves, ItemBlockLeaves.class, BlockIndex.LEAVES_NAME);
		GameRegistry.registerBlock(wood, ItemBlockWood.class, BlockIndex.WOOD_NAME);
		GameRegistry.registerBlock(sapling, ItemBlockSapling.class, BlockIndex.SAPLING_NAME);
		GameRegistry.registerBlock(mold, BlockIndex.MOLD_NAME);
		GameRegistry.registerBlock(bank, BlockIndex.BANK_NAME);
		GameRegistry.registerBlock(essence, BlockIndex.LIQUID_ESSENCE_NAME);
		GameRegistry.registerBlock(crystal, ItemBlockCrystal.class, BlockIndex.CRYSTAL_NAME);
		GameRegistry.registerBlock(collector, BlockIndex.COLLECTOR_NAME);
		GameRegistry.registerBlock(extractor, ItemBlockExtractor.class, BlockIndex.EXTRACTOR_NAME);
		//GameRegistry.registerBlock(soulTrap, BlockIndex.SOUL_TRAP_NAME);
		GameRegistry.registerBlock(soulCrystalOre, BlockIndex.SOUL_CRYSTAL_ORE_NAME);
		GameRegistry.registerBlock(workTable, BlockIndex.WORK_TABLE_NAME);
		GameRegistry.registerBlock(dimensionCrack, BlockIndex.DIMENSION_CRACK_NAME);
		GameRegistry.registerBlock(pipe, BlockIndex.PIPE_NAME);
		//GameRegistry.registerBlock(machine, ItemBlockMachine.class, BlockIndex.MACHINE_NAME);
		registerRecipes();
	}
	
	public static void registerRecipes(){
		GameRegistry.addRecipe(new ItemStack(workTable), new Object[]{
			"III",
			"PWP",
			"PCP",
			'I', Item.ingotIron,
			'P', Item.paper,
			'W', Block.workbench,
			'C', Block.chest
		});
		
		GameRegistry.addShapelessRecipe(new ItemStack(Block.planks, 4), new Object[]{
			new ItemStack(wood, 1, BlockIndex.WOOD_FRAGMENT_METADATA),
		});
	}
	
	public static void config(Configuration config){
		BlockIndex.MACHINE_ID = config.getBlock(BlockIndex.MACHINE_NAME, BlockIndex.MACHINE_DEFAULT_ID).getInt(BlockIndex.MACHINE_DEFAULT_ID);
		BlockIndex.PIPE_ID = config.getBlock(BlockIndex.PIPE_NAME, BlockIndex.PIPE_DEFAULT_ID).getInt(BlockIndex.PIPE_DEFAULT_ID);
		BlockIndex.DIMENSION_CRACK_ID = config.getBlock(BlockIndex.DIMENSION_CRACK_NAME, BlockIndex.DIMENSION_CRACK_DEFAULT_ID).getInt(BlockIndex.DIMENSION_CRACK_DEFAULT_ID);
		BlockIndex.WORK_TABLE_ID = config.getBlock(BlockIndex.WORK_TABLE_NAME, BlockIndex.WORK_TABLE_DEFAULT_ID).getInt(BlockIndex.WORK_TABLE_DEFAULT_ID);
		BlockIndex.SOUL_CRYSTAL_ORE_ID = config.getBlock(BlockIndex.SOUL_CRYSTAL_ORE_NAME, BlockIndex.SOUL_CRYSTAL_ORE_DEFAULT_ID).getInt(BlockIndex.SOUL_CRYSTAL_ORE_DEFAULT_ID);
		BlockIndex.SOUL_TRAP_ID = config.getBlock(BlockIndex.SOUL_TRAP_NAME, BlockIndex.SOUL_TRAP_DEFAULT_ID).getInt(BlockIndex.SOUL_TRAP_DEFAULT_ID);
		BlockIndex.EXTRACTOR_ID = config.getBlock(BlockIndex.EXTRACTOR_NAME, BlockIndex.EXTRACTOR_DEFAULT_ID).getInt(BlockIndex.EXTRACTOR_DEFAULT_ID);
		BlockIndex.COLLECTOR_ID = config.getBlock(BlockIndex.COLLECTOR_NAME, BlockIndex.COLLECTOR_DEFAULT_ID).getInt(BlockIndex.COLLECTOR_DEFAULT_ID);
		BlockIndex.CRYSTAL_ID = config.getBlock(BlockIndex.CRYSTAL_NAME, BlockIndex.CRYSTAL_DEFAULT_ID).getInt(BlockIndex.CRYSTAL_DEFAULT_ID);
		BlockIndex.LIQUID_ESSENCE_ID = config.getBlock(BlockIndex.LIQUID_ESSENCE_NAME, BlockIndex.LIQUID_ESSENCE_DEFAULT_ID).getInt(BlockIndex.LIQUID_ESSENCE_DEFAULT_ID);
		BlockIndex.BANK_ID = config.getBlock(BlockIndex.BANK_NAME, BlockIndex.BANK_DEFAULT_ID).getInt(BlockIndex.BANK_DEFAULT_ID);
		BlockIndex.MOLD_ID = config.getBlock(BlockIndex.MOLD_NAME, BlockIndex.MOLD_DEFAULT_ID).getInt(BlockIndex.MOLD_DEFAULT_ID);
		BlockIndex.SAPLING_ID = config.getBlock(BlockIndex.SAPLING_NAME, BlockIndex.SAPLING_DEFAULT_ID).getInt(BlockIndex.SAPLING_DEFAULT_ID);
		BlockIndex.WOOD_ID = config.getBlock(BlockIndex.WOOD_NAME, BlockIndex.WOOD_DEFAULT_ID).getInt(BlockIndex.WOOD_DEFAULT_ID);
		BlockIndex.LEAVES_ID = config.getBlock(BlockIndex.LEAVES_NAME, BlockIndex.LEAVES_DEFAULT_ID).getInt(BlockIndex.LEAVES_DEFAULT_ID);
		
	}
	
}
