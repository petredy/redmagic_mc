package com.petredy.redmagic.core;

import com.google.common.base.Throwables;
import com.petredy.redmagic.client.guis.*;
import com.petredy.redmagic.container.*;
import com.petredy.redmagic.lib.Guis;
import com.petredy.redmagic.network.PacketTradingSync;
import com.petredy.redmagic.tileentities.*;
import com.petredy.redmagic.trading.TradingManager;

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
		if(ID == Guis.WORK_TABLE){
			return new ContainerWorkTable(player, (TileEntityWorkTable)tileEntity);
		}
		if(ID == Guis.PLAYER_INVENTORY){
			return new ContainerPlayerInventory(player);
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
		if(ID == Guis.TRADING_CHEST){
			return new GuiTradingSystem(player, (TileEntityTradingChest)tileEntity);
		}
		if(ID == Guis.WORK_TABLE){
			return new GuiWorkTable(player, (TileEntityWorkTable)tileEntity);
		}
		if(ID == Guis.PLAYER_INVENTORY){
			return new GuiPlayerInventory(player);
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
