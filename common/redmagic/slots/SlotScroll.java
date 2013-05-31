package redmagic.slots;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.ItemStack;

public class SlotScroll extends SlotNoInteraction{
	
	public SlotScroll(ItemStack stack, int par3, int par4) {
		super(getFakeInventory(stack), 0, par3, par4);
	}
	
	private static IInventory getFakeInventory(ItemStack stack){
		InventoryBasic basic = new InventoryBasic("fake", true, 1);
		basic.setInventorySlotContents(0, stack);
		return basic;
	}
}
