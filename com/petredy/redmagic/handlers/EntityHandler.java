package com.petredy.redmagic.handlers;

import net.minecraft.entity.EntityEggInfo;
import net.minecraft.entity.EntityList;

import com.petredy.redmagic.Redmagic;
import com.petredy.redmagic.entities.EntitySoul;
import com.petredy.redmagic.lib.Entities;

import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.EntityRegistry.EntityRegistration;

public class EntityHandler {

	public static void init(){
		Entities.SOUL_ID = EntityRegistry.findGlobalUniqueEntityId();
		EntityRegistry.registerModEntity(EntitySoul.class, Entities.SOUL_NAME, Entities.SOUL_ID, Redmagic.instance, Entities.SOUL_RANGE, Entities.SOUL_UPDATE, false);
		EntityList.IDtoClassMapping.put(Entities.SOUL_ID, EntitySoul.class);
		EntityList.entityEggs.put(Entities.SOUL_ID, new EntityEggInfo(Entities.SOUL_ID, 0x33ffff, 0xffffff));
	}
	
}
