package redmagic.entities.particle;

import redmagic.configuration.Texture;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EntityWorkTableFX extends EntityCustomFX{

	public int mode = 0;
	public ItemStack stack;
	
	public EntityWorkTableFX(World world, double x, double y, double z, int mode, ItemStack data) {
		super(world, x, y, z);
		this.mode = mode;
		this.stack = data;
		this.particleMaxAge = 10;
		this.setParticleTextureIndex(0);
		this.particleScale = 3;
	}
	
	private void setTextureByMode(){
		this.texture = Texture.WORK_TABLE_FX_0;
		if(this.mode == 1){
			this.texture = Texture.WORK_TABLE_FX_3;
		}else if(this.mode == 2){
			this.texture = Texture.WORK_TABLE_FX_6;
		}else if(this.mode == 3){
			this.texture = Texture.WORK_TABLE_FX_10;
		}
	}
	
	@Override
	public void renderParticle(Tessellator par1Tessellator, float par2, float par3, float par4, float par5, float par6, float par7)
    {
		this.setTextureByMode();
		this.particleScale = 3;
		super.renderParticle(par1Tessellator, par2, par3, par4, par5, par6, par7);
    }

}
