package redmagic.configuration;

public class LogicIndex {
	
	//---------------------------------------------------------------
	//Dimension Cracks
	public static final int DIMENSION_CRACK_DEPOSIT_AMOUNT = 1;
	public static final int DIMENSION_CRACK_RARITY = 10;
	
	//---------------------------------------------------------------
	//ISoul
	public static final String[] SOUL_TYPES = new String[]{
		"none",
		"filter",
		"furnace",
		"storage"
	};
	public static final int SOUL_NONE = 0;
	public static final int SOUL_FILTER = 1;
	public static final int SOUL_FURNACE = 2;
	public static final int SOUL_STORAGE = 3;
	
	public static int SOUL_MAX_INTELLIGENCE = 100;
	public static int SOUL_MAX_STRENGTH = 100;
	public static int SOUL_MAX_CAPACITY = 100;
	public static int SOUL_MAX_ILLUSION = 100;
	public static int SOUL_MAX_SATISFACTION = 100;
	
	public static int SOUL_START_INTELLIGENCE = 10;
	public static int SOUL_START_STRENGTH = 10;
	public static int SOUL_START_CAPACITY = 10;
	public static int SOUL_START_ILLUSION = 10;
	public static int SOUL_START_SATISFACTION = 10;
	
	//---------------------------------------------------------------
	//Soul Fragments
	public static String[] SOUL_FRAGMENT_TYPES = new String[]{
		ItemIndex.FRAGMENT_INTELLIGENCE_NAME,
		ItemIndex.FRAGMENT_STRENGTH_NAME,
		ItemIndex.FRAGMENT_CAPACITY_NAME,
		ItemIndex.FRAGMENT_ILLUSION_NAME,
		ItemIndex.FRAGMENT_SATISFACTION_NAME,
		ItemIndex.FRAGMENT_ALL_NAME
	};
	
	public static int MAX_SOUL_FRAGMENTS_PER_ENTITY = 3;
	public static float SOUL_FRAGMENT_INTELLIGENCE_CHANCE = 0.2F;
	public static float SOUL_FRAGMENT_STRENGTH_CHANCE = 0.2F;
	public static float SOUL_FRAGMENT_CAPACITY_CHANCE = 0.3F;
	public static float SOUL_FRAGMENT_ILLUSION_CHANCE = 0.5F;
	public static float SOUL_FRAGMENT_SATISFACTION_CHANCE = 0.1F;
	public static float SOUL_FRAGMENT_ALL_CHANCE = 0.001F;
	
	public static float[] SOUL_FRAGMENT_CHANCES = new float[]{
		SOUL_FRAGMENT_INTELLIGENCE_CHANCE,
		SOUL_FRAGMENT_STRENGTH_CHANCE,
		SOUL_FRAGMENT_CAPACITY_CHANCE,
		SOUL_FRAGMENT_ILLUSION_CHANCE,
		SOUL_FRAGMENT_SATISFACTION_CHANCE,
		SOUL_FRAGMENT_ALL_CHANCE
	};
	
	public static int SOUL_FRAGMENT_DROP_RANGE = 2;
	
	//---------------------------------------------------------------
	// Filters
	public static final int FILTER_RANGE = 2;
	public static final int FILTER_STORAGE = 100;
	public static final int FILTER_TRANSPORT = 40;
	public static final float FILTER_AIR_RAITING = 0.1F;
	public static final float FILTER_WATER_RAITING = 0.3F;
	public static final float FILTER_LAVA_RAITING = 1F;
	//---------------------------------------------------------------
	//Pipes
	public static final int PIPE_MAX_ESSENCES = 5000;
	public static final int PIPE_ESSENCES_PER_TICK = 1000;
	//---------------------------------------------------------------
	//Machines
	public static final int FURNACE_MAX_ESSENCES = 10000;
	public static final int FURNACE_COSTS = 100;
	
	public static final int STORAGE_MAX_ESSENCES = 100000;
	//---------------------------------------------------------------
	//Ores
	public static final int ORE_SOULCRYSTAL_CHUNK_DEPOSITS = 10;
	public static final int ORE_SOULCRYSTAL_DEPOSIT_AMOUNT = 5;
	//---------------------------------------------------------------
	//Education System
	public static final int EDUCATION_BASIC_BLOCKS_NEEDED = 4;
	public static final int EDUCATION_BASIC_BLOCKS_NEEDED_FOR_MODULE = 4;
}
