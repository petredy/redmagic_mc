package com.petredy.redmagic.client.knowledge;

import java.util.Collection;
import java.util.Iterator;

import org.lwjgl.opengl.GL11;

import com.petredy.redmagic.Redmagic;
import com.petredy.redmagic.client.guis.button.ButtonCustom;
import com.petredy.redmagic.client.guis.button.ButtonKnowledge;
import com.petredy.redmagic.knowledge.KnowledgeGroup;
import com.petredy.redmagic.knowledge.KnowledgeSystem;
import com.petredy.redmagic.knowledge.Knowledge;
import com.petredy.redmagic.lib.KnowledgeIndex;
import com.petredy.redmagic.lib.Textures;
import com.petredy.redmagic.tileentities.TileEntityKnowledgeTransceiver;
import com.petredy.redmagic.utils.LogUtils;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;

public class GuiBasicKnowledge extends GuiScreen {

	public TileEntityKnowledgeTransceiver entity;
	public int xSize, ySize;
	
	public GuiBasicKnowledge(TileEntityKnowledgeTransceiver entity){
		this.entity = entity;
	}
	
	public void initGui() {
		
		this.xSize = 166;
		this.ySize = 175;
		int x = (this.width - this.xSize) / 2;
		int y = (this.height - this.ySize) / 2;
		this.buttonList.clear();
		
		KnowledgeGroup group = KnowledgeSystem.getGroup(KnowledgeIndex.G_BASIC);
		Collection<Knowledge> col = group.getAll();
		Iterator<Knowledge> it = col.iterator();
		int count = 0;
		while(it.hasNext()){
			Knowledge data = it.next();
			LogUtils.log(data.name);
			int x2 = (count % 5);
			int y2 = (count / 4);
			LogUtils.log(x2 + "/" + y2);
			this.buttonList.add(new ButtonKnowledge(data.name, count, x + (int)(x2 * 32) + 20, y + (int)(y2 * 32) + 20, x2 * 64, y2 * 32, x2 * 96, y2 * 32, 32, 32, Textures.KNOWLEDGE_BASIC));
			count++;
		}
		
		super.initGui();
	}
	
	protected void actionPerformed(GuiButton par1GuiButton) {
		if(par1GuiButton.enabled){
			
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
