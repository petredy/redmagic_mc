package redmagic.client;

import redmagic.client.renderers.ItemBlockRenderer;
import redmagic.configuration.ItemIndex;
import redmagic.configuration.Reference;
import redmagic.configuration.RenderIndex;
import redmagic.core.CommonProxy;
import redmagic.entities.particle.EntityStarFX;
import redmagic.handlers.KeyBindingHandler;
import redmagic.helpers.KeyBindingHelper;
import redmagic.helpers.LogHelper;
import redmagic.items.ItemManager;
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
        KeyBindingHelper.addKeyBinding(name, value);
        KeyBindingHelper.addIsRepeating(false);
    }
	
	@Override
    public void initRendering() {
//		RenderIndex.BANK = RenderingRegistry.getNextAvailableRenderId();
//		RenderIndex.PIPE = RenderingRegistry.getNextAvailableRenderId();
	}
	
	@Override
    public void registerRendering(){
//		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityPipe.class, new RenderPipe());
//		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityBank.class, new RenderBank());
//		
//		RenderingRegistry.registerBlockHandler(new RenderPipe());
//		RenderingRegistry.registerBlockHandler(new RenderBank());
//		
		//RenderingRegistry.registerEntityRenderingHandler(EntityPlayer.class, new RenderSoulPlayer());
		MinecraftForgeClient.registerItemRenderer(ItemManager.artifact.itemID, new ItemBlockRenderer());
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
			//efx = new EntityCrackFX(world, d1, d2, d3);
		}else if(name.equals("worktable")){
			//efx = new EntityWorkTableFX(world, d1, d2, d3, (Integer)data[0], (ItemStack)data[1]);
		}else if(name.equals("star")){
			efx = new EntityStarFX(world, d1, d2, d3, (Double)data[0], (Double)data[1], (Double)data[2]);
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
	
	public void bindTexture(String path){
		Minecraft.getMinecraft().func_110434_K().func_110577_a(new ResourceLocation(Reference.MOD_ID, path));
	}
}
