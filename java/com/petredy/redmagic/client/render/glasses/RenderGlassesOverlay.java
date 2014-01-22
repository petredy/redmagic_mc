package com.petredy.redmagic.client.render.glasses;

import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;

import org.lwjgl.opengl.GL11;

import com.petredy.redmagic.utils.GlassesUtils;
import com.petredy.redmagic.utils.ItemUtils;

public class RenderGlassesOverlay {
	public static void render(ItemStack stack, EntityPlayer player, ScaledResolution resolution, float partialTicks, boolean hasScreen, int mouseX, int mouseY){
		
		ScaledResolution scaledresolution = new ScaledResolution(Minecraft.getMinecraft().gameSettings, Minecraft.getMinecraft().displayWidth, Minecraft.getMinecraft().displayHeight);
        int width = scaledresolution.getScaledWidth();
        int height = scaledresolution.getScaledHeight();
		MovingObjectPosition position = ItemUtils.getMovingObjectPosition(player.worldObj, player, true);
		
		
		GlassesRender render = GlassesUtils.getRenderByStack(stack);
		if(render != null){
			render.render(stack, player, width, height, position, GlassesUtils.getSwitchingStatus(stack));
		}
	}
}
