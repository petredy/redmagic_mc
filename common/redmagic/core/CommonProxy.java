package redmagic.core;

import com.google.common.base.Throwables;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.server.gui.PlayerListBox;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import redmagic.Redmagic;
import redmagic.configuration.BlockIndex;
import redmagic.configuration.GuiIndex;
import redmagic.configuration.Texture;
import redmagic.client.guis.*;
import redmagic.containers.*;
import redmagic.handlers.WorldLoadingHandler;
import redmagic.lib.bag.BagHelper;
import redmagic.network.PacketBankSync;
import redmagic.network.PacketHandler;
import redmagic.tileentities.*;
import redmagic.tileentities.bank.TileEntityBank;
import redmagic.tileentities.tree.TileEntityTreeWood;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.server.FMLServerHandler;

public class CommonProxy implements IGuiHandler{
	
	public void registerAll(){
		this.registerRendering();
		this.registerSound();
		this.registerTextures();
		this.registerKeyBinding();
	}
	
	public void registerKeyBinding(){};
	public void registerRendering(){};
	public void registerSound(){};
	public void setKeyBinding(String name, int value){};
    public void initRendering() {}
	
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
		if(ID == GuiIndex.WORK_TABLE){
			return new ContainerWorkTable(player, (TileEntityWorkTable)tileEntity);
		}
		if(ID == GuiIndex.BANK){
			PacketDispatcher.sendPacketToPlayer(PacketHandler.populatePacket(new PacketBankSync(Redmagic.bankData)), (Player) player);
			return new ContainerBank(player, (TileEntityBank)tileEntity);
		}
		if(ID == GuiIndex.TREE){
			if(((TileEntityTreeWood)tileEntity).hasSoul())
			return new ContainerTree(player, (TileEntityTreeWood)tileEntity);
		}
		if(ID == GuiIndex.SOUL_FORGE){
			return new ContainerSoulForge(player, (TileEntitySoulForge)tileEntity);
		}
		if(ID == GuiIndex.BAG){
			return new ContainerBag(player, (TileEntityBag) BagHelper.createTileByData(BagHelper.getData(world, x), world), x);
		}
		if(ID == GuiIndex.SCROLL){
			return new ContainerScroll(x);
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
		if(ID == GuiIndex.WORK_TABLE){
			return new GuiWorkTable(player, (TileEntityWorkTable)tileEntity);
		}
		if(ID == GuiIndex.BANK){
			return new GuiBank(player, (TileEntityBank)tileEntity);
		}
		if(ID == GuiIndex.TREE){
			if(((TileEntityTreeWood)tileEntity).hasSoul())
			return new GuiTree(player, (TileEntityTreeWood)tileEntity);
		}
		if(ID == GuiIndex.SOUL_FORGE){
			return new GuiSoulForge(player, (TileEntitySoulForge)tileEntity);
		}
		if(ID == GuiIndex.BAG){
			return new GuiBag(player, (TileEntityBag) BagHelper.createTileByData(BagHelper.getData(world, x), world), x);
		}
		if(ID == GuiIndex.SCROLL){
			return new GuiScroll(player, x, y);
		}
		return null;
	}
	
	public World getClientWorld(){
		return null;
	}
	
	public World getServerOverworld(){
		return FMLServerHandler.instance().getServer().worldServerForDimension(0);
	}

	public void registerTextures() {
		
	}

	public void addEffect(String s, World world, double d1, double d2, double d3, Object...data) {
		
	}

	public void addEffect(String name, int type, World world, double d1, double d2, double d3, double d4, double d5, double d6) {
		
	}


}
