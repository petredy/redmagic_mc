package redmagic.blocks.multi.education;

import redmagic.api.multiblock.IEducationEntity;
import redmagic.api.multiblock.IMultiBlock;
import redmagic.api.multiblock.IMultiEntity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class EducationBlock implements IMultiBlock{
	
	public int x, y, z;
	
	public EducationBlock(){
		
	}
	
	public EducationBlock(int x, int y, int z){
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	@Override
	public boolean isMultiBlock(World world){
		TileEntity entity = world.getBlockTileEntity(x, y, z);
		if(entity != null && entity instanceof IEducationEntity)return true;
		return false;
	}
	
	public static boolean isMultiBlock(World world, int x, int y, int z){
		TileEntity entity = world.getBlockTileEntity(x, y, z);
		if(entity != null && entity instanceof IEducationEntity)return true;
		return false;
	}


	@Override
	public int getX() {
		return x;
	}

	@Override
	public int getY() {
		return y;
	}

	@Override
	public int getZ() {
		return z;
	}

	@Override
	public IMultiEntity getBasicEntity(World world) {
		return (IMultiEntity) world.getBlockTileEntity(x, y, z);
	}

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		
		this.x = tag.getInteger("x");
		this.y = tag.getInteger("y");
		this.z = tag.getInteger("z");
	}

	public static IMultiBlock loadFromNBT(NBTTagCompound tag) {
		EducationBlock block = new EducationBlock();
		block.readFromNBT(tag);
		return block;
	}

	@Override
	public void writeToNBT(NBTTagCompound tag) {
		tag.setInteger("x", x);
		tag.setInteger("y", y);
		tag.setInteger("z", z);
		
	}
}
