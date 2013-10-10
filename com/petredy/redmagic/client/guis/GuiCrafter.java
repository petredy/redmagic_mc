package com.petredy.redmagic.client.guis;

import org.lwjgl.opengl.GL11;

import com.petredy.redmagic.Redmagic;
import com.petredy.redmagic.blocks.Blocks;
import com.petredy.redmagic.client.guis.button.ButtonCustom;
import com.petredy.redmagic.container.ContainerCrafter;
import com.petredy.redmagic.container.ContainerFreezer;
import com.petredy.redmagic.items.Items;
import com.petredy.redmagic.lib.Guis;
import com.petredy.redmagic.lib.Machines;
import com.petredy.redmagic.lib.Textures;
import com.petredy.redmagic.machines.MachineFreezer;
import com.petredy.redmagic.network.PacketCraft;
import com.petredy.redmagic.network.PacketHandler;
import com.petredy.redmagic.tileentities.TileEntityCrafter;
import com.petredy.redmagic.tileentities.TileEntityMachine;

import cpw.mods.fml.common.network.PacketDispatcher;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;

public class GuiCrafter extends GuiContainer {

	public TileEntityCrafter entity;
	
	public GuiCrafter(EntityPlayer player, TileEntityCrafter entity) {
		super(new ContainerCrafter(player, entity));
		this.entity = entity;
	}
	
	protected void drawGuiContainerForegroundLayer(int par1, int par2) {
		this.fontRenderer.drawString(new ItemStack(Blocks.crafter).getDisplayName(), 8, 4, Guis.DEFAULT_FONT_COLOR);
	}
	
	public void initGui()
    {
		this.ySize = 215;
        super.initGui();
        int x = (this.width - this.xSize) / 2;
		int y = (this.height - this.ySize) / 2;
        this.buttonList.clear();
        this.buttonList.add(new ButtonCustom(0, x + 90, y + 35, 176, 0, 176, 15, 22, 15, Textures.CRAFTER));
    }
	
	protected void actionPerformed(GuiButton par1GuiButton) {
		if(par1GuiButton.id == 0){
			PacketDispatcher.sendPacketToServer(PacketHandler.populatePacket(new PacketCraft(entity.xCoord, entity.yCoord, entity.zCoord)));
		}
	}
	

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
		GL11.glPushMatrix();
		
		GL11.glColor3f(1, 1, 1);
		Redmagic.proxy.bindTexture(Textures.CRAFTER);
		int x = (this.width - this.xSize) / 2;
		int y = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(x, y, 0, 0, this.xSize,this.ySize);
		
		GL11.glPopMatrix();
	
	}

}
