package redmagic.blocks.multi.education;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import cpw.mods.fml.common.network.PacketDispatcher;

import redmagic.api.multiblock.IEducationEntity;
import redmagic.api.multiblock.IMultiBlock;
import redmagic.api.multiblock.IStructure;
import redmagic.configuration.Reference;
import redmagic.core.Logger;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.world.World;

public class EducationStructure implements IStructure{
	
	public List<IMultiBlock> blocks = new ArrayList<IMultiBlock>();
	public IMultiBlock dataBlock;
	
	public EducationStructure(){
		
	}
	
	public boolean buildFromBlock(World world, int x, int y, int z){
		
		this.lookAtBlock(world, x, y, z);
		int blocks = this.getBlocks().size();
		if(blocks == 8){
			this.dataBlock = this.getBlockAt(x, y, z);
			this.notifyAllBlocks(world, this);
			return true;
		}
		
		return false;
	}
	
	private void notifyAllBlocks(World world, IStructure structure) {
		Iterator<IMultiBlock> it = blocks.iterator();
		while(it.hasNext()){
			IMultiBlock block = it.next();
			this.notifyBlock(world, block, structure);
		}
	}

	private void notifyBlock(World world, IMultiBlock block, IStructure structure) {
		IEducationEntity entity = (IEducationEntity) block.getBasicEntity(world);
		if(entity != null){
			entity.setStructure(structure);
			
			NBTTagCompound tag = new NBTTagCompound();
			entity.writeToNBT(tag);
			PacketDispatcher.sendPacketToAllAround(block.getX(), block.getY(), block.getZ(), 40, world.getWorldInfo().getDimension(), new Packet132TileEntityData(block.getX(), block.getY(), block.getZ(), 0, tag));
		}
	}

	private void lookAtBlock(World world, int x, int y, int z){
		if(!this.containsBlockAt(x, y, z)){
			EducationBlock block = new EducationBlock(x, y, z);
			if(block.isMultiBlock(world) && !block.getBasicEntity(world).hasStructure()){
				blocks.add(block);
				this.lookAtBlocksAround(world, x, y, z);
			}
		}
	}
	
	private void lookAtBlocksAround(World world, int x, int y, int z){
		this.lookAtBlock(world, x + 1, y, z);
		this.lookAtBlock(world, x - 1, y, z);
		this.lookAtBlock(world, x, y + 1, z);
		this.lookAtBlock(world, x, y - 1, z);
		this.lookAtBlock(world, x, y, z + 1);
		this.lookAtBlock(world, x, y, z - 1);
		
	}

	@Override
	public boolean containsBlockAt(int x, int y, int z) {
		Iterator<IMultiBlock> it = blocks.iterator();
		while(it.hasNext()){
			IMultiBlock block = it.next();
			if(block.getX() == x && block.getY() == y && block.getZ() == z)return true;
		}
		return false;
	}
	
	@Override
	public List<IMultiBlock> getBlocks() {
		return blocks;
	}



	@Override
	public void createStructure(World world) {
		Iterator<IMultiBlock> it = blocks.iterator();
		while(it.hasNext()){
			IMultiBlock block = it.next();
			IEducationEntity entity = (IEducationEntity) block.getBasicEntity(world);
			entity.setStructure(this);
		}
		
	}

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		NBTTagCompound structure = (NBTTagCompound) tag.getTag(Reference.MOD_ID+ "_education_structure");
		Logger.log(structure);
		Logger.log(Reference.MOD_ID+ "_education_structure");
		if(structure != null){
			NBTTagList list = structure.getTagList("blocks");
			
			for(int i = 0; i < list.tagCount(); i++){
				NBTTagCompound block = (NBTTagCompound) list.tagAt(i);
				if(block != null){
					blocks.add(EducationBlock.loadFromNBT(block));
				}
			}
			this.dataBlock = EducationBlock.loadFromNBT((NBTTagCompound) structure.getTag("dataBlock"));
		}
	}

	public static IStructure loadFromNBT(NBTTagCompound tag) {
		EducationStructure structure = new EducationStructure();
		structure.readFromNBT(tag);
		return structure.blocks.size() > 0 ? structure : null;
	}

	@Override
	public void writeToNBT(NBTTagCompound tag) {
		
		NBTTagCompound structure = new NBTTagCompound();
		NBTTagList blockList = new NBTTagList();
		
		Iterator<IMultiBlock> it = blocks.iterator();
		while(it.hasNext()){
			IMultiBlock block = it.next();
			NBTTagCompound blockTag = new NBTTagCompound();
			block.writeToNBT(blockTag);
			blockList.appendTag(blockTag);
		}
		
		structure.setTag("blocks", blockList);
		
		NBTTagCompound dataBlock = new NBTTagCompound();
		this.dataBlock.writeToNBT(dataBlock);
		structure.setTag("dataBlock", dataBlock);
		
		tag.setTag(Reference.MOD_ID + "_education_structure", structure);
	}

	@Override
	public void destroy(World world) {
		this.notifyAllBlocks(world, null);
	}

	@Override
	public IMultiBlock getDataBlock() {
		return this.dataBlock;
	}

	@Override
	public IMultiBlock getBlockAt(int x, int y, int z) {
		Iterator<IMultiBlock> it = blocks.iterator();
		while(it.hasNext()){
			IMultiBlock block = it.next();
			if(block.getX() == x && block.getY() == y && block.getZ() == z)return block;
		}
		return null;
 	}

	
}
