package redmagic.entities.particle;

import org.lwjgl.opengl.GL11;

import redmagic.configuration.Texture;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.World;

public class EntityCrackFX extends EntityFX{

	public EntityCrackFX(World world, double x, double y, double z) {
		super(world, x, y, z);
		this.particleMaxAge = 10;
		this.worldObj = world;
		this.setParticleTextureIndex(0);
		this.particleScale = 5;
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
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, Minecraft.getMinecraft().renderEngine.getTexture(Texture.DIMENSION_CRACK_FX));
        float f = (float)(this.particleTextureIndexX % 16);
        float f1 = f + 1;
        float f2 = (float)(this.particleTextureIndexY / 16);
        float f3 = f2 + 1F;
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
