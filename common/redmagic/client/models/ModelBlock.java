package redmagic.client.models;

import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import redmagic.configuration.BlockIndex;

public class ModelBlock {
	public IModelCustom block;
	
    public ModelBlock(){
    	block = AdvancedModelLoader.loadModel("/assets/redmagic/models/block.obj");
    }
    
    public void render(){
    	block.renderAll();
    }
    
    public void renderPart(String part){
    	block.renderPart(part);
    }
}
