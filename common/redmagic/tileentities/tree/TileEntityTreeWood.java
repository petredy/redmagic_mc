package redmagic.tileentities.tree;

import java.util.Iterator;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.liquids.ILiquidTank;
import net.minecraftforge.liquids.LiquidStack;
import redmagic.api.multiblock.IMultiEntity;
import redmagic.api.multiblock.IStructure;
import redmagic.blocks.multi.tree.TreeStructure;
import redmagic.core.Logger;
import redmagic.helpers.SoulHelper;
import redmagic.helpers.TreeHelper;
import redmagic.lib.tree.SoulBlock;
import redmagic.tileentities.TileEntityStorage;

public class TileEntityTreeWood extends TileEntityStorage implements IMultiEntity{

	public Integer structureID = null;
	public int updateCount = 0, neededUpdateCount = 50;
	public boolean init = false;
	
	public TileEntityTreeWood() {
		super(0);
		
	}
	
	public void updateEntity(){
		if(updateCount > neededUpdateCount && this.hasStructure() && init){
			updateCount = 0;
			this.update();
		}
		updateCount++;
		if(this.hasStructure() && !init)this.init();
	}
	
	private void update() {
		TreeStructure structure = (TreeStructure) this.getStructure();
		SoulBlock block = structure.storage.getBlockAt(xCoord, yCoord, zCoord);
		if(block != null){
			SoulHelper.getSoulByStack(block.soul).onUpdate(this, structure, xCoord, yCoord, zCoord);
		}
		TreeHelper.saveStructure(worldObj, structureID, structure);
		if(!worldObj.isRemote)TreeHelper.syncClient(worldObj, structureID);
	}

	public void wrench(){
		if(this.hasStructure()){
			TreeStructure structure = (TreeStructure) this.getStructure();
			SoulBlock block = structure.storage.getBlockAt(xCoord, yCoord, zCoord);
			if(block != null){
				SoulHelper.getSoulByStack(block.soul).onWrench(this, structure, xCoord, yCoord, zCoord);
				TreeHelper.saveStructure(worldObj, structureID, structure);
			}	
		}
	}
	
	public void addSoul(ItemStack soul){
		if(this.hasStructure()){
			TreeStructure structure = (TreeStructure) this.getStructure();
			structure.storage.add(xCoord, yCoord, zCoord, soul);
			init = false;
			TreeHelper.saveStructure(worldObj, structureID, structure);
		}
	}
	
	public void removeSoul(){
		TreeStructure structure = (TreeStructure) this.getStructure();
		SoulBlock block = structure.storage.getBlockAt(xCoord, yCoord, zCoord);
		if(block != null){
			SoulHelper.getSoulByStack(block.soul).remove(this, structure, xCoord, yCoord, zCoord);
			structure.storage.remove(xCoord, yCoord, zCoord);
			TreeHelper.saveStructure(worldObj, structureID, structure);
		}
	}
	
	public void addCapacityToTank(int amount){
		if(this.hasStructure()){
			TreeStructure structure = (TreeStructure) this.getStructure();
			structure.tank.setCapacity(structure.tank.getCapacity() + amount);
			TreeHelper.saveStructure(worldObj, structureID, structure);
		}
	}
	
	public void removeCapacityFromTank(int amount) {
		if(this.hasStructure()){
			TreeStructure structure = (TreeStructure) this.getStructure();
			structure.tank.setCapacity(structure.tank.getCapacity() - amount);
			TreeHelper.saveStructure(worldObj, structureID, structure);
		}
	}
	
	@Override
	public void readFromNBT(NBTTagCompound tag){
		super.readFromNBT(tag);
		
		this.structureID = tag.getInteger("ID");
	}

	private void init() {
		TreeStructure structure = (TreeStructure) this.getStructure();
		SoulBlock block = structure.storage.getBlockAt(xCoord, yCoord, zCoord);
		if(block != null){
			SoulHelper.getSoulByStack(block.soul).init(this, structure, xCoord, yCoord, zCoord);
			TreeHelper.saveStructure(worldObj, structureID, structure);
			worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
			init = true;
		}
	}

	@Override
	public void writeToNBT(NBTTagCompound tag){
		super.writeToNBT(tag);
		
		if(this.hasStructure()){
			tag.setInteger("ID", this.structureID);
		}
	}

	@Override
	public IStructure getStructure() {
		if(this.hasStructure()){
			return TreeHelper.loadStructure(worldObj, structureID);
		}
		return null;
	}

	@Override
	public void setStructure(IStructure structure) {
		// NOOP
	}
	
	@Override
	public void setStructure(Integer id) {
		this.structureID = id;
	}

	@Override
	public boolean hasStructure() {
		return this.structureID != null && TreeHelper.loadStructure(worldObj, structureID) != null;
	}

	@Override
	public void destroyStructure() {
		if(this.hasStructure()){
			// NOOP
		}
	}

	@Override
	public void buildStructure() {
		if(this.hasStructure()){
			TreeStructure structure = (TreeStructure)TreeHelper.loadStructure(worldObj, structureID);
			structure.notifyAllBlocks(worldObj, structureID);
		}
	}

	@Override
	public World getWorld() {
		return worldObj;
	}

	public void removeBlock() {
		if(this.hasStructure()){
			this.removeSoul();
			TreeStructure structure = (TreeStructure) this.getStructure();
			structure.removeBlock(worldObj, xCoord, yCoord, zCoord);
		}
	}

	public void addBlock() {
		if(!this.hasStructure()){
			if(this.addBlockFrom(xCoord + 1, yCoord, zCoord))return;
			if(this.addBlockFrom(xCoord - 1, yCoord, zCoord))return;
			if(this.addBlockFrom(xCoord, yCoord + 1, zCoord))return;
			if(this.addBlockFrom(xCoord, yCoord - 1, zCoord))return;
			if(this.addBlockFrom(xCoord, yCoord, zCoord + 1))return;
			if(this.addBlockFrom(xCoord, yCoord, zCoord - 1))return;
		}
	}
	
	private boolean addBlockFrom(int x, int y, int z) {
		TileEntity entity = worldObj.getBlockTileEntity(x, y, z);
		if(entity instanceof IMultiEntity && entity instanceof TileEntityTreeWood){
			TileEntityTreeWood tile = (TileEntityTreeWood)entity;
			if(tile != null && tile.hasStructure()){
				TreeStructure structure = (TreeStructure) tile.getStructure();
				this.structureID = structure.id;
				structure.addBlock(worldObj, xCoord, yCoord, zCoord, TreeStructure.woodKey);
				return true;
			}
		}
		if(entity instanceof IMultiEntity && entity instanceof TileEntityTreeLeaves){
			TileEntityTreeLeaves tile = (TileEntityTreeLeaves)entity;
			if(tile != null && tile.hasStructure()){
				TreeStructure structure = (TreeStructure) tile.getStructure();
				this.structureID = structure.id;
				structure.addBlock(worldObj, xCoord, yCoord, zCoord, TreeStructure.woodKey);
				return true;
			}
		}
		return false;
	}
	
	public Packet getDescriptionPacket()
    {
		NBTTagCompound tag = new NBTTagCompound();
		this.writeToNBT(tag);
        return new Packet132TileEntityData(this.xCoord, this.yCoord, this.zCoord, 0, tag);
    }
	
	public void onDataPacket(INetworkManager net, Packet132TileEntityData pkt)
    {
		NBTTagCompound tag = pkt.customParam1;
		this.readFromNBT(tag);
    }

	
	// TileEntityStorage Redirect
	
	@Override
	public int store(LiquidStack resource, Object... data) {
		if(this.hasStructure()){
			TreeStructure structure = (TreeStructure) this.getStructure();
			int essences = this.getEssences();
			int stored = structure.tank.fill(resource, true);
			if(this.getEssences() > this.getMaxEssences()){
				LiquidStack liquid = structure.tank.getLiquid();
				liquid.amount = this.getMaxEssences();
				structure.tank.setLiquid(liquid);
				TreeHelper.saveStructure(worldObj, structureID, structure);
				return this.getMaxEssences() - essences;
			}
			TreeHelper.saveStructure(worldObj, structureID, structure);
			return stored;
		}
		return 0;
	}

	@Override
	public LiquidStack extract(int amount, Object... data) {
		if(this.hasStructure()){
			TreeStructure structure = (TreeStructure) this.getStructure();
			LiquidStack rtn = structure.tank.drain(amount, true);
			TreeHelper.saveStructure(worldObj, structureID, structure);
			return rtn;
		}
		return null;
	}

	@Override
	public int getEssences(Object... data) {
		if(this.hasStructure()){
			TreeStructure structure = (TreeStructure) this.getStructure();
			return structure.tank.getLiquid() != null ? structure.tank.getLiquid().amount : 0;
		}
		return 0;
	}

	@Override
	public int getMaxEssences() {
		if(this.hasStructure()){
			TreeStructure structure = (TreeStructure) this.getStructure();
			return structure.tank.getCapacity();
		}
		return 0;
	}

	@Override
	public int fill(ForgeDirection from, LiquidStack resource, boolean doFill) {
		if(this.hasStructure()){
			TreeStructure structure = (TreeStructure) this.getStructure();
			int rtn = structure.tank.fill(resource, doFill);
			TreeHelper.saveStructure(worldObj, structureID, structure);
			return rtn;
		}
		return 0;
	}

	@Override
	public int fill(int tankIndex, LiquidStack resource, boolean doFill) {
		if(this.hasStructure()){
			TreeStructure structure = (TreeStructure) this.getStructure();
			int rtn = structure.tank.fill(resource, doFill);
			TreeHelper.saveStructure(worldObj, structureID, structure);
			return rtn;
		}
		return 0;
	}

	@Override
	public LiquidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
		if(this.hasStructure()){
			TreeStructure structure = (TreeStructure) this.getStructure();
			LiquidStack rtn = structure.tank.drain(maxDrain, doDrain);
			TreeHelper.saveStructure(worldObj, structureID, structure);
			return rtn;
		}
		return null;
	}

	@Override
	public LiquidStack drain(int tankIndex, int maxDrain, boolean doDrain) {
		if(this.hasStructure()){
			TreeStructure structure = (TreeStructure) this.getStructure();
			LiquidStack rtn = structure.tank.drain(maxDrain, doDrain);
			TreeHelper.saveStructure(worldObj, structureID, structure);
			return rtn;
		}
		return null;
	}

	@Override
	public ILiquidTank[] getTanks(ForgeDirection direction) {
		if(this.hasStructure()){
			return new ILiquidTank[]{((TreeStructure)this.getStructure()).tank};
		}
		return new ILiquidTank[]{};
	}

	@Override
	public ILiquidTank getTank(ForgeDirection direction, LiquidStack type) {
		if(this.hasStructure()){
			return ((TreeStructure)this.getStructure()).tank;
		}
		return null;
	}
}
