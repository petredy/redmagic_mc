package redmagic.lib.souls;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.liquids.LiquidStack;
import redmagic.blocks.multi.tree.TreeStructure;
import redmagic.configuration.LogicIndex;
import redmagic.configuration.Reference;
import redmagic.core.Logger;
import redmagic.helpers.InventoryHelper;
import redmagic.helpers.SoulHelper;
import redmagic.lib.souls.container.SoulContainer;
import redmagic.lib.souls.gui.SoulGui;
import redmagic.lib.souls.inventory.SoulInventory;
import redmagic.tileentities.tree.TileEntityTreeWood;

public class SoulFurnace extends Soul{

	public int count;
	public ItemStack burnStack;
	
	@Override
	public void init(ItemStack soul, TileEntityTreeWood entity, TreeStructure structure, int x, int y, int z) {
		
	}

	@Override
	public void onUpdate(ItemStack soul, TileEntityTreeWood entity, TreeStructure structure, int x, int y, int z) {
		if(count > LogicIndex.SOUL_MAX_INTELLIGENCE - SoulHelper.getIntelligence(soul)){
			if(this.burnStack != null){
				int amount = SoulHelper.getStrength(soul);
				for(int i = 0; i < amount && i < burnStack.stackSize; i++){
					ItemStack output = FurnaceRecipes.smelting().getSmeltingResult(burnStack);
					InventoryHelper.addItemStackToInventory(InventoryHelper.getNextInventory(entity.worldObj, x, y, z,false), output.copy());
				}
				burnStack.stackSize -= amount;
				if(burnStack.stackSize <= 0){
					count = 0;
				}else{
					count = 1;
				}
			}
		}else if(count > 0){
			LiquidStack drain = structure.tank.drain(LogicIndex.FURNACE_COSTS, true);
			if(drain != null && drain.amount == LogicIndex.FURNACE_COSTS)count++;
			else if(drain != null){
				structure.tank.fill(drain, true);
			}
		}else{
			IInventory inv = InventoryHelper.getNextInventory(entity.worldObj, x, y, z, false);
			if(inv != null){
				if(InventoryHelper.containsBurnableItem(inv)){
					LiquidStack drain = structure.tank.drain(LogicIndex.FURNACE_COSTS, true);
					if(drain != null && drain.amount == LogicIndex.FURNACE_COSTS){
						ItemStack stack = InventoryHelper.popBurnableItemStack(inv);
						if(stack != null){
							this.burnStack = stack;
							count++;
						}
					}else if(drain != null){
						structure.tank.fill(drain, true);
					}
				}
			}
		}
		SoulHelper.setInteger(soul, "count", count);
		
	}

	@Override
	public void onWrench(ItemStack soul, TileEntityTreeWood entity, TreeStructure structure, EntityPlayer player, int x, int y, int z) {
		int count = SoulHelper.getInteger(soul, "count");
		if(count > 0){
			count = 0;
			ItemStack[] stacks = SoulHelper.getItemStacks(soul, "burn");
			for(int i = 0; i < stacks.length; i++){
				if(!entity.worldObj.isRemote)InventoryHelper.dropItemStack(stacks[i], entity.worldObj, x, y, z);
			}
			SoulHelper.setInteger(soul, "count", 0);
			SoulHelper.addItemStacks(soul, "burn", new ItemStack[]{});
		}
		
	}

	@Override
	public void remove(ItemStack soul, TileEntityTreeWood entity, TreeStructure structure, int x, int y, int z) {
		this.onWrench(soul, entity, structure, null, x, y, z);
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
		this.count = tag.getInteger(Reference.MOD_ID + "_count");
		this.burnStack = ItemStack.loadItemStackFromNBT(tag);
	}

	@Override
	public void writeToNBT(NBTTagCompound tag) {
		tag.setInteger(Reference.MOD_ID + "_count", count);
		if(this.burnStack != null)this.burnStack.writeToNBT(tag);
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
