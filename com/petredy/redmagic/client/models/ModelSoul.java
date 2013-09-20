package com.petredy.redmagic.client.models;

import com.petredy.redmagic.lib.Entities;

import net.minecraft.client.model.ModelBase;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

public class ModelSoul extends ModelBase{

	public IModelCustom soul;
	
	public ModelSoul(){
		soul = AdvancedModelLoader.loadModel(Entities.SOUL_MODEL);
	}
	
	public void render(){
		soul.renderAll();
	}
	
}
