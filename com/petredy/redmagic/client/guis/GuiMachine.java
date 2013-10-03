package com.petredy.redmagic.client.guis;

import org.lwjgl.opengl.GL11;

import com.petredy.redmagic.Redmagic;
import com.petredy.redmagic.blocks.Blocks;
import com.petredy.redmagic.container.ContainerMachine;
import com.petredy.redmagic.lib.BlockIndex;
import com.petredy.redmagic.lib.Guis;
import com.petredy.redmagic.lib.Textures;
import com.petredy.redmagic.tileentities.TileEntityMachine;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.StatCollector;

public class GuiMachine extends GuiContainer{

	public TileEntityMachine machine;
	
	public GuiMachine(EntityPlayer player, TileEntityMachine entity) {
		super(new ContainerMachine(player, entity));
		this.machine = entity;
	}
	
	protected void drawGuiContainerForegroundLayer(int par1, int par2) {
		
		this.fontRenderer.drawString(StatCollector.translateToLocal(Blocks.machine.getUnlocalizedName()), 8, 4, Guis.DEFAULT_FONT_COLOR);
		
		String energy = "Red: " + machine.energy;
		
		this.fontRenderer.drawString(energy, 8, 30, Guis.DEFAULT_FONT_COLOR);
		
		String heat = "Heat: " + machine.heat;
		
		this.fontRenderer.drawString(heat, 8, 40, Guis.DEFAULT_FONT_COLOR);
		
		String maxHeat = "MaxHeat: " + machine.maxHeat;
		
		this.fontRenderer.drawString(maxHeat, 8, 50, Guis.DEFAULT_FONT_COLOR);
	}
	

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
		GL11.glPushMatrix();
		
		GL11.glColor3f(1, 1, 1);
		Redmagic.proxy.bindTexture(Textures.MACHINE);
		int x = (this.width - this.xSize) / 2;
		int y = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(x, y, 0, 0, this.xSize,this.ySize);
		
		GL11.glPopMatrix();
	}

}
