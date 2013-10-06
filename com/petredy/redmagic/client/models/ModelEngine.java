package com.petredy.redmagic.client.models;

import com.petredy.redmagic.lib.Rendering;

import net.minecraft.client.model.ModelBase;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

public class ModelEngine extends ModelBase{

	public IModelCustom model;
	
	public ModelEngine(){
		model = AdvancedModelLoader.loadModel(Rendering.ENGINE);
	}
	
	public void render(String part){
		model.renderPart(part);
	}
	
}
