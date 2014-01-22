package com.petredy.redmagic.client.guis;

import org.lwjgl.opengl.GL11;

import com.petredy.redmagic.Redmagic;
import com.petredy.redmagic.api.machinery.IMachineHandler;
import com.petredy.redmagic.blocks.Blocks;
import com.petredy.redmagic.container.ContainerFurnace;
import com.petredy.redmagic.items.Items;
import com.petredy.redmagic.lib.BlockIndex;
import com.petredy.redmagic.lib.Guis;
import com.petredy.redmagic.lib.Machines;
import com.petredy.redmagic.lib.Textures;
import com.petredy.redmagic.machines.Machine;
import com.petredy.redmagic.machines.MachineFurnace;
import com.petredy.redmagic.tileentities.TileEntityMachine;
import com.petredy.redmagic.utils.BlockUtils;
import com.petredy.redmagic.utils.LogUtils;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

public class GuiFurnace extends GuiContainer{

	public IMachineHandler machine;
	public MachineFurnace furnace;
	
	public GuiFurnace(EntityPlayer player, IMachineHandler entity) {
		super(new ContainerFurnace(player, entity));
		this.machine = entity;
		this.furnace = (MachineFurnace) this.machine.getMachineOnSide(player.getEntityData().getInteger("redmagic.machine.side"));
	}
	
	protected void drawGuiContainerForegroundLayer(int par1, int par2) {
		this.fontRenderer.drawString(new ItemStack(Items.machine, 1, Machines.FURNACE_METADATA).getDisplayName(), 8, 4, Guis.DEFAULT_FONT_COLOR);
	}
	

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
		GL11.glPushMatrix();
		
		GL11.glColor3f(1, 1, 1);
		Redmagic.proxy.bindTexture(Textures.FURNACE);
		int x = (this.width - this.xSize) / 2;
		int y = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(x, y, 0, 0, this.xSize,this.ySize);
		
		float percent = furnace != null ? (float)this.furnace.burnTick / (float)this.furnace.needBurnTime : 0;
		this.drawTexturedModalRect(x + 79, y + 34, 176, 14, (int)(percent * 24), 16);
		
		GL11.glPopMatrix();
	
	}

}
