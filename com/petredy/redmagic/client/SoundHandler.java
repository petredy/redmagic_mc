package com.petredy.redmagic.client;

import com.petredy.redmagic.lib.Sounds;
import com.petredy.redmagic.utils.LogUtils;

import net.minecraftforge.client.event.sound.PlaySoundEvent;
import net.minecraftforge.event.ForgeSubscribe;



public class SoundHandler {

	@ForgeSubscribe
	public void onSound(PlaySoundEvent event) {
		// For each custom sound file we have defined in Sounds
        for (String soundFile : Sounds.soundFiles) {
            // Try to add the custom sound file to the pool of sounds
            try {
                event.manager.soundPoolSounds.addSound(soundFile);
            }
            // If we cannot add the custom sound file to the pool, log the exception
            catch (Exception e) {
                LogUtils.log("Failed loading sound file: " + soundFile);
            }
        }
    }
}
