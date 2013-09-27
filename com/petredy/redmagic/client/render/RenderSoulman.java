package com.petredy.redmagic.client.render;

import org.lwjgl.opengl.GL11;

import com.petredy.redmagic.Redmagic;
import com.petredy.redmagic.lib.Textures;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

public class RenderSoulman extends RendererLivingEntity{

	public RenderSoulman() {
		super(new ModelBiped(), 0);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return Textures.SOULMAN;
	}
	
	@Override
	protected void renderModel(EntityLivingBase par1EntityLivingBase, float par2, float par3, float par4, float par5, float par6, float par7)
    {
		GL11.glPushMatrix();
		GL11.glTranslatef(0, 1f, 0);
		GL11.glScalef(0.35f, 0.35f, 0.35f);
		
		Redmagic.proxy.bindTexture(Textures.SOULMAN);
		this.mainModel.render(par1EntityLivingBase, par2, par3, par4, par5, par6, par7);
		GL11.glPopMatrix();
    }
	
	protected void renderLivingLabel(EntityLivingBase par1EntityLivingBase, String par2Str, double par3, double par5, double par7, int par9)
    {
		
    }

}
