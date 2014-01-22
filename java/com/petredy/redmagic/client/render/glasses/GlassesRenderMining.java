package com.petredy.redmagic.client.render.glasses;

import org.lwjgl.opengl.GL11;

import com.petredy.redmagic.utils.GlassesUtils;
import com.petredy.redmagic.utils.RenderUtils;


import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.ResourceLocation;

public class GlassesRenderMining extends GlassesRenderOnline{
	public void render(ItemStack stack, EntityPlayer player, int width, int height, MovingObjectPosition target, int switchingTicks){
		
		ResourceLocation texture = this.getTexture(player);
		this.renderOverlay(width, height, texture);
		this.renderMode(width, height);
		
		ItemStack ore = GlassesUtils.renderOres(player, width, height);
		if(ore != null)RenderUtils.renderRotatingBlockIntoGUI(Minecraft.getMinecraft().fontRenderer, Minecraft.getMinecraft().renderEngine, ore, width - 80, height - 80, 0, 4);
	}
}
