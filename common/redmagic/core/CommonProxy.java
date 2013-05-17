package redmagic.core;

import com.google.common.base.Throwables;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.server.gui.PlayerListBox;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import redmagic.configuration.BlockIndex;
import redmagic.configuration.GuiIndex;
import redmagic.configuration.Texture;
import redmagic.client.guis.*;
import redmagic.containers.*;
import redmagic.tileentities.*;
import redmagic.tileentities.bank.TileEntityBank;
import redmagic.tileentities.tree.TileEntityTreeWood;
import cpw.mods.fml.common.network.IGuiHandler;
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
	
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
		if(ID == GuiIndex.WORK_TABLE){
			return new ContainerWorkTable(player, (TileEntityWorkTable)tileEntity);
		}
		if(ID == GuiIndex.EDUCATION_BASIC){
			return new ContainerEducationBasic(player, tileEntity);
		}
		if(ID == GuiIndex.COLLECTOR){
			return new ContainerOneSlot(player, (IInventory) tileEntity);
		}
		if(ID == GuiIndex.BANK){
			return new ContainerBank(player, (TileEntityBank)tileEntity);
		}
		if(ID == GuiIndex.TREE){
			if(((TileEntityTreeWood)tileEntity).hasSoul())
			return new ContainerTree(player, (TileEntityTreeWood)tileEntity);
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
		if(ID == GuiIndex.WORK_TABLE){
			return new GuiWorkTable(player, (TileEntityWorkTable)tileEntity);
		}
		if(ID == GuiIndex.EDUCATION_BASIC){
			return new GuiEducationBasic(player, tileEntity);
		}
		if(ID == GuiIndex.COLLECTOR){
			return new GuiOneSlot(player, (IInventory) tileEntity);
		}
		if(ID == GuiIndex.BANK){
			return new GuiBank(player, (TileEntityBank)tileEntity);
		}
		if(ID == GuiIndex.TREE){
			if(((TileEntityTreeWood)tileEntity).hasSoul())
			return new GuiTree(player, (TileEntityTreeWood)tileEntity);
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
