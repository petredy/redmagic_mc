package redmagic.slots;

import redmagic.items.ItemBag;
import redmagic.lib.bag.BagHelper;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotBag extends Slot{

	public int id;
	
	public SlotBag(IInventory par1iInventory, int par2, int par3, int par4, int id) {
		super(par1iInventory, par2, par3, par4);
		this.id = id;
	}
	
	@Override
	public boolean isItemValid(ItemStack par1ItemStack)
    {
        if(par1ItemStack != null && par1ItemStack.getItem() instanceof ItemBag){
        	if(id == BagHelper.getID(par1ItemStack))return false;
        }
        return true;
    }

}
