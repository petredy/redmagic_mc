package redmagic.client.renderers;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import redmagic.items.ItemGlasses;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class RenderSoulPlayer extends RenderPlayer{

	@Override
	public void doRender(Entity entity, double d0, double d1, double d2, float f, float f1) {
        super.doRender(entity, d0, d1, d2, f, f1);
	}
	 
	protected void renderModel(EntityLiving par1EntityLiving, float par2, float par3, float par4, float par5, float par6, float par7)
    {
        this.func_98190_a(par1EntityLiving);

        if (!par1EntityLiving.isInvisible())
        {
        	if(par1EntityLiving instanceof EntityPlayer){
        		EntityPlayer player = (EntityPlayer)par1EntityLiving;
        		if(this.wearsMyArmor(player)){
        			GL11.glPushMatrix();
                    GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.15F);
                    GL11.glDepthMask(false);
                    GL11.glEnable(GL11.GL_BLEND);
                    GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
                    GL11.glAlphaFunc(GL11.GL_GREATER, 0.1F);
                    this.mainModel.render(par1EntityLiving, par2, par3, par4, par5, par6, par7);
                    GL11.glDisable(GL11.GL_BLEND);
                    GL11.glAlphaFunc(GL11.GL_GREATER, 0.1F);
                    GL11.glPopMatrix();
                    GL11.glDepthMask(true);
        		}else{
        			this.mainModel.render(par1EntityLiving, par2, par3, par4, par5, par6, par7);
        		}
        	}
        	
        }
        else if (!par1EntityLiving.func_98034_c(Minecraft.getMinecraft().thePlayer))
        {
            GL11.glPushMatrix();
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.15F);
            GL11.glDepthMask(false);
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
            GL11.glAlphaFunc(GL11.GL_GREATER, 0.003921569F);
            this.mainModel.render(par1EntityLiving, par2, par3, par4, par5, par6, par7);
            GL11.glDisable(GL11.GL_BLEND);
            GL11.glAlphaFunc(GL11.GL_GREATER, 0.1F);
            GL11.glPopMatrix();
            GL11.glDepthMask(true);
        }
        else
        {
            this.mainModel.setRotationAngles(par2, par3, par4, par5, par6, par7, par1EntityLiving);
        }
    }

	private boolean wearsMyArmor(EntityPlayer player) {
		for(int i = 0; i < player.inventory.armorInventory.length; i++){
			ItemStack armorStack = player.inventory.armorInventory[i];
			if(armorStack != null && armorStack.getItem() instanceof ItemGlasses)return true;
		}
		return false;
	}


}
