package redmagic.containers;

import redmagic.lib.scrolls.Scroll;
import redmagic.lib.scrolls.ScrollHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;

public class ContainerScroll extends Container{

	public ContainerScroll(int id){
		this.update(id);
	}
	
	public void update(int id){
		this.inventorySlots.clear();
		Scroll scroll = ScrollHelper.getScroll(id);
		if(scroll != null)scroll.createContainer(this);
	}
	
	public void addSlot(Slot slot){
		this.addSlotToContainer(slot);
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer entityplayer) {
		return true;
	}

}
