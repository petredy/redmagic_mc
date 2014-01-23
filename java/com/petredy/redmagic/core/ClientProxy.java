package com.petredy.redmagic.core;


import com.petredy.redmagic.client.SoundHandler;
import com.petredy.redmagic.entities.particle.EntityCustomFX;
import com.petredy.redmagic.forge.helper.EventHelper;
import com.petredy.redmagic.handlers.InputHandler;
import com.petredy.redmagic.lib.Keys;
import com.petredy.redmagic.lib.Rendering;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;

public class ClientProxy extends CommonProxy{
	
	
	
	@Override
	public void registerKeyBinding() {
		Keys.extra = new KeyBinding(Keys.KEY_EXTRA_NAME, Keys.KEY_EXTRA_ID, "key.categories.gameplay");
		ClientRegistry.registerKeyBinding(Keys.extra);
		
		EventHelper.register(new InputHandler());
	}
	
	@Override
    public void initRendering() {

	}
	
	@Override
    public void registerRendering(){	
			
	}
	
	
	@Override
	public void registerSound(){
		MinecraftForge.EVENT_BUS.register(new SoundHandler());
	}
	
	@Override
	public void addEffect(String name, World world, double d1, double d2, double d3, Object...data) {
		EntityFX efx = null;
		
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
