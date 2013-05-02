package redmagic.configuration;

public class LogicIndex {
	
	//Dimension Cracks
	public static final int DIMENSION_CRACK_DEPOSIT_AMOUNT = 1;
	public static final int DIMENSION_CRACK_RARITY = 10;
	
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
	public static int SOUL_START_RESISTENCE = 10;
	
	
	public static final int FILTER_RANGE = 2;
	public static final int FILTER_STORAGE = 100;
	public static final int FILTER_TRANSPORT = 40;
	public static final float FILTER_AIR_RAITING = 0.1F;
	public static final float FILTER_WATER_RAITING = 0.3F;
	public static final float FILTER_LAVA_RAITING = 1F;
	
	public static final int PIPE_MAX_ESSENCES = 5000;
	public static final int PIPE_ESSENCES_PER_TICK = 1000;
	
	
	public static final int FURNACE_MAX_ESSENCES = 10000;
	public static final int FURNACE_COSTS = 100;
	
	public static final int STORAGE_MAX_ESSENCES = 100000;
	
	public static final int ORE_SOULCRYSTAL_CHUNK_DEPOSITS = 10;
	public static final int ORE_SOULCRYSTAL_DEPOSIT_AMOUNT = 5;
	
	public static final int SOUL_TRAP_MAX_NECTAR = 9;
	
	//Education System
	public static final int EDUCATION_BASIC_BLOCKS_NEEDED = 4;
	public static final int EDUCATION_BASIC_BLOCKS_NEEDED_FOR_MODULE = 4;
	
	//Lens
	public static final int LENS_RANGE = 3;
}
