package redmagic.tileentities.education;



import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.tileentity.TileEntity;
import redmagic.api.multiblock.IEducationEntity;
import redmagic.api.multiblock.IMultiBlock;
import redmagic.api.multiblock.IStructure;
import redmagic.blocks.multi.education.EducationStructure;
import redmagic.helpers.InventoryHelper;
import redmagic.tileentities.TileEntityInventory;

public class TileEntityEducationBasic extends TileEntityInventory implements IEducationEntity{

	public EducationStructure structure;
	private int soulSlot = 0;
	
	public TileEntityEducationBasic() {
		super(1, "Education System");
	}
	
	@Override
	public void buildStructure() {
		EducationStructure structure = new EducationStructure();
		structure.buildFromBlock(this.worldObj, this.xCoord, this.yCoord, this.zCoord);
	}

	@Override
	public void destroyStructure() {
		if(structure != null)structure.destroy(worldObj);
	}

	@Override
	public IStructure getStructure() {
		return structure;
	}

	@Override
	public void setStructure(IStructure structure) {
		this.structure = (EducationStructure) structure;
	}

	@Override
	public boolean hasStructure() {
		return structure != null;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound tagCompound){
		super.readFromNBT(tagCompound);
		
		this.structure = (EducationStructure) EducationStructure.loadFromNBT(tagCompound);
	}
	
	@Override
	public void writeToNBT(NBTTagCompound tagCompound){
		super.writeToNBT(tagCompound);
		
		if(this.structure != null)this.structure.writeToNBT(tagCompound);
	}
	
	public Packet getDescriptionPacket()
    {
        NBTTagCompound tag = new NBTTagCompound();
        this.writeToNBT(tag);
        return new Packet132TileEntityData(this.xCoord, this.yCoord, this.zCoord, 0, tag);
    }
	
	public void onDataPacket(INetworkManager net, Packet132TileEntityData pkt)
    {
		this.readFromNBT(pkt.customParam1);
		if(this.structure != null && this.structure.blocks.size() <= 0)this.structure = null;
		this.worldObj.markBlockForRenderUpdate(this.xCoord, this.yCoord, this.zCoord);
    }

	@Override
	public void saveSoul(ItemStack currentItem) {
		if(this.structure != null){
			IMultiBlock data = this.structure.getDataBlock();
			if(data != null && (data.getX() != this.xCoord || data.getY() != this.yCoord || data.getZ() != this.zCoord)){
				IEducationEntity entity = (IEducationEntity) data.getBasicEntity(worldObj);
				entity.saveSoul(currentItem);
			}else{
				this.inv[this.soulSlot ] = currentItem;
			}
		}
	}
	
	@Override
	public void dropAll() {
		if(this.hasStructure()){
			IMultiBlock data = this.structure.getDataBlock();
			if(data != null){
				TileEntity entity = worldObj.getBlockTileEntity(data.getX(), data.getY(), data.getZ());
				if(entity != null)InventoryHelper.dropInventory((IInventory)entity, worldObj, data.getX(), data.getY(), data.getZ());
			}
		}
	}
	
	@Override
	public ItemStack getSoul() {
		return this.inv[this.soulSlot];
	}

}
