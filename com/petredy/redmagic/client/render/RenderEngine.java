/**
 * Copyright (c) SpaceToad, 2011 http://www.mod-buildcraft.com
 *
 * BuildCraft is distributed under the terms of the Minecraft Mod Public License
 * 1.0, or MMPL. Please check the contents of the license located in
 * http://www.mod-buildcraft.com/MMPL-1.0.txt
 */
package com.petredy.redmagic.client.render;

import net.minecraft.block.Block;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.ForgeDirection;
import org.lwjgl.opengl.GL11;

import com.petredy.redmagic.Redmagic;
import com.petredy.redmagic.client.models.ModelEngine;
import com.petredy.redmagic.lib.Textures;
import com.petredy.redmagic.tileentities.TileEntityEngine;
import com.petredy.redmagic.utils.BlockUtils;
import com.petredy.redmagic.utils.LogUtils;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class RenderEngine extends TileEntitySpecialRenderer implements ISimpleBlockRenderingHandler{

	public ModelEngine engine;
	
	public RenderEngine(){
		engine = new ModelEngine();
	}
	
	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID,
			RenderBlocks renderer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean shouldRender3DInInventory() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getRenderId() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void renderTileEntityAt(TileEntity tileentity, double x, double y, double z, float f) {
		TileEntityEngine entity = (TileEntityEngine)tileentity;
		
		GL11.glPushMatrix();
		GL11.glTranslated(x + 0.5, y + 0.5, z + 0.5);
		
		this.rotate(entity);
		
		
		
		Redmagic.proxy.bindTexture(Textures.ENGINE_BASE);
		engine.render("Base");
		Redmagic.proxy.bindTexture(Textures.ENGINE_GENERATOR);
		engine.render("Generator");
		Redmagic.proxy.bindTexture(Textures.ENGINE_TOP);
		engine.render("Top");
		
		
		
		GL11.glPopMatrix();
		
		GL11.glPushMatrix();
		
		double moving = 0.4;
		moving *= (double)entity.moving / 100;
		
		
		
		this.move(entity, x, y, z, moving);
		this.rotate(entity);
		Redmagic.proxy.bindTexture(Textures.ENGINE_ROTATING);
		engine.render("Rotating");
		GL11.glPopMatrix();
		
	}

	private void move(TileEntityEngine entity, double x, double y, double z, double moving) {
		switch(BlockUtils.forgeDirectionToInt(entity.side)){
		case 0:
			GL11.glTranslated(x + 0.5, y + 0.5 - moving, z + 0.5);
			break;
		case 1:
			GL11.glTranslated(x + 0.5, y + 0.5 + moving, z + 0.5);
			break;
		case 2:
			GL11.glTranslated(x + 0.5, y + 0.5, z + 0.5 - moving);
			break;
		case 3:
			GL11.glTranslated(x + 0.5, y + 0.5, z + 0.5 + moving);
			break;
		case 4:
			GL11.glTranslated(x + 0.5 - moving, y + 0.5, z + 0.5);
			break;
		case 5:
			GL11.glTranslated(x + 0.5 + moving, y + 0.5, z + 0.5);
			break;
		}
	}

	private void rotate(TileEntityEngine entity) {
		switch(BlockUtils.forgeDirectionToInt(entity.side)){
		case 0:
			GL11.glRotatef(180, 1, 0, 0);
			break;
		case 1:
			break;
		case 2:
			GL11.glRotatef(-90, 1, 0, 0);
			break;
		case 3:
			GL11.glRotatef(90, 1, 0, 0);
			break;
		case 4:
			GL11.glRotatef(90, 0, 0, 1);
			break;
		case 5:
			GL11.glRotatef(-90, 0, 0, 1);
			break;
		}
	}
}
