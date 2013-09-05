package redmagic.lib.abilities.life;

import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import redmagic.configuration.AbilityIndex;
import redmagic.handlers.TalentRenderHandler;
import redmagic.helpers.LogHelper;
import redmagic.lib.abilities.Ability;
import redmagic.lib.gods.GodManager;
import redmagic.lib.gods.Sphere;
import redmagic.lib.player.PlayerInformation;
import redmagic.lib.talent.Talent;

public class ExorcismAbility extends Ability{
	
	public ExorcismAbility(){
		super();
		this.name = AbilityIndex.EXORCISM; 
	}
	
	
	/**
	 * use
	 * parameter Object[] data:
	 * 1: ItemStack - item used
	 * 2: EntityPlayer - player
	 * 3: Entity - Target
	 * 4: PlayerInformation - players' information
	 */
	@Override
	public void use(Object[] data){
		ItemStack stack = (ItemStack)data[0];
		EntityPlayer player = (EntityPlayer)data[1];
		Entity entity = (Entity)data[2];
		PlayerInformation info = (PlayerInformation)data[3];
		if(entity instanceof EntityZombie){
			for(Sphere sphere: GodManager.getSpheres()){
				Talent path = sphere.getPath();
				if(path != null && path.isTalent(Talent.life) && sphere.isInRange((int)entity.posX, (int)entity.posY, (int)entity.posZ)){
					EntityVillager npc = new EntityVillager(player.worldObj);
					npc.setPositionAndRotation(entity.posX, entity.posY, entity.posZ, entity.rotationYaw, entity.rotationPitch);
					if(!player.worldObj.isRemote)player.worldObj.spawnEntityInWorld(npc);
					entity.setDead();
					if(!player.capabilities.isCreativeMode)player.inventory.setInventorySlotContents(player.inventory.currentItem, null);
					break;
				}
			}
		}
		
	}
	
	@Override
	public void unlock(EntityPlayer player, boolean byClick) {
		if(!byClick){
			super.unlock(player, byClick);
			TalentRenderHandler.guiTalent.queueTakenTalent(Talent.lifeExorcism);
		}
	}
}
