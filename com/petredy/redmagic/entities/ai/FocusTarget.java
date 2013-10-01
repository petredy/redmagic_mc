package com.petredy.redmagic.entities.ai;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import com.petredy.redmagic.utils.BlockUtils.VirtualBlock;

public class FocusTarget {

	public Entity entity;
	public VirtualBlock block;
	public ItemStack item;
	
	public FocusTarget(Entity entity) {
		this.entity = entity;
	}
	
	public FocusTarget(int x, int y, int z){
		this.block = new VirtualBlock(x, y, z);
	}
	
	public FocusTarget(ItemStack stack){
		this.item = stack;
	}

	public boolean isBlock(){
		return block != null;
	}
	
	public boolean isEntity(){
		return entity != null;
	}
	
	public boolean isItem(){
		return item != null;
	}

	public String getName(World world) {
		if(isBlock()){
			Block block = Block.blocksList[world.getBlockId(this.block.x, this.block.y, this.block.z)];
			if(block != null){
				return block.getUnlocalizedName();
			}
		}else if(isEntity()){
			if(entity != null){
				return EntityList.getEntityString(entity);
			}
		}else if(isItem()){
			return item.getUnlocalizedName();
		}
		return null;
	}
	
}
