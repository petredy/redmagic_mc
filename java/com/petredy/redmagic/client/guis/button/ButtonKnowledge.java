package com.petredy.redmagic.client.guis.button;

import org.lwjgl.opengl.GL11;

import com.petredy.redmagic.Redmagic;
import com.petredy.redmagic.utils.ResourceLocationUtils;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.util.ResourceLocation;

public class ButtonKnowledge extends ButtonCustom{

	public String name;
	
	public ButtonKnowledge(String name, int par1, int par2, int par3, int u, int v, int u2, int v2, int par4, int par5, ResourceLocation texture) {
		super(par1, par2, par3, u, v, u2, v2, par4, par5, texture);
		this.name = name;
	}
	
	

}
