package redmagic.tileentities.tree;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import redmagic.api.multiblock.IMultiEntity;
import redmagic.api.multiblock.IStructure;
import redmagic.blocks.multi.tree.TreeStructure;
import redmagic.helpers.TreeHelper;

public class TileEntityTreeLeaves extends TileEntity implements IMultiEntity{

	public Integer structureID = null;
	
	public TileEntityTreeLeaves() {
		
	}
	
	@Override
	public void readFromNBT(NBTTagCompound tag){
		super.readFromNBT(tag);
		
		this.structureID = tag.getInteger("ID");
	}

	@Override
	public void writeToNBT(NBTTagCompound tag){
		super.writeToNBT(tag);
		
		if(this.structureID != null)tag.setInteger("ID", structureID);
	}
	
	@Override
	public IStructure getStructure() {
		return TreeHelper.loadStructure(worldObj, structureID);
	}

	@Override
	public void setStructure(IStructure structure) {
		
	}

	@Override
	public boolean hasStructure() {
		return this.structureID != null && TreeHelper.loadStructure(worldObj, structureID) != null;
	}

	@Override
	public void destroyStructure() {
		if(this.hasStructure()){
			TreeStructure structure = TreeHelper.loadStructure(worldObj, structureID);
			structure.destroy(worldObj);
			TreeHelper.saveStructure(worldObj, structureID, structure);
		}
	}

	@Override
	public void buildStructure() {
		if(this.hasStructure()){
			TreeStructure structure = TreeHelper.loadStructure(worldObj, structureID);
			structure.notifyAllBlocks(worldObj, this.structureID);
			TreeHelper.saveStructure(worldObj, structureID, structure);
		}
	}

	@Override
	public World getWorld() {
		return worldObj;
	}

	public void removeBlock() {
		if(this.hasStructure()){
			TreeStructure structure = TreeHelper.loadStructure(worldObj, structureID);
			structure.removeBlock(worldObj, xCoord, yCoord, zCoord);	

		}
	}

	public void addBlock(){
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
				structure.addBlock(worldObj, xCoord, yCoord, zCoord, TreeStructure.leaveKey);
				return true;
			}
		}
		if(entity instanceof IMultiEntity && entity instanceof TileEntityTreeLeaves){
			TileEntityTreeLeaves tile = (TileEntityTreeLeaves)entity;
			if(tile != null && tile.hasStructure()){
				TreeStructure structure = (TreeStructure) tile.getStructure();
				this.structureID = structure.id;
				structure.addBlock(worldObj, xCoord, yCoord, zCoord, TreeStructure.leaveKey);
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

	@Override
	public void setStructure(Integer id) {
		this.structureID = id;
	}
}
