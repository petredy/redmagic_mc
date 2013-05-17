package redmagic.client.renderers.glasses;

import java.util.List;

import redmagic.configuration.Texture;
import redmagic.core.Logger;
import redmagic.helpers.GlassesHelper;
import redmagic.helpers.RenderHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;

public class GlassesRenderOnline extends GlassesRender{
	public void render(ItemStack stack, EntityPlayer player, int width, int height, MovingObjectPosition target, int switchingTicks){

		if(switchingTicks < GlassesHelper.SWITCHING_FINISHED){
			this.switchToOnline(stack, player, width, height, switchingTicks);
		}else{
			//Should be in an other mode
			GlassesHelper.renderMachineName(player, target, width, height);
			
			String texture = this.getTexture(player);
			
			this.renderOverlay(width, height, texture);
			
			this.renderMode(width, height);
		}
	}

	protected void switchToOnline(ItemStack stack, EntityPlayer player, int width, int height, int switchingTicks) {
		Logger.log("switch to online");
		GlassesHelper.setSwitchingStatus(stack, GlassesHelper.getSwitchingStatus(stack) + 1);
		String texture = Texture.OVERLAY_START + switchingTicks + ".png";
		this.renderOverlay(width, height, texture);
	}
	
	protected String getTexture(EntityPlayer player){
		String texture = Texture.OVERLAY;
		List<EntityMob> mobs = player.worldObj.getEntitiesWithinAABB(EntityMob.class, AxisAlignedBB.getBoundingBox(player.posX - 10, player.posY - 2, player.posZ - 10, player.posX + 10, player.posY + 2, player.posZ + 10));
		if(mobs.size() > 0)texture = Texture.DANGER_OVERLAY;
		return texture;
	}

}
