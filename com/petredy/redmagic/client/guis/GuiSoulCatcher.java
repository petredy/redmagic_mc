package com.petredy.redmagic.client.guis;

import org.lwjgl.opengl.GL11;

import com.petredy.redmagic.Redmagic;
import com.petredy.redmagic.blocks.Blocks;
import com.petredy.redmagic.container.ContainerSoulCatcher;
import com.petredy.redmagic.lib.Textures;
import com.petredy.redmagic.tileentities.TileEntitySoulCatcher;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.StatCollector;

public class GuiSoulCatcher extends GuiContainer{

	public GuiSoulCatcher(EntityPlayer player, TileEntitySoulCatcher entity) {
		super(new ContainerSoulCatcher(player, entity));
	}

	@Override
	public void drawGuiContainerForegroundLayer(int par1, int par2){		
		fontRenderer.drawString(StatCollector.translateToLocal(Blocks.soulCatcher.getUnlocalizedName()), 8, 6, 4210752);
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
		GL11.glColor4f(1F, 1F, 1F, 1F);
		Redmagic.proxy.bindTexture(Textures.SOUL_CATCHER);
		int x = (this.width - this.xSize) / 2;
		int y = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
	}

}
