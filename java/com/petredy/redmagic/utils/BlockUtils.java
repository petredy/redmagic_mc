package com.petredy.redmagic.utils;

import java.util.List;
import java.util.Random;

import com.petredy.redmagic.forge.helper.BlockHelper;
import com.petredy.redmagic.forge.helper.WorldHelper;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockUtils {
	public static void breakBlock(World world, int x, int y, int z, int forcedLifespan, boolean drop) {
		Block block = BlockHelper.getBlock(world, x, y, z);
		
		if (!block.isAir(world, x, y, z) && drop && !world.isRemote && world.getGameRules().getGameRuleBooleanValue("doTileDrops")) {
			List<ItemStack> items = block.getDrops(world, x, y, z, world.getBlockMetadata(x, y, z), 0);

			for (ItemStack item : items) {
				float var = 0.7F;
				double dx = world.rand.nextFloat() * var + (1.0F - var) * 0.5D;
				double dy = world.rand.nextFloat() * var + (1.0F - var) * 0.5D;
				double dz = world.rand.nextFloat() * var + (1.0F - var) * 0.5D;
				EntityItem entityitem = new EntityItem(world, x + dx, y + dy, z + dz, item);

				entityitem.lifespan = forcedLifespan;
				entityitem.field_145804_b = 10;

				world.spawnEntityInWorld(entityitem);
			}
		}

		BlockHelper.setBlockToAir(world, x, y, z);
	}
	
	
	public static void dropItems(World world, int x, int y, int z){
		TileEntity entity = WorldHelper.getBlockTileEntity(world, x, y, z);
		if(entity instanceof IInventory){
			IInventory inv = (IInventory)entity;
			Random rand = new Random();
			for(int i = 0; i < inv.getSizeInventory(); i++){
				ItemStack stack = inv.getStackInSlot(i);
				if(stack != null){
					double xRand = rand.nextDouble();
					double yRand = rand.nextDouble();
					double zRand = rand.nextDouble();
					
					EntityItem item = new EntityItem(world, x + xRand, y + yRand, z + zRand, stack);
					world.spawnEntityInWorld(item);
				}
			}
		}
	}
	
	
	public static ForgeDirection getRotation(World par0World, int par1, int par2, int par3, EntityLivingBase par5EntityLivingBase, boolean upAndDown){
		double d0 = par5EntityLivingBase.posY + 1.82D - (double)par5EntityLivingBase.yOffset; 
		
		if (upAndDown && d0 - (double)par2 > 2.0D)
		{
			return ForgeDirection.UP;
		}
		
		if (upAndDown && (double)par2 - d0 > 0.0D)
		{
		    return ForgeDirection.DOWN;
		}
		
		int l = MathHelper.floor_double((double)(par5EntityLivingBase.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
		return l == 0 ? ForgeDirection.NORTH : (l == 1 ? ForgeDirection.EAST : (l == 2 ? ForgeDirection.SOUTH : (l == 3 ? ForgeDirection.WEST : ForgeDirection.DOWN)));
	}
	
	public static int getHitIndex4x4(float x, float z){
		int count = 0;
		for(int j = 1; j <= 4; j++){
			for(int i = 1; i <= 4; i++){
				if(x < i * 0.25f && x > i * 0.25f - 0.25f && z > j * 0.25f - 0.25f && z < j * 0.25f){
					return count; 
				}
				count++;
			}
		}
		return -1;
	}
	
	public static int getHitIndex3x3(float x, float z){
		int count = 0;
		for(int j = 1; j <= 3; j++){
			for(int i = 1; i <= 3; i++){
				if(x < i * 0.33f && x > i * 0.33f - 0.33f && z > j * 0.33f - 0.33f && z < j * 0.33f){
					return count; 
				}
				count++;
			}
		}
		return -1;

	}
	
	
	public static class VirtualBlock{
		
		public int x, y, z;
		
		public VirtualBlock(){}
		
		public VirtualBlock(int x, int y, int z){
			this.x = x;
			this.y = y;
			this.z = z;
		}
		
		public void readFromNBT(NBTTagCompound tag){
			this.x = tag.getInteger("redmagic.BlockUtils.VirtualBlock.x");
			this.y = tag.getInteger("redmagic.BlockUtils.VirtualBlock.y");
			this.z = tag.getInteger("redmagic.BlockUtils.VirtualBlock.z");
		}
		
		public static VirtualBlock loadFromNBT(NBTTagCompound tag){
			VirtualBlock block = new VirtualBlock();
			block.readFromNBT(tag);
			return block;
		}
		
		public void writeToNBT(NBTTagCompound tag){
			tag.setInteger("redmagic.BlockUtils.VirtualBlock.x", x);
			tag.setInteger("redmagic.BlockUtils.VirtualBlock.y", y);
			tag.setInteger("redmagic.BlockUtils.VirtualBlock.z", z);
		}
	}
}
