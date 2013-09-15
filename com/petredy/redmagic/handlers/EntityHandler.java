package com.petredy.redmagic.handlers;

import net.minecraft.entity.EntityEggInfo;
import net.minecraft.entity.EntityList;

import com.petredy.redmagic.Redmagic;
import com.petredy.redmagic.entities.EntitySoulman;
import com.petredy.redmagic.lib.Entities;

import cpw.mods.fml.common.registry.EntityRegistry;

public class EntityHandler {

	public static void init(){
		EntityRegistry.registerModEntity(EntitySoulman.class, Entities.SOUL_NAME, Entities.SOUL_ID, Redmagic.instance, 100, 1, true);
		
		EntityList.IDtoClassMapping.put(Entities.SOUL_ID, EntitySoulman.class);
		EntityList.entityEggs.put(Entities.SOUL_ID, new EntityEggInfo(Entities.SOUL_ID, 0xffffff, 0x00000));
	}
}
