package redmagic.client.renderers;

import java.util.Random;

import org.lwjgl.opengl.GL11;

import redmagic.Redmagic;
import redmagic.client.models.ModelAltar;
import redmagic.client.models.ModelBlock;
import redmagic.configuration.RenderIndex;
import redmagic.tileentities.TileEntityAltar;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class RenderAltar extends TileEntitySpecialRenderer implements ISimpleBlockRenderingHandler{

	public ModelBlock block;
	public ModelAltar altar;
	
	public RenderAltar(){
		block = new ModelBlock();
		altar = new ModelAltar();
	}
	
	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
		Redmagic.proxy.bindTexture("models/altar.png");
		GL11.glPushMatrix();
		
		altar.render();
		
		
		GL11.glPopMatrix();
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
		return RenderIndex.ALTAR;
	}

	@Override
	public void renderTileEntityAt(TileEntity tileentity, double d0, double d1, double d2, float f) {
		
		TileEntityAltar entity = (TileEntityAltar)tileentity;
		
		Redmagic.proxy.bindTexture("models/altar.png");
		GL11.glPushMatrix();
		GL11.glTranslated(d0 + 0.5, d1 + 0.5, d2 + 0.5);
		altar.render();
		GL11.glPopMatrix();
		
		if(entity.path != null){
		
			Redmagic.proxy.bindTexture("models/block.png");
			GL11.glPushMatrix();
			GL11.glTranslated(d0 + 0.5, d1 + 0.5, d2 + 0.5);
			GL11.glScalef(0.4f, 0.4f, 0.4f);
			
			float x = entity.rotationX;
			float y = entity.rotationY;
			float z = entity.rotationZ;
			float speed = entity.rotationSpeed;
			
			GL11.glRotatef(x, 1, 0, 0);
			GL11.glRotatef(y, 0, 1, 0);
			GL11.glRotatef(z, 0, 0, 1);
			
			block.render();
			
			Random rand = new Random();
			float offX = rand.nextFloat() * speed;
			float offY = rand.nextFloat() * speed;
			float offZ = rand.nextFloat() * speed;
			entity.rotationX = x + offX < 360 ? x + offX: 0;
			entity.rotationY = y + offY < 360 ? y + offY: 0;
			entity.rotationZ = z + offZ < 360 ? z + offZ: 0;
			
			GL11.glPopMatrix();
		
		}
	}

}
