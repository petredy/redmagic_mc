package redmagic.client.guis;

import org.lwjgl.opengl.GL11;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.StatCollector;
import redmagic.configuration.Texture;
import redmagic.containers.ContainerCrystalizer;
import redmagic.tileentities.TileEntityCrystalizer;

public class GuiCrystalizer extends GuiContainer{
	public TileEntityCrystalizer entity;
	
	public GuiCrystalizer(EntityPlayer player, TileEntityCrystalizer tileEntity) {
		super(new ContainerCrystalizer(player,tileEntity));
		this.entity = tileEntity;
	}
	

	@Override
	public void drawGuiContainerForegroundLayer(int par1, int par2){		
		fontRenderer.drawString(this.entity.getInvName(), 8, 6, 4210752);
		
		fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 8, this.ySize - 96 + 2, 4210752);
	}
	
	@SuppressWarnings("static-access")
	@Override
	protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3) {
		GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
		this.mc.renderEngine.bindTexture(Texture.CRYSTALIZER);
		int x = (this.width - this.xSize) / 2;
		int y = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
		
		float percentX = (float)this.entity.crystalizeTime / (float)this.entity.neededTime;
		int offsetX = (int)(24 * percentX);
		this.drawTexturedModalRect(x + 79, y + 34, 196, 0, offsetX, 16);
		
	}

}

