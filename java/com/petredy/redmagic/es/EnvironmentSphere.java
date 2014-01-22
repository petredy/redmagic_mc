package com.petredy.redmagic.es;

import com.petredy.redmagic.utils.BlockUtils;
import com.petredy.redmagic.utils.LogUtils;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EnvironmentSphere {

	public int x, y, z;
	public int lengthR, lengthL, lengthF, lengthB;
	public int height;
	
	public EnvironmentSphere(int x, int y, int z){
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public boolean search(World world){
		
		ItemStack sphereStack = this.getSphereStack(world);
		if(sphereStack != null){
			lengthR = this.getLength(world, 1, 0, 0, sphereStack);
			lengthL = this.getLength(world, -1, 0, 0, sphereStack);
			lengthF = this.getLength(world, 0, 0, 1, sphereStack);
			lengthB = this.getLength(world, 0, 0, -1, sphereStack);

			int heightR = this.getHeight(world, x + lengthR, y, z, sphereStack);
			int heightL = this.getHeight(world, x - lengthL, y, z, sphereStack);
			int heightF = this.getHeight(world, x, y, z + lengthF, sphereStack);
			int heightB = this.getHeight(world, x, y, z - lengthB, sphereStack);
			if(heightR == heightL && heightR == heightF && heightR == heightB){
				height = heightR;
			}else{
				height = heightR;
				if(heightL < heightR)height = heightL;
				if(heightF < heightL)height = heightF;
				if(heightB < heightF)height = heightB;
			}
			if(lengthR > 0 && lengthL > 0 && lengthF > 0 && lengthB > 0 && height > 0)return true;
		}
		return false;
	}
	
	public Environment getEnvironment(World world, float max){
		Environment environment = new Environment(Math.max(0, lengthL + lengthR - 1), Math.max(0, lengthF + lengthB - 1), Math.max(0, height - 1));
		environment.scan(world, x - lengthL + 1, y + 1, z - lengthB + 1, max);
		return environment;
	}
	
	public void deleteContent(World world){
		for(int y = this.y + 1; y < this.y + height; y++){
			for(int x = this.x - lengthL + 1; x < this.x + lengthR; x++){
				for(int z = this.z - lengthB + 1; z < this.z + lengthF; z++){
					this.destroy(world, x, y, z);
				}
			}
		}
	}
	
	public void setEnvironment(World world, Environment environment){
		environment.place(world, x - lengthL, y, z - lengthB);
	}

	private void destroy(World world, int x, int y, int z) {
		int id = world.getBlockId(x, y, z);
		if(id != Block.bedrock.blockID){
			if(world.getBlockTileEntity(x, y, z) != null)world.setBlockTileEntity(x, y, z, Block.blocksList[id].createTileEntity(world, world.getBlockMetadata(x, y, z)));
			world.setBlockToAir(x, y, z);
		}
	}
	
	public void place(World world, Environment env) {
		if(env.valide){
			if(matches(env)){
				env.place(world, x - lengthL + 1, y + 1, z - lengthB + 1);
			}
		}
	}
	
	public boolean matches(Environment env){
		return env.width == lengthL + lengthR - 1 && env.breadth == lengthB + lengthF - 1 && env.height == height - 1;
	}

	private int getHeight(World world, int x, int yStart, int z, ItemStack search) {
		int count = 1;
		for(int y = yStart + 1; y < 256; y++){
			ItemStack block = BlockUtils.getBlockStack(world, x, y, z);
			if(block != null && block.isItemEqual(search)){
				count++;
			}else{
				break;
			}
		}
		return count;
	}

	private int getLength(World world, int xOff, int yOff, int zOff, ItemStack search) {
		ItemStack block = null;
		int count = 1;
		do{
			block = BlockUtils.getBlockStack(world, x + (xOff * count), y + (yOff * count), z + (zOff * count));
			if(block != null && block.isItemEqual(search))count++;
			else block = null;
		}while(block != null);
		return count - 1;
	}

	public ItemStack getSphereStack(World world) {
		ItemStack right = BlockUtils.getBlockStack(world, x + 1, y, z);
		ItemStack left = BlockUtils.getBlockStack(world, x - 1, y, z);
		ItemStack front = BlockUtils.getBlockStack(world, x, y, z + 1);
		ItemStack back = BlockUtils.getBlockStack(world, x, y, z - 1);
		if(right != null && left != null && front != null && back != null && right.isItemEqual(left) && right.isItemEqual(front) && right.isItemEqual(back))return right.copy();
		return null;
	}
	
}
