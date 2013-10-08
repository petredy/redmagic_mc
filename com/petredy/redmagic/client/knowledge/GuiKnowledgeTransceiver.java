package com.petredy.redmagic.client.knowledge;

import org.lwjgl.opengl.GL11;

import com.petredy.redmagic.Redmagic;
import com.petredy.redmagic.client.guis.button.ButtonCustom;
import com.petredy.redmagic.items.Items;
import com.petredy.redmagic.lib.Guis;
import com.petredy.redmagic.lib.Machines;
import com.petredy.redmagic.lib.Textures;
import com.petredy.redmagic.tileentities.TileEntityKnowledgeTransceiver;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

public class GuiKnowledgeTransceiver extends GuiScreen{

	public TileEntityKnowledgeTransceiver entity;
	public int xSize, ySize;
	
	public GuiKnowledgeTransceiver(EntityPlayer player, TileEntityKnowledgeTransceiver entity) {
		super();
		this.entity = entity;
	}
	
	public boolean doesGuiPauseGame(){
        return false;
    }
	
	public void initGui() {
		
		this.xSize = 166;
		this.ySize = 175;
		int x = (this.width - this.xSize) / 2;
		int y = (this.height - this.ySize) / 2;
		this.buttonList.clear();
		this.buttonList.add(new ButtonCustom(0, x + 20, y + 20, 0, 0, 32, 0, 32, 32, Textures.SYMBOLS));
		super.initGui();
	}
	
	protected void actionPerformed(GuiButton par1GuiButton) {
		if(par1GuiButton.enabled){
			switch(par1GuiButton.id){
			case 0: this.mc.displayGuiScreen(new GuiBasicKnowledge(entity));
			
			}
		}
	}
	
	
	public void drawScreen(int par1, int par2, float par3){
		GL11.glPushMatrix();
		
		GL11.glColor3f(1, 1, 1);
		Redmagic.proxy.bindTexture(Textures.KNOWLEDGE_TRANSCEIVER);
		int x = (this.width - this.xSize) / 2;
		int y = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(x, y, 0, 0, this.xSize,this.ySize);
		GL11.glPopMatrix();
		
		super.drawScreen(par1, par2, par3);
    }
	
	public void drawDefaultBackground(){}
}
