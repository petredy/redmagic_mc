package redmagic.client.renderers;

import org.lwjgl.opengl.GL11;

import redmagic.Redmagic;
import redmagic.client.models.ModelRune;
import redmagic.client.models.ModelBlock;
import redmagic.configuration.BlockIndex;
import redmagic.configuration.ItemIndex;
import redmagic.configuration.RenderIndex;
import redmagic.helpers.LogHelper;
import redmagic.lib.rune.Marker;
import redmagic.tileentities.TileEntityRune;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;


public class RenderRune extends TileEntitySpecialRenderer implements ISimpleBlockRenderingHandler{

	public ModelRune rune;
	public ModelBlock block;
	
	public RenderRune(){
		rune = new ModelRune();
		block = new ModelBlock();
	}
	
	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
		Redmagic.proxy.bindTexture("models/runeblock.png");
		GL11.glPushMatrix();
		GL11.glColor4f(1, 1, 1, 1);
		this.block.render();
		GL11.glPopMatrix();
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
		return true;
	}
	
	@Override
	public void renderTileEntityAt(TileEntity tileentity, double x, double y, double z, float f) {
		
		TileEntityRune entity = (TileEntityRune)tileentity;
		
		
		
		
		Redmagic.proxy.bindTexture("models/rune.png");
		int count = 0;
		for(Marker marker: entity.getMarkers()){
			if(marker != null){
				GL11.glPushMatrix();
				
				switch(marker.id){
				case BlockIndex.RUNE_RED_METADATA:
					GL11.glColor4f(0.545f, 0.145f, 0, 1);
					break;
				case BlockIndex.RUNE_BLUE_METADATA:
					GL11.glColor4f(0.063f, 0.306f, 0.545f, 1);
					break;
				case BlockIndex.RUNE_GREEN_METADATA:
					GL11.glColor4f(0.133f, 0.545f, 0.133f, 1);
				}			
				GL11.glTranslated(marker.getX(x, count), y + 1, marker.getZ(z, count));
				rune.render();
				
				GL11.glPopMatrix();
			}
			count++;
		}
		Redmagic.proxy.bindTexture("models/runeblock.png");
		GL11.glPushMatrix();
		GL11.glTranslated(x + 0.5d, y + 0.5d, z + 0.5d);
		GL11.glColor4f(1, 1, 1, 1);
		
		block.render();
		GL11.glPopMatrix();
		
		
		
		
	}

	@Override
	public boolean shouldRender3DInInventory() {
		return false;
	}
	@Override
	public int getRenderId() {
		return RenderIndex.RUNE;
	}


	

}
