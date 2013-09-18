package com.petredy.redmagic.client.render;

import org.lwjgl.opengl.GL11;

import com.petredy.redmagic.Redmagic;
import com.petredy.redmagic.client.models.ModelCage;
import com.petredy.redmagic.lib.Rendering;
import com.petredy.redmagic.lib.Textures;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;

public class RenderCage extends TileEntitySpecialRenderer implements ISimpleBlockRenderingHandler{

	ModelCage cage;
	
	public RenderCage(){
		cage = new ModelCage();
	}
	
	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
		GL11.glPushMatrix();
		Redmagic.proxy.bindTexture(Textures.CAGE);
		GL11.glRotated(90, 0, 1, 0);
		cage.render();
		
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
		return Rendering.CAGE_ID;
	}

	@Override
	public void renderTileEntityAt(TileEntity tileentity, double d0, double d1, double d2, float f) {
		
		GL11.glPushMatrix();
		Redmagic.proxy.bindTexture(Textures.CAGE);
		
		GL11.glTranslated(d0 + 0.5, d1 + 0.5, d2 + 0.5);
		GL11.glScalef(0.6F, 0.6F, 0.6F);
		cage.render();
		
		GL11.glPopMatrix();
		
	}

	
	
}
