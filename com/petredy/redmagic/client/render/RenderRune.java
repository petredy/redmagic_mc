package com.petredy.redmagic.client.render;

import org.lwjgl.opengl.GL11;

import com.petredy.redmagic.Redmagic;
import com.petredy.redmagic.client.models.ModelRune;
import com.petredy.redmagic.lib.Rendering;
import com.petredy.redmagic.lib.Runes;
import com.petredy.redmagic.rune.Marker;
import com.petredy.redmagic.tileentities.TileEntityRune;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;

public class RenderRune extends TileEntitySpecialRenderer implements ISimpleBlockRenderingHandler{

	public ModelRune rune;
	
	public RenderRune(){
		rune = new ModelRune();
	}
	
	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
		
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean shouldRender3DInInventory() {
		return false;
	}

	@Override
	public int getRenderId() {
		return Rendering.RUNE_ID;
	}

	@Override
	public void renderTileEntityAt(TileEntity tileentity, double x, double y, double z, float f) {
		TileEntityRune entity = (TileEntityRune)tileentity;
		
		GL11.glPushMatrix();
		Redmagic.proxy.bindTexture(Runes.TEXTURE);
		int count = 0;
		for(Marker marker: entity.getMarkers()){
			if(marker != null){
				GL11.glPushMatrix();
				
				switch(marker.id){
				case Runes.RUNE_RED_METADATA:
					GL11.glColor4f(0.545f, 0.145f, 0, 1);
					break;
				case Runes.RUNE_BLUE_METADATA:
					GL11.glColor4f(0.063f, 0.306f, 0.545f, 1);
					break;
				case Runes.RUNE_GREEN_METADATA:
					GL11.glColor4f(0.133f, 0.545f, 0.133f, 1);
				}			
				GL11.glTranslated(marker.getX(x, count), y, marker.getZ(z, count));
				GL11.glScalef(2.0F, 2.0F, 2.0F);
				rune.render();
				
				GL11.glPopMatrix();
			}
			count++;
		}
		
		
		GL11.glPopMatrix();
	}

}
