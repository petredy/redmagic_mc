package redmagic.client.models;

import net.minecraft.client.model.ModelBase;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

public class ModelForge extends ModelBase{
	
	public IModelCustom forge;
	
    public ModelForge(){
    	forge = AdvancedModelLoader.loadModel("/mods/redmagic/models/forge.obj");
    }
    
    public void render(){
    	forge.renderAll();
    }
    
    public void renderPart(String part){
    	forge.renderPart(part);
    }
}
