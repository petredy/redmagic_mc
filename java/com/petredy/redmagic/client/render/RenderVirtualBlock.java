package com.petredy.redmagic.client.render;

import java.util.Date;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import com.petredy.redmagic.lib.Textures;

import cpw.mods.fml.client.FMLClientHandler;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class RenderVirtualBlock{
	
	public static int age;
	public static long time;
	
	public RenderVirtualBlock(){
		
	}
	
	
	public void render(World world, ItemStack stack, double x, double y, double z){
		RenderItem renderer = new RenderItem();
		renderer.setRenderManager(RenderManager.instance);
		
		EntityItem item = new EntityItem(world, x + 0.5, y + 0.5, z + 0.5, stack);
		item.age = age;
		item.hoverStart = 1;
		renderer.doRenderItem(item, x + 0.5, y + 0.5, z + 0.5, 0, 0);
		
		if(new Date().getTime() - time > 1){
			age++;
			time = new Date().getTime();
		}
	}
	
}
