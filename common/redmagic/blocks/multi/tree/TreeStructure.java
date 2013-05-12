package redmagic.blocks.multi.tree;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import cpw.mods.fml.common.network.PacketDispatcher;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.world.World;
import net.minecraftforge.liquids.LiquidTank;
import redmagic.api.multiblock.IMultiBlock;
import redmagic.api.multiblock.IMultiEntity;
import redmagic.api.multiblock.IStructure;
import redmagic.blocks.BlockManager;
import redmagic.blocks.multi.extractor.ExtractorBlock;
import redmagic.configuration.Reference;
import redmagic.core.Logger;
import redmagic.helpers.TreeHelper;
import redmagic.lib.tree.SoulStorage;
import redmagic.lib.tree.SoulTank;

public class TreeStructure implements IStructure{

	
	public List<IMultiBlock> blocks = new ArrayList<IMultiBlock>();
	public SoulStorage storage = new SoulStorage();
	public SoulTank tank = new SoulTank(0);
	public IMultiBlock dataBlock;
	public int metadata;
	public int id;
	
	public static int dataKey = 2;
	public static int woodKey = 1;
	public static int leaveKey = 0;
	
	public TreeStructure(){}
	public TreeStructure(int metadata){
		this.metadata = metadata;
	}
	
	public void addBlock(World world, int x, int y, int z, int type){
		if(!this.containsBlockAt(x, y, z)){
			TreeBlock block = new TreeBlock(x, y, z);
			block.type = type;
			blocks.add(block);
			TreeHelper.saveStructure(world, this.id, this);
			this.updateAllBlocks(world);
		}
	}
	
	public void removeBlock(World world, int x, int y, int z){
		TreeBlock block = (TreeBlock) this.getBlockAt(x, y, z);
		if(block != null){
			blocks.remove(block);
			TreeHelper.saveStructure(world, this.id, this);
			this.updateAllBlocks(world);
			if(blocks.size() <= 2){
				this.destroy(world);
			}
		}
	}
	
	private void updateAllBlocks(World world) {
		Iterator<IMultiBlock> it = blocks.iterator();
		while(it.hasNext()){
			IMultiBlock block = it.next();
			if(block != null)world.markBlockForUpdate(block.getX(), block.getY(), block.getZ());
		}
	}
	@Override
	public boolean buildFromBlock(World world, int x, int y, int z) {
		return false;
	}
	
	public void notifyAllBlocks(World world, Integer structure) {
		Iterator<IMultiBlock> it = blocks.iterator();
		while(it.hasNext()){
			IMultiBlock block = it.next();
			this.notifyBlock(world, block, structure);
		}
	}

	public void notifyBlock(World world, IMultiBlock block, Integer structure) {
		IMultiEntity entity = (IMultiEntity) block.getBasicEntity(world);
		if(entity != null){
			entity.setStructure(structure);
			entity.getWorld().markBlockForUpdate(block.getX(), block.getY(), block.getZ());
		}
	}

	@Override
	public void destroy(World world) {
		this.notifyAllBlocks(world, null);
	}

	@Override
	public List<IMultiBlock> getBlocks() {
		return blocks;
	}

	@Override
	public boolean containsBlockAt(int x, int y, int z) {
		return getBlockAt(x, y, z) != null;
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

	@Override
	public IMultiBlock getDataBlock() {
		return dataBlock;
	}

	@Override
	public List<IMultiBlock> getBlockType(int type) {
		List<IMultiBlock> list = new ArrayList<IMultiBlock>();
		Iterator<IMultiBlock> it = blocks.iterator();
		while(it.hasNext()){
			IMultiBlock block = it.next();
			if(block.getType() == type)list.add(block);
		}
		return list;
	}

	@Override
	public void createStructure(World world) {
		Iterator<IMultiBlock> it = blocks.iterator();
		while(it.hasNext()){
			IMultiBlock block = it.next();
			IMultiEntity entity = (IMultiEntity) block.getBasicEntity(world);
			entity.setStructure(this);
		}
	}

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		NBTTagCompound structure = (NBTTagCompound) tag.getTag(Reference.MOD_ID+ "_tree_structure");
		if(structure != null){
			NBTTagList list = structure.getTagList("blocks");
			
			for(int i = 0; i < list.tagCount(); i++){
				NBTTagCompound block = (NBTTagCompound) list.tagAt(i);
				if(block != null){
					blocks.add(TreeBlock.loadFromNBT(block));
				}
			}
			this.dataBlock = TreeBlock.loadFromNBT((NBTTagCompound) structure.getTag("dataBlock"));
		}
		this.storage = SoulStorage.loadFromNBT(structure);
		this.tank.readFromNBT(structure);
	}
	
	public static TreeStructure loadFromNBT(NBTTagCompound tag){
		TreeStructure structure = new TreeStructure();
		structure.readFromNBT(tag);
		return structure;
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
		if(this.dataBlock != null)this.dataBlock.writeToNBT(dataBlock);
		structure.setTag("dataBlock", dataBlock);
		this.storage.writeToNBT(structure);
		this.tank.writeToNBT(structure);
		tag.setTag(Reference.MOD_ID + "_tree_structure", structure);
		
		
	}
	
	
	

}
