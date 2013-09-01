package redmagic.client.models;

import redmagic.configuration.BlockIndex;
import net.minecraft.client.model.ModelBase;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

public class ModelRune extends ModelBase{
	
	public IModelCustom rune;
	
    public ModelRune(){
    	rune = AdvancedModelLoader.loadModel("/assets/redmagic/models/" + BlockIndex.RUNE_NAME + ".obj");
    }
    
    public void render(){
    	rune.renderAll();
    }
    
    public void renderPart(String part){
    	rune.renderPart(part);
    }
}
