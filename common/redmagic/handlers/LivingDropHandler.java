package redmagic.handlers;

import redmagic.configuration.Reference;
import redmagic.helpers.TamingHelper;
import redmagic.tileentities.TileEntityTaming;
import net.minecraft.entity.item.EntityItem;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingDropsEvent;

public class LivingDropHandler {

	
	@ForgeSubscribe
	public void drop(LivingDropsEvent event){
		if(event.entityLiving.getEntityData().getTag(Reference.MOD_ID) != null){
			TileEntityTaming tileEntity = TamingHelper.getTileEntity(event.entityLiving.worldObj, event.entityLiving.entityId);
			EntityItem item = new EntityItem(event.entityLiving.worldObj, event.entityLiving.posX, event.entityLiving.posY, event.entityLiving.posZ, tileEntity.inv[TileEntityTaming.soulSlot]);
			event.drops.add(item);
		}
	}
	
}
