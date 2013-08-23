package redmagic.core;

import com.google.common.base.Throwables;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import redmagic.Redmagic;
import redmagic.configuration.BlockIndex;
import redmagic.configuration.GuiIndex;
import redmagic.configuration.Reference;
import redmagic.configuration.Texture;
import redmagic.client.guis.*;
import redmagic.containers.*;
import redmagic.network.PacketHandler;
import redmagic.tileentities.*;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.server.FMLServerHandler;

public class CommonProxy implements IGuiHandler{
	
	public void registerKeyBinding(){};
	public void registerRendering(){};
	public void registerSound(){};
	public void setKeyBinding(String name, int value){};
    public void initRendering() {}
	
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity tileEntity = world.getBlockTileEntity(x, y, z);

		return null;
	}
	
	public World getClientWorld(){
		return null;
	}
	
	public World getServerOverworld(){
		return FMLServerHandler.instance().getServer().worldServerForDimension(0);
	}

	public void addEffect(String s, World world, double d1, double d2, double d3, Object...data) {
		
	}

	public void addEffect(String name, int type, World world, double d1, double d2, double d3, double d4, double d5, double d6) {
		
	}
	
	public void bindTexture(String path){
		
	}


}
