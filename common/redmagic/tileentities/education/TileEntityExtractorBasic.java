package redmagic.tileentities.education;



import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import redmagic.api.multiblock.IMultiEntity;
import redmagic.api.multiblock.IMultiBlock;
import redmagic.api.multiblock.IStructure;
import redmagic.blocks.multi.extractor.ExtractorStructure;
import redmagic.helpers.InventoryHelper;

public class TileEntityExtractorBasic extends TileEntity implements IMultiEntity{

	public ExtractorStructure structure;
	private int soulSlot = 0;
	
	public TileEntityExtractorBasic() {

	}
	
	@Override
	public void buildStructure() {
		ExtractorStructure structure = new ExtractorStructure();
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
		this.structure = (ExtractorStructure) structure;
	}

	@Override
	public boolean hasStructure() {
		return structure != null;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound tagCompound){
		super.readFromNBT(tagCompound);
		
		this.structure = (ExtractorStructure) ExtractorStructure.loadFromNBT(tagCompound);
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
	public World getWorld() {
		return worldObj;
	}

	@Override
	public void setStructure(Integer id) {
		// TODO Auto-generated method stub
		
	}

}
