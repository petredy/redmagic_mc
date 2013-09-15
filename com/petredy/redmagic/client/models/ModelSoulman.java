package com.petredy.redmagic.client.models;

import org.lwjgl.opengl.GL11;

import com.petredy.redmagic.entities.EntitySoulman;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.model.ModelSnowMan;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.MathHelper;

public class ModelSoulman extends ModelBiped{

	public ModelRenderer bottomBody;
	
	public ModelSoulman(){
		super();
		float f = 4.0F;
        float f1 = 0.0F;
        this.bipedHead = (new ModelRenderer(this, 0, 0)).setTextureSize(64, 64);
        this.bipedHead.addBox(-4.0F, -7.0F, -4.0F, 8, 8, 8, f1 - 0.5F);
        this.bipedHead.setRotationPoint(0.0F, 0.0F + f, 0.0F);
        this.bipedRightArm = (new ModelRenderer(this, 32, 0)).setTextureSize(64, 64);
        this.bipedRightArm.addBox(-1.0F, 0.0F, -1.0F, 12, 2, 2, f1 - 0.5F);
        this.bipedRightArm.setRotationPoint(0.0F, 0.0F + f + 9.0F - 7.0F, 0.0F);
        this.bipedLeftArm = (new ModelRenderer(this, 32, 0)).setTextureSize(64, 64);
        this.bipedLeftArm.addBox(-1.0F, 0.0F, -1.0F, 12, 2, 2, f1 - 0.5F);
        this.bipedLeftArm.setRotationPoint(0.0F, 0.0F + f + 9.0F - 7.0F, 0.0F);
        this.bipedBody = (new ModelRenderer(this, 0, 16)).setTextureSize(64, 64);
        this.bipedBody.addBox(-5.0F, -10.0F, -5.0F, 10, 10, 10, f1 - 0.5F);
        this.bipedBody.setRotationPoint(0.0F, 0.0F + f + 9.0F, 0.0F);
        this.bottomBody = (new ModelRenderer(this, 0, 36)).setTextureSize(64, 64);
        this.bottomBody.addBox(-6.0F, -12.0F, -6.0F, 12, 12, 12, f1 - 0.5F);
        this.bottomBody.setRotationPoint(0.0F, 0.0F + f + 20.0F, 0.0F);
	}
	
	
	public void render(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7){
        this.setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
        float f6 = 2.0F;
        GL11.glPushMatrix();
        GL11.glScalef(1.5F / f6, 1.5F / f6, 1.5F / f6);
        GL11.glTranslatef(0.0F, 17.5F * par7, 0.0F);
        this.bipedHead.render(par7);
        GL11.glPopMatrix();
        GL11.glPushMatrix();
        GL11.glScalef(1.0F / f6, 1.0F / f6, 1.0F / f6);
        GL11.glTranslatef(0.0F, 24.0F * par7, 0.0F);
        this.bipedBody.render(par7);
        this.bottomBody.render(par7);
        this.bipedRightArm.render(par7);
        this.bipedLeftArm.render(par7);
        this.bipedHeadwear.render(par7);
        GL11.glPopMatrix();
    }
	
	
	public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity)
    {
		
		if(((EntitySoulman)par7Entity).getCurrentItemOrArmor(0) != null){
			this.heldItemLeft = 20;
			if(((EntitySoulman)par7Entity).getCurrentItemOrArmor(0).getItem() instanceof ItemBlock)this.heldItemRight = 20;
		}else{
			this.heldItemLeft = 0;
			this.heldItemRight = 0;
		}
		
		
        this.bipedHead.rotateAngleY = par4 / (180F / (float)Math.PI);
        this.bipedHead.rotateAngleX = par5 / (180F / (float)Math.PI);
        this.bipedHeadwear.rotateAngleY = this.bipedHead.rotateAngleY;
        this.bipedHeadwear.rotateAngleX = this.bipedHead.rotateAngleX;
        
        float armHelp1 = MathHelper.sin(this.bipedBody.rotateAngleY);
        float armHelp2 = MathHelper.cos(this.bipedBody.rotateAngleY);
        this.bipedRightArm.rotateAngleZ = 1.0F;
        this.bipedLeftArm.rotateAngleZ = -1.0F;
        this.bipedRightArm.rotateAngleY = 0.0F + this.bipedBody.rotateAngleY;
        this.bipedLeftArm.rotateAngleY = (float)Math.PI + this.bipedBody.rotateAngleY;
        this.bipedRightArm.rotationPointX = armHelp2 * 5.0F;
        this.bipedRightArm.rotationPointZ = -armHelp1 * 5.0F;
        this.bipedLeftArm.rotationPointX = -armHelp2 * 5.0F;
        this.bipedLeftArm.rotationPointZ = armHelp1 * 5.0F;
        
        
        if (this.isRiding)
        {
            this.bipedRightArm.rotateAngleX += -((float)Math.PI / 5F);
            this.bipedLeftArm.rotateAngleX += -((float)Math.PI / 5F);
            this.bipedRightLeg.rotateAngleX = -((float)Math.PI * 2F / 5F);
            this.bipedLeftLeg.rotateAngleX = -((float)Math.PI * 2F / 5F);
            this.bipedRightLeg.rotateAngleY = ((float)Math.PI / 10F);
            this.bipedLeftLeg.rotateAngleY = -((float)Math.PI / 10F);
        }


        if (this.heldItemLeft != 0)
        {
        	this.bipedLeftArm.rotateAngleZ = this.bipedLeftArm.rotateAngleZ * 0.5F - ((float)Math.PI / 10F) * (float)this.heldItemLeft;
            this.bipedLeftArm.rotateAngleY = this.bipedLeftArm.rotateAngleY * 0.6F -((float)Math.PI / 10F) * (float)this.heldItemLeft;
        }

        if (this.heldItemRight != 0)
        {
        	this.bipedRightArm.rotateAngleZ = this.bipedRightArm.rotateAngleZ * 0.5F - ((float)Math.PI / 10F) * (float)this.heldItemRight;
            this.bipedRightArm.rotateAngleY = this.bipedRightArm.rotateAngleY * 0.5F -((float)Math.PI / 10F) * (float)this.heldItemRight + 1;
        }

        //this.bipedRightArm.rotateAngleY = 0.0F;
        //this.bipedLeftArm.rotateAngleY = 0.0F;
        float f6;
        float f7;

        if (this.onGround > -9990.0F)
        {
            f6 = this.onGround;
            this.bipedBody.rotateAngleY = MathHelper.sin(MathHelper.sqrt_float(f6) * (float)Math.PI * 2.0F) * 0.2F;
            this.bottomBody.rotateAngleY = MathHelper.sin(MathHelper.sqrt_float(f6) * (float)Math.PI * 2.0F) * 0.2F;
            
            this.bipedLeftArm.rotationPointZ = MathHelper.sin(this.bipedBody.rotateAngleY) * 5.0F;
            this.bipedLeftArm.rotationPointX = -MathHelper.cos(this.bipedBody.rotateAngleY) * 5.0F;
            
            this.bipedRightArm.rotationPointZ = -MathHelper.sin(this.bipedBody.rotateAngleY) * 5.0F;
            this.bipedRightArm.rotationPointX = MathHelper.cos(this.bipedBody.rotateAngleY) * 5.0F;
            
            this.bipedLeftArm.rotateAngleY += this.bipedBody.rotateAngleY;
            
            this.bipedRightArm.rotateAngleY += this.bipedBody.rotateAngleY;
            this.bipedRightArm.rotateAngleX += this.bipedBody.rotateAngleY;
            f6 = 1.0F - this.onGround;
            f6 *= f6;
            f6 *= f6;
            f6 = 1.0F - f6;
            f7 = MathHelper.sin(f6 * (float)Math.PI);
            float f8 = MathHelper.sin(this.onGround * (float)Math.PI) * -(this.bipedHead.rotateAngleX - 0.7F) * 0.75F;
            //this.bipedLeftArm.rotateAngleX = (float)((double)this.bipedRightArm.rotateAngleX - ((double)f7 * 1.2D + (double)f8));
            //this.bipedLeftArm.rotateAngleY += this.bipedBody.rotateAngleY * 2.0F;
            //this.bipedLeftArm.rotateAngleZ = MathHelper.sin(this.onGround * (float)Math.PI) * -0.4F;
            
        }

        if (this.isSneak)
        {
            this.bipedBody.rotateAngleX = 0.5F;
            this.bottomBody.rotateAngleX = 0.5F;
            this.bipedRightArm.rotateAngleX += 0.4F;
            this.bipedLeftArm.rotateAngleX += 0.4F;
            this.bipedRightLeg.rotationPointZ = 4.0F;
            this.bipedLeftLeg.rotationPointZ = 4.0F;
            this.bipedRightLeg.rotationPointY = 9.0F;
            this.bipedLeftLeg.rotationPointY = 9.0F;
            this.bipedHead.rotationPointY = 1.0F;
            this.bipedHeadwear.rotationPointY = 1.0F;
        }
        else
        {
            this.bipedBody.rotateAngleX = 0.0F;
            this.bottomBody.rotateAngleX = 0.0F;
            this.bipedRightLeg.rotationPointZ = 0.1F;
            this.bipedLeftLeg.rotationPointZ = 0.1F;
            this.bipedRightLeg.rotationPointY = 12.0F;
            this.bipedLeftLeg.rotationPointY = 12.0F;
            this.bipedHead.rotationPointY = 0.0F;
            this.bipedHeadwear.rotationPointY = 0.0F;
        }

        
        this.bipedRightArm.rotateAngleZ += MathHelper.cos(par3 * 0.09F) * 0.05F + 0.05F;
        this.bipedLeftArm.rotateAngleZ -= MathHelper.cos(par3 * 0.09F) * 0.05F + 0.05F;
        //this.bipedRightArm.rotateAngleX += MathHelper.sin(par3 * 0.067F) * 0.05F;
        //this.bipedLeftArm.rotateAngleX -= MathHelper.sin(par3 * 0.067F) * 0.05F;
		
        if(this.aimedBow)
        {
            f6 = 0.0F;
            f7 = 0.0F;
            this.bipedRightArm.rotateAngleZ = 0.0F;
            this.bipedLeftArm.rotateAngleZ = 0.0F;
            this.bipedRightArm.rotateAngleY = -(0.1F - f6 * 0.6F) + this.bipedHead.rotateAngleY;
            this.bipedLeftArm.rotateAngleY = 0.1F - f6 * 0.6F + this.bipedHead.rotateAngleY + 0.4F;
            this.bipedRightArm.rotateAngleX = -((float)Math.PI / 2F) + this.bipedHead.rotateAngleX;
            this.bipedLeftArm.rotateAngleX = -((float)Math.PI / 2F) + this.bipedHead.rotateAngleX;
            this.bipedRightArm.rotateAngleX -= f6 * 1.2F - f7 * 0.4F;
            this.bipedLeftArm.rotateAngleX -= f6 * 1.2F - f7 * 0.4F;
            this.bipedRightArm.rotateAngleZ += MathHelper.cos(par3 * 0.09F) * 0.05F + 0.05F;
            this.bipedLeftArm.rotateAngleZ -= MathHelper.cos(par3 * 0.09F) * 0.05F + 0.05F;
            this.bipedRightArm.rotateAngleX += MathHelper.sin(par3 * 0.067F) * 0.05F;
            this.bipedLeftArm.rotateAngleX -= MathHelper.sin(par3 * 0.067F) * 0.05F;
        }
        
    }
	
	public void renderPart(String part, Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7){
    	this.setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
    	float f6 = 2.0F;
        GL11.glPushMatrix();
        GL11.glScalef(1.5F / f6, 1.5F / f6, 1.5F / f6);
        GL11.glTranslatef(0.0F, 16.0F * par7, 0.0F);
        if(part.equals("head"))this.bipedHead.render(par7);
        GL11.glPopMatrix();
        GL11.glPushMatrix();
        GL11.glScalef(1.0F / f6, 1.0F / f6, 1.0F / f6);
        GL11.glTranslatef(0.0F, 24.0F * par7, 0.0F);
    	
    	if(part.equals("body"))this.bipedBody.render(par7);
    	if(part.equals("bottom"))this.bottomBody.render(par7);
    	if(part.equals("rightHand"))this.bipedRightArm.render(par7);
    	if(part.equals("leftHand"))this.bipedLeftArm.render(par7);
    	
    	GL11.glPopMatrix();
    }

	
}
