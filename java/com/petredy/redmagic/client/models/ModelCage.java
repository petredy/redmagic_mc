package com.petredy.redmagic.client.models;

import org.bouncycastle.asn1.x509.qualified.Iso4217CurrencyCode;

import com.petredy.redmagic.lib.Rendering;

import net.minecraft.client.model.ModelBase;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

public class ModelCage extends ModelBase{

	public IModelCustom cage;
	
	public ModelCage(){
		cage = AdvancedModelLoader.loadModel(Rendering.CAGE);
	}
	
	public void render(){
		cage.renderAll();
	}
	
}
