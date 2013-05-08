package redmagic.blocks.multi.extractor;

import redmagic.api.multiblock.IMultiBlock;
import redmagic.api.multiblock.IMultiEntity;
import redmagic.configuration.BlockIndex;
import redmagic.tileentities.education.TileEntityExtractorBasic;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class ExtractorBlock implements IMultiBlock{
	
	public int x, y, z;
	public int type;
	
	public ExtractorBlock(){
		
	}
	
	public ExtractorBlock(int x, int y, int z){
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	@Override
	public boolean isMultiBlock(World world){
		TileEntity entity = world.getBlockTileEntity(x, y, z);
		if(entity != null && entity instanceof IMultiEntity)return true;
		return false;
	}
	
	public static boolean isMultiBlock(World world, int x, int y, int z){
		TileEntity entity = world.getBlockTileEntity(x, y, z);
		if(entity != null && entity instanceof IMultiEntity)return true;
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
	public int getType() {
		return type;
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
		this.type = tag.getInteger("type");
	}

	public static IMultiBlock loadFromNBT(NBTTagCompound tag) {
		ExtractorBlock block = new ExtractorBlock();
		block.readFromNBT(tag);
		return block;
	}

	@Override
	public void writeToNBT(NBTTagCompound tag) {
		tag.setInteger("x", x);
		tag.setInteger("y", y);
		tag.setInteger("z", z);
		tag.setInteger("type", type);
	}

	public void setType(World world) {
		if(world.getBlockTileEntity(x, y, z) instanceof TileEntityExtractorBasic){
			type = BlockIndex.EXTRACTOR_BASIC_METADATA;
		}else{
			type = BlockIndex.EXTRACTOR_COLLECTOR_METADATA;
		}
	}
}
