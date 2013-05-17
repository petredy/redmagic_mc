package redmagic.lib.souls;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.liquids.LiquidStack;
import redmagic.Redmagic;
import redmagic.blocks.multi.tree.TreeStructure;
import redmagic.configuration.GuiIndex;
import redmagic.configuration.LogicIndex;
import redmagic.core.Logger;
import redmagic.helpers.InventoryHelper;
import redmagic.lib.souls.container.SoulContainer;
import redmagic.lib.souls.container.SoulContainerCrafting;
import redmagic.lib.souls.gui.SoulGui;
import redmagic.lib.souls.gui.SoulGuiCrafting;
import redmagic.lib.souls.inventory.SoulInventory;
import redmagic.lib.souls.inventory.SoulInventoryCrafting;
import redmagic.recipes.tree.TreeCraftingRegistry;
import redmagic.recipes.worktable.WorkTableRegistry;
import redmagic.tileentities.tree.TileEntityTreeWood;

public class SoulCrafting extends Soul{

	public SoulInventoryCrafting inventory = new SoulInventoryCrafting();
	
	@Override
	public void init(ItemStack soul, TileEntityTreeWood entity, TreeStructure structure, int x, int y, int z) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUpdate(ItemStack soul, TileEntityTreeWood entity, TreeStructure structure, int x, int y, int z) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onWrench(ItemStack soul, TileEntityTreeWood entity, TreeStructure structure, EntityPlayer player, int x, int y, int z) {
		
	}

	@Override
	public void remove(ItemStack soul, TileEntityTreeWood entity, TreeStructure structure, int x, int y, int z) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public SoulInventory getInventory() {
		return inventory;
	}

	@Override
	public SoulGui getGui() {
		return new SoulGuiCrafting();
	}
	
	public SoulContainer getContainer(){
		return new SoulContainerCrafting();
	}

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		NBTTagList list = tag.getTagList("Items");
		for(int i = 0; i < list.tagCount(); i++){
			NBTTagCompound item = (NBTTagCompound) list.tagAt(i);
			byte slot = item.getByte("Slot");
			if(item != null && slot >= 0 && slot < 10){
				this.inventory.inv[slot] = ItemStack.loadItemStackFromNBT(item);
			}
		}
	}

	@Override
	public void writeToNBT(NBTTagCompound tag) {
		NBTTagList list = new NBTTagList();
		for(int i = 0; i < inventory.inv.length; i++){
			if(inventory.inv[i] != null){
				NBTTagCompound item = new NBTTagCompound();
				item.setByte("Slot", (byte) i);
				inventory.inv[i].writeToNBT(item);
				list.appendTag(item);
			}
		}
		tag.setTag("Items", list);
		
	}

	@Override
	public void onRedstoneOn(ItemStack soul, TileEntityTreeWood entity, TreeStructure structure, int x, int y, int z) {
		ItemStack[] matrix = new ItemStack[9];
		int count = 0;
		for(int i = 1; i < 10; i++){
			matrix[count++] = entity.getStackInSlot(i);
		}
		ItemStack output = TreeCraftingRegistry.find(matrix, soul);
		if(output == null)output = WorkTableRegistry.find(matrix);
		if(output != null){
			LiquidStack drain = entity.drain(ForgeDirection.UNKNOWN, LogicIndex.CRAFTING_COSTS, true);
			if(drain != null && drain.amount >= LogicIndex.CRAFTING_COSTS){
				for(int i = 1; i < 10; i++){
					entity.decrStackSize(i, 1);
				}
				if(entity.getStackInSlot(0) == null){
					entity.setInventorySlotContents(0, output.copy());
				}else{
					if(entity.getStackInSlot(0).isItemEqual(output) && entity.getStackInSlot(0).stackSize + output.stackSize <= output.getMaxStackSize()){
						entity.decrStackSize(0, -output.stackSize);
					}else{
						InventoryHelper.dropItemStack(output.copy(), entity.worldObj, entity.xCoord, entity.yCoord, entity.zCoord);
					}
				}
				
			}else{
				if(drain != null)entity.fill(ForgeDirection.UNKNOWN, drain, true);
			}
		}
	}

	@Override
	public void onRedstoneOff(ItemStack soul, TileEntityTreeWood entity, TreeStructure structure, int x, int y, int z) {
		
	}

	@Override
	public void onActivated(ItemStack soulStack, TileEntityTreeWood entity, TreeStructure structure, EntityPlayer player, int x, int y, int z) {
		player.openGui(Redmagic.instance, GuiIndex.TREE, player.worldObj, x, y, z);
	}

}
