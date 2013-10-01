package com.petredy.redmagic.handlers;

import java.util.EnumSet;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import com.petredy.redmagic.Redmagic;
import com.petredy.redmagic.lib.Textures;
import com.petredy.redmagic.redenergy.EnergyMap;
import com.petredy.redmagic.redenergy.RedEnergy;
import com.petredy.redmagic.redenergy.Redkey;
import com.petredy.redmagic.utils.LogUtils;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

public class RenderHandler implements ITickHandler{

	@Override
	public void tickStart(EnumSet<TickType> type, Object... tickData) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tickEnd(EnumSet<TickType> type, Object... tickData) {
		if(type.contains(TickType.CLIENT) && Minecraft.getMinecraft().thePlayer instanceof EntityPlayer){
			EntityPlayer player = Minecraft.getMinecraft().thePlayer;
			for(int x = 0; x < 2; x++){
				for(int y = 0; y < 2; y++){
					for(int z = 0; z < 2; z++){
						RedEnergy en = EnergyMap.getEnergy(Redkey.get((int)player.posX + x, (int)player.posY + y, (int)player.posZ + z));
						if(en != null && en.amount > 0){
							renderEnergyAt((int)player.posX + x, (int)player.posY + y, (int)player.posZ + z);
						}else{
							
						}
					}
				}
			}
		}
	}

	private void renderEnergyAt(int x, int y, int z) {
		 GL11.glDepthMask(false);
	        GL11.glDisable(GL11.GL_CULL_FACE);

	        for (int i = 0; i < 6; i++) {
	            int zCorrection = i == 2 ? -1 : 1;
	            GL11.glPushMatrix();
	            GL11.glTranslated(x, y, z);
	            GL11.glScalef(1F, 1F, 1F);
	            GL11.glTranslated(0, 0, 0.5f * zCorrection);
	            GL11.glClear(GL11.GL_DEPTH_BUFFER_BIT);
	            LogUtils.log("render");
	            renderPulsingQuad(Textures.HOLE_FX, 0.75F);
	            GL11.glPopMatrix();
	        }

	        GL11.glEnable(GL11.GL_CULL_FACE);
	        GL11.glDepthMask(true);
	}

	private void renderPulsingQuad(ResourceLocation texture, float f) {

        FMLClientHandler.instance().getClient().renderEngine.bindTexture(texture);
        Tessellator tessellator = Tessellator.instance;

        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        //GL11.glColor4f(1, 1, 1, pulseTransparency);

        tessellator.startDrawingQuads();
        //tessellator.setColorRGBA_F(1, 1, 1, pulseTransparency);

        tessellator.addVertexWithUV(-0.5D, 0.5D, 0F, 0, 1);
        tessellator.addVertexWithUV(0.5D, 0.5D, 0F, 1, 1);
        tessellator.addVertexWithUV(0.5D, -0.5D, 0F, 1, 0);
        tessellator.addVertexWithUV(-0.5D, -0.5D, 0F, 0, 0);

        tessellator.draw();
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glDisable(GL12.GL_RESCALE_NORMAL);
	}

	@Override
	public EnumSet<TickType> ticks() {
		return EnumSet.of(TickType.RENDER, TickType.CLIENT);
	}

	@Override
	public String getLabel() {
		return this.getClass().getSimpleName();
	}

	
	
}
