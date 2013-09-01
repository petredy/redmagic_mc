package redmagic.entities;

import redmagic.helpers.LogHelper;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityRedmagicItem extends EntityItem{
	
	public EntityRedmagicItem(World world){
		super(world);
		this.lifespan = -1;
		this.motionX = 0;
		this.motionZ = 0;
	}
	
	public EntityRedmagicItem(World par1World, double par2, double par4, double par6, ItemStack par8ItemStack) {
		super(par1World, par2, par4 + 0.5, par6, par8ItemStack);
	}
	
	public void setEntityItemStack(ItemStack par1ItemStack){
		LogHelper.log(par1ItemStack);
        this.getDataWatcher().updateObject(10, par1ItemStack);
        this.getDataWatcher().setObjectWatched(10);
    }
	
	protected boolean canTriggerWalking(){
        return false;
    }
	
	public boolean canBeCollidedWith(){
		return !this.isDead;
    }
    
	
	@Override
	public void onCollideWithPlayer(EntityPlayer par1EntityPlayer){
		
    }

	public void onUpdate(){
		super.onUpdate();
		this.motionX = 0;
		this.motionZ = 0;
    }
	public AxisAlignedBB getBoundingBox(){
        return AxisAlignedBB.getBoundingBox(0, 0, 0, 0.125f, 0.125f, 0.125f);
    }
	
	public AxisAlignedBB getCollisionBox(Entity par1Entity){
		return par1Entity.boundingBox;
    }
	 

}
