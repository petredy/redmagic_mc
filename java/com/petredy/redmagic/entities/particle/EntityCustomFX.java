package com.petredy.redmagic.entities.particle;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class EntityCustomFX extends EntityFX{

	public float xStart = 0F;
	public float yStart = 0F;
	public float xEnd = 1F;
	public float yEnd = 1F;
	public ResourceLocation texture;
	
	public EntityCustomFX(World world, double x, double y, double z, ResourceLocation texture) {
		super(world, x, y, z);
		this.texture = texture;
	}
	
	public void setScale(float scale){
		this.particleScale = scale;
	}
	
	public void setMaxAge(int age){
		this.particleMaxAge = age;
	}
	
	public void onUpdate()
    {
		super.onUpdate();
    }
	
	public void renderParticle(Tessellator par1Tessellator, float par2, float par3, float par4, float par5, float par6, float par7)
    {
		Tessellator tessellator1 = new Tessellator();
        tessellator1.startDrawingQuads();
        tessellator1.setBrightness(getBrightnessForRender(par2));
        Minecraft.getMinecraft().getTextureManager().bindTexture(this.texture);
        float f = xStart;
        float f1 = xEnd;
        float f2 = yStart;
        float f3 = yEnd;
        float f4 = 0.1F * particleScale;
        float f5 = (float)((prevPosX + (posX - prevPosX) * (double)par2) - interpPosX);
        float f6 = (float)((prevPosY + (posY - prevPosY) * (double)par2) - interpPosY);
        float f7 = (float)((prevPosZ + (posZ - prevPosZ) * (double)par2) - interpPosZ);
        float f8 = 1.0F;
        tessellator1.setColorOpaque_F(particleRed * f8, particleGreen * f8, particleBlue * f8);
        tessellator1.addVertexWithUV(f5 - par3 * f4 - par6 * f4, f6 - par4 * f4, f7 - par5 * f4 - par7 * f4, f1, f3);
        tessellator1.addVertexWithUV((f5 - par3 * f4) + par6 * f4, f6 + par4 * f4, (f7 - par5 * f4) + par7 * f4, f1, f2);
        tessellator1.addVertexWithUV(f5 + par3 * f4 + par6 * f4, f6 + par4 * f4, f7 + par5 * f4 + par7 * f4, f, f2);
        tessellator1.addVertexWithUV((f5 + par3 * f4) - par6 * f4, f6 - par4 * f4, (f7 + par5 * f4) - par7 * f4, f, f3);
        tessellator1.draw();
    }
}

