package redmagic.client.guis;

import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.List;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.StatCollector;
import redmagic.client.guis.button.ButtonNextPage;
import redmagic.configuration.LogicIndex;
import redmagic.configuration.ScrollIndex;
import redmagic.configuration.Texture;
import redmagic.containers.ContainerOneSlot;
import redmagic.containers.ContainerScroll;
import redmagic.containers.ContainerSoulForge;
import redmagic.core.Logger;
import redmagic.lib.scrolls.Scroll;
import redmagic.lib.scrolls.ScrollHelper;
import redmagic.tileentities.TileEntitySoulForge;

public class GuiScroll extends GuiContainer{;
	
	public int id ,glasses, xsize, ysize;

	public GuiScroll(EntityPlayer player, int id, int glasses){
		super(new ContainerScroll(id));
		this.id = id;
		this.glasses = glasses;
		this.xSize = this.xsize = 256;
		this.ySize = this.ysize = 256;
	}
	
	public void initGui()
    {
        super.initGui();
        this.xSize = this.xsize = 256;
		this.ySize = this.ysize = 256;
		this.buttonList.add(new ButtonNextPage(0, this.guiLeft + 45, this.guiTop + 230, false));
		this.buttonList.add(new ButtonNextPage(1, this.guiLeft + 190, this.guiTop + 230, true));
    }
	
	protected void actionPerformed(GuiButton button) {
		if(button.id == 0){
			id -= 1;
			if(id < 0)id = ScrollIndex.NAMES.length - 1;
			((ContainerScroll)this.inventorySlots).update(id);
		}else if(button.id == 1){
			id += 1;
			if(id >= ScrollIndex.NAMES.length)id = 0;
			((ContainerScroll)this.inventorySlots).update(id);
		}
	}
	
	public boolean doesGuiPauseGame(){
		return false;
	}
	

	public void drawGuiContainerForegroundLayer(int par1, int par2){
		Scroll scroll = ScrollHelper.getScroll(id);
		String headline = scroll.getHeadline();
		fontRenderer.drawString(headline, this.xSize / 2 - fontRenderer.getStringWidth(headline) / 2, 10, 4210752);
		String intro = scroll.getIntroduction();
		List<String> introList = fontRenderer.listFormattedStringToWidth(intro, 80);
		Iterator<String> introIt = introList.iterator();
		int count = 0;
		while(introIt.hasNext() && count < 5){
			String text = introIt.next();
			fontRenderer.drawString(text, 45, 30 + 12 * count, 4210752);
			count++;
		}
		String details = scroll.getDetails();
		List<String> detailList = fontRenderer.listFormattedStringToWidth(details, 160);
		Iterator<String> detailIt = detailList.iterator();
		int count2 = 0;
		while(detailIt.hasNext() && count2 < 12){
			String text = detailIt.next();
			fontRenderer.drawString(text, 50,  100 + 12 * count2, 4210752);
			count2++;
		}
		
		scroll.drawOthers(this, fontRenderer, this.mc.renderEngine);
		

	}
	
	protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3) {
		GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        
		if(glasses == 1){
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
			this.mc.renderEngine.bindTexture(Texture.SCROLL_GLASSES);
			int x = (this.width - this.xSize) / 2;
			int y = (this.height - this.ySize) / 2;
			this.drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
		}else if(glasses == 0){
			this.mc.renderEngine.bindTexture(Texture.SCROLL);
			int x = (this.width - this.xSize) / 2;
			int y = (this.height - this.ySize) / 2;
			this.drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
		}
	}

}

