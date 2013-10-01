package com.petredy.redmagic.client.render;

import static net.minecraftforge.client.IItemRenderer.ItemRenderType.EQUIPPED;
import static net.minecraftforge.client.IItemRenderer.ItemRendererHelper.BLOCK_3D;

import org.lwjgl.opengl.GL11;

import com.petredy.redmagic.Redmagic;
import com.petredy.redmagic.entities.EntitySoulman;
import com.petredy.redmagic.lib.Textures;
import com.petredy.redmagic.utils.LogUtils;

import net.minecraft.block.Block;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
import net.minecraft.client.renderer.tileentity.TileEntitySkullRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.common.MinecraftForge;

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
		
		if(this.mainModel instanceof ModelBiped && par1EntityLivingBase instanceof EntitySoulman){
			ModelBiped model = (ModelBiped)mainModel;
			EntitySoulman man = (EntitySoulman)par1EntityLivingBase;
			model.setRotationAngles(par2, par3, par4, par5, par6, par7, par1EntityLivingBase);
			
			Redmagic.proxy.bindTexture(man.body.leftArm.getTexture());
			model.bipedLeftArm.render(par7);
			
			Redmagic.proxy.bindTexture(man.body.rightArm.getTexture());
			model.bipedRightArm.render(par7);
			
			Redmagic.proxy.bindTexture(man.body.leftLeg.getTexture());
			model.bipedLeftLeg.render(par7);
			
			Redmagic.proxy.bindTexture(man.body.rightLeg.getTexture());
			model.bipedRightLeg.render(par7);
			
			Redmagic.proxy.bindTexture(man.body.body.getTexture());
			model.bipedBody.render(par7);
			
			Redmagic.proxy.bindTexture(man.body.head.getTexture());
			model.bipedHead.render(par7);
			
		}else{
			Redmagic.proxy.bindTexture(Textures.SOULMAN);
			this.mainModel.render(par1EntityLivingBase, par2, par3, par4, par5, par6, par7);
		}
		GL11.glPopMatrix();
    }
	
	protected void renderLivingLabel(EntityLivingBase par1EntityLivingBase, String par2Str, double par3, double par5, double par7, int par9)
	{
    }
	
	protected void renderEquippedItems(EntityLivingBase par1EntityLivingBase, float par2) {
		this.renderSpecials((EntitySoulman)par1EntityLivingBase, par2);
	}
	
	protected void renderSpecials(EntitySoulman soulman, float par2){
		ModelBiped model = (ModelBiped)this.mainModel;
        float f1 = 1.0F;
        GL11.glColor3f(f1, f1, f1);
        super.renderEquippedItems(soulman, par2);
        super.renderArrowsStuckInEntity(soulman, par2);
        ItemStack itemstack = soulman.inventory.armorItemInSlot(3);

        if (itemstack != null)
        {
            GL11.glPushMatrix();
            model.bipedHead.postRender(0.0625F);
            float f2;

            if (itemstack != null && itemstack.getItem() instanceof ItemBlock)
            {
                IItemRenderer customRenderer = MinecraftForgeClient.getItemRenderer(itemstack, EQUIPPED);
                boolean is3D = (customRenderer != null && customRenderer.shouldUseRenderHelper(EQUIPPED, itemstack, BLOCK_3D));

                if (is3D || RenderBlocks.renderItemIn3d(Block.blocksList[itemstack.itemID].getRenderType()))
                {
                    f2 = 0.625F;
                    GL11.glTranslatef(0.0F, -0.25F, 0.0F);
                    GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glScalef(f2, -f2, -f2);
                }

                this.renderManager.itemRenderer.renderItem(soulman, itemstack, 0);
            }
            else if (itemstack.getItem().itemID == Item.skull.itemID)
            {
                f2 = 1.0625F;
                GL11.glScalef(f2, -f2, -f2);
                String s = "";

                if (itemstack.hasTagCompound() && itemstack.getTagCompound().hasKey("SkullOwner"))
                {
                    s = itemstack.getTagCompound().getString("SkullOwner");
                }

                TileEntitySkullRenderer.skullRenderer.func_82393_a(-0.5F, 0.0F, -0.5F, 1, 180.0F, itemstack.getItemDamage(), s);
            }

            GL11.glPopMatrix();
        }



        ItemStack itemstack1 = soulman.inventory.getCurrentItem();

        if (itemstack1 != null)
        {
            GL11.glPushMatrix();
            model.bipedRightArm.postRender(0.0625F);
            GL11.glTranslatef(-0.0625F, 0.4375F, 0.0625F);
            
            EnumAction enumaction = null;

            if (soulman.getItemInUseCount() > 0)
            {
                enumaction = itemstack1.getItemUseAction();
            }

            float f11;

            IItemRenderer customRenderer = MinecraftForgeClient.getItemRenderer(itemstack1, EQUIPPED);
            boolean is3D = (customRenderer != null && customRenderer.shouldUseRenderHelper(EQUIPPED, itemstack1, BLOCK_3D));
            boolean isBlock = itemstack1.itemID < Block.blocksList.length && itemstack1.getItemSpriteNumber() == 0;

            if (is3D || (isBlock && RenderBlocks.renderItemIn3d(Block.blocksList[itemstack1.itemID].getRenderType())))
            {
                f11 = 0.5F;
                GL11.glTranslatef(0.0F, 0.1875F, -0.3125F);
                f11 *= 0.75F;
                GL11.glRotatef(20.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                GL11.glScalef(-f11, -f11, f11);
            }
            else if (itemstack1.itemID == Item.bow.itemID)
            {
                f11 = 0.625F;
                GL11.glTranslatef(0.0F, 0.125F, 0.3125F);
                GL11.glRotatef(-20.0F, 0.0F, 1.0F, 0.0F);
                GL11.glScalef(f11, -f11, f11);
                GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
            }
            else if (Item.itemsList[itemstack1.itemID].isFull3D())
            {
                f11 = 0.625F;

                if (Item.itemsList[itemstack1.itemID].shouldRotateAroundWhenRendering())
                {
                    GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
                    GL11.glTranslatef(0.0F, -0.125F, 0.0F);
                }

                if (soulman.getItemInUseCount() > 0 && enumaction == EnumAction.block)
                {
                    GL11.glTranslatef(0.05F, 0.0F, -0.1F);
                    GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glRotatef(-10.0F, 1.0F, 0.0F, 0.0F);
                    GL11.glRotatef(-60.0F, 0.0F, 0.0F, 1.0F);
                }

                GL11.glTranslatef(0.0F, 0.1875F, 0.0F);
                GL11.glScalef(f11, -f11, f11);
                GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
            }
            else
            {
                f11 = 0.375F;
                GL11.glTranslatef(0.25F, 0.1875F, -0.1875F);
                GL11.glScalef(f11, f11, f11);
                GL11.glRotatef(60.0F, 0.0F, 0.0F, 1.0F);
                GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(20.0F, 0.0F, 0.0F, 1.0F);
            }

            float f12;
            float f13;
            int j;

            if (itemstack1.getItem().requiresMultipleRenderPasses())
            {
                for (j = 0; j < itemstack1.getItem().getRenderPasses(itemstack1.getItemDamage()); ++j)
                {
                    int k = itemstack1.getItem().getColorFromItemStack(itemstack1, j);
                    f13 = (float)(k >> 16 & 255) / 255.0F;
                    f12 = (float)(k >> 8 & 255) / 255.0F;
                    float f6 = (float)(k & 255) / 255.0F;
                    GL11.glColor4f(f13, f12, f6, 1.0F);
                    this.renderManager.itemRenderer.renderItem(soulman, itemstack1, j);
                }
            }
            else
            {
                j = itemstack1.getItem().getColorFromItemStack(itemstack1, 0);
                float f14 = (float)(j >> 16 & 255) / 255.0F;
                f13 = (float)(j >> 8 & 255) / 255.0F;
                f12 = (float)(j & 255) / 255.0F;
                GL11.glColor4f(f14, f13, f12, 1.0F);
                this.renderManager.itemRenderer.renderItem(soulman, itemstack1, 0);
            }

            GL11.glPopMatrix();
        }
    }

}
