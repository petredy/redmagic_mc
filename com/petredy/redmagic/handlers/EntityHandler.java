package com.petredy.redmagic.handlers;

import net.minecraft.entity.EntityEggInfo;
import net.minecraft.entity.EntityList;

import com.petredy.redmagic.Redmagic;
import com.petredy.redmagic.entities.EntitySoulman;
import com.petredy.redmagic.lib.Entities;

import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.EntityRegistry.EntityRegistration;

public class EntityHandler {

	public static void init(){
		Entities.SOULMAN_ID = EntityRegistry.findGlobalUniqueEntityId();
		EntityRegistry.registerModEntity(EntitySoulman.class, Entities.SOULMAN_NAME, Entities.SOULMAN_ID, Redmagic.instance, Entities.SOULMAN_RANGE, Entities.SOULMAN_UPDATE, true);
	}
	
}
