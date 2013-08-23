package redmagic.configuration;

public class Sounds {
	private static final String SOUND_RESOURCE_LOCATION = Reference.MOD_ID +":";
    private static final String SOUND_PREFIX = Reference.MOD_ID + ":";

    public static String[] soundFiles = {
           SOUND_RESOURCE_LOCATION + "hammer.wav",
           SOUND_RESOURCE_LOCATION + "chest_open.wav",
           SOUND_RESOURCE_LOCATION + "chest_close.wav"
    };
    
    public static final String HAMMER = SOUND_PREFIX + "hammer";
	public static final String CHEST_OPEN = SOUND_PREFIX + "chest_open";
	public static final String CHEST_CLOSE = SOUND_PREFIX + "chest_close";
}
