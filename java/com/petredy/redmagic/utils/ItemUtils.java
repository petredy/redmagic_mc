package com.petredy.redmagic.utils;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class ItemUtils {
	public static ItemStack consumeItem(ItemStack stack) {
		if (stack.stackSize == 1) {
			if (stack.getItem().hasContainerItem())
				//return stack.getItem().getContainerItemStack(stack);
				return null;
			else
				return null;
		} else {
			stack.splitStack(1);

			return stack;
		}
	}
	
	public static MovingObjectPosition getMovingObjectPosition(World par1World, EntityPlayer par2EntityPlayer, boolean par3){
		float f = 1.0F;
        float f1 = par2EntityPlayer.prevRotationPitch + (par2EntityPlayer.rotationPitch - par2EntityPlayer.prevRotationPitch) * f;
        float f2 = par2EntityPlayer.prevRotationYaw + (par2EntityPlayer.rotationYaw - par2EntityPlayer.prevRotationYaw) * f;
        double d0 = par2EntityPlayer.prevPosX + (par2EntityPlayer.posX - par2EntityPlayer.prevPosX) * (double)f;
        double d1 = par2EntityPlayer.prevPosY + (par2EntityPlayer.posY - par2EntityPlayer.prevPosY) * (double)f + (double)(par1World.isRemote ? par2EntityPlayer.getEyeHeight() - par2EntityPlayer.getDefaultEyeHeight() : par2EntityPlayer.getEyeHeight()); // isRemote check to revert changes to ray trace position due to adding the eye height clientside and player yOffset differences
        double d2 = par2EntityPlayer.prevPosZ + (par2EntityPlayer.posZ - par2EntityPlayer.prevPosZ) * (double)f;
        Vec3 vec3 = par1World.getWorldVec3Pool().getVecFromPool(d0, d1, d2);
        float f3 = MathHelper.cos(-f2 * 0.017453292F - (float)Math.PI);
        float f4 = MathHelper.sin(-f2 * 0.017453292F - (float)Math.PI);
        float f5 = -MathHelper.cos(-f1 * 0.017453292F);
        float f6 = MathHelper.sin(-f1 * 0.017453292F);
        float f7 = f4 * f5;
        float f8 = f3 * f5;
        double d3 = 5.0D;
        if (par2EntityPlayer instanceof EntityPlayerMP)
        {
            d3 = ((EntityPlayerMP)par2EntityPlayer).theItemInWorldManager.getBlockReachDistance();
        }
        Vec3 vec31 = vec3.addVector((double)f7 * d3, (double)f6 * d3, (double)f8 * d3);
        return par1World.func_147447_a(vec3, vec31, par3, !par3, false);
	}
	
	public static void initNBT(ItemStack stack){
		if(stack.stackTagCompound == null)stack.stackTagCompound = new NBTTagCompound();
	}
	
	public static void setInteger(ItemStack stack, String key, int integer){
		initNBT(stack);
		stack.stackTagCompound.setInteger(key, integer);
	}
	
	public static int getInteger(ItemStack stack, String key){
		initNBT(stack);
		return stack.stackTagCompound.getInteger(key);
	}
	
	public static void setIntegerArray(ItemStack stack, String key, int[] data){
		initNBT(stack);
		stack.stackTagCompound.setIntArray(key, data);
	}
	
	public static int[] getIntegerArray(ItemStack stack, String key){
		initNBT(stack);
		return stack.stackTagCompound.getIntArray(key);
	}

	public static long getLong(ItemStack stack, String key) {
		initNBT(stack);
		return stack.stackTagCompound.getLong(key);
	}

	public static void setLong(ItemStack stack, String key, long value) {
		initNBT(stack);
		stack.stackTagCompound.setLong(key, value);
		
	}
	
	public static NBTTagCompound getData(ItemStack stack, String key) {
		initNBT(stack);
		return stack.stackTagCompound.getCompoundTag(key);
	}

	public static void setData(ItemStack stack, String key, NBTTagCompound tag) {
		initNBT(stack);
		stack.stackTagCompound.setTag(key, tag);
		
	}
	
	public static NBTTagList getDataList(ItemStack stack, String key) {
		initNBT(stack);
		return (NBTTagList) stack.stackTagCompound.getTag(key);
	}

	public static void setDataList(ItemStack stack, String key, NBTTagList tag) {
		initNBT(stack);
		stack.stackTagCompound.setTag(key, tag);
		
	}

	public static float getFloat(ItemStack stack, String key) {
		initNBT(stack);
		return stack.stackTagCompound.getFloat(key);
	}
	
	public static void setFloat(ItemStack stack, String key, float value){
		initNBT(stack);
		stack.stackTagCompound.setFloat(key, value);
	}

	public static void setString(ItemStack stack, String key, String value) {
		initNBT(stack);
		stack.stackTagCompound.setString(key, value);
	}
	
	public static String getString(ItemStack stack, String key){
		initNBT(stack);
		return stack.stackTagCompound.getString(key);
	}
	
	public static void setItemStackData(ItemStack stack, String key, ItemStack value){
		initNBT(stack);
		NBTTagCompound tag = new NBTTagCompound();
		value.writeToNBT(tag);
		stack.stackTagCompound.setTag(key, tag);
	}
	
	public static ItemStack getItemStackData(ItemStack stack, String key){
		initNBT(stack);
		NBTTagCompound tag = stack.stackTagCompound.getCompoundTag(key);
		if(tag != null)return ItemStack.loadItemStackFromNBT(tag);
		return null;
	}
}
