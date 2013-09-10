package com.petredy.redmagic.core;


import com.petredy.redmagic.client.SoundHandler;
import com.petredy.redmagic.client.render.RenderEngine;
import com.petredy.redmagic.handlers.KeyBindingHandler;
import com.petredy.redmagic.lib.Rendering;
import com.petredy.redmagic.tileentities.TileEntityEngine;
import com.petredy.redmagic.utils.KeyBindingUtils;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.KeyBindingRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;

public class ClientProxy extends CommonProxy{
	
	
	@Override
	public void registerKeyBinding() {
		KeyBindingRegistry.registerKeyBinding(new KeyBindingHandler());
	}
	
	@Override
    public void setKeyBinding(String name, int value) {
        KeyBindingUtils.addKeyBinding(name, value);
        KeyBindingUtils.addIsRepeating(false);
    }
	
	@Override
    public void initRendering() {
		Rendering.ENGINE_ID = RenderingRegistry.getNextAvailableRenderId();
	}
	
	@Override
    public void registerRendering(){	
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityEngine.class, new RenderEngine());
		RenderingRegistry.registerBlockHandler(new RenderEngine());
	}
	
	
	@Override
	public void registerSound(){
		MinecraftForge.EVENT_BUS.register(new SoundHandler());
	}
	
	@Override
	public void addEffect(String name, World world, double d1, double d2, double d3, Object...data) {
		EntityFX efx = null;

		if(name == "custom")
		{
			
		}

		if(efx != null)
		{
			Minecraft.getMinecraft().effectRenderer.addEffect(efx);
		}
	}
	
	@Override
	public World getClientWorld()
	{
		return FMLClientHandler.instance().getClient().theWorld;
	}
	
	public World getServerOverworld(){
		return null;
	}
	
	public void registerWorldLoadingHandler() {}
	
	public void bindTexture(ResourceLocation location){
		Minecraft.getMinecraft().getTextureManager().bindTexture(location);
	}
}