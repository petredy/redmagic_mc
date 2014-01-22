package com.petredy.redmagic.client.render.glasses;

import java.util.List;

import com.petredy.redmagic.lib.Textures;
import com.petredy.redmagic.utils.GlassesUtils;
import com.petredy.redmagic.utils.ResourceLocationUtils;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.ResourceLocation;

public class GlassesRenderOnline extends GlassesRender{
	public void render(ItemStack stack, EntityPlayer player, int width, int height, MovingObjectPosition target, int switchingTicks){
//		if(switchingTicks < GlassesHelper.SWITCHING_FINISHED ){
//			this.switchToOnline(stack, player, width, height, switchingTicks);
//		}else{
			
		
			ResourceLocation texture = this.getTexture(player);
			
			this.renderOverlay(width, height, texture);
//		}
	}

	protected void switchToOnline(ItemStack stack, EntityPlayer player, int width, int height, int switchingTicks) {
		GlassesUtils.setSwitchingStatus(stack, GlassesUtils.getSwitchingStatus(stack) + 1);
		//ResourceLocation texture = ResourceLocationUtils.getResourceLocation(Texture.OVERLAY_START + switchingTicks + ".png");
		//this.renderOverlay(width, height, texture);
	}
	
	protected ResourceLocation getTexture(EntityPlayer player){
		ResourceLocation texture = Textures.OVERLAY;
		List<EntityMob> mobs = player.worldObj.getEntitiesWithinAABB(EntityMob.class, AxisAlignedBB.getBoundingBox(player.posX - 10, player.posY - 2, player.posZ - 10, player.posX + 10, player.posY + 2, player.posZ + 10));
		if(mobs.size() > 0)texture = Textures.DANGER_OVERLAY;
		return texture;
	}

}
