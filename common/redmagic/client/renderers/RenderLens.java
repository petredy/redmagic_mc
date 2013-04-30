package redmagic.client.renderers;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;

public class RenderLens extends TileEntitySpecialRenderer{

	@Override
	public void renderTileEntityAt(TileEntity tileentity, double x, double y, double z, float par8) {
        Tessellator tessellator = Tessellator.instance;
        this.bindTextureByName("/misc/beam.png");
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glDisable(GL11.GL_CULL_FACE);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glDepthMask(true);
        tessellator.startDrawingQuads();
        tessellator.setColorRGBA(255, 255, 255, 32);
        tessellator.addVertexWithUV(x + 0, y + 1, z + 0, 0, 0);
        tessellator.addVertexWithUV(x + 1, y + 1, z, 1, 0);
        tessellator.addVertexWithUV(x + 1, y + 2, z, 1, 1);
        tessellator.addVertexWithUV(x + 0, y + 2, z + 0, 0, 1);
        tessellator.draw();
	}

}
