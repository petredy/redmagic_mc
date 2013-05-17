package redmagic.lib.souls;

import redmagic.blocks.multi.tree.TreeStructure;
import redmagic.lib.souls.container.SoulContainer;
import redmagic.lib.souls.gui.SoulGui;
import redmagic.lib.souls.inventory.SoulInventory;
import redmagic.tileentities.tree.TileEntityTreeWood;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public abstract class Soul {
	
	public abstract void init(ItemStack soul, TileEntityTreeWood entity, TreeStructure structure, int x, int y, int z);
	
	public abstract void onUpdate(ItemStack soul, TileEntityTreeWood entity, TreeStructure structure, int x, int y, int z);
	
	public abstract void onWrench(ItemStack soul, TileEntityTreeWood entity, TreeStructure structure, EntityPlayer player, int x, int y, int z);

	public abstract SoulInventory getInventory();
	
	public abstract SoulGui getGui();
	
	public abstract SoulContainer getContainer();
	
	public abstract void onRedstoneOn(ItemStack soul, TileEntityTreeWood entity, TreeStructure structure, int x, int y, int z);
	
	public abstract void onRedstoneOff(ItemStack soul, TileEntityTreeWood entity, TreeStructure structure, int x, int y, int z);
	
	public abstract void remove(ItemStack soul, TileEntityTreeWood entity, TreeStructure structure, int x, int y, int z);

	public abstract void readFromNBT(NBTTagCompound tag);
	
	public abstract void writeToNBT(NBTTagCompound tag);

	public abstract void onActivated(ItemStack soulStack, TileEntityTreeWood entity, TreeStructure structure, EntityPlayer player, int x, int y, int z);
	
}
