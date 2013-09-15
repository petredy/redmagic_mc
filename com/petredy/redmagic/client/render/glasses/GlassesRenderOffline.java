package com.petredy.redmagic.client.render.glasses;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;

public class GlassesRenderOffline extends GlassesRender{
	public void render(ItemStack stack, EntityPlayer player, int width, int height, MovingObjectPosition target, int switchingTicks){
//		if(switchingTicks < GlassesHelper.SWITCHING_FINISHED){
//			GlassesHelper.setSwitchingStatus(stack, GlassesHelper.getSwitchingStatus(stack) + 1);
//			String texture = Texture.OVERLAY_START + (GlassesHelper.SWITCHING_FINISHED - switchingTicks - 1) + ".png";
//			this.renderOverlay(width, height, texture);
//		}
	}
}
