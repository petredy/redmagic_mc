package com.petredy.redmagic.client.guis;

import org.lwjgl.opengl.GL11;

import com.petredy.redmagic.Redmagic;
import com.petredy.redmagic.api.machinery.IMachineHandler;
import com.petredy.redmagic.blocks.Blocks;
import com.petredy.redmagic.container.ContainerDisintegrator;
import com.petredy.redmagic.container.ContainerTribological;
import com.petredy.redmagic.items.Items;
import com.petredy.redmagic.lib.BlockIndex;
import com.petredy.redmagic.lib.Guis;
import com.petredy.redmagic.lib.Machines;
import com.petredy.redmagic.lib.Textures;
import com.petredy.redmagic.machines.MachineDisintegrator;
import com.petredy.redmagic.tileentities.TileEntityMachine;
import com.petredy.redmagic.utils.LogUtils;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

public class GuiTribological extends GuiContainer{

	public IMachineHandler machine;
	
	public GuiTribological(EntityPlayer player, IMachineHandler entity) {
		super(new ContainerTribological(player, entity));
		this.machine = entity;
	}
	
	protected void drawGuiContainerForegroundLayer(int par1, int par2) {
		this.fontRenderer.drawString(new ItemStack(Blocks.machinery, 1, BlockIndex.MACHINERY_DRIVE_METADATA).getDisplayName(), 8, 4, Guis.DEFAULT_FONT_COLOR);
	}
	

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
		GL11.glPushMatrix();
		
		GL11.glColor3f(1, 1, 1);
		Redmagic.proxy.bindTexture(Textures.GUI_3x3);
		int x = (this.width - this.xSize) / 2;
		int y = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(x, y, 0, 0, this.xSize,this.ySize);
		
		GL11.glPopMatrix();
	
	}

}
