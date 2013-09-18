package com.petredy.redmagic.lib;

public class Sounds {
	private static final String SOUND_RESOURCE_LOCATION = Reference.MOD_ID.toLowerCase() + ":";
    private static final String SOUND_PREFIX = Reference.MOD_ID.toLowerCase() + ":";
	
	public static final String[] soundFiles = new String[]{
		SOUND_RESOURCE_LOCATION + "chest_open.wav",
		SOUND_RESOURCE_LOCATION + "chest_close.wav",
		SOUND_RESOURCE_LOCATION + "whisper.wav"
	};
	
	public static final String CHEST_OPEN = SOUND_PREFIX + "chest_open";
	public static final String CHEST_CLOSE = SOUND_PREFIX + "chest_close";
	public static final String SOUL_WHISPER = SOUND_PREFIX + "whisper";
	
	
}
