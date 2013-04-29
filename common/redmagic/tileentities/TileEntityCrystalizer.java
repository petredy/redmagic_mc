package redmagic.tileentities;

import redmagic.helpers.SoulHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;


public class TileEntityCrystalizer extends TileEntityInventory{

	public static final int crystalSlot = 0;
	public static final int soulSlot = 1;
	public static final int outputSlot = 2;
	
	public int crystalizeTime = 0;
	public static final int neededTime = 10;
	public ItemStack crystalizeSoul = null;
	
	
	public TileEntityCrystalizer() {
		super(3, "Crystalizer");
	}
	
	public void updateEntity(){
		if(this.isCrystalizing()){
			this.crystalize();
		}else if(this.canCrystalyze()){
			this.beginCrystalize();
		}
	}

	@SuppressWarnings("static-access")
	private void crystalize() {
		this.crystalizeTime++;
		if(this.crystalizeTime >= this.neededTime){
			ItemStack output = SoulHelper.getCrystalBySoul(this.crystalizeSoul);
			this.inv[outputSlot] = output;
			this.crystalizeTime = 0;
		}
	}

	@SuppressWarnings("static-access")
	private void beginCrystalize() {
		this.crystalizeSoul = this.inv[soulSlot].copy();
		this.setInventorySlotContents(this.soulSlot, null);
		this.decrStackSize(this.crystalSlot, 1);
		if(this.inv[crystalSlot] != null && this.inv[crystalSlot].stackSize <= 0)this.inv[crystalSlot] = null;
		this.crystalizeTime++;
	}

	private boolean canCrystalyze() {
		return this.inv[crystalSlot] != null && this.inv[soulSlot] != null && this.inv[outputSlot] == null;
	}

	private boolean isCrystalizing() {
		return crystalizeTime > 0;
	}

	public boolean isUseableByPlayer(EntityPlayer par1EntityPlayer)
    {
        return this.worldObj.getBlockTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : par1EntityPlayer.getDistanceSq((double)this.xCoord + 0.5D, (double)this.yCoord + 0.5D, (double)this.zCoord + 0.5D) <= 64.0D;
    }

}
