package com.petredy.redmagic.client.guis;

import org.lwjgl.opengl.GL11;

import com.petredy.redmagic.Redmagic;
import com.petredy.redmagic.blocks.Blocks;
import com.petredy.redmagic.container.ContainerDeintegrator;
import com.petredy.redmagic.items.Items;
import com.petredy.redmagic.lib.BlockIndex;
import com.petredy.redmagic.lib.Guis;
import com.petredy.redmagic.lib.Machines;
import com.petredy.redmagic.lib.Textures;
import com.petredy.redmagic.machines.MachineDeintegrator;
import com.petredy.redmagic.tileentities.TileEntityMachine;
import com.petredy.redmagic.utils.LogUtils;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

public class GuiDeintegrator extends GuiContainer{

	public TileEntityMachine machine;
	public MachineDeintegrator deintegrator;
	
	public GuiDeintegrator(EntityPlayer player, TileEntityMachine entity) {
		super(new ContainerDeintegrator(player, entity));
		this.machine = entity;
		this.deintegrator = (MachineDeintegrator) this.machine.getMachine(Machines.DEINTEGRATOR_METADATA);
	}
	
	protected void drawGuiContainerForegroundLayer(int par1, int par2) {
		this.fontRenderer.drawString(StatCollector.translateToLocal(new ItemStack(Items.machine, 1, Machines.DEINTEGRATOR_METADATA).getUnlocalizedName()), 8, 4, Guis.DEFAULT_FONT_COLOR);
	}
	

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
		GL11.glPushMatrix();
		
		GL11.glColor3f(1, 1, 1);
		Redmagic.proxy.bindTexture(Textures.DEINTEGRATOR);
		int x = (this.width - this.xSize) / 2;
		int y = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(x, y, 0, 0, this.xSize,this.ySize);
		
		float percent = (float)this.deintegrator.tick / (float)this.deintegrator.neededTicks;
		
		this.drawTexturedModalRect(x + 10, y + 68, 0, 166, (int)(percent * 156), 13);
		
		GL11.glPopMatrix();
	
	}

}
