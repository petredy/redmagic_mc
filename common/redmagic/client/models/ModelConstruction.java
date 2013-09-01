package redmagic.client.models;

import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import redmagic.configuration.BlockIndex;

public class ModelConstruction {
	public IModelCustom construction;
	
    public ModelConstruction(){
    	construction = AdvancedModelLoader.loadModel("/assets/redmagic/models/construction.obj");
    }
    
    public void render(){
    	construction.renderAll();
    }
    
    public void renderPart(String part){
    	construction.renderPart(part);
    }
}
