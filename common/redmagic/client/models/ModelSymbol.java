package redmagic.client.models;


import net.minecraft.client.model.ModelBase;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

public class ModelSymbol extends ModelBase{
	
	public IModelCustom symbol;
	
    public ModelSymbol(){
    	symbol = AdvancedModelLoader.loadModel("/mods/redmagic/symbol.obj");
    }
    
    public void render(){
    	symbol.renderAll();
    }
    
    public void renderPart(String part){
    	symbol.renderPart(part);
    }
}
