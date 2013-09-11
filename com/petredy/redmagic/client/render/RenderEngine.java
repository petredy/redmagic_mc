package com.petredy.redmagic.client.render;

import org.lwjgl.opengl.GL11;

import com.petredy.redmagic.Redmagic;
import com.petredy.redmagic.client.ModelEngine;
import com.petredy.redmagic.lib.Rendering;
import com.petredy.redmagic.lib.Textures;
import com.petredy.redmagic.tileentities.TileEntityEngine;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.ForgeDirection;

public class RenderEngine extends TileEntitySpecialRenderer implements ISimpleBlockRenderingHandler{

	public ModelEngine engine;
	
	public RenderEngine(){
		engine = new ModelEngine();
	}
	
	@Override
	public void renderTileEntityAt(TileEntity tileentity, double d0, double d1, double d2, float f) {
		
		TileEntityEngine entity = (TileEntityEngine)tileentity;
		
		RenderCustomItemStack itemRenderer = new RenderCustomItemStack();
		itemRenderer.setRenderManager(RenderManager.instance);
		
		EntityItem item = new EntityItem(entity.worldObj);
		item.setEntityItemStack(new ItemStack(Block.dirt));
		GL11.glPushMatrix();
		itemRenderer.doRenderItem(item, d0 + 0.5, d1 + 0.85, d2 + 0.5, 0, entity.rotate++);
		if(entity.rotate > 360)entity.rotate = 0;
		GL11.glPopMatrix();
		
		
		GL11.glPushMatrix();
		
		GL11.glTranslated(d0 + 0.5, d1 + 0.5, d2 + 0.5);
		
		
		if(entity.side == ForgeDirection.DOWN){
			GL11.glRotatef(180, 0, 0, -1);
		}else if(entity.side == ForgeDirection.WEST){
			GL11.glRotatef(90, 0, 0, 1);
		}else if(entity.side == ForgeDirection.EAST){
			GL11.glRotatef(90, 0, 0, -1);
		}else if(entity.side == ForgeDirection.NORTH){
			GL11.glRotatef(90, -1, 0, 0);
		}else if(entity.side == ForgeDirection.SOUTH){
			GL11.glRotatef(90, 1, 0, 0);
		}
		Redmagic.proxy.bindTexture(Textures.ENGINE);
		engine.render("Engine");
		
		
		
		
		GL11.glPopMatrix();
	}

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
		GL11.glPushMatrix();
		Redmagic.proxy.bindTexture(Textures.ENGINE);
		engine.renderAll();
		
		GL11.glPopMatrix();
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z,
			Block block, int modelId, RenderBlocks renderer) {
		return false;
	}

	@Override
	public boolean shouldRender3DInInventory() {
		return true;
	}

	@Override
	public int getRenderId() {
		return Rendering.ENGINE_ID;
	}

}
