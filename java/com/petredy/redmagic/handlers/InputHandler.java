package com.petredy.redmagic.handlers;

import com.petredy.redmagic.lib.Keys;
import com.petredy.redmagic.network.NetworkHandler;
import com.petredy.redmagic.network.packets.KeyPressedDataPacket;
import com.petredy.redmagic.utils.LogUtils;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent.KeyInputEvent;

public class InputHandler {

	@SubscribeEvent
	public void onKeyInput(KeyInputEvent event){
		if(Keys.extra.func_151470_d()){
			LogUtils.log("Pressed key--------------------------------------------");
			
			NetworkHandler.sendToServer(new KeyPressedDataPacket(Keys.KEY_EXTRA_ID));
		}
	}
	
}
