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
import redmagic.containers.ContainerTree;
import redmagic.helpers.SoulHelper;
import redmagic.helpers.TreeHelper;
import redmagic.lib.souls.gui.SoulGui;
import redmagic.tileentities.tree.TileEntityTreeWood;

public class GuiTree extends GuiContainer{
	public TileEntityTreeWood entity;
	
	public GuiTree(EntityPlayer player, TileEntityTreeWood entity){
		super(new ContainerTree(player, entity));
		this.entity = entity;
	}
	

	@Override
	public void drawGuiContainerForegroundLayer(int par1, int par2){
		if(this.entity.hasSoul()){
			SoulGui gui = TreeHelper.loadStructure(this.entity.worldObj, this.entity.structureID).storage.getBlockAt(this.entity.xCoord, this.entity.yCoord, this.entity.zCoord).soul.getGui();
			if(gui != null)gui.drawGuiContainerForegroundLayer(par1, par2);
		}
		fontRenderer.drawString(this.entity.getInvName(), 8, 6, 4210752);
		
		fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 8, this.ySize - 96 + 2, 4210752);
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3) {
		if(this.entity.hasSoul()){
			SoulGui gui = TreeHelper.loadStructure(this.entity.worldObj, this.entity.structureID).storage.getBlockAt(this.entity.xCoord, this.entity.yCoord, this.entity.zCoord).soul.getGui();
			if(gui != null){
				GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
				String texture = gui.getTexture();
				this.mc.renderEngine.bindTexture(texture);
				int x = (this.width - this.xSize) / 2;
				int y = (this.height - this.ySize) / 2;
				this.drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
				gui.drawGuiContainerBackgroundLayer(var1, var2, var3);
			}
		}
	}
	

}

