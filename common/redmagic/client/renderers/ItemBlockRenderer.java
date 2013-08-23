package redmagic.client.renderers;

import org.lwjgl.opengl.GL11;

import redmagic.Redmagic;
import redmagic.client.models.ModelArtifact;
import redmagic.configuration.Reference;
import redmagic.helpers.LogHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.common.ForgeHooks;

public class ItemBlockRenderer implements IItemRenderer{

	
	public ModelArtifact artifact;
	
	public ItemBlockRenderer(){
		artifact = new ModelArtifact();
	}
	
	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		return true;
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item,
			ItemRendererHelper helper) {
		return false;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
		switch(type){
		case INVENTORY:
			this.renderItemInInventory();
			break;
		case ENTITY:
			this.renderItemAsEntity();
			break;
		default:
			this.renderItemEquipped();
			break;
		}
	}

	private void renderItemEquipped() {
		GL11.glPushMatrix();
		GL11.glScalef(0.2f, 0.2f, 0.2f);
		GL11.glTranslatef(4.0F, 3.0F, 1.0F);
		
		Redmagic.proxy.bindTexture("models/artifact.png");
		artifact.render();
		
		GL11.glPopMatrix();
	}

	private void renderItemAsEntity() {
		GL11.glPushMatrix();
		GL11.glScalef(0.2f, 0.2f, 0.2f);
		GL11.glTranslatef(0.0F, 2.0F, 0.0F);
		
		Redmagic.proxy.bindTexture("models/artifact.png");
		artifact.render();
		
		GL11.glPopMatrix();
	}

	private void renderItemInInventory() {
		GL11.glPushMatrix();
		GL11.glScalef(3.5f, 3.5f, 3.5f);
		GL11.glTranslatef(2.25F, 2.25F, 0.0F);
		
		/*
		 * TODO: GL11.glRotatef(x, y, z); 
		 */
		Redmagic.proxy.bindTexture("models/artifact.png");
		artifact.render();
		
		GL11.glPopMatrix();
		
	}
	

}
