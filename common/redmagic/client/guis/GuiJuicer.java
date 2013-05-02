package redmagic.client.guis;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import redmagic.configuration.LogicIndex;
import redmagic.configuration.Texture;
import redmagic.containers.ContainerJuicer;
import redmagic.tileentities.TileEntityJuicer;

public class GuiJuicer extends GuiContainer{
	public TileEntityJuicer entity;
	
	public GuiJuicer(EntityPlayer player, TileEntityJuicer tileEntity) {
		super(new ContainerJuicer(player,tileEntity));
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
		if(this.entity.tank.getLiquid() != null){
			double percent = (double)this.entity.tank.getLiquid().amount / (double)this.entity.tank.getCapacity();
			this.drawTexturedModalRect(x + 9, y + 75, 0, ySize, (int)((xSize - 18) * percent), 5);
		}
	}

}

