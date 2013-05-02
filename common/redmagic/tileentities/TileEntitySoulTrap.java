package redmagic.tileentities;

import java.util.Random;

import net.minecraft.entity.player.EntityPlayer;
import redmagic.configuration.BlockIndex;
import redmagic.helpers.InventoryHelper;
import redmagic.helpers.SoulHelper;

public class TileEntitySoulTrap extends TileEntityInventory{
	
	public int count = 0;
	public int need = 100;
	
	
	public TileEntitySoulTrap() {
		super(1, BlockIndex.SOUL_TRAP_NAME);
		
	}
	
	public void updateEntity(){
		if(count > need){
			count = 0;
			this.update();
		}
		count++;
	}
	
	private void update() {
		if(new Random().nextInt(1000) < 1){
			this.inv[0] = SoulHelper.createNewSoul();
		}
	}

	public boolean isUseableByPlayer(EntityPlayer par1EntityPlayer)
    {
        return this.worldObj.getBlockTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : par1EntityPlayer.getDistanceSq((double)this.xCoord + 0.5D, (double)this.yCoord + 0.5D, (double)this.zCoord + 0.5D) <= 64.0D;
    }

}
