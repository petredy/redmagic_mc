package com.petredy.redmagic.client.render;

import static net.minecraftforge.client.IItemRenderer.ItemRenderType.EQUIPPED;
import static net.minecraftforge.client.IItemRenderer.ItemRendererHelper.BLOCK_3D;

import org.lwjgl.opengl.GL11;

import com.petredy.redmagic.Redmagic;
import com.petredy.redmagic.client.models.ModelSoulman;
import com.petredy.redmagic.entities.EntitySoulman;
import com.petredy.redmagic.lib.Textures;
import com.petredy.redmagic.utils.LogUtils;

import net.minecraft.block.Block;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelSnowMan;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderSnowMan;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntitySnowman;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.IItemRenderer.ItemRenderType;
import net.minecraftforge.client.MinecraftForgeClient;

public class RenderSoulman extends RenderLiving{

    /** A reference to the Snowman model in RenderSnowMan. */
    private ModelSoulman soulman;
    
    private ResourceLocation soulman_default_texture = Textures.SOUL_MAN;

    public RenderSoulman()
    {
        super(new ModelSoulman(), 0.5F);
        this.soulman = (ModelSoulman)super.mainModel;
        this.setRenderPassModel(this.soulman);
    }

    protected void renderSoulmanHead(EntitySoulman par1EntitySoulman, float par2)
    {
        super.renderEquippedItems(par1EntitySoulman, par2);
        ItemStack itemstack = new ItemStack(Block.dirt, 1);

        if (itemstack != null && itemstack.getItem() instanceof ItemBlock)
        {
            GL11.glPushMatrix();
            this.soulman.bipedHead.postRender(0.0625F);

            IItemRenderer customRenderer = MinecraftForgeClient.getItemRenderer(itemstack, EQUIPPED);
            boolean is3D = (customRenderer != null && customRenderer.shouldUseRenderHelper(EQUIPPED, itemstack, BLOCK_3D));

            if (is3D || RenderBlocks.renderItemIn3d(Block.blocksList[itemstack.itemID].getRenderType()))
            {
                float f1 = 0.625F;
                GL11.glTranslatef(0.0F, -0.34375F, 0.0F);
                GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
                GL11.glScalef(f1, -f1, f1);
            }

            this.renderManager.itemRenderer.renderItem(par1EntitySoulman, itemstack, 0);
            GL11.glPopMatrix();
        }
    }

    protected ResourceLocation getSoulmanTextures(EntitySoulman entity)
    {
        return soulman_default_texture;
    }

    protected void renderEquippedItems(EntityLivingBase par1EntityLivingBase, float par2)
    {
        //this.renderSoulmanHead((EntitySoulman)par1EntityLivingBase, par2);
        
        EntitySoulman soulman = (EntitySoulman)par1EntityLivingBase;
        ItemStack itemstack = soulman.getHeldItem();
        /*
        if (itemstack != null && itemstack.getItem() instanceof ItemBlock)
        {
            this.renderCarriedBlock(soulman, itemstack, par2);
        }else if(itemstack != null){
        	this.renderCarriedItem(soulman, itemstack, par2);
        }*/
        
        
        this.renderCarriedItemStack(soulman, itemstack, par2);
        
    }

    private void renderCarriedItemStack(EntitySoulman soulman, ItemStack itemstack, float par2) {

        if (itemstack != null)
        {
            GL11.glPushMatrix();
            GL11.glTranslatef(-0.0625F, 0.4375F, 0.0625F);


            EnumAction enumaction = null;
            //this.soulman.bipedRightArm.postRender(0.0625F);

            float f11;

            IItemRenderer customRenderer = MinecraftForgeClient.getItemRenderer(itemstack, EQUIPPED);
            boolean is3D = (customRenderer != null && customRenderer.shouldUseRenderHelper(EQUIPPED, itemstack, BLOCK_3D));
            boolean isBlock = itemstack.itemID < Block.blocksList.length && itemstack.getItemSpriteNumber() == 0;

            if (is3D || (isBlock && RenderBlocks.renderItemIn3d(Block.blocksList[itemstack.itemID].getRenderType())))
            {
                f11 = 0.5F;
                GL11.glTranslatef(0.1F, 0.45F, -0.4F);
                f11 *= 0.75F;
                GL11.glRotatef(20.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                GL11.glScalef(-f11, -f11, f11);
            }
            else if (itemstack.itemID == Item.bow.itemID)
            {
                f11 = 0.625F;
                GL11.glTranslatef(0.0F, 0.125F, 0.3125F);
                GL11.glRotatef(-20.0F, 0.0F, 1.0F, 0.0F);
                GL11.glScalef(f11, -f11, f11);
                GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
            }
            else if (Item.itemsList[itemstack.itemID].isFull3D())
            {
                f11 = 0.625F;

                if (Item.itemsList[itemstack.itemID].shouldRotateAroundWhenRendering())
                {
                    GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
                    GL11.glTranslatef(0.0F, -0.125F, 0.0F);
                }

                GL11.glTranslatef(0.0F, 0.1875F, 0.0F);
                GL11.glScalef(f11, -f11, f11);
                //GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
                //GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
            }
            else
            {
                f11 = 0.375F;
                GL11.glTranslatef(-0.3F, 0.6F, -0.5F);
                GL11.glScalef(f11, f11, f11);
                GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
                //GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(20.0F, 0.0F, 0.0F, 1.0F);
            }

            float f12;
            float f13;
            int j;
            float f6;
            
            if (itemstack.getItem().requiresMultipleRenderPasses())
            {
                for (j = 0; j < itemstack.getItem().getRenderPasses(itemstack.getItemDamage()); ++j)
                {
                    int k = itemstack.getItem().getColorFromItemStack(itemstack, j);
                    f13 = (float)(k >> 16 & 255) / 255.0F;
                    f12 = (float)(k >> 8 & 255) / 255.0F;
                    f6 = (float)(k & 255) / 255.0F;
                    GL11.glColor4f(f13, f12, f6, 1.0F);
                    this.renderManager.itemRenderer.renderItem(soulman, itemstack, j);
                }
            }
            else
            {
                j = itemstack.getItem().getColorFromItemStack(itemstack, 0);
                float f14 = (float)(j >> 16 & 255) / 255.0F;
                f13 = (float)(j >> 8 & 255) / 255.0F;
                f12 = (float)(j & 255) / 255.0F;
                GL11.glColor4f(f14, f13, f12, 1.0F);
                this.renderManager.itemRenderer.renderItem(soulman, itemstack, 0);
            }

            GL11.glPopMatrix();
        }
	}

	private void renderCarriedItem(EntitySoulman soulman, ItemStack itemstack, float par2) {
    	RenderItem renderer = new RenderItem();
		renderer.setRenderManager(RenderManager.instance);
		EntityItem item = new EntityItem(soulman.worldObj);
		item.age = 100;
		item.hoverStart = 0;
		item.setEntityItemStack(itemstack);
		GL11.glPushMatrix();
		
		//GL11.glTranslatef(0, 0.5f, 0.5f);
		
		renderer.doRenderItem(item, soulman.posY, soulman.posY + 1, soulman.posZ, 0.0f, 0.0f);
		GL11.glPopMatrix();
	}

	private void renderCarriedBlock(EntitySoulman soulman, ItemStack itemstack, float par2) {
    	GL11.glPushMatrix();

        IItemRenderer customRenderer = MinecraftForgeClient.getItemRenderer(itemstack, EQUIPPED);
        boolean is3D = (customRenderer != null && customRenderer.shouldUseRenderHelper(EQUIPPED, itemstack, BLOCK_3D));

        if (is3D || (itemstack.getItem() instanceof ItemBlock && RenderBlocks.renderItemIn3d(Block.blocksList[itemstack.itemID].getRenderType())))
        {
            float f1 = 0.625F;
            GL11.glTranslatef(0.0F, -0.34375F, 0.0F);
            GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
            GL11.glScalef(f1, -f1, f1);
        }

        this.renderManager.itemRenderer.renderItem(soulman, itemstack, 0);
        
        GL11.glPopMatrix();
	}

	/**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(Entity par1Entity)
    {
        return this.getSoulmanTextures((EntitySoulman)par1Entity);
    }
    
    @Override
    public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
    {
        this.doRenderLiving((EntityLiving)par1Entity, par2, par4, par6, par8, par9);
    }
    
    @Override
    public void doRenderLiving(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9)
    {
        super.doRenderLiving(par1EntityLiving, par2, par4, par6, par8, par9);
    }
    
    protected void preRenderCallback(EntityLivingBase par1EntityLivingBase, float par2) {
    	GL11.glScalef(0.8F, 0.8F, 0.8F);
    }
    
    protected void renderModel(EntityLivingBase par1EntityLivingBase, float par2, float par3, float par4, float par5, float par6, float par7){
    	
    	Redmagic.proxy.bindTexture(soulman_default_texture);
    	this.soulman.render(par1EntityLivingBase, par2, par3, par4, par5, par6, par7);
    	
    }
}
