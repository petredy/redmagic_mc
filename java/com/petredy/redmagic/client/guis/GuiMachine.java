package com.petredy.redmagic.client.guis;

import org.lwjgl.opengl.GL11;

import com.petredy.redmagic.Redmagic;
import com.petredy.redmagic.api.machinery.IMachineHandler;
import com.petredy.redmagic.blocks.Blocks;
import com.petredy.redmagic.container.ContainerMachine;
import com.petredy.redmagic.lib.BlockIndex;
import com.petredy.redmagic.lib.Elements;
import com.petredy.redmagic.lib.Guis;
import com.petredy.redmagic.lib.Textures;
import com.petredy.redmagic.tileentities.TileEntityMachine;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

public class GuiMachine extends GuiContainer{

	public IMachineHandler machine;
	
	public GuiMachine(EntityPlayer player, IMachineHandler entity) {
		super(new ContainerMachine(player, entity));
		this.machine = entity;
	}
	
	protected void drawGuiContainerForegroundLayer(int par1, int par2) {
		
		this.fontRenderer.drawString(StatCollector.translateToLocal(new ItemStack(Blocks.machine).getDisplayName()), 8, 4, Guis.DEFAULT_FONT_COLOR);
		
		
		String earth = "Earth: " + machine.getEnergyHandler().getStoredEnergy().getValue(Elements.EARTH);
		String air = "Air: " + machine.getEnergyHandler().getStoredEnergy().getValue(Elements.AIR);
		String water = "Water: " + machine.getEnergyHandler().getStoredEnergy().getValue(Elements.WATER);
		String fire = "Fire: " + machine.getEnergyHandler().getStoredEnergy().getValue(Elements.FIRE);
		String theVoid = "Void: " + machine.getEnergyHandler().getStoredEnergy().getValue(Elements.VOID);
		this.fontRenderer.drawString(earth, 8, 15, Guis.DEFAULT_FONT_COLOR);
		this.fontRenderer.drawString(air, 8, 25, Guis.DEFAULT_FONT_COLOR);
		this.fontRenderer.drawString(water, 8, 35, Guis.DEFAULT_FONT_COLOR);
		this.fontRenderer.drawString(fire, 8, 45, Guis.DEFAULT_FONT_COLOR);
		this.fontRenderer.drawString(theVoid, 8, 55, Guis.DEFAULT_FONT_COLOR);
		
		
		String heat = "Heat: " + machine.getHeat();
		this.fontRenderer.drawString(heat, 8, 65, Guis.DEFAULT_FONT_COLOR);
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
