package redmagic.client;

import redmagic.client.renderers.*;
import redmagic.core.CommonProxy;
import redmagic.entities.particle.EntityCrackFX;
import redmagic.entities.particle.EntityWorkTableFX;
import redmagic.handlers.KeyBindingHandler;
import redmagic.helpers.KeyBindingHelper;
import redmagic.tileentities.TileEntityPipe;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.KeyBindingRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;

public class ClientProxy extends CommonProxy{
	
	
	@Override
	public void registerAll(){
		//this.registerRendering();
		this.registerSound();
		this.registerKeyBinding();
	}
	
	@Override
	public void registerKeyBinding() {
		KeyBindingRegistry.registerKeyBinding(new KeyBindingHandler());
	}
	
	@Override
    public void setKeyBinding(String name, int value) {
        KeyBindingHelper.addKeyBinding(name, value);
        KeyBindingHelper.addIsRepeating(false);
    }
	
	@Override
    public void registerRendering(){
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityPipe.class, new RenderPipe());
	}
	
	
	@Override
	public void registerSound(){
		MinecraftForge.EVENT_BUS.register(new SoundHandler());
	}
	
	@Override
	public void addEffect(String name, World world, double d1, double d2, double d3, Object...data) {
		EntityFX efx = null;

		if(name == "crack")
		{
			efx = new EntityCrackFX(world, d1, d2, d3);
		}else if(name.equals("worktable")){
			efx = new EntityWorkTableFX(world, d1, d2, d3, (Integer)data[0], (ItemStack)data[1]);
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
	
}
