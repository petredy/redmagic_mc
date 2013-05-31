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
import redmagic.containers.ContainerBag;
import redmagic.tileentities.TileEntityBag;

public class GuiBag extends GuiContainer{
	public TileEntityBag entity;
	
	public GuiBag(EntityPlayer player, TileEntityBag entity, int id){
		super(new ContainerBag(player, entity, id));
		this.entity = entity;
	}
	
	public void initGui(){
		this.ySize = ySize + 55;
		super.initGui();
    }

	@Override
	public void drawGuiContainerForegroundLayer(int par1, int par2){
		fontRenderer.drawString(this.entity.getInvName(), 8, 6, 4210752);
		
		fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 8, this.ySize - 92, 4210752);
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3) {
		GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
		this.mc.renderEngine.bindTexture(Texture.BAG);
		int x = (this.width - this.xSize) / 2;
		int y = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
	}

}

