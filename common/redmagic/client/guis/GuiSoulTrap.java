package redmagic.client.guis;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import redmagic.configuration.LogicIndex;
import redmagic.configuration.Texture;
import redmagic.containers.ContainerSoulTrap;
import redmagic.tileentities.TileEntitySoulTrap;

public class GuiSoulTrap extends GuiContainer{
	public TileEntitySoulTrap entity;
	
	public GuiSoulTrap(EntityPlayer player, TileEntitySoulTrap tileEntity) {
		super(new ContainerSoulTrap(player,tileEntity));
		this.entity = tileEntity;
	}
	

	@Override
	public void drawGuiContainerForegroundLayer(int par1, int par2){		
		fontRenderer.drawString(this.entity.getInvName(), 8, 6, 4210752);
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3) {
		GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
		this.mc.renderEngine.bindTexture(Texture.SOUL_TRAP);
		int x = (this.width - this.xSize) / 2;
		int y = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
		
		double percent = (double)this.entity.soul_nectar / (double)LogicIndex.SOUL_TRAP_MAX_NECTAR;
		this.drawTexturedModalRect(x + 9, y + 75, 0, ySize, (int)((xSize - 18) * percent), 5);
	}

}

