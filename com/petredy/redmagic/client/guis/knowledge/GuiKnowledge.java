package com.petredy.redmagic.client.guis.knowledge;

import org.lwjgl.opengl.GL11;

import com.petredy.redmagic.Redmagic;
import com.petredy.redmagic.knowledge.Knowledge;
import com.petredy.redmagic.lib.Guis;
import com.petredy.redmagic.lib.Textures;
import com.petredy.redmagic.utils.LogUtils;

import net.minecraft.client.gui.GuiScreen;

public class GuiKnowledge extends GuiScreen{

	public Knowledge knowledge;
	private int guiLeft;
	private int guiTop;
	private int xSize;
	private int ySize;
	
	
	public void initGui() {
		this.xSize = 175;
		this.ySize = 166;
		this.guiLeft = (this.width - this.xSize) / 2;
        this.guiTop = (this.height - this.ySize) / 2;
	}
	
	public void drawScreen(int par1, int par2, float par3){
		
		
		
		GL11.glPushMatrix();
		
		int x = (this.width - this.xSize) / 2;
		int y = (this.height - this.ySize) / 2;
		GL11.glColor3f(1, 1, 1);
		Redmagic.proxy.bindTexture(Textures.KNOWLEDGE_OVERVIEW);
		if(knowledge.progress < 100f){
			this.drawTexturedModalRect(x, y, 0, 0, this.xSize,this.ySize);
		}else{
			Redmagic.proxy.bindTexture(Textures.KNOWLEDGE_DESCRIPTION);
			this.drawTexturedModalRect(x, y, 0, 0, this.xSize,this.ySize);
			this.fontRenderer.drawString(knowledge.getName(), x + 8, y + 5, Guis.DEFAULT_FONT_COLOR);
			this.fontRenderer.drawSplitString(knowledge.getDescription(), x + 12, y + 30, 150, Guis.DEFAULT_FONT_COLOR);
		}
		GL11.glColor3f(1, 1, 1);
		Redmagic.proxy.bindTexture(Textures.KNOWLEDGE_DESCRIPTION);
		this.drawTexturedModalRect(x + 11, y + 17, 0, 166, (int) (knowledge.progress / 100 * 151), 4);
		GL11.glPopMatrix();
		
		
		super.drawScreen(par1, par2, par3);
    }
	
	public void drawBackground(int par1){}
	
	public boolean doesGuiPauseGame()
    {
        return false;
    }
}
