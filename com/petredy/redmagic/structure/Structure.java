package com.petredy.redmagic.structure;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.petredy.redmagic.api.machines.IMachineHandler;
import com.petredy.redmagic.api.redenergy.IEnergyHandler;
import com.petredy.redmagic.blocks.Blocks;
import com.petredy.redmagic.machines.Machine;
import com.petredy.redmagic.network.PacketHandler;
import com.petredy.redmagic.network.PacketStructureSync;
import com.petredy.redmagic.redenergy.EnergyMap;
import com.petredy.redmagic.redenergy.RedEnergy;
import com.petredy.redmagic.tileentities.TileEntityStructure;
import com.petredy.redmagic.utils.BlockUtils.VirtualBlock;
import com.petredy.redmagic.utils.LogUtils;

import cpw.mods.fml.common.network.PacketDispatcher;


public class Structure implements IEnergyHandler, IMachineHandler{
	
	public VirtualBlock[] blocks = new VirtualBlock[27];
	public Machine[] machines = new Machine[6];
	public int id;
	
	public long updateTime;
	public float energy;
	public float heat;
	
	public int syncTick;
	
	
	public static int BOTTOM = 4;
	public static int TOP = 22;
	public static int BACK = 16;
	public static int FRONT = 10;
	public static int LEFT = 14;
	public static int RIGHT = 12;
	
	public static int MIDDLE = 13;
	
	public World world;
	
	public void requestUpdate(World world){
		update(world);
		
		if(syncTick < 0){
			syncTick = 0;
			NBTTagCompound tag = new NBTTagCompound();
			writeToNBT(tag);
			PacketDispatcher.sendPacketToAllInDimension(PacketHandler.populatePacket(new PacketStructureSync(tag)), world.provider.dimensionId);
		}
		syncTick++;
	}
	
	public void setWorld(World world){
		this.world = world;
	}
	
	public void update(World world){
		for(Machine machine: machines){
			if(machine != null){
				machine.update((IMachineHandler)this);
			}
		}
	}
	
	public void build(World world, int xCoord, int yCoord, int zCoord){
		if(this.isCenter(world, xCoord, yCoord, zCoord)){
			this.scan(world, xCoord, yCoord, zCoord);
			return;
		}
		int y = this.getLayer(world, xCoord, yCoord, zCoord);
		if(y == -1 || y == 0 || y == 1){
			if(this.isMiddleBlock(world, xCoord, yCoord, zCoord)){
				this.scan(world, xCoord, yCoord -y, zCoord);
				return;
			}
			if(this.scanCorners(world, xCoord, yCoord, zCoord, y, getCornerDirection(world, xCoord, yCoord, zCoord)))return;
			if(this.scanLines(world, xCoord, yCoord, zCoord, y, getLineDirection(world, xCoord, yCoord, zCoord)))return;
		}
	}
	
	private boolean scanLines(World world, int xCoord, int yCoord, int zCoord, int y, Integer orientation){
		if(orientation != null){
			switch(orientation){
			case 0:
				scan(world, xCoord, yCoord - y, zCoord + 1);
				return true;
			case 1:
				scan(world, xCoord + 1, yCoord - y, zCoord);
				return true;
			case 2:
				scan(world, xCoord - 1, yCoord - y, zCoord);
				return true;
			case 3:
				scan(world, xCoord, yCoord - y, zCoord - 1);
				return true;
			}
		}
		return false;
	}
	
	private boolean scanCorners(World world, int xCoord, int yCoord, int zCoord, int y, Integer direction){
		if(direction != null){
			switch(direction){
			case 0:
				this.scan(world, xCoord + 1, yCoord - y, zCoord + 1);
				return true;
			case 1:
				this.scan(world, xCoord - 1, yCoord - y, zCoord + 1);
				return true;
			case 2:
				this.scan(world, xCoord + 1, yCoord - y, zCoord - 1);
				return true;
			case 3:
				this.scan(world, xCoord - 1, yCoord - y, zCoord - 1);
				return true;
			}
		}
		return false;
	}
	
	
	/**
	 * Returns the orientation of a line
	 * @param x
	 * @param y
	 * @param z
	 * @return
	 * -------------------
	 * |||| x | 0 | x ||||
	 * -------------------
	 * |||| 1 | x | 2 ||||
	 * -------------------
	 * |||| x | 3 | x ||||
	 * -------------------
	 * 
	 */
	private Integer getLineDirection(World world, int x, int y, int z) {
		if(isLine(world, x, y, z, 1, 0) && isStructureBlock(world, x, y, z + 1))return 0;
		if(isLine(world, x, y, z, 0, 1) && isStructureBlock(world, x - 1, y, z))return 2;
		if(isLine(world, x, y, z, 0, 1) && isStructureBlock(world, x + 1, y, z))return 1;
		if(isLine(world, x, y, z, 1, 0) && isStructureBlock(world, x, y, z - 1))return 3;
		return null;
	}
	
	private boolean isLine(World world, int x, int y, int z, int xOff, int zOff){
		if(isStructureBlock(world, x + xOff, y, z) && isStructureBlock(world, x - xOff, y, z))return true;
		if(isStructureBlock(world, x, y, z + zOff) && isStructureBlock(world, x, y, z - zOff))return true;
		return false;
	}
	
	/**
	 * Returns the orientation of the corner
	 * 
	 * @param x
	 * @param y
	 * @param z
	 * @return orientation:
	 *  --------------------
	 *  |||| 0 | x | 1 |||||
	 *  --------------------
	 *  |||| x | x | y |||||
	 *  --------------------
	 *  |||| 2 | x | 3 ||||
	 *  --------------------
		 */
	private Integer getCornerDirection(World world, int x, int y, int z) {
		if(isStructureBlock(world, x - 1, y, z) && isStructureBlock(world, x, y, z - 1)){
			return 3;
		}
		if(isStructureBlock(world, x + 1, y, z) && isStructureBlock(world, x, y, z - 1)){
			return 2;
		}
		if(isStructureBlock(world, x - 1, y, z) && isStructureBlock(world, x, y, z + 1)){
			return 1;
		}
		if(isStructureBlock(world, x + 1, y, z) && isStructureBlock(world, x, y, z + 1)){
			return 0;
		}
		return null;
	}

	private boolean isMiddleBlock(World world, int x, int y, int z) {
		return  isStructureBlock(world, x + 1, y, z) &&
				isStructureBlock(world, x - 1, y, z) &&
				isStructureBlock(world, x, y, z + 1) &&
				isStructureBlock(world, x, y, z - 1);
	}

	private int getLayer(World world, int x, int y, int z) {
		if(isStructureBlock(world, x, y + 1, z) && isStructureBlock(world, x, y - 1, z))return 0;
		if(isStructureBlock(world, x, y + 1, z) && !isStructureBlock(world, x, y - 1, z))return -1;
		if(!isStructureBlock(world, x, y + 1, z) && isStructureBlock(world, x, y - 1, z))return 1;
		return 2;
	}

	public void scan(World world, int x, int y, int z) {
		int count = 0;
		for(int i = -1; i <= 1; i++){
			for(int j = -1; j <= 1; j++){
				for(int k = -1; k <= 1; k++){
					if(isStructureBlock(world, x + k, y + i, z + j))count++;
				}
			}
		}
		if(count == 27){
			int index = 0;
			for(int yOff = -1; yOff <= 1; yOff++){
				for(int zOff = -1; zOff <= 1; zOff++){
					for(int xOff = -1; xOff <= 1; xOff++){
						if(isStructureBlock(world, x + yOff, y + zOff, z + xOff)){
							blocks[index++] = new VirtualBlock(x + xOff, y + yOff, z + zOff);
						}
					}
				}
			}
		}
	}

	private boolean isCenter(World world, int x, int y, int z) {
		return  isStructureBlock(world, x, y - 1, z) &&
				isStructureBlock(world, x, y + 1, z) &&
				isStructureBlock(world, x, y, z - 1) &&
				isStructureBlock(world, x, y, z + 1) &&
				isStructureBlock(world, x - 1, y, z) &&
				isStructureBlock(world, x + 1, y, z);
	}
	
	public boolean isStructureBlock(World world, int x, int y, int z){
		TileEntity entity = world.getBlockTileEntity(x, y, z);
		if(entity instanceof TileEntityStructure){
			if(!((TileEntityStructure)entity).hasStructure()){
				int last = ((TileEntityStructure)entity).lastIndex;
				this.id = last > 0 ? last : id;
				return true;
			}	
		}
		return false;
	}

	public boolean isComplete() {
		int count = 0;
		for(VirtualBlock block: blocks){
			if(block == null)return false;
		}
		return true;
	}
	
	public void notifyAllBlocks(World world, int id){
		for(int i = 0; i < blocks.length; i++){
			TileEntity entity = world.getBlockTileEntity(blocks[i].x, blocks[i].y, blocks[i].z);
			if(entity instanceof TileEntityStructure){
				((TileEntityStructure)entity).notify(id);
			}
		}
	}

	public void readFromNBT(NBTTagCompound tag) {
		NBTTagList list = tag.getTagList("blocks");
		for(int i = 0; i < list.tagCount(); i++){
			NBTTagCompound blockTag = (NBTTagCompound) list.tagAt(i);
			blocks[i] = VirtualBlock.loadFromNBT(blockTag);
		}
		this.updateTime = tag.getLong("updateTime");
		this.energy = tag.getFloat("energy");
		this.heat = tag.getFloat("heat");
		this.id = tag.getInteger("index");
		
		NBTTagList machineList = tag.getTagList("redmagic.machines");
		for(int i = 0; i < machineList.tagCount(); i++){
			NBTTagCompound machineTag = (NBTTagCompound) machineList.tagAt(i);
			Machine machine = Machine.loadFromNBT(machineTag);
			machines[machine.getSide()] = machine;
		}
	}
	
	public void writeToNBT(NBTTagCompound tag){
		NBTTagList list = new NBTTagList();
		for(VirtualBlock block: blocks){
			NBTTagCompound blockTag = new NBTTagCompound();
			block.writeToNBT(blockTag);
			list.appendTag(blockTag);
		}
		tag.setTag("blocks", list);
		tag.setLong("updateTime", updateTime);
		tag.setFloat("energy", energy);
		tag.setFloat("heat", heat);
		tag.setInteger("index", id);
		
		NBTTagList machineList = new NBTTagList();
		for(Machine machine: machines){
			if(machine != null){
				NBTTagCompound machineTag = new NBTTagCompound();
				machine.writeToNBT(machineTag);
				machineList.appendTag(machineTag);
			}
		}
		tag.setTag("redmagic.machines", machineList);
	}

	public int getPosition(int x, int y, int z) {
		for(int i = 0; i < blocks.length; i++){
			if(blocks[i].x == x && blocks[i].y == y && blocks[i].z == z)return i;
		}
		return -1;
	}
	
	public int getSideByPosition(int position) {
		switch(position){
		case 4: return 0;
		case 10: return 3;
		case 12: return 5;
		case 14: return 4;
		case 16: return 2;
		case 22: return 1;
		default: return -1;
		}
	}

	// --------------------------------------------------------------------------
	// IEnergyHandler
	
	@Override
	public float collect(World world, float amount, int chunkX, int chunkZ) {
		float collected = EnergyMap.consumeEnergy(world.provider.dimensionId, chunkX, chunkZ, amount);
		return store(collected);
	}

	@Override
	public float release(World world, float amount, int chunkX, int chunkZ) {
		float released = EnergyMap.releaseEnergy(new RedEnergy(world.provider.dimensionId, chunkX, chunkZ, amount));
		return use(released);
	}

	@Override
	public float use(float amount) {
		if(energy - amount >= 0){
			energy -= amount;
			return amount;
		}
		return 0;
	}

	@Override
	public float store(float store) {
		energy += store;
		return store;
	}

	@Override
	public float getStoredEnergy() {
		return energy;
	}

	@Override
	public int getChunkX() {
		return blocks[13] != null ? blocks[13].x / 16 : Integer.MAX_VALUE;
	}

	@Override
	public int getChunkZ() {
		return blocks[13] != null ? blocks[13].z / 16 : Integer.MAX_VALUE;
	}

	// ---------------------------------------------------------------------
	// IMachineHandler
	
	@Override
	public Machine getMachineOnSide(int i) {
		return machines[i];
	}

	@Override
	public IEnergyHandler getEnergyHandler() {
		return (IEnergyHandler)this;
	}

	@Override
	public float getHeat() {
		return heat;
	}

	@Override
	public int getSize() {
		int size = 0;
		for(Machine machine: machines){
			if(machine != null)size += machine.getSize();
		}
		return size;
	}

	@Override
	public void setHeat(float heat) {
		this.heat = heat;
	}

	@Override
	public World getWorld() {
		return world;
	}

	@Override
	public int getXCoord(int side) {
		switch(side){
		case 1: return blocks[TOP] != null ? blocks[TOP].x : Integer.MAX_VALUE;
		case 0: return blocks[BOTTOM] != null ? blocks[BOTTOM].x : Integer.MAX_VALUE;
		case 2: return blocks[BACK] != null ? blocks[BACK].x : Integer.MAX_VALUE;
		case 3: return blocks[FRONT] != null ? blocks[FRONT].x : Integer.MAX_VALUE;
		case 4: return blocks[LEFT] != null ? blocks[LEFT].x : Integer.MAX_VALUE;
		case 5: return blocks[RIGHT] != null ? blocks[RIGHT].x : Integer.MAX_VALUE;
		default: return Integer.MAX_VALUE;
		}
	}

	@Override
	public int getYCoord(int side) {
		switch(side){
		case 1: return blocks[TOP] != null ? blocks[TOP].y : Integer.MAX_VALUE;
		case 0: return blocks[BOTTOM] != null ? blocks[BOTTOM].y : Integer.MAX_VALUE;
		case 2: return blocks[BACK] != null ? blocks[BACK].y : Integer.MAX_VALUE;
		case 3: return blocks[FRONT] != null ? blocks[FRONT].y : Integer.MAX_VALUE;
		case 4: return blocks[LEFT] != null ? blocks[LEFT].y : Integer.MAX_VALUE;
		case 5: return blocks[RIGHT] != null ? blocks[RIGHT].y : Integer.MAX_VALUE;
		default: return Integer.MAX_VALUE;
		}
	}

	@Override
	public int getZCoord(int side) {
		switch(side){
		case 1: return blocks[TOP] != null ? blocks[TOP].z : Integer.MAX_VALUE;
		case 0: return blocks[BOTTOM] != null ? blocks[BOTTOM].z : Integer.MAX_VALUE;
		case 2: return blocks[BACK] != null ? blocks[BACK].z : Integer.MAX_VALUE;
		case 3: return blocks[FRONT] != null ? blocks[FRONT].z : Integer.MAX_VALUE;
		case 4: return blocks[LEFT] != null ? blocks[LEFT].z : Integer.MAX_VALUE;
		case 5: return blocks[RIGHT] != null ? blocks[RIGHT].z : Integer.MAX_VALUE;
		default: return Integer.MAX_VALUE;
		}
	}

	@Override
	public Machine getMachine(int metadata) {
		for(Machine machine: machines){
			if(machine != null && machine.getMetadata() == metadata)return machine;
		}
		return null;
	}
	
	
}
