package com.petredy.redmagic.client;

import org.bouncycastle.asn1.x509.qualified.Iso4217CurrencyCode;

import com.petredy.redmagic.lib.Rendering;

import net.minecraft.client.model.ModelBase;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

public class ModelEngine extends ModelBase{

	public IModelCustom engine;
	
	public ModelEngine(){
		this.engine = AdvancedModelLoader.loadModel(Rendering.ENGINE);
	}
	
	public void render(String part){
		this.engine.renderPart(part);
	}
	
	public void renderAll(){
		this.engine.renderAll();
	}
	
}
