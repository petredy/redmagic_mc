package redmagic.containers;

import redmagic.tileentities.TileEntitySoulTrap;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;

public class ContainerSoulTrap extends Container{

	public TileEntitySoulTrap entity;
	
	public ContainerSoulTrap(EntityPlayer player, TileEntitySoulTrap entity){
		super();
		this.entity = entity;
		
		this.addSlotToContainer(new Slot(this.entity, 0, 62, 17));
		this.addSlotToContainer(new Slot(this.entity, 1, 80, 17));
		this.addSlotToContainer(new Slot(this.entity, 2, 98, 17));
		this.addSlotToContainer(new Slot(this.entity, 3, 62, 35));
		this.addSlotToContainer(new Slot(this.entity, 4, 80, 35));
		this.addSlotToContainer(new Slot(this.entity, 5, 98, 35));
		this.addSlotToContainer(new Slot(this.entity, 6, 62, 53));
		this.addSlotToContainer(new Slot(this.entity, 7, 80, 53));
		this.addSlotToContainer(new Slot(this.entity, 8, 98, 53));
		
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
		return this.entity.isUseableByPlayer(entityplayer);
	}

}
