/**
 * Copyright (c) SpaceToad, 2011 http://www.mod-buildcraft.com
 *
 * BuildCraft is distributed under the terms of the Minecraft Mod Public License
 * 1.0, or MMPL. Please check the contents of the license located in
 * http://www.mod-buildcraft.com/MMPL-1.0.txt
 */
package com.petredy.redmagic.client.render;

import static net.minecraftforge.common.ForgeDirection.DOWN;
import static net.minecraftforge.common.ForgeDirection.EAST;
import static net.minecraftforge.common.ForgeDirection.NORTH;
import static net.minecraftforge.common.ForgeDirection.SOUTH;
import static net.minecraftforge.common.ForgeDirection.UP;
import static net.minecraftforge.common.ForgeDirection.WEST;
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
import com.petredy.redmagic.lib.Rendering;
import com.petredy.redmagic.lib.Textures;
import com.petredy.redmagic.tileentities.TileEntityEngine;
import com.petredy.redmagic.utils.BlockUtils;
import com.petredy.redmagic.utils.LogUtils;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class RenderEngine extends TileEntitySpecialRenderer implements ISimpleBlockRenderingHandler{
	
	private static final ResourceLocation CHAMBER_TEXTURE = Textures.ENGINE_CHAMBER;
	private static final ResourceLocation TRUNK_BLUE_TEXTURE = Textures.ENGINE_TRUNK;
	
	private ModelBase model = new ModelBase() {
	};
	private ModelRenderer box;
	private ModelRenderer trunk;
	private ModelRenderer movingBox;
	private ModelRenderer chamber;
	private ResourceLocation baseTexture;
	private static final float[] angleMap = new float[6];

	static {
		angleMap[EAST.ordinal()] = (float) -Math.PI / 2;
		angleMap[WEST.ordinal()] = (float) Math.PI / 2;
		angleMap[UP.ordinal()] = 0;
		angleMap[DOWN.ordinal()] = (float) Math.PI;
		angleMap[SOUTH.ordinal()] = (float) Math.PI / 2;
		angleMap[NORTH.ordinal()] = (float) -Math.PI / 2;
	}

	public RenderEngine() {

		// constructor:
		box = new ModelRenderer(model, 0, 1);
		box.addBox(-8F, -8F, -8F, 16, 4, 16);
		box.rotationPointX = 8;
		box.rotationPointY = 8;
		box.rotationPointZ = 8;

		trunk = new ModelRenderer(model, 1, 1);
		trunk.addBox(-4F, -4F, -4F, 8, 12, 8);
		trunk.rotationPointX = 8F;
		trunk.rotationPointY = 8F;
		trunk.rotationPointZ = 8F;

		movingBox = new ModelRenderer(model, 0, 1);
		movingBox.addBox(-8F, -4, -8F, 16, 4, 16);
		movingBox.rotationPointX = 8F;
		movingBox.rotationPointY = 8F;
		movingBox.rotationPointZ = 8F;

		chamber = new ModelRenderer(model, 1, 1);
		chamber.addBox(-5F, -4, -5F, 10, 2, 10);
		chamber.rotationPointX = 8F;
		chamber.rotationPointY = 8F;
		chamber.rotationPointZ = 8F;
	}

	public RenderEngine(ResourceLocation baseTexture) {
		this();
		this.baseTexture = baseTexture;
		setTileEntityRenderer(TileEntityRenderer.instance);
	}

	@Override
	public void renderTileEntityAt(TileEntity tileentity, double x, double y, double z, float f) {

		TileEntityEngine engine = ((TileEntityEngine) tileentity);

		if (engine != null) {
			render(engine.speed, engine.moving / (float)100, engine.moving, engine.side, Textures.ENGINE_BASE, x, y, z);
		}
	}

	private void render(float speed, float progress, float moving, ForgeDirection orientation, ResourceLocation baseTexture, double x, double y, double z) {

		GL11.glPushMatrix();
		GL11.glPushAttrib(GL11.GL_ENABLE_BIT);
		GL11.glColor3f(1, 1, 1);

		this.move(orientation, x, y, z, 0);

		float step = moving / (float)100 * 7f;
		
		float factor = (float) (1.0 / 16.0);
		
		this.rotate(orientation);
		
		Redmagic.proxy.bindTexture(baseTexture);

		box.render(factor);

		Redmagic.proxy.bindTexture(TRUNK_BLUE_TEXTURE);

		trunk.render(factor);

		GL11.glPopAttrib();
		GL11.glPopMatrix();
		

		float chamberf = 2F / 16F;
		float chamberMove = 0;
		for (int i = 0; i <= step + 2; i+=2) {
			GL11.glPushMatrix();
			this.move(orientation, x, y, z, chamberMove++ * chamberf);
			this.rotate(orientation);
			Redmagic.proxy.bindTexture(CHAMBER_TEXTURE);
			chamber.render(factor);
			GL11.glPopMatrix();
		}


		GL11.glPushMatrix();
		
		Redmagic.proxy.bindTexture(baseTexture);
		
		this.move(orientation, x, y, z, Math.min(99, moving) / 100 * 0.5);
		this.rotate(orientation);
		movingBox.render(factor);
		
		GL11.glPopMatrix();
	}

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID,
			RenderBlocks renderer) {
		
		GL11.glPushMatrix();
		float factor = (float) (1.0 / 16.0);
		Redmagic.proxy.bindTexture(Textures.ENGINE_BASE);
		box.render(factor);
		movingBox.render(factor);
		Redmagic.proxy.bindTexture(TRUNK_BLUE_TEXTURE);
		trunk.render(factor);
		Redmagic.proxy.bindTexture(CHAMBER_TEXTURE);
		chamber.render(factor);
		GL11.glPopMatrix();
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z,
			Block block, int modelId, RenderBlocks renderer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean shouldRender3DInInventory() {
		return true;
	}

	@Override
	public int getRenderId() {
		// TODO Auto-generated method stub
		return Rendering.ENGINE_ID;
	}
	
	private void move(ForgeDirection side, double x, double y, double z, double moving) {
		switch(BlockUtils.forgeDirectionToInt(side)){
		case 0:
			GL11.glTranslated(x, y - moving + 1, z + 1);
			break;
		case 1:
			GL11.glTranslated(x, y + moving, z);
			break;
		case 2:
			GL11.glTranslated(x, y, z - moving + 1);
			break;
		case 3:
			GL11.glTranslated(x, y + 1, z + moving);
			break;
		case 4:
			GL11.glTranslated(x - moving + 1, y, z);
			break;
		case 5:
			GL11.glTranslated(x + moving, y + 1, z);
			break;
		}
	}

	private void rotate(ForgeDirection side) {
		switch(BlockUtils.forgeDirectionToInt(side)){
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
