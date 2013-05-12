package redmagic.blocks.multi.tree;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import redmagic.api.multiblock.IMultiBlock;
import redmagic.api.multiblock.IMultiEntity;
import redmagic.blocks.BlockManager;

public class TreeBlock implements IMultiBlock{

	public int x, y, z;
	public int type;
	
	public TreeBlock(){}
	
	public TreeBlock(int x, int y, int z){
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	@Override
	public boolean isMultiBlock(World world) {
		int id = world.getBlockId(x, y, z);
		return id == BlockManager.wood.blockID || id == BlockManager.leaves.blockID;
	}

	@Override
	public IMultiEntity getBasicEntity(World world) {
		return (IMultiEntity) world.getBlockTileEntity(x, y, z);
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
	public int getType() {
		return type;
	}

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		this.x = tag.getInteger("x");
		this.y = tag.getInteger("y");
		this.z = tag.getInteger("z");
		this.type = tag.getInteger("type");
	}

	@Override
	public void writeToNBT(NBTTagCompound tag) {
		tag.setInteger("x", this.x);
		tag.setInteger("y", this.y);
		tag.setInteger("z", this.z);
		tag.setInteger("type", this.type);
	}

	public static IMultiBlock loadFromNBT(NBTTagCompound tag) {
		TreeBlock block = new TreeBlock();
		block.readFromNBT(tag);
		return block;
	}

}
