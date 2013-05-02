package redmagic.client.renderers;

import java.util.Random;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;

import redmagic.tileentities.TileEntityLens;

public class RenderLens extends TileEntitySpecialRenderer{

	@Override
	public void renderTileEntityAt(TileEntity tileentity, double x, double y, double z, float par8) {
		if(((TileEntityLens)tileentity).active){
			Tessellator tessellator = Tessellator.instance;
	        this.bindTextureByName("/misc/beam.png");
	        GL11.glTexParameterf(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, 10497.0F);
            GL11.glTexParameterf(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, 10497.0F);
	        GL11.glDisable(GL11.GL_LIGHTING);
	        GL11.glDisable(GL11.GL_CULL_FACE);
	        GL11.glDisable(GL11.GL_BLEND);
	        GL11.glDepthMask(true);
	        tessellator.startDrawingQuads();
	        tessellator.setColorRGBA(255, 255, 255, 32);
	        
	        //Beam
	        double texY = new Random().nextDouble() * 10 + 10;
	        tessellator.addVertexWithUV(x + 0.4, y, z + 0.4, 0, 0);
	        tessellator.addVertexWithUV(x + 0.6, y, z + 0.4, 1, 0);
	        tessellator.addVertexWithUV(x + 0.6, y - 3, z + 0.4, 1, texY);
	        tessellator.addVertexWithUV(x + 0.4, y - 3, z + 0.4, 0, texY);
	        
	        tessellator.addVertexWithUV(x + 0.4, y, z + 0.6, 0, 0);
	        tessellator.addVertexWithUV(x + 0.6, y, z + 0.6, 1, 0);
	        tessellator.addVertexWithUV(x + 0.6, y - 3, z + 0.6, 1, texY);
	        tessellator.addVertexWithUV(x + 0.4, y - 3, z + 0.6, 0, texY);
	        
	        tessellator.addVertexWithUV(x + 0.4, y, z + 0.6, 0, 0);
	        tessellator.addVertexWithUV(x + 0.4, y, z + 0.4, 1, 0);
	        tessellator.addVertexWithUV(x + 0.4, y - 3, z + 0.4, 1, texY);
	        tessellator.addVertexWithUV(x + 0.4, y - 3, z + 0.6, 0, texY);
	        
	        tessellator.addVertexWithUV(x + 0.6, y, z + 0.6, 0, 0);
	        tessellator.addVertexWithUV(x + 0.6, y, z + 0.4, 1, 0);
	        tessellator.addVertexWithUV(x + 0.6, y - 3, z + 0.4, 1, texY);
	        tessellator.addVertexWithUV(x + 0.6, y - 3, z + 0.6, 0, texY);
	        
	        tessellator.draw();
	        
	        
	        //Shine
	        GL11.glEnable(GL11.GL_BLEND);
	        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
	        GL11.glDepthMask(false);
	        tessellator.startDrawingQuads();
	        tessellator.setColorRGBA(255, 255, 255, 32);
	        
	        tessellator.addVertexWithUV(x + 0.3, y, z + 0.3, 0, 0);
	        tessellator.addVertexWithUV(x + 0.7, y, z + 0.3, 1, 0);
	        tessellator.addVertexWithUV(x + 0.7, y - 3, z + 0.3, 1, texY);
	        tessellator.addVertexWithUV(x + 0.3, y - 3, z + 0.3, 0, texY);
	        
	        tessellator.addVertexWithUV(x + 0.3, y, z + 0.7, 0, 0);
	        tessellator.addVertexWithUV(x + 0.7, y, z + 0.7, 1, 0);
	        tessellator.addVertexWithUV(x + 0.7, y - 3, z + 0.7, 1, texY);
	        tessellator.addVertexWithUV(x + 0.3, y - 3, z + 0.7, 0, texY);
	        
	        tessellator.addVertexWithUV(x + 0.3, y, z + 0.7, 0, 0);
	        tessellator.addVertexWithUV(x + 0.3, y, z + 0.3, 1, 0);
	        tessellator.addVertexWithUV(x + 0.3, y - 3, z + 0.3, 1, texY);
	        tessellator.addVertexWithUV(x + 0.3, y - 3, z + 0.7, 0, texY);
	        
	        tessellator.addVertexWithUV(x + 0.7, y, z + 0.7, 0, 0);
	        tessellator.addVertexWithUV(x + 0.7, y, z + 0.3, 1, 0);
	        tessellator.addVertexWithUV(x + 0.7, y - 3, z + 0.3, 1, texY);
	        tessellator.addVertexWithUV(x + 0.7, y - 3, z + 0.7, 0, texY);
	        tessellator.draw();
	        GL11.glEnable(GL11.GL_LIGHTING);
            GL11.glEnable(GL11.GL_TEXTURE_2D);
            GL11.glDepthMask(true);
		}
	}

}
