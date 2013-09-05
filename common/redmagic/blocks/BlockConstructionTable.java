package redmagic.blocks;

import redmagic.Redmagic;
import redmagic.configuration.AbilityIndex;
import redmagic.configuration.BlockIndex;
import redmagic.configuration.RenderIndex;
import redmagic.handlers.TalentRenderHandler;
import redmagic.helpers.BlockHelper;
import redmagic.helpers.InventoryHelper;
import redmagic.helpers.LogHelper;
import redmagic.helpers.PlayerInformationHelper;
import redmagic.items.ItemConstructionTool;
import redmagic.lib.crafting.CraftingManager;
import redmagic.lib.player.PlayerInformation;
import redmagic.lib.talent.Talent;
import redmagic.tileentities.TileEntityConstructionTable;
import redmagic.tileentities.TileEntityRune;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockConstructionTable extends BlockContainer{

	protected BlockConstructionTable(int par1) {
		super(par1, Material.wood);
		this.setUnlocalizedName(BlockIndex.CONSTRUCTION_TABLE_NAME);
		this.setCreativeTab(Redmagic.tabRedmagic);
		this.setBounds();
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntityConstructionTable();
	}
	
	public static boolean isNormalCube(int par0)
    {
		return false;
    }
	
	public boolean renderAsNormalBlock()
    {
        return false;
    }
	
	public boolean isOpaqueCube()
    {
        return false;
    }
	
	public int getRenderType()
    {
        return RenderIndex.CONSTRUCTION_TABLE;
    }
	
	public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
    {
        this.setBounds();
    }
	
	private void setBounds()
    {
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
    }
	
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float offX, float offY, float offZ)
    {
		if(side == 1){
			int index = BlockHelper.getHitIndex3x3(offX, offZ);
			ItemStack current = player.getCurrentEquippedItem();
			TileEntityConstructionTable entity = (TileEntityConstructionTable) world.getBlockTileEntity(x, y, z);
			if(current == null){
				LogHelper.log(index);
				if(index > -1){
					ItemStack atmItem = entity.getStackInSlot(index);
					LogHelper.log("drop " + atmItem);
					this.dropItem(atmItem, world, x, y, z);
					entity.setInventorySlotContents(index, null);
					return true;
				}
			}else{	
				if(current.getItem() instanceof ItemConstructionTool){
					//craft
					int[] order = new int[]{ 0, 1, 2, 3, 4, 5, 6, 7, 8};
					switch(entity.side){
					case 2:
						order = entity.craftingSideNorth;
						break;
					case 3:
						order = entity.craftingSideSouth;
						break;
					case 4:
						order = entity.craftingSideEast;
						break;
					case 5:
						order = entity.craftingSideWest;
						break;
					}
					ItemStack output = CraftingManager.find(entity.inv, PlayerInformationHelper.getPlayerInformation(player), order);
					if(output != null){
						if(!world.isRemote)InventoryHelper.dropItemStack(output.copy(), world, x, y, z);
						for(int i = 0; i < entity.getSizeInventory(); i++){
							entity.decrStackSize(i, 1);
						}
					}
				}else if(index > -1){
					ItemStack atmItem = entity.getStackInSlot(index);
					this.dropItem(atmItem, world, x, y, z);
					ItemStack curr = current.copy();
					curr.stackSize = 1;
					entity.setInventorySlotContents(index, curr);
					player.inventory.decrStackSize(player.inventory.currentItem, 1);
				}
				return true;
			}
		}
		return false;
    }
	
	private void dropItem(ItemStack stack, World world, float x, float y, float z){
		if(stack != null && !world.isRemote){
			InventoryHelper.dropItemStack(stack, world, x, y, z);
		}
	}
	
	public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLivingBase, ItemStack par6ItemStack)
    {
		this.unlockConstructionTableTalent(par5EntityLivingBase);
        TileEntityConstructionTable construction = (TileEntityConstructionTable) par1World.getBlockTileEntity(par2, par3, par4);
    	int l = MathHelper.floor_double((double)(par5EntityLivingBase.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
    	if(l == 0){
    		construction.side = 2;
    	}else if(l == 1){
    		construction.side = 5;
    	}else if(l == 2){
    		construction.side = 3;
    	}else if(l == 3){
    		construction.side = 4;
    	}
    }

	private void unlockConstructionTableTalent(EntityLivingBase par5EntityLivingBase) {
		if(par5EntityLivingBase instanceof EntityPlayer){
			EntityPlayer player = (EntityPlayer) par5EntityLivingBase;
			PlayerInformation info = PlayerInformationHelper.getPlayerInformation(player);
			if(info.pathManager.hasPath() && info.pathManager.isTalentUnlockable(Talent.getTalent(AbilityIndex.CONSTRUCTION_TABLE + "." + info.pathManager.path.name)) && !info.pathManager.isTalentUnlocked(Talent.getTalent(AbilityIndex.CONSTRUCTION_TABLE + "." + info.pathManager.path.name))){
				Talent talent = Talent.getTalent(AbilityIndex.CONSTRUCTION_TABLE + "." + info.pathManager.path.name);
				talent.unlockable.unlock(player, false);
				TalentRenderHandler.guiTalent.queueTakenTalent(talent);
			}
		}
	}
	
	@Override
	public void breakBlock(World par1World, int par2, int par3, int par4, int par5, int par6){
		InventoryHelper.dropInventory((IInventory) par1World.getBlockTileEntity(par2, par3, par4), par1World, par2, par3, par4);
		super.breakBlock(par1World, par2, par3, par4, par5, par6);
    }


}
