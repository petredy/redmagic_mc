package redmagic.containers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;

public class ContainerOneSlot extends Container{

	public IInventory inv;
	
	public ContainerOneSlot(EntityPlayer player, IInventory inv){
		super();
		this.inv = inv;
		
		this.addSlotToContainer(new Slot(this.inv, 0, 80, 32));
		
		this.bindPlayerInventory(player.inventory);
	}
	
	protected void bindPlayerInventory(InventoryPlayer inventory){
		for (int i = 0; i < 3; ++i)
        {
            for (int j = 0; j < 9; ++j)
            {
                this.addSlotToContainer(new Slot(inventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for (int i = 0; i < 9; ++i)
        {
            this.addSlotToContainer(new Slot(inventory, i, 8 + i * 18, 142));
        }
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer entityplayer) {
		return this.inv.isUseableByPlayer(entityplayer);
	}

}
