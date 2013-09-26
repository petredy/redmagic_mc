package com.petredy.redmagic.core;


import com.petredy.redmagic.client.SoundHandler;
import com.petredy.redmagic.client.render.*;
import com.petredy.redmagic.entities.particle.EntityCustomFX;
import com.petredy.redmagic.entities.particle.EntityHoleFX;
import com.petredy.redmagic.entities.particle.EntitySoulFX;
import com.petredy.redmagic.entities.particle.EntityStarFX;
import com.petredy.redmagic.handlers.KeyBindingHandler;
import com.petredy.redmagic.lib.Rendering;
import com.petredy.redmagic.tileentities.TileEntityCage;
import com.petredy.redmagic.tileentities.TileEntityEarthwire;
import com.petredy.redmagic.tileentities.TileEntityTradingChest;
import com.petredy.redmagic.utils.KeyBindingUtils;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.KeyBindingRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
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
		Rendering.TRADING_CHEST_ID = RenderingRegistry.getNextAvailableRenderId();
		Rendering.CAGE_ID = RenderingRegistry.getNextAvailableRenderId();
		Rendering.SOUL_CHEST_ID = RenderingRegistry.getNextAvailableRenderId();
	}
	
	@Override
    public void registerRendering(){	
		
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTradingChest.class, new RenderTradingChest());
		RenderingRegistry.registerBlockHandler(new RenderTradingChest());
		
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCage.class, new RenderCage());
		RenderingRegistry.registerBlockHandler(new RenderCage());
	}
	
	
	@Override
	public void registerSound(){
		MinecraftForge.EVENT_BUS.register(new SoundHandler());
	}
	
	@Override
	public void addEffect(String name, World world, double d1, double d2, double d3, Object...data) {
		EntityFX efx = null;

		if(name.equals("star")){
			efx = new EntityStarFX(world, d1, d2, d3, (Double)data[0], (Double)data[1], (Double)data[2]);
		}
		
		if(name.equals("hole")){
			efx = new EntityHoleFX(world, d1, d2, d3, (String)data[0]);
		}
		
		if(name.equals("soul")){
			efx = new EntitySoulFX(world, d1, d2, d3);
		}
		
		if(name.equals("custom")){
			efx = new EntityCustomFX(world, d1, d2, d3, (ResourceLocation)data[0]);
			if(data[1] != null && data[1] instanceof Float)((EntityCustomFX)efx).setScale((Float)data[1]);
			if(data[2] != null && data[2] instanceof Integer)((EntityCustomFX)efx).setScale((Integer)data[2]);
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
