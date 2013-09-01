package redmagic.client.renderers;

import org.lwjgl.opengl.GL11;

import redmagic.Redmagic;
import redmagic.client.models.ModelBlock;
import redmagic.configuration.RenderIndex;
import redmagic.helpers.LogHelper;
import redmagic.tileentities.TileEntityConstructionTable;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;

public class RenderConstruction extends TileEntitySpecialRenderer implements ISimpleBlockRenderingHandler{

	ModelBlock construction;
	
	public RenderConstruction(){
		construction = new ModelBlock();
	}
	
	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
		GL11.glPushMatrix();
		Redmagic.proxy.bindTexture("models/construction.png");
		GL11.glScalef(1.0f, 0.5f, 1.0f);
		construction.render();
		
		GL11.glPopMatrix();
		
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
		return false;
	}

	@Override
	public boolean shouldRender3DInInventory() {
		return true;
	}

	@Override
	public int getRenderId() {
		return RenderIndex.CONSTRUCTION_TABLE;
	}

	@Override
	public void renderTileEntityAt(TileEntity tileentity, double d0, double d1, double d2, float f) {
		
		
		TileEntityConstructionTable entity = (TileEntityConstructionTable)tileentity;
		
		
		
		
		RenderConstructionItem renderer = new RenderConstructionItem();
		renderer.setRenderManager(RenderManager.instance);
		for(int i = 0; i < entity.getSizeInventory(); i++){
			int col = i % 3;
			int row = i / 3;
			ItemStack slot = entity.getStackInSlot(i);
			if(slot != null){
				
				EntityItem item = new EntityItem(entity.worldObj);
				item.age = 100;
				item.hoverStart = 0;
				item.setEntityItemStack(slot.copy());
				GL11.glPushMatrix();
				renderer.doRenderItem(item, d0 + col * 0.3 + 0.2, d1 + 0.55, d2 + row * 0.3 + 0.2, 0.0f, 0.0f);
				GL11.glPopMatrix();
			}
		}
		
		GL11.glPushMatrix();
		GL11.glTranslated(d0 + 0.5, d1 + 0.25, d2 + 0.5);
		GL11.glColor3f(1.0f, 1.0f, 1.0f);
		GL11.glScalef(1.0f, 0.5f, 1.0f);
		if(entity.side == 2)GL11.glRotatef(90, 0, 1, 0);
		if(entity.side == 3)GL11.glRotatef(-90, 0, 1, 0);
		if(entity.side == 4)GL11.glRotatef(180, 0, 1, 0);
		if(entity.side == 5)GL11.glRotatef(0, 0, 1, 0);
		
		
		Redmagic.proxy.bindTexture("models/construction.png");
		construction.render();
		
		GL11.glPopMatrix();
		
	}

}
