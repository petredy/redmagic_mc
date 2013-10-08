package com.petredy.redmagic.client.guis.button;

import org.lwjgl.opengl.GL11;

import com.petredy.redmagic.Redmagic;
import com.petredy.redmagic.utils.ResourceLocationUtils;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.util.ResourceLocation;

public class ButtonCustom extends GuiButton{
	
	public ResourceLocation texture;
	public int u,v ;
	public int u2, v2;
	public ButtonCustom(int par1, int par2, int par3, int u, int v, int u2, int v2, int par4, int par5, ResourceLocation texture) {
		super(par1, par2, par3, par4, par5, "");
		this.texture = texture;
		this.u2 = u2;
		this.v2 = v2;
	}
	
	public void drawButton(Minecraft par1Minecraft, int par2, int par3)
    {
        if (this.drawButton)
        {
            FontRenderer fontrenderer = par1Minecraft.fontRenderer;
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            this.field_82253_i = par2 >= this.xPosition && par3 >= this.yPosition && par2 < this.xPosition + this.width && par3 < this.yPosition + this.height;
            int k = this.getHoverState(this.field_82253_i);
            
            if(k == 1){
            	Redmagic.proxy.bindTexture(texture);
            	this.drawTexturedModalRect(xPosition, yPosition, u, v, width, height);
            }else if(k == 2){
            	Redmagic.proxy.bindTexture(texture);
            	this.drawTexturedModalRect(xPosition, yPosition, u2, v2, width, height);
            }
            
            this.mouseDragged(par1Minecraft, par2, par3);
            int l = 14737632;

            if (!this.enabled)
            {
                l = -6250336;
            }
            else if (this.field_82253_i)
            {
                l = 16777120;
            }

            this.drawCenteredString(fontrenderer, this.displayString, this.xPosition + this.width / 2, this.yPosition + (this.height - 8) / 2, l);
        }
    }
}
