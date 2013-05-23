package redmagic.client.guis;

import java.lang.reflect.InvocationTargetException;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.StatCollector;
import redmagic.configuration.LogicIndex;
import redmagic.configuration.Texture;
import redmagic.containers.ContainerOneSlot;
import redmagic.containers.ContainerSoulForge;
import redmagic.tileentities.TileEntitySoulForge;

public class GuiSoulForge extends GuiContainer{
	public TileEntitySoulForge entity;
	
	public GuiSoulForge(EntityPlayer player, TileEntitySoulForge entity){
		super(new ContainerSoulForge(player, entity));
		this.entity = entity;
	}
	
	public void drawDefaultBackground(){}
	
	public boolean doesGuiPauseGame(){
		return false;
	}
	

	@Override
	public void drawGuiContainerForegroundLayer(int par1, int par2){}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3) {
		GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		this.mc.renderEngine.bindTexture(Texture.SOUL_FORGE_INV);
		int x = (this.width - this.xSize) / 2 + 7;
		int y = (this.height - this.ySize) / 2 + 70;
		this.drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
		
		this.mc.renderEngine.bindTexture(Texture.SOUL_FORGE_CRAFT);
		x = (this.width - this.xSize) / 2;
		y = (this.height - this.ySize) / 2 - 15;
		this.drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
	}

}

