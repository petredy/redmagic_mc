package com.petredy.redmagic.entities;

import java.util.ArrayList;
import java.util.List;

import com.petredy.redmagic.entities.ai.EntityAIStayAtBase;
import com.petredy.redmagic.items.Items;
import com.petredy.redmagic.soul.SoulInventory;
import com.petredy.redmagic.soul.SoulStack;
import com.petredy.redmagic.utils.LogUtils;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntitySoul extends EntityLiving {

	public SoulInventory inventory = new SoulInventory();
	
	public EntitySoul(World par1World) {
		super(par1World);
		this.setSize(0.3f, 0.3f);
		this.tasks.addTask(0, new EntityAIStayAtBase(this));
		this.tasks.addTask(5, new EntityAIWatchClosest(this, EntityPlayer.class, 1F));
	}

	protected boolean isAIEnabled()
    {
        return true;
    }
	
	public boolean isEntityInvulnerable()
    {
		return true;
    }
	
	public boolean canBePushed()
    {
        return false;
    }

	@Override
	public ItemStack getHeldItem() {
		// TODO Auto-generated method stub
		return new ItemStack(Items.soul);
	}

	@Override
	public ItemStack getCurrentItemOrArmor(int i) {
		return new ItemStack(Items.glasses);
	}

	@Override
	public void setCurrentItemOrArmor(int i, ItemStack itemstack) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ItemStack[] getLastActiveItems() {
		return new ItemStack[5];
	}
	
	@Override
	public void writeEntityToNBT(NBTTagCompound tag){
		super.writeEntityToNBT(tag);
		this.inventory.writeToNBT(tag);
	}
	
	@Override
	public void readEntityFromNBT(NBTTagCompound tag){
		super.readEntityFromNBT(tag);
		this.inventory = SoulInventory.loadFromNBT(tag);
	}
}
