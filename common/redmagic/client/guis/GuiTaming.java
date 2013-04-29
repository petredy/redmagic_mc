package redmagic.client.guis;

import org.lwjgl.opengl.GL11;

import redmagic.configuration.Texture;
import redmagic.containers.ContainerTaming;
import redmagic.helpers.TamingHelper;
import redmagic.tileentities.TileEntityTaming;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.StatCollector;

public class GuiTaming extends GuiContainer{
	
	public TileEntityTaming tileEntity;
	
	public GuiTaming(EntityPlayer player, TileEntityTaming tileEntity, int id){
		super(new ContainerTaming(player, tileEntity, id));
		this.tileEntity = tileEntity;
	}
	
	@Override
	public void drawGuiContainerForegroundLayer(int par1, int par2){
		fontRenderer.drawString("Taming", 8, 6, 4210752);
		
		fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 8, this.ySize - 94, 4210752);
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3) {
		GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
		this.mc.renderEngine.bindTexture(Texture.TAMING);
		int x = (this.width - this.xSize) / 2;
		int y = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
		
		float percentX = (float)TamingHelper.getSoulTime(tileEntity) / (float)TamingHelper.getMaxSoulTime();
		int offsetX = (int)(54 * percentX);
		if(offsetX > 54)offsetX = 54;
		this.drawTexturedModalRect(x - offsetX + 116, y + 34, 230 - offsetX, 0, offsetX, 18);
		
	}
}
