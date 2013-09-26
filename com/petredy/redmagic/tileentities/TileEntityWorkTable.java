package com.petredy.redmagic.tileentities;

import com.petredy.redmagic.container.ContainerCrafting;
import com.petredy.redmagic.lib.BlockIndex;
import com.petredy.redmagic.utils.InventoryUtils;
import com.petredy.redmagic.utils.LogUtils;

import cpw.mods.fml.common.registry.GameRegistry;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerDestroyItemEvent;

public class TileEntityWorkTable extends TileEntityInventory {

	public static final int OUTPUT = 0;
	public InventoryBasic craftingMatrix;
	
	public TileEntityWorkTable() {
		super(BlockIndex.WORK_TABLE_NAME, 1);
		this.craftingMatrix = new InventoryBasic(this.getInvName(), false, 9);
	}

	public ForgeDirection side;

	public void build(EntityPlayer player){
		if(inv[OUTPUT] != null){
			inv[OUTPUT].onCrafting(worldObj, player, inv[OUTPUT].stackSize);
			GameRegistry.onItemCrafted(player, inv[OUTPUT], this);
		}
		if(InventoryUtils.containsInventoryItems(player.inventory, this.getCrafting())){
			InventoryUtils.reduceItemsInInventory(player.inventory, this.getCrafting());
		}else{
			for(int i = 0; i < 9; i++){
				ItemStack itemstack1 = this.getStackInSlot(i);
				LogUtils.log(itemstack1);
				this.decrStackSize(i, 1);
				if (itemstack1 != null && itemstack1.getItem().hasContainerItem())
                {
					
                    ItemStack itemstack2 = itemstack1.getItem().getContainerItemStack(itemstack1);
                    if (itemstack2.isItemStackDamageable() && itemstack2.getItemDamage() > itemstack2.getMaxDamage())
                    {
                        MinecraftForge.EVENT_BUS.post(new PlayerDestroyItemEvent(player, itemstack2));
                        itemstack2 = null;
                    }

                    if (itemstack2 != null && (!itemstack1.getItem().doesContainerItemLeaveCraftingGrid(itemstack1) || !player.inventory.addItemStackToInventory(itemstack2)))
                    {
                        if (this.getStackInSlot(i) == null)
                        {
                            this.setInventorySlotContents(i, itemstack2);
                        }
                        else
                        {
                            player.dropPlayerItem(itemstack2);
                        }
                    }
                }
			}
		}
		
	}
	
	@Override
	public ItemStack decrStackSize(int par1, int par2)
    {
		if(par1 > 0){
			return this.craftingMatrix.decrStackSize(par1 - 1, par2);
		}else{
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
    }
	
	public ItemStack getStackInSlot(int par1)
    {
		if(par1 > 0)return craftingMatrix.getStackInSlot(par1 - 1);
		return super.getStackInSlot(par1);
    }
	
	public ItemStack getStackInSlotOnClosing(int par1)
    {
		if(par1 > 0)return craftingMatrix.getStackInSlotOnClosing(par1 - 1);
		return super.getStackInSlotOnClosing(par1);
    }
	
	public void setInventorySlotContents(int par1, ItemStack par2ItemStack)
    {
		if(par1 > 0)craftingMatrix.setInventorySlotContents(par1 - 1, par2ItemStack);
		else super.setInventorySlotContents(par1, par2ItemStack);
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
			stacks[i] = craftingMatrix.getStackInSlot(i);
		}
		return stacks;
	}
	
	public void readFromNBT(NBTTagCompound tag){
		super.readFromNBT(tag);
		InventoryUtils.readFromNBT(craftingMatrix, tag);
	}
	
	public void writeToNBT(NBTTagCompound tag){
		super.writeToNBT(tag);
		InventoryUtils.writeToNBT(craftingMatrix, tag);
	}
	
	
	
	
	
}
