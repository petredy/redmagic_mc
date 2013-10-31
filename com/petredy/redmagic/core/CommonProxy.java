package com.petredy.redmagic.core;

import com.google.common.base.Throwables;
import com.petredy.redmagic.api.machinery.IMachineHandler;
import com.petredy.redmagic.client.guis.*;
import com.petredy.redmagic.container.*;
import com.petredy.redmagic.lib.Guis;
import com.petredy.redmagic.network.PacketTradingSync;
import com.petredy.redmagic.tileentities.*;
import com.petredy.redmagic.trading.TradingManager;
import com.petredy.redmagic.utils.LogUtils;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
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
		if(ID == Guis.TRADING_CHEST){
			return new ContainerTradingChest(player,(TileEntityTradingChest)tileEntity);
		}
		if(ID == Guis.PLAYER_INVENTORY){
			return new ContainerPlayerInventory(player);
		}
		if(ID == Guis.MACHINE){
			return new ContainerMachine(player, (IMachineHandler)tileEntity);
		}
		if(ID == Guis.FURNACE){
			return new ContainerFurnace(player, (IMachineHandler)tileEntity);
		}
		if(ID == Guis.DISINTEGRATOR){
			return new ContainerDisintegrator(player, (IMachineHandler)tileEntity);
		}
		if(ID == Guis.FREEZER){
			return new ContainerFreezer(player, (IMachineHandler)tileEntity);
		}
		if(ID == Guis.COMPACTOR){
			return new ContainerCompactor(player, (IMachineHandler)tileEntity);
		}
		if(ID == Guis.REDMETER){
			return new ContainerRedmeter(player);
		}
		if(ID == Guis.SIEVE){
			return new ContainerSieve(player, (IMachineHandler)tileEntity);
		}
		if(ID == Guis.CRYSTALIZER){
			return new ContainerCrystalizer(player, (IMachineHandler)tileEntity);
		}
		if(ID == Guis.ES_LOADER){
			return new ContainerESLoader(player, (TileEntityESLoader)tileEntity);
		}
		if(ID == Guis.TRIBOLOGICAL){
			return new ContainerTribological(player, (IMachineHandler)tileEntity);
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
		if(ID == Guis.TRADING_CHEST){
			return new GuiTradingSystem(player, (TileEntityTradingChest)tileEntity);
		}
		if(ID == Guis.PLAYER_INVENTORY){
			return new GuiPlayerInventory(player);
		}
		if(ID == Guis.MACHINE){
			return new GuiMachine(player, (IMachineHandler)tileEntity);
		}
		if(ID == Guis.FURNACE){
			return new GuiFurnace(player, (IMachineHandler)tileEntity);
		}
		if(ID == Guis.DISINTEGRATOR){
			return new GuiDisintegrator(player, (IMachineHandler)tileEntity);
		}
		if(ID == Guis.FREEZER){
			return new GuiFreezer(player, (IMachineHandler)tileEntity);
		}
		if(ID == Guis.COMPACTOR){
			return new GuiCompactor(player, (IMachineHandler)tileEntity);
		}
		if(ID == Guis.REDMETER){
			return new GuiRedmeter(player);
		}
		if(ID == Guis.SIEVE){
			return new GuiSieve(player, (IMachineHandler)tileEntity);
		}
		if(ID == Guis.CRYSTALIZER){
			return new GuiCrystalizer(player, (IMachineHandler)tileEntity);
		}
		if(ID == Guis.ES_LOADER){
			return new GuiESLoader(player, (TileEntityESLoader)tileEntity);
		}
		if(ID == Guis.TRIBOLOGICAL){
			return new GuiTribological(player, (IMachineHandler)tileEntity);
		}
		return null;
	}
	
	public World getClientWorld(){
		return null;
	}

	public void addEffect(String s, World world, double d1, double d2, double d3, Object...data) {
		
	}

	public void addEffect(String name, int type, World world, double d1, double d2, double d3, double d4, double d5, double d6) {
		
	}
	
	public void bindTexture(ResourceLocation resourceLocation){
		
	}


}
