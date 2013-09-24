package com.petredy.redmagic.tileentities;

import com.petredy.redmagic.container.ContainerCrafting;
import com.petredy.redmagic.lib.BlockIndex;
import com.petredy.redmagic.utils.InventoryUtils;
import com.petredy.redmagic.utils.LogUtils;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;

public class TileEntityWorkTable extends TileEntityInventory {

	public static final int OUTPUT = 9;
	
	public TileEntityWorkTable() {
		super(BlockIndex.WORK_TABLE_NAME, 10);
	}

	public ForgeDirection side;

	public void build(EntityPlayer player){
		if(InventoryUtils.containsInventoryItems(player.inventory, this.getCrafting())){
			InventoryUtils.reduceItemsInInventory(player.inventory, this.getCrafting());
		}else{
			for(int i = 0; i < 9; i++){
				this.decrStackSize(i, 1);
			}
		}
	}
	
	@Override
	public ItemStack decrStackSize(int par1, int par2)
    {
        if (this.inv[par1] != null)
        {
            ItemStack itemstack;

            if (this.inv[par1].stackSize <= par2)
            {
                itemstack = this.inv[par1];
                this.inv[par1] = null;
                this.onInventoryChanged();
                return itemstack;
            }
            else
            {
                itemstack = this.inv[par1].splitStack(par2);

                if (this.inv[par1].stackSize == 0)
                {
                    this.inv[par1] = null;
                }
                this.onInventoryChanged();
                return itemstack;
            }
        }
        else
        {
            return null;
        }
    }
	
	@Override
	public void onInventoryChanged(int slot)
    {
		if(slot != OUTPUT){
			InventoryCrafting crafting = new InventoryCrafting(new ContainerCrafting(this.getCrafting()), 3, 3);
			ItemStack[] matrix = this.getCrafting();
			for(int i = 0; i < matrix.length; i++){
				crafting.setInventorySlotContents(i, matrix[i]);
			}
			
			ItemStack output = CraftingManager.getInstance().findMatchingRecipe(crafting, worldObj);
			inv[OUTPUT] = output;
		}
	}

	public ItemStack[] getCrafting() {
		ItemStack[] stacks = new ItemStack[9];
		for(int i = 0; i < 9; i++){
			stacks[i] = inv[i];
		}
		return stacks;
	}
	
	
	
	
	
}
