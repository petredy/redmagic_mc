package redmagic.handlers;

import redmagic.Redmagic;
import redmagic.configuration.EntityIndex;
import redmagic.entities.EntityRedmagicItem;
import cpw.mods.fml.common.registry.EntityRegistry;

public class EntityHandler {

	public static void init(){
		EntityRegistry.registerModEntity(EntityRedmagicItem.class, EntityIndex.REDMAGIC_ITEM_NAME, EntityIndex.REDMAGIC_ITEM_ID, Redmagic.instance, 64, 1, false);
	}
	
}
