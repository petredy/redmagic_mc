package redmagic.client.renderers;

import org.lwjgl.opengl.GL11;

import redmagic.configuration.BlockIndex;
import redmagic.tileentities.TileEntityBlockEntity;
import net.minecraft.client.model.ModelPig;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.tileentity.TileEntity;

public class RenderBlockEntity extends TileEntitySpecialRenderer{

	@Override
	public void renderTileEntityAt(TileEntity tileentity, double x, double y, double z, float f) {
		if(tileentity instanceof TileEntityBlockEntity){
			TileEntityBlockEntity entity = (TileEntityBlockEntity)tileentity;
			
			GL11.glPushMatrix();
			switch(entity.side){
			case 0:
				//GL11.glRotatef(180, 1, 0, 0);
			}
			switch(entity.type){
			case BlockIndex.ENTITY_PIG_METADATA:
				ModelPig pig = new ModelPig();
				pig.render(new EntityPig(entity.worldObj), (float)x, (float)y, (float)z, 1.0F, 1.0F, f);
			}
			GL11.glPopMatrix();
		}
		
	}

}
