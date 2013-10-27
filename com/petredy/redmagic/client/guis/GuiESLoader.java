package com.petredy.redmagic.client.guis;

import org.lwjgl.opengl.GL11;

import com.petredy.redmagic.Redmagic;
import com.petredy.redmagic.api.es.IESContainer;
import com.petredy.redmagic.blocks.Blocks;
import com.petredy.redmagic.container.ContainerESLoader;
import com.petredy.redmagic.es.EnvironmentSphere;
import com.petredy.redmagic.items.Items;
import com.petredy.redmagic.lib.BlockIndex;
import com.petredy.redmagic.lib.Guis;
import com.petredy.redmagic.lib.Textures;
import com.petredy.redmagic.tileentities.TileEntityESLoader;
import com.petredy.redmagic.utils.LogUtils;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

public class GuiESLoader extends GuiContainer{
	
	public TileEntityESLoader loader;
	
	public GuiESLoader(EntityPlayer player, TileEntityESLoader entity) {
		super(new ContainerESLoader(player, entity));
		this.loader = entity;
	}
	
	protected void drawGuiContainerForegroundLayer(int par1, int par2) {
		this.fontRenderer.drawString(new ItemStack(Blocks.esLoader).getDisplayName(), 8, 4, Guis.DEFAULT_FONT_COLOR);
		
		EnvironmentSphere sphere = new EnvironmentSphere(loader.xCoord, loader.yCoord, loader.zCoord);
		sphere.search(loader.worldObj);
		this.fontRenderer.drawString("Left: " + sphere.lengthL, 8, 20, Guis.DEFAULT_FONT_COLOR);
		this.fontRenderer.drawString("Right: " + sphere.lengthR, 8, 30, Guis.DEFAULT_FONT_COLOR);
		this.fontRenderer.drawString("Back: " + sphere.lengthB, 8, 40, Guis.DEFAULT_FONT_COLOR);
		this.fontRenderer.drawString("Front: " + sphere.lengthF, 8, 50, Guis.DEFAULT_FONT_COLOR);
		this.fontRenderer.drawString("Height: " + sphere.height, 8, 60, Guis.DEFAULT_FONT_COLOR);
		
	}
	

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
		GL11.glPushMatrix();
		
		GL11.glColor3f(1, 1, 1);
		Redmagic.proxy.bindTexture(Textures.GUI_1);
		int x = (this.width - this.xSize) / 2;
		int y = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(x, y, 0, 0, this.xSize,this.ySize);
		
		GL11.glPopMatrix();
	
	}

}
