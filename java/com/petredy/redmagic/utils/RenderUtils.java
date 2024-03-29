package com.petredy.redmagic.utils;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import com.petredy.redmagic.lib.Textures;
import com.petredy.redmagic.utils.BlockUtils.VirtualBlock;

import cpw.mods.fml.client.FMLClientHandler;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.ForgeDirection;

public class RenderUtils {
	
	private static int rotationAngle = 0;
	
	private static int pulse = 0;
    private static boolean doInc = true;
	
	public static void renderRotatingBlockIntoGUI(FontRenderer fontRenderer, TextureManager renderEngine, ItemStack stack, int x, int y, float zLevel, float scale) {

        RenderBlocks renderBlocks = new RenderBlocks();

        Block block = Block.blocksList[stack.itemID];
		Minecraft.getMinecraft().getTextureManager().bindTexture(Textures.VANILLA_BLOCK_TEXTURE_SHEET);
        GL11.glPushMatrix();
        GL11.glTranslatef(x - 2, y + 3, -3.0F + zLevel);
        GL11.glScalef(10.0F, 10.0F, 10.0F);
        GL11.glTranslatef(1.0F, 0.5F, 1.0F);
        GL11.glScalef(1.0F * scale, 1.0F * scale, -1.0F);
        GL11.glRotatef(210.0F, 1.0F, 0.0F, 0.0F);
        GL11.glRotatef(0F + 1 * rotationAngle, 0.0F, 1.0F, 0.0F);
        rotationAngle = (rotationAngle + 1) % 360;

        int var10 = Item.itemsList[stack.itemID].getColorFromItemStack(stack, 0);
        float var16 = (var10 >> 16 & 255) / 255.0F;
        float var12 = (var10 >> 8 & 255) / 255.0F;
        float var13 = (var10 & 255) / 255.0F;

        GL11.glColor4f(var16, var12, var13, 1.0F);

        GL11.glRotatef(-90.0F, 0.0F, 1.0F, 0.0F);
        renderBlocks.useInventoryTint = true;
        renderBlocks.renderBlockAsItem(block, stack.getItemDamage(), 1.0F);
        renderBlocks.useInventoryTint = true;
        GL11.glPopMatrix();
    }
	
	public static void renderBlockIntoGUI(FontRenderer fontRenderer, TextureManager renderEngine, ItemStack stack, int x, int y, float zLevel, float scale) {

        RenderBlocks renderBlocks = new RenderBlocks();

        Block block = Block.blocksList[stack.itemID];
		Minecraft.getMinecraft().getTextureManager().bindTexture(Textures.VANILLA_BLOCK_TEXTURE_SHEET);
        GL11.glPushMatrix();
        GL11.glTranslatef(x - 2, y + 3, -3.0F + zLevel);
        GL11.glScalef(10.0F, 10.0F, 10.0F);
        GL11.glTranslatef(1.0F, 0.5F, 1.0F);
        GL11.glScalef(1.0F * scale, 1.0F * scale, -1.0F);
        GL11.glRotatef(210.0F, 1.0F, 0.0F, 0.0F);
        GL11.glRotatef(45F, 0, 1, 0);
        int var10 = Item.itemsList[stack.itemID].getColorFromItemStack(stack, 0);
        float var16 = (var10 >> 16 & 255) / 255.0F;
        float var12 = (var10 >> 8 & 255) / 255.0F;
        float var13 = (var10 & 255) / 255.0F;

        GL11.glColor4f(var16, var12, var13, 1.0F);

        renderBlocks.useInventoryTint = true;
        renderBlocks.renderBlockAsItem(block, stack.getItemDamage(), 1.0F);
        renderBlocks.useInventoryTint = true;
        GL11.glPopMatrix();
    }

    public static void renderItemIntoGUI(FontRenderer fontRenderer, TextureManager renderEngine, ItemStack itemStack, int x, int y, float opacity, float scale) {

        Icon icon = itemStack.getIconIndex();
        GL11.glDisable(GL11.GL_LIGHTING);
		Minecraft.getMinecraft().getTextureManager().bindTexture(Textures.VANILLA_ITEM_TEXTURE_SHEET);
        int overlayColour = itemStack.getItem().getColorFromItemStack(itemStack, 0);
        float red = (overlayColour >> 16 & 255) / 255.0F;
        float green = (overlayColour >> 8 & 255) / 255.0F;
        float blue = (overlayColour & 255) / 255.0F;
        GL11.glColor4f(red, green, blue, opacity);
        drawTexturedQuad(x, y, icon, 16 * scale, 16 * scale, -90);
        GL11.glEnable(GL11.GL_LIGHTING);

    }

    public static void drawTexturedQuad(int x, int y, Icon icon, float width, float height, double zLevel) {

        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        tessellator.addVertexWithUV(x + 0, y + height, zLevel, icon.getMinU(), icon.getMaxV());
        tessellator.addVertexWithUV(x + width, y + height, zLevel, icon.getMaxU(), icon.getMaxV());
        tessellator.addVertexWithUV(x + width, y + 0, zLevel, icon.getMaxU(), icon.getMinV());
        tessellator.addVertexWithUV(x + 0, y + 0, zLevel, icon.getMinU(), icon.getMinV());
        tessellator.draw();
    }
    
}
