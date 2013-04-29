package redmagic.containers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.tileentity.TileEntity;

public class ContainerEducationBasic extends Container{

	public TileEntity entity;
	
	public ContainerEducationBasic(EntityPlayer player, TileEntity entity){
		super();
		this.entity = entity;
		
		this.addSlotToContainer(new Slot((IInventory) this.entity, 0, 80, 35));
		
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
		return ((IInventory) this.entity).isUseableByPlayer(entityplayer);
	}

}
