package com.petredy.redmagic.client.guis;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import com.petredy.redmagic.Redmagic;
import com.petredy.redmagic.container.ContainerTradingChest;
import com.petredy.redmagic.container.ContainerWorkTable;
import com.petredy.redmagic.lib.BlockIndex;
import com.petredy.redmagic.lib.Guis;
import com.petredy.redmagic.lib.Textures;
import com.petredy.redmagic.tileentities.TileEntityWorkTable;
import com.petredy.redmagic.trading.TradingItem;
import com.petredy.redmagic.trading.TradingManager;
import com.petredy.redmagic.utils.InventoryUtils;
import com.petredy.redmagic.utils.LogUtils;

import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

public class GuiWorkTable extends GuiContainer{

	private TileEntityWorkTable entity;
	private float currentScroll;
	private boolean isScrolling;
	private boolean wasClicking;

	public GuiWorkTable(EntityPlayer player, TileEntityWorkTable entity) {
		super(new ContainerWorkTable(player, entity));
		this.entity = entity;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2){
		this.fontRenderer.drawString(StatCollector.translateToLocal(BlockIndex.WORK_TABLE_NAME), 8, 5, Guis.DEFAULT_FONT_COLOR);
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
		
		GL11.glPushMatrix();
		
		GL11.glColor3f(1, 1, 1);
		Redmagic.proxy.bindTexture(Textures.WORK_TABLE);
		int x = (this.width - this.xSize) / 2;
		int y = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(x, y, 0, 0, this.xSize,this.ySize);
		
		GL11.glPopMatrix();

	}

}
