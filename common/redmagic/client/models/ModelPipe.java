package redmagic.client.models;


import net.minecraft.client.model.ModelBase;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

public class ModelPipe extends ModelBase{
	
	public IModelCustom pipe;
	
    public ModelPipe(){
    	pipe = AdvancedModelLoader.loadModel("/mods/redmagic/pipe.obj");
    }
    
    public void render(){
    	pipe.renderAll();
    }
    
    public void renderPart(String part){
    	pipe.renderPart(part);
    }
}
