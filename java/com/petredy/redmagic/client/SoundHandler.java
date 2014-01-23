package com.petredy.redmagic.client;

import com.petredy.redmagic.lib.Sounds;
import com.petredy.redmagic.utils.LogUtils;

import net.minecraftforge.client.event.sound.SoundLoadEvent;



public class SoundHandler {

	@ForgeSubscribe
	public void onSound(SoundLoadEvent event) {
		// For each custom sound file we have defined in Sounds
        for (String soundFile : Sounds.soundFiles) {
            // Try to add the custom sound file to the pool of sounds
            try {
                //event.manager.addSound(soundFile);
            }
            // If we cannot add the custom sound file to the pool, log the exception
            catch (Exception e) {
                LogUtils.log("Failed loading sound file: " + soundFile);
            }
        }
    }
}
