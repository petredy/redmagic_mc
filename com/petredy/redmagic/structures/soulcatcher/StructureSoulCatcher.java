package com.petredy.redmagic.structures.soulcatcher;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import buildcraft.api.power.PowerHandler;
import buildcraft.api.power.PowerHandler.PowerReceiver;

import com.petredy.redmagic.items.Items;
import com.petredy.redmagic.tileentities.TileEntitySoulCatcher;
import com.petredy.redmagic.utils.InventoryUtils;
import com.petredy.redmagic.utils.LogUtils;
import com.petredy.redmagic.utils.WorldSavedDataUtils;

import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

public class StructureSoulCatcher {

	public static final HashMap<Integer, Boolean> structures = new HashMap<Integer, Boolean>();
	
	
	public static final String TOKEN_PREFIX = "redmagic.soul.catcher.";
	public List<StructureSoulCatcherLayer> layers = new ArrayList<StructureSoulCatcherLayer>();
	public int id;
	public InventoryBasic inv = new InventoryBasic(TOKEN_PREFIX + "inventory", false, 9);
	public int neededTicks = 300;
	public int ticks = 0;
	
	public static StructureSoulCatcher find(TileEntity entity){
		if(StructureSoulCatcherBlock.isSoulCatcherBlock(entity)){
			TileEntity bot = entity.worldObj.getBlockTileEntity(entity.xCoord, entity.yCoord - 1, entity.zCoord);
			if(StructureSoulCatcherBlock.isSoulCatcherStructure(bot)){
				LogUtils.log(((TileEntitySoulCatcher)bot).id);
				return StructureSoulCatcher.loadFromNBT(WorldSavedDataUtils.loadData(entity.worldObj, TOKEN_PREFIX + ((TileEntitySoulCatcher)bot).id));
			}
			
			TileEntity top = entity.worldObj.getBlockTileEntity(entity.xCoord, entity.yCoord + 1, entity.zCoord);
			if(StructureSoulCatcherBlock.isSoulCatcherStructure(top)){
				return StructureSoulCatcher.loadFromNBT(WorldSavedDataUtils.loadData(entity.worldObj, TOKEN_PREFIX + ((TileEntitySoulCatcher)top).id));
			}
		}
		return null;
	}


	private static void notifyStructure(World worldObj, StructureSoulCatcher structure) {
		for(StructureSoulCatcherLayer layer: structure.layers){
			layer.notifyBlocks(worldObj, structure);
		}
	}
	
	public void readFromNBT(NBTTagCompound tag){
		NBTTagList list = tag.getTagList("layers");
		for(int i = 0; i < list.tagCount(); i++){
			NBTTagCompound layerTag = (NBTTagCompound) list.tagAt(i);
			layers.add(StructureSoulCatcherLayer.loadFromNBT(layerTag));
		}
		this.id = tag.getInteger("id");
		NBTTagCompound invTag = (NBTTagCompound) tag.getTag("inv");
		if(invTag != null)InventoryUtils.readFromNBT(inv, invTag);
		StructureSoulCatcher.structures.put(id, true);
		this.ticks = tag.getInteger("ticks");
	}
	
	public static StructureSoulCatcher loadFromNBT(NBTTagCompound tag){
		StructureSoulCatcher structure = new StructureSoulCatcher();
		if(tag != null)structure.readFromNBT(tag);
		return structure;
	}
	
	public void writeToNBT(NBTTagCompound tag){
		NBTTagList list = new NBTTagList();
		for(StructureSoulCatcherLayer layer: layers){
			NBTTagCompound layerTag = new NBTTagCompound();
			layer.writeToNBT(layerTag);
			list.appendTag(layerTag);
		}
		tag.setTag("layers", list);
		tag.setInteger("id", id);
		NBTTagCompound invTag = new NBTTagCompound();
		InventoryUtils.writeToNBT(inv, invTag);
		tag.setTag("inv", invTag);
		tag.setInteger("ticks", ticks);
	}
	
	public void addLayer(World world, StructureSoulCatcherLayer layer) {
		this.layers.add(layer);
		this.notifyStructure(world, this);
		NBTTagCompound tag = new NBTTagCompound();
		this.writeToNBT(tag);
		WorldSavedDataUtils.saveData(world, TOKEN_PREFIX + id, tag);
	}


	public static void build(World worldObj, StructureSoulCatcherLayer layer) {
		StructureSoulCatcher structure = new StructureSoulCatcher();
		structure.layers.add(layer);
		structure.id = WorldSavedDataUtils.createUniqueToken(worldObj, TOKEN_PREFIX);
		StructureSoulCatcher.structures.put(structure.id, true);
		NBTTagCompound tag = new NBTTagCompound();
		structure.writeToNBT(tag);
		WorldSavedDataUtils.saveData(worldObj, TOKEN_PREFIX + structure.id, tag);
		StructureSoulCatcher.notifyStructure(worldObj, structure);
		
	}


	public void breakLayer(World worldObj, int yCoord) {
		for(StructureSoulCatcherLayer layer: layers){
			if(layer.y >= yCoord){
				layer.notifyBlocks(worldObj, null);
				layers.remove(layer);
			}
		}
		if(layers.size() == 0)structures.put(id, false);
	}
	
	public void update(World world){
		
		if(ticks < neededTicks && !world.isRemote){
			ticks++;
		}else if(!world.isRemote){
			ticks = 0;
			Random rand = new java.util.Random();
			if(rand.nextInt(1000 - ((int)Math.pow(this.layers.size(), 2) < 1000 ? (int) Math.pow(this.layers.size(), 2) : 999)) < 5)InventoryUtils.addItemStackToInventory(inv, new ItemStack(Items.soul));
		}
	}


}
