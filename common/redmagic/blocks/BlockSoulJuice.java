package redmagic.blocks;

import java.util.Random;

import redmagic.Redmagic;
import redmagic.configuration.BlockIndex;
import redmagic.configuration.Reference;
import net.minecraft.block.BlockFluid;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class BlockSoulJuice extends BlockFluid{

	protected BlockSoulJuice(int par1) {
		super(par1, Material.water);
		this.setCreativeTab(Redmagic.tabRedMagic);
		this.setUnlocalizedName(BlockIndex.SOUL_JUICE_NAME);
	}
	
	public void randomDisplayTick(World par1World, int par2, int par3, int par4, Random par5Random){
		
    }
	
	public void onBlockAdded(World par1World, int par2, int par3, int par4){
        
    }

    public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, int par5){
        
    }
    
    public void registerIcons(IconRegister par1IconRegister)
    {
    	this.theIcon = new Icon[]{par1IconRegister.registerIcon(Reference.MOD_ID + ":" + BlockIndex.SOUL_JUICE_NAME), par1IconRegister.registerIcon(Reference.MOD_ID + ":" + BlockIndex.SOUL_JUICE_NAME + "_flowing")};
    }
    
    public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {
    	if(entity instanceof EntityItem){
    		EntityItem entityItem = (EntityItem)entity;
    		
    	}
    	
    	if(entity instanceof EntityLiving){
    		
    	}
    }
    

}
