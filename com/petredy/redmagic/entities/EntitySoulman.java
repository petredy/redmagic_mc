package com.petredy.redmagic.entities;

import com.petredy.redmagic.blocks.Blocks;
import com.petredy.redmagic.container.InventorySoulman;
import com.petredy.redmagic.entities.ai.EntityAIPerformRuneTask;
import com.petredy.redmagic.entities.ai.EntityAISearchRune;
import com.petredy.redmagic.items.Items;
import com.petredy.redmagic.lib.Textures;
import com.petredy.redmagic.rune.Rune;
import com.petredy.redmagic.utils.LogUtils;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.monster.EntitySnowman;
import net.minecraft.entity.passive.IAnimals;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.ItemStack;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class EntitySoulman extends EntityCreature implements IAnimals{

	public InventorySoulman inventory;
	public Rune activeRune;
	public double field_71079_bU;
	public double field_71082_cx;
	public double field_71089_bV;

	public EntitySoulman(World par1World) {
		super(par1World);
		this.setSize(0.3F, 0.3F);
		inventory = new InventorySoulman(2);
		this.tasks.addTask(0, new EntityAIPerformRuneTask(this));
		this.tasks.addTask(1, new EntityAISearchRune(this));
		
	}
	
	public boolean isAIEnabled()
    {
        return true;
    }
	

	public int getItemInUseCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	public Object getTextureCape() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean getHideCape() {
		return false;
	}

	public Scoreboard getWorldScoreboard() {
		return null;
	}

	public float getBedOrientationInDegrees() {
		// TODO Auto-generated method stub
		return 0;
	}

	public ResourceLocation getLocationSkin(EntitySoulman soulman) {
		return Textures.SOUL_MAN;
	}

}
