package redmagic.tileentities;

import java.util.ArrayList;
import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import redmagic.api.frame.ISoul;
import redmagic.blocks.BlockManager;
import redmagic.configuration.BlockIndex;
import redmagic.core.Logger;
import redmagic.helpers.ModifierHelper;
import redmagic.lib.sacrifice.SacrificeRegistry;

public class TileEntityCollector extends TileEntityInventory{
	
	public int activeCount = 0, maxActiveCount = 100;
	
	
	public TileEntityCollector() {
		super(1, "Collector");
	}
	
	public void updateEntity(){
		if(activeCount > maxActiveCount){
			if(this.inv[0] != null && this.inv[0].getItem() instanceof ISoul && this.worldObj.getBlockId(xCoord, yCoord - 3, zCoord) == BlockManager.soulJuice.blockID){
				this.sacrifice();
			}
			this.activeCount = 0;
			this.worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, BlockManager.collector.blockID, 0);
			worldObj.markBlockForRenderUpdate(xCoord, yCoord, zCoord);
		}
		if(activeCount > 0){
			activeCount++;
			this.worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, BlockManager.collector.blockID, ++blockMetadata);
		}
	}

	private void sacrifice() {
		Logger.log("sacrifice");
		List<ModifierHelper> modifiers = this.getModifiers();
		List<EntityItem> items = this.worldObj.getEntitiesWithinAABB(EntityItem.class, AxisAlignedBB.getBoundingBox(xCoord, yCoord - 3, zCoord, xCoord + 1, yCoord - 2, zCoord + 1));
		
		for(EntityItem item: items){
			if(item != null && item.getEntityItem() != null){
				SacrificeRegistry.changeSoul(item.getEntityItem(), this.inv[0], modifiers);
				item.setDead();
			}
		}
	}

	private List<ModifierHelper> getModifiers() {
		List<ModifierHelper> modifiers = new ArrayList<ModifierHelper>();
		if(this.worldObj.getBlockId(xCoord + 2, yCoord, zCoord) == BlockManager.crystal.blockID){
			int metadata = this.worldObj.getBlockMetadata(xCoord + 2, yCoord, zCoord);
			modifiers.add(this.getModifierByMetadata(metadata));
		}
		
		if(this.worldObj.getBlockId(xCoord - 2, yCoord, zCoord) == BlockManager.crystal.blockID){
			int metadata = this.worldObj.getBlockMetadata(xCoord - 2, yCoord, zCoord);
			modifiers.add(this.getModifierByMetadata(metadata));
		}
		
		if(this.worldObj.getBlockId(xCoord, yCoord, zCoord + 2) == BlockManager.crystal.blockID){
			int metadata = this.worldObj.getBlockMetadata(xCoord, yCoord, zCoord + 2);
			modifiers.add(this.getModifierByMetadata(metadata));
		}
		
		if(this.worldObj.getBlockId(xCoord, yCoord, zCoord - 2) == BlockManager.crystal.blockID){
			int metadata = this.worldObj.getBlockMetadata(xCoord, yCoord, zCoord - 2);
			modifiers.add(this.getModifierByMetadata(metadata));
		}
		return modifiers;
	}
	
	private ModifierHelper getModifierByMetadata(int metadata){
		switch(metadata){
		case BlockIndex.CRYSTAL_INTELLIGENCE_METADATA:
			return new ModifierHelper(BlockIndex.CRYSTAL_INTELLIGENCE_NAME,1);
		case BlockIndex.CRYSTAL_STRENGTH_METADATA:
			return new ModifierHelper(BlockIndex.CRYSTAL_STRENGTH_NAME, 1);
		case BlockIndex.CRYSTAL_CAPACITY_METADATA:
			return new ModifierHelper(BlockIndex.CRYSTAL_CAPACITY_NAME, 1);
		case BlockIndex.CRYSTAL_ILLUSION_METADATA:
			return new ModifierHelper(BlockIndex.CRYSTAL_ILLUSION_NAME, 1);
		}
		return null;
	}
	
	@SideOnly(Side.CLIENT)
    public AxisAlignedBB getRenderBoundingBox()
    {
        AxisAlignedBB bb = INFINITE_EXTENT_AABB;

        return bb;
    }
	

}
