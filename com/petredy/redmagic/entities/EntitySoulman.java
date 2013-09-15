package com.petredy.redmagic.entities;

import com.petredy.redmagic.blocks.Blocks;
import com.petredy.redmagic.items.Items;
import com.petredy.redmagic.utils.LogUtils;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.monster.EntitySnowman;
import net.minecraft.entity.passive.IAnimals;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EntitySoulman extends EntityCreature implements IAnimals{

	public EntitySoulman(World par1World) {
		super(par1World);
		this.setSize(0.3F, 0.7F);
		//this.setCurrentItemOrArmor(0, new ItemStack(Blocks.engine, 2));
	}
	
	public boolean isChild()
    {
        return true;
    }

}
