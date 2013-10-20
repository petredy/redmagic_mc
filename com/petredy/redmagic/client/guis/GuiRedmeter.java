package com.petredy.redmagic.client.guis;

import org.lwjgl.opengl.GL11;

import com.petredy.redmagic.Redmagic;
import com.petredy.redmagic.blocks.Blocks;
import com.petredy.redmagic.container.ContainerMachine;
import com.petredy.redmagic.container.ContainerRedmeter;
import com.petredy.redmagic.items.Items;
import com.petredy.redmagic.lib.BlockIndex;
import com.petredy.redmagic.lib.Elements;
import com.petredy.redmagic.lib.Guis;
import com.petredy.redmagic.lib.Textures;
import com.petredy.redmagic.redvalue.RedvalueDictionary;
import com.petredy.redmagic.redvalue.element.Composition;
import com.petredy.redmagic.tileentities.TileEntityMachine;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

public class GuiRedmeter extends GuiContainer{

	
	public GuiRedmeter(EntityPlayer player) {
		super(new ContainerRedmeter(player));
	}
	
	protected void drawGuiContainerForegroundLayer(int par1, int par2) {
		
		this.fontRenderer.drawString(StatCollector.translateToLocal(new ItemStack(Items.redmeter).getUnlocalizedName()), 8, 4, Guis.DEFAULT_FONT_COLOR);

		Slot slot = this.inventorySlots.getSlot(0);
		if(slot != null && slot.getHasStack()){
			Composition composition = RedvalueDictionary.getComposition(slot.getStack());
			if(composition != null){
				this.fontRenderer.drawString("Earth: " + ((Float)composition.getValue(Elements.EARTH)).toString(), 50, 20, Guis.DEFAULT_FONT_COLOR);
				this.fontRenderer.drawString("Air: " + ((Float)composition.getValue(Elements.AIR)).toString(), 50, 30, Guis.DEFAULT_FONT_COLOR);
				this.fontRenderer.drawString("Water: " + ((Float)composition.getValue(Elements.WATER)).toString(), 50, 40, Guis.DEFAULT_FONT_COLOR);
				this.fontRenderer.drawString("Fire: " +((Float)composition.getValue(Elements.FIRE)).toString(), 50, 50, Guis.DEFAULT_FONT_COLOR);
				this.fontRenderer.drawString("Void: " + ((Float)composition.getValue(Elements.VOID)).toString(), 50, 60, Guis.DEFAULT_FONT_COLOR);
			}
		}
		 
		
		
	}
	

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
		GL11.glPushMatrix();
		
		GL11.glColor3f(1, 1, 1);
		Redmagic.proxy.bindTexture(Textures.REDMETER);
		int x = (this.width - this.xSize) / 2;
		int y = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(x, y, 0, 0, this.xSize,this.ySize);
		
		GL11.glPopMatrix();
	}

}
