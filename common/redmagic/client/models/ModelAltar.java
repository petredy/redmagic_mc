package redmagic.client.models;

import redmagic.configuration.BlockIndex;
import redmagic.configuration.ItemIndex;
import net.minecraft.client.model.ModelBase;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

public class ModelAltar extends ModelBase{

	public IModelCustom altar;
	
    public ModelAltar(){
    	altar = AdvancedModelLoader.loadModel("/assets/redmagic/models/" + BlockIndex.ALTAR_NAME + ".obj");
    }
    
    public void render(){
    	altar.renderAll();
    }
    
    public void renderPart(String part){
    	altar.renderPart(part);
    }
	
}
