package redmagic.handlers;

import java.util.Random;

import cpw.mods.fml.common.registry.GameRegistry;
import redmagic.entities.EntityRedmagicItem;
import redmagic.helpers.LogHelper;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.player.EntityInteractEvent;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;

public class PlayerInteractHandler {

	@ForgeSubscribe
	public void onInteractWithEntity(EntityInteractEvent event){
		if(event.target instanceof EntityRedmagicItem){
			LogHelper.log("Interact with redmagicItem");
			EntityRedmagicItem item = (EntityRedmagicItem)event.target;
			if(item.getEntityItem() != null){
				if(event.entityPlayer.inventory.addItemStackToInventory(item.getEntityItem().copy())){
					GameRegistry.onPickupNotification(event.entityPlayer, item);
					Random rand = new Random();
		            item.playSound("random.pop", 0.2F, ((rand.nextFloat() - rand.nextFloat()) * 0.7F + 1.0F) * 2.0F);
		            event.entityPlayer.onItemPickup(item, item.getEntityItem().stackSize);	            
		            EntityItemPickupEvent pickUpEvent = new EntityItemPickupEvent(event.entityPlayer, item);
		
		            item.setDead();
		            if (MinecraftForge.EVENT_BUS.post(pickUpEvent))
		            {
		                return;
		            }
				}
			}
			
		}
	}
	
}
