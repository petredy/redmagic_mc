package com.petredy.redmagic.redhole;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

import com.petredy.redmagic.lib.Redholes;
import com.petredy.redmagic.lib.Sounds;
import com.petredy.redmagic.utils.ItemUtils;

public class HoleTransportable extends Hole{

	public HoleTransportable(){
		id = Redholes.TRANSPORTABLE_ID;
		name = Redholes.TRANSPORTABLE_NAME;
	}
	
	public ItemStack getSurroundingBlock(){
		return new ItemStack(Block.blockNetherQuartz);
	}
	
	public String getHoleColor(){
		return "1#1#1";
	}
	
	public void activate(ItemStack stack, World world, EntityPlayer player){
		MovingObjectPosition location = ItemUtils.getMovingObjectPosition(world, player, false);
		if(location != null){
			ItemStack block = ItemUtils.getItemStackData(stack, getItemToken());
			if(block == null){
				ItemStack save = new ItemStack(world.getBlockId(location.blockX, location.blockY, location.blockZ), 1, world.getBlockMetadata(location.blockX, location.blockY, location.blockZ));
				if(save != null && !save.isItemEqual(new ItemStack(Block.bedrock))){
					ItemUtils.setItemStackData(stack, getItemToken(), save);
					TileEntity entity = world.getBlockTileEntity(location.blockX, location.blockY, location.blockZ);
					if(entity != null){
						NBTTagCompound data = new NBTTagCompound();
						entity.writeToNBT(data);
						ItemUtils.setData(stack, getTileEntityToken(), data);
						world.setBlockTileEntity(location.blockX, location.blockY, location.blockZ,Block.blocksList[save.itemID].createTileEntity(world, world.getBlockMetadata(location.blockX, location.blockY, location.blockZ)));
					}
					world.destroyBlock(location.blockX, location.blockY, location.blockZ, false);
					player.swingItem();
				}
			}else if(block.itemID > 0 && world.getBlockId(location.blockX, location.blockY, location.blockZ) != Block.bedrock.blockID){
				world.setBlock(location.blockX, location.blockY, location.blockZ, block.itemID, block.getItemDamage(), 3);
				player.swingItem();
				TileEntity entity = world.getBlockTileEntity(location.blockX, location.blockY, location.blockZ);
				if(entity != null){
					NBTTagCompound data = ItemUtils.getData(stack, getTileEntityToken());
					if(data != null){
						entity.readFromNBT(data);
						entity.xCoord = location.blockX;
						entity.yCoord = location.blockY;
						entity.zCoord = location.blockZ;
					}
				}
				ItemUtils.setItemStackData(stack, getItemToken(), new ItemStack(0, 1, 0));
				ItemUtils.setData(stack, getTileEntityToken(), new NBTTagCompound());
			}
		}
	}
	
	public String getItemToken(){
		return "redmagic.redhole.transportable.item";
	}
	
	public String getTileEntityToken(){
		return "redmagic.redhole.transportable.entity";
	}
	
	public void addInformation(ItemStack redhole, EntityPlayer player, List list) {
		ItemStack save = ItemUtils.getItemStackData(redhole, getItemToken());
		if(save != null)list.add(save.getDisplayName());
	}
	
}
