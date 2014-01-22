package com.petredy.redmagic.client.guis;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import com.petredy.redmagic.Redmagic;
import com.petredy.redmagic.container.ContainerPlayerInventory;
import com.petredy.redmagic.lib.BlockIndex;
import com.petredy.redmagic.lib.Guis;
import com.petredy.redmagic.lib.Textures;
import com.petredy.redmagic.utils.LogUtils;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

public class GuiPlayerInventory extends GuiContainer{

	public GuiPlayerInventory(EntityPlayer player) {
		super(new ContainerPlayerInventory(player));
	}

	public void initGui(){
        this.ySize = 220;
		super.initGui();
        
    }
	
	@Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2){
		if(((ContainerPlayerInventory)inventorySlots).information != null)this.fontRenderer.drawString(StatCollector.translateToLocal(((ContainerPlayerInventory)inventorySlots).information.inventory.getInvName()), 8, 5, Guis.DEFAULT_FONT_COLOR);
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
		
		GL11.glPushMatrix();
		
		GL11.glColor3f(1, 1, 1);
		Redmagic.proxy.bindTexture(Textures.PLAYER_INVENTORY);
		int x = (this.width - this.xSize) / 2;
		int y = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(x, y, 0, 0, this.xSize,this.ySize);
		
		GL11.glPopMatrix();

	}

}
