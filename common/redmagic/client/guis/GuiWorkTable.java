package redmagic.client.guis;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.common.network.PacketDispatcher;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.StatCollector;
import redmagic.client.guis.button.ButtonCustom;
import redmagic.configuration.Texture;
import redmagic.containers.ContainerWorkTable;
import redmagic.network.PacketHandler;
import redmagic.network.PacketWorkTable;
import redmagic.tileentities.TileEntityWorkTable;

public class GuiWorkTable extends GuiContainer{
	public TileEntityWorkTable entity;
	public ContainerWorkTable container;
	
	public GuiWorkTable(EntityPlayer player, TileEntityWorkTable tileEntity) {
		super(new ContainerWorkTable(player,tileEntity));
		this.entity = tileEntity;
		this.container = (ContainerWorkTable) this.inventorySlots;
	}
	
	@SuppressWarnings("unchecked")
	public void initGui()
    {
		super.initGui();
		this.buttonList.add(new GuiButton(0, this.guiLeft + 100, this.guiTop + 8, 20, 20, "<"));
		this.buttonList.add(new GuiButton(1, this.guiLeft + 143, this.guiTop + 8, 20, 20, ">"));
    }
	
	protected void actionPerformed(GuiButton button) {
		if(button.id == 0){
			PacketDispatcher.sendPacketToServer(PacketHandler.populatePacket(new PacketWorkTable(this.entity.xCoord, this.entity.yCoord, this.entity.zCoord, -1)));
			this.entity.craftingIndex -= 1;
			this.entity.showCrafting();
			this.container.showCrafting();
		}else if(button.id == 1){
			PacketDispatcher.sendPacketToServer(PacketHandler.populatePacket(new PacketWorkTable(this.entity.xCoord, this.entity.yCoord, this.entity.zCoord, 1)));
			this.entity.craftingIndex += 1;
			this.entity.showCrafting();
			this.container.showCrafting();
		}
		
	}

	@Override
	public void drawGuiContainerForegroundLayer(int par1, int par2){		
		fontRenderer.drawString(this.entity.getInvName(), 8, 6, 4210752);
		
		fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 8, this.ySize - 96 + 2, 4210752);
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3) {
		GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
		this.mc.renderEngine.bindTexture(Texture.WORK_TABLE);
		int x = (this.width - this.xSize) / 2;
		int y = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
		
		int count = 0;
		for(int i = 0; i < 3; i++){
			for(int j = 0; j < 3; j++){
				this.mc.renderEngine.bindTexture(this.container.slotTextures[count]);
				this.drawTexturedModalRect(x + 30 + j * 18, y + 17 + i * 18, 0, 0, 16, 16);
				count++;
			}
		}
		
	}

}

