package redmagic.client.models;


import redmagic.configuration.ItemIndex;
import redmagic.configuration.Reference;
import net.minecraft.client.model.ModelBase;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

public class ModelArtifact extends ModelBase{
	
	public IModelCustom artifact;
	
    public ModelArtifact(){
    	artifact = AdvancedModelLoader.loadModel("/assets/redmagic/models/" + ItemIndex.ARTIFACT_NAME + ".obj");
    }
    
    public void render(){
    	artifact.renderAll();
    }
    
    public void renderPart(String part){
    	artifact.renderPart(part);
    }
}
