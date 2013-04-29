package redmagic.containers;

import net.minecraft.inventory.InventoryBasic;

public class InventoryWorkTable extends InventoryBasic{

	public ContainerWorkTable container;
	public InventoryWorkTable(ContainerWorkTable container) {
		super("ContainerWorkTable", true, 10);
		this.container = container;
	}
	
	public void onInventoryChanged()
    {
		super.onInventoryChanged();
		container.onCraftMatrixChanged(this);
    }

}
