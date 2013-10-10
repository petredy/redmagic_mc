package com.petredy.redmagic.tileentities;

import com.petredy.redmagic.knowledge.Knowledge;
import com.petredy.redmagic.knowledge.KnowledgeCrafting;
import com.petredy.redmagic.knowledge.KnowledgeSystem;
import com.petredy.redmagic.lib.BlockIndex;
import com.petredy.redmagic.utils.ArtifactUtils;
import com.petredy.redmagic.utils.InventoryUtils;

import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEntityCrafter extends TileEntityInventory {
	
	public static final int ARTIFACT = 0;
	public static final int OUTPUT = 1;
	
	public ItemStack lastArtifact;
	
	public InventoryBasic matrix = new InventoryBasic("", false, 9);
	
	
	public void updateEntity(){
		if(lastArtifact == null && getArtifact() != null || (lastArtifact != null && getArtifact() != null && !lastArtifact.isItemEqual(getArtifact()))){
			Knowledge knowledge = KnowledgeSystem.getKnowledge(ArtifactUtils.getKnowledge(getArtifact()));
			if(knowledge instanceof KnowledgeCrafting){
				ItemStack[] crafting = ((KnowledgeCrafting)knowledge).recipe;
				for(int i = 0; i < crafting.length; i++){
					matrix.setInventorySlotContents(i, crafting[i]);
				}
			}
		}else if(getArtifact() == null){
			for(int i = 0; i < 9; i++){
				matrix.setInventorySlotContents(i, null);
			}
		}
	}
	
	public void craft() {
		ItemStack output = this.getStackInSlot(OUTPUT);
		Knowledge k = KnowledgeSystem.getKnowledge(ArtifactUtils.getKnowledge(getArtifact()));
		if(k instanceof KnowledgeCrafting){
			KnowledgeCrafting crafting = (KnowledgeCrafting)k;
			if(crafting.item != null){
				if(output != null && output.isItemEqual(crafting.item) && output.stackSize + crafting.item.stackSize <= output.getMaxStackSize()){
					if(InventoryUtils.containsInventoryItems(this, InventoryUtils.getSlots(matrix))){ 
						InventoryUtils.reduceItemsInInventory(this, InventoryUtils.getSlots(matrix));
						ItemStack out = crafting.item.copy();
						out.stackSize = crafting.item.stackSize + output.stackSize;
						this.setInventorySlotContents(OUTPUT, out);
					}
				}else if(output == null){
					if(InventoryUtils.containsInventoryItems(this, InventoryUtils.getSlots(matrix))){ 
						InventoryUtils.reduceItemsInInventory(this, InventoryUtils.getSlots(matrix));
						this.setInventorySlotContents(OUTPUT, crafting.item.copy());
					}
				}
			}
			
		}
	}
	
	public TileEntityCrafter() {
		super(BlockIndex.CRAFTER_NAME, 20);
	}
	
	public boolean hasArtifact(){
		return getStackInSlot(ARTIFACT) != null;
	}
	
	public ItemStack getArtifact(){
		return getStackInSlot(ARTIFACT);
	}
	
	public void setArtifact(ItemStack stack){
		setInventorySlotContents(ARTIFACT, stack);
	}

	
}
