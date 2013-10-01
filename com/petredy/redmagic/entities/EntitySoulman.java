package com.petredy.redmagic.entities;

import java.util.Iterator;
import java.util.List;

import com.petredy.redmagic.container.inventory.InventorySoulman;
import com.petredy.redmagic.entities.ai.EntityAIFocus;
import com.petredy.redmagic.entities.ai.EntityAILearn;
import com.petredy.redmagic.entities.ai.FocusTarget;
import com.petredy.redmagic.items.Items;
import com.petredy.redmagic.soul.Soul;
import com.petredy.redmagic.soulman.SoulmanBody;
import com.petredy.redmagic.utils.InventoryUtils;
import com.petredy.redmagic.utils.LogUtils;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIDoorInteract;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIOpenDoor;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.world.World;

public class EntitySoulman extends EntityCreature{

	public SoulmanBody body;
	public Soul soul;
	public InventorySoulman inventory = new InventorySoulman();

	public FocusTarget focus;
	
	public EntitySoulman(World par1World) {
		super(par1World);
		this.setSize(0.75f, 1.8f);
		this.stepHeight = 1.0f;
		body = new SoulmanBody();
		soul = new Soul();
		this.setCanPickUpLoot(true);
		this.tasks.addTask(0, new EntityAIFocus(this));
		this.tasks.addTask(1, new EntityAILearn(this));
		this.tasks.addTask(5, new EntityAIDoorInteract(this){});
		this.tasks.addTask(2, new EntityAISwimming(this));
		this.tasks.addTask(7, new EntityAILookIdle(this));
		this.tasks.addTask(6, new EntityAIOpenDoor(this, true));
		this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 0f));
		this.tasks.addTask(9, new EntityAIWander(this, 0.3f));
		
		this.getNavigator().setCanSwim(true);
		this.getNavigator().setEnterDoors(true);
		
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
    
    public void onLivingUpdate()
    {
        super.onLivingUpdate();
        this.worldObj.theProfiler.startSection("looting");

        if (!this.worldObj.isRemote && this.canPickUpLoot() && !this.dead && this.worldObj.getGameRules().getGameRuleBooleanValue("mobGriefing"))
        {
            List list = this.worldObj.getEntitiesWithinAABB(EntityItem.class, this.boundingBox.expand(1.0D, 0.0D, 1.0D));
            Iterator iterator = list.iterator();

            while (iterator.hasNext())
            {
                EntityItem entityitem = (EntityItem)iterator.next();

                if (!entityitem.isDead && entityitem.getEntityItem() != null)
                {
                    ItemStack itemstack = entityitem.getEntityItem();
                    int i = getArmorPosition(itemstack);

                    if (i > -1)
                    {
                        boolean flag = true;
                        ItemStack itemstack1 = this.getCurrentItemOrArmor(i);

                        if (itemstack1 != null)
                        {
                            if (i == 0)
                            {
                                if (itemstack.getItem() instanceof ItemSword && !(itemstack1.getItem() instanceof ItemSword))
                                {
                                    flag = true;
                                }
                                else if (itemstack.getItem() instanceof ItemSword && itemstack1.getItem() instanceof ItemSword)
                                {
                                    ItemSword itemsword = (ItemSword)itemstack.getItem();
                                    ItemSword itemsword1 = (ItemSword)itemstack1.getItem();

                                    if (itemsword.func_82803_g() == itemsword1.func_82803_g())
                                    {
                                        flag = itemstack.getItemDamage() > itemstack1.getItemDamage() || itemstack.hasTagCompound() && !itemstack1.hasTagCompound();
                                    }
                                    else
                                    {
                                        flag = itemsword.func_82803_g() > itemsword1.func_82803_g();
                                    }
                                }
                                else
                                {
                                	if(InventoryUtils.addItemStackToInventory(inventory, itemstack1) == null){
                                		itemstack1 = null;
                                		this.setCurrentItemOrArmor(0, null);
                                		flag = true;
                                	}else{
                                		flag = false;
                                	}
                                }
                            }
                            else if (itemstack.getItem() instanceof ItemArmor && !(itemstack1.getItem() instanceof ItemArmor))
                            {
                                flag = true;
                            }
                            else if (itemstack.getItem() instanceof ItemArmor && itemstack1.getItem() instanceof ItemArmor)
                            {
                                ItemArmor itemarmor = (ItemArmor)itemstack.getItem();
                                ItemArmor itemarmor1 = (ItemArmor)itemstack1.getItem();

                                if (itemarmor.damageReduceAmount == itemarmor1.damageReduceAmount)
                                {
                                    flag = itemstack.getItemDamage() > itemstack1.getItemDamage() || itemstack.hasTagCompound() && !itemstack1.hasTagCompound();
                                }
                                else
                                {
                                    flag = itemarmor.damageReduceAmount > itemarmor1.damageReduceAmount;
                                }
                            }
                            else
                            {
                                flag = false;
                            }
                        }

                        if (flag)
                        {

                            this.setCurrentItemOrArmor(i, itemstack);
                            this.equipmentDropChances[i] = 2.0F;
                            this.onItemPickup(entityitem, 1);
                            entityitem.setDead();
                        }
                    }
                }
            }
        }

        this.worldObj.theProfiler.endSection();
    }
    
    public void setCurrentItemOrArmor(int par1, ItemStack par2ItemStack)
    {
        this.inventory.equip[par1] = par2ItemStack;
    }
    
    public ItemStack getCurrentItemOrArmor(int par1)
    {
        return this.inventory.equip[par1];
    }

	public int getItemInUseCount() {
		return 0;
	}
	
	protected int getDropItemId()
    {
        return Items.soul.itemID;
    }
	
	public EntityItem entityDropItem(ItemStack par1ItemStack, float par2)
    {
		EntityItem entity = new EntityItem(worldObj, posX, posY, posZ, par1ItemStack);
		worldObj.spawnEntityInWorld(entity);
		return entity;
    }
	
	protected void dropEquipment(boolean par1, int par2)
    {
        for (int j = 0; j < this.inventory.equip.length; ++j)
        {
            ItemStack itemstack = this.getCurrentItemOrArmor(j);
            if (itemstack != null)
            {
                this.entityDropItem(itemstack, 0.0F);
            }
        }
        InventoryUtils.dropInventory(inventory, worldObj, posX, posY, posZ);
    }

}
