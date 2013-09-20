package com.petredy.redmagic.client.render;

import org.lwjgl.opengl.GL11;

import com.petredy.redmagic.Redmagic;
import com.petredy.redmagic.client.models.ModelSoul;
import com.petredy.redmagic.lib.Textures;
import com.petredy.redmagic.utils.LogUtils;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;

public class RenderEntitySoul extends RenderLiving{

	public ModelSoul model;
	
	public RenderEntitySoul(){
		super(new ModelSoul(), 0);
		model = (ModelSoul) this.mainModel;
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return Textures.SOUL;
	}
	
	public void doRenderLiving(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9)
    {
        GL11.glPushMatrix();
        Redmagic.proxy.bindTexture(Textures.SOUL);
        GL11.glTranslated(par2, par4 + 0.5, par6);
        GL11.glScalef(1, 1, 1);
        
        model.render();
        
        GL11.glPopMatrix();
    }

}
