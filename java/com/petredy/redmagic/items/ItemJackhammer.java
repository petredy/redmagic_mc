package com.petredy.redmagic.items;

import com.petredy.redmagic.Redmagic;
import com.petredy.redmagic.lib.ItemIndex;
import com.petredy.redmagic.lib.Reference;
import com.petredy.redmagic.redenergy.EnergyMap;
import com.petredy.redmagic.redenergy.RedEnergy;
import com.petredy.redmagic.redvalue.element.Composition;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemTool;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;

public class ItemJackhammer extends ItemTool{																		
	
    public static final Block[] blocksEffectiveAgainst = new Block[] {Block.cobblestone, Block.stoneDoubleSlab, Block.stoneSingleSlab, Block.stone, Block.sandStone, Block.cobblestoneMossy, Block.oreIron, Block.blockIron, Block.oreCoal, Block.blockGold, Block.oreGold, Block.oreDiamond, Block.blockDiamond, Block.ice, Block.netherrack, Block.oreLapis, Block.blockLapis, Block.oreRedstone, Block.oreRedstoneGlowing, Block.rail, Block.railDetector, Block.railPowered, Block.railActivator, Block.grass, Block.dirt, Block.sand, Block.gravel, Block.snow, Block.blockSnow, Block.blockClay, Block.tilledField, Block.slowSand, Block.mycelium};

	
	public ItemJackhammer(int par1) {
		super(par1, 2, Items.rheniumMaterial, blocksEffectiveAgainst);
		this.setUnlocalizedName(ItemIndex.JACKHAMMER_NAME);
		this.setCreativeTab(Redmagic.tabRedmagic);
	}

	public void registerIcons(IconRegister iconRegister){
		this.itemIcon = iconRegister.registerIcon(Reference.MOD_ID + ":" + ItemIndex.JACKHAMMER_NAME);
	}
	
	public void onUpdate(ItemStack par1ItemStack, World par2World, Entity par3Entity, int par4, boolean par5) {
		if(par1ItemStack.getItemDamage() > 0){
			RedEnergy energy = new RedEnergy(par2World.provider.dimensionId, (int)(par3Entity.posX / 16), (int)(par3Entity.posZ / 16), Composition.getStandard(0, 0.1f, 0, 0, 0));
			if(EnergyMap.consumeEnergy(energy.copy()).isEqual(energy.copy())){
				par1ItemStack.setItemDamage(par1ItemStack.getItemDamage() - 1);
			}
		}
	}


    public boolean hitEntity(ItemStack par1ItemStack, EntityLivingBase par2EntityLivingBase, EntityLivingBase par3EntityLivingBase)
    {
        if(par1ItemStack.getItemDamage() < par1ItemStack.getMaxDamage())par1ItemStack.damageItem(2, par3EntityLivingBase);
        return true;
    }
	
	public boolean onBlockDestroyed(ItemStack par1ItemStack, World par2World, int par3, int par4, int par5, int par6, EntityLivingBase par7EntityLivingBase)
    {
        if ((double)Block.blocksList[par3].getBlockHardness(par2World, par4, par5, par6) != 0.0D)
        {
            if(par1ItemStack.getItemDamage() + 1 <= par1ItemStack.getMaxDamage())par1ItemStack.setItemDamage(par1ItemStack.getItemDamage() + 1);
        }

        return true;
    }
	
	@Override
    public float getStrVsBlock(ItemStack stack, Block block, int meta) 
    {
		if(stack.getItemDamage() < stack.getMaxDamage()){
	        if (ForgeHooks.isToolEffective(stack, block, meta))
	        {
	            return efficiencyOnProperMaterial;
	        }
		}
        return getStrVsBlock(stack, block);
    }
	
	public boolean canHarvestBlock(Block par1Block)
    {
        return par1Block == Block.obsidian ? this.toolMaterial.getHarvestLevel() == 3 : (par1Block != Block.blockDiamond && par1Block != Block.oreDiamond ? (par1Block != Block.oreEmerald && par1Block != Block.blockEmerald ? (par1Block != Block.blockGold && par1Block != Block.oreGold ? (par1Block != Block.blockIron && par1Block != Block.oreIron ? (par1Block != Block.blockLapis && par1Block != Block.oreLapis ? (par1Block != Block.oreRedstone && par1Block != Block.oreRedstoneGlowing ? (par1Block.blockMaterial == Material.rock ? true : (par1Block.blockMaterial == Material.iron ? true : par1Block.blockMaterial == Material.anvil)) : this.toolMaterial.getHarvestLevel() >= 2) : this.toolMaterial.getHarvestLevel() >= 1) : this.toolMaterial.getHarvestLevel() >= 1) : this.toolMaterial.getHarvestLevel() >= 2) : this.toolMaterial.getHarvestLevel() >= 2) : this.toolMaterial.getHarvestLevel() >= 2);
    }

    public float getStrVsBlock(ItemStack par1ItemStack, Block par2Block)
    {
        if(par1ItemStack.getItemDamage() < par1ItemStack.getMaxDamage()) return par2Block != null && (par2Block.blockMaterial == Material.iron || par2Block.blockMaterial == Material.anvil || par2Block.blockMaterial == Material.rock) ? this.efficiencyOnProperMaterial : super.getStrVsBlock(par1ItemStack, par2Block);
        return 1.0f;
    }
}
