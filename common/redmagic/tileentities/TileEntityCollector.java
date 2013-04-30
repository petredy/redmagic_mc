package redmagic.tileentities;

import redmagic.api.frame.ISoul;

public class TileEntityCollector extends TileEntityInventory{
	
	
	
	public TileEntityCollector() {
		super(1, "Collector");
	}

	public void getDayLight() {
		if(this.inv[0] != null && this.inv[0].getItem() instanceof ISoul && !this.worldObj.isRemote){
			ISoul soul = (ISoul) this.getStackInSlot(0).getItem();
			soul.setSatisfaction(this.inv[0], soul.getSatisfaction(this.inv[0]) - 1);
		}
	}
	
	public void getMoonLight(){
		if(this.inv[0] != null && this.inv[0].getItem() instanceof ISoul && !this.worldObj.isRemote){
			ISoul soul = (ISoul) this.getStackInSlot(0).getItem();
			soul.setSatisfaction(this.inv[0], soul.getSatisfaction(this.inv[0]) + 1);
		}
	}
	
	

}
