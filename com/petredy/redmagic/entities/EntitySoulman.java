package com.petredy.redmagic.entities;

import net.minecraft.entity.EntityLiving;
import net.minecraft.world.World;

public class EntitySoulman extends EntityLiving{

	public EntitySoulman(World par1World) {
		super(par1World);
		this.setSize(0.3f, 0.3f);
	}
	
	protected boolean isAIEnabled()
    {
        return true;
    }

    protected boolean canDespawn()
    {
        return false;
    }
    
    public boolean canBreatheUnderwater()
    {
        return true;
    }

}
