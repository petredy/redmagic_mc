package redmagic.client.guis;

import org.lwjgl.opengl.GL11;
import redmagic.configuration.Texture;
import redmagic.containers.ContainerEducationBasic;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.StatCollector;

public class GuiEducationBasic extends GuiContainer{
	
	public TileEntity tileEntity;
	
	public GuiEducationBasic(EntityPlayer player, TileEntity tileEntity){
		super(new ContainerEducationBasic(player, tileEntity));
		this.tileEntity = tileEntity;
	}
	
	@Override
	public void drawGuiContainerForegroundLayer(int par1, int par2){
		fontRenderer.drawString("Education", 8, 6, 4210752);
		
		fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 8, this.ySize - 94, 4210752);
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3) {
		GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
		this.mc.renderEngine.bindTexture(Texture.EDUCATION_BASIC);
		int x = (this.width - this.xSize) / 2;
		int y = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
	}
}
