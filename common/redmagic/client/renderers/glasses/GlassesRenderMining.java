package redmagic.client.renderers.glasses;

import org.lwjgl.opengl.GL11;

import redmagic.core.Logger;
import redmagic.helpers.GlassesHelper;
import redmagic.helpers.RenderHelper;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;

public class GlassesRenderMining extends GlassesRenderOnline{
	public void render(ItemStack stack, EntityPlayer player, int width, int height, MovingObjectPosition target, int switchingTicks){
		
		String texture = this.getTexture(player);
		this.renderOverlay(width, height, texture);
		this.renderMode(width, height);
		
		ItemStack ore = GlassesHelper.renderOres(player, width, height);
		if(ore != null)RenderHelper.renderRotatingBlockIntoGUI(Minecraft.getMinecraft().fontRenderer, Minecraft.getMinecraft().renderEngine, ore, width - 80, height - 80, 0, 4);
	}
}
