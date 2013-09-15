package com.petredy.redmagic.client.models;

import com.petredy.redmagic.lib.Rendering;

import net.minecraft.client.model.ModelBase;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

public class ModelRune extends ModelBase{

	public IModelCustom rune;
	
	public ModelRune(){
		rune = AdvancedModelLoader.loadModel(Rendering.RUNE);
	}
	
	public void render(){
		rune.renderAll();
	}
	
	
}
