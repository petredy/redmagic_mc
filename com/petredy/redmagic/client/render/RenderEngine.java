package com.petredy.redmagic.client.render;

import org.lwjgl.opengl.GL11;

import com.petredy.redmagic.Redmagic;
import com.petredy.redmagic.client.models.ModelEngine;
import com.petredy.redmagic.lib.Rendering;
import com.petredy.redmagic.lib.Textures;
import com.petredy.redmagic.tileentities.TileEntityEngine;
import com.petredy.redmagic.utils.LogUtils;

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
		
		double x = 0.5;
		double y = 0.85;
		double z = 0.5;
		
		
		if(entity.side == ForgeDirection.DOWN){
			y = 0.15;
		}else if(entity.side == ForgeDirection.WEST){
			y = 0.5;
			x = 0.15;
		}else if(entity.side == ForgeDirection.EAST){
			y = 0.5;
			x = 0.85;
		}else if(entity.side == ForgeDirection.NORTH){
			y = 0.5;
			z = 0.15;
		}else if(entity.side == ForgeDirection.SOUTH){
			y = 0.5;
			z = 0.85;
		}
		if(entity.getItem() != null){
			RenderCustomItemStack itemRenderer = new RenderCustomItemStack();
			itemRenderer.setRenderManager(RenderManager.instance);
			
			EntityItem item = new EntityItem(entity.worldObj);
			item.setEntityItemStack(entity.getItem());
			GL11.glPushMatrix();
			itemRenderer.doRenderItem(item, d0 + x, d1 + y, d2 + z, 0, entity.rotate += entity.speed);
			if(entity.rotate > 360)entity.rotate = 0;
			GL11.glPopMatrix();
		}
		
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
		engine.render("Engine");
		
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
