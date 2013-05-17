package redmagic.lib.souls;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import redmagic.blocks.multi.tree.TreeStructure;
import redmagic.configuration.LogicIndex;
import redmagic.helpers.SoulHelper;
import redmagic.lib.souls.container.SoulContainer;
import redmagic.lib.souls.gui.SoulGui;
import redmagic.lib.souls.inventory.SoulInventory;
import redmagic.lib.tree.SoulBlock;
import redmagic.tileentities.tree.TileEntityTreeWood;

public class SoulEssenceStorage extends Soul{

	@Override
	public void init(ItemStack soul, TileEntityTreeWood entity, TreeStructure structure, int x, int y, int z) {
		entity.addCapacityToTank(LogicIndex.STORAGE_MAX_ESSENCES * SoulHelper.getCapacity(soul));
	}

	@Override
	public void onUpdate(ItemStack soul, TileEntityTreeWood entity, TreeStructure structure, int x, int y, int z) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onWrench(ItemStack soul, TileEntityTreeWood entity, TreeStructure structure, EntityPlayer player, int x, int y, int z) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(ItemStack soul, TileEntityTreeWood entity, TreeStructure structure, int x, int y, int z) {
		entity.removeCapacityFromTank(LogicIndex.STORAGE_MAX_ESSENCES * SoulHelper.getCapacity(soul));
	}
	
	@Override
	public SoulInventory getInventory() {
		return null;
	}
	
	@Override
	public SoulGui getGui() {
		return null;
	}
	
	public SoulContainer getContainer(){
		return null;
	}

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void writeToNBT(NBTTagCompound tag) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onRedstoneOn(ItemStack soul, TileEntityTreeWood entity, TreeStructure structure, int x, int y, int z) {
		
	}

	@Override
	public void onRedstoneOff(ItemStack soul, TileEntityTreeWood entity, TreeStructure structure, int x, int y, int z) {
		
	}
	
	@Override
	public void onActivated(ItemStack soulStack, TileEntityTreeWood entity, TreeStructure structure, EntityPlayer player, int x, int y, int z) {
		
	}
}
