package redmagic.client.renderers;

import org.lwjgl.opengl.GL11;

import redmagic.client.models.ModelPipe;
import redmagic.configuration.RenderIndex;
import redmagic.configuration.Texture;
import redmagic.tileentities.TileEntityPipe;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;

public class RenderPipe extends TileEntitySpecialRenderer implements ISimpleBlockRenderingHandler{

	public ModelPipe model;
	public String texture;
	
	public RenderPipe()
	{
		model = new ModelPipe();
	}
	
	public void renderAModelAt(TileEntity entity, double d, double d1, double d2, float f)
	{	
		GL11.glPushMatrix();
		GL11.glPushMatrix();
		GL11.glTranslatef((float)d + 0.5f, (float)d1 + 0.5f, (float)d2 + 0.5f);
		GL11.glScalef(0.515F, 0.515F, 0.515F);
		if(entity instanceof TileEntityPipe){
			TileEntityPipe pipe = (TileEntityPipe)entity;
			if(pipe.getEssences() > 0){
				if(pipe.isExtractor()){
					this.texture = Texture.PIPE_EXTRACTOR_ACTIVE;
				}else if(pipe.isFiller()){
					this.texture = Texture.PIPE_FILLER_ACTIVE;
				}else{
					this.texture = Texture.PIPE_ACTIVE;
				}
			}else{
				if(pipe.isExtractor()){
					this.texture = Texture.PIPE_EXTRACTOR_INACTIVE;
				}else if(pipe.isFiller()){
					this.texture = Texture.PIPE_FILLER_INACTIVE;
				}else{
					this.texture = Texture.PIPE_INACTIVE;
				}
			}
			FMLClientHandler.instance().getClient().renderEngine.bindTexture(this.texture);
			model.renderPart("base");
			if(pipe.left)model.renderPart("left");
			if(pipe.right)model.renderPart("right");
			if(pipe.back)model.renderPart("back");
			if(pipe.front)model.renderPart("front");
			if(pipe.bottom)model.renderPart("bottom");
			if(pipe.top)model.renderPart("top");
		}
		
		
		
		GL11.glPopMatrix();
		GL11.glPopMatrix();
	}
	
	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
		GL11.glPushMatrix();
		GL11.glPushMatrix();
		GL11.glTranslatef((float)0, (float)0, (float)0);
		GL11.glScalef(0.51F, 0.51F, 0.51F);
		FMLClientHandler.instance().getClient().renderEngine.bindTexture("");
		model.renderPart("base");
		model.renderPart("top");
		model.renderPart("bottom");
		GL11.glPopMatrix();
		GL11.glPopMatrix();
	}
	
	public void renderTileEntityAt(TileEntity tileentity, double d, double d1, double d2, float f)
	{
		renderAModelAt(tileentity, d, d1, d2, f);
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
		return false;
	}

	@Override
	public boolean shouldRender3DInInventory() {
		return true;
	}

	@Override
	public int getRenderId() {
		return RenderIndex.PIPE;
	}
}
