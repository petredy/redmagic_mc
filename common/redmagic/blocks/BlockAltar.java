package redmagic.blocks;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import redmagic.Redmagic;
import redmagic.configuration.AbilityIndex;
import redmagic.configuration.BlockIndex;
import redmagic.configuration.RenderIndex;
import redmagic.handlers.TalentRenderHandler;
import redmagic.helpers.ArtifactHelper;
import redmagic.helpers.InventoryHelper;
import redmagic.helpers.LogHelper;
import redmagic.helpers.PlayerInformationHelper;
import redmagic.items.ItemArtifact;
import redmagic.items.ItemManager;
import redmagic.lib.abilities.AltarAbility;
import redmagic.lib.artifact.ArtifactInformation;
import redmagic.lib.player.PlayerInformation;
import redmagic.lib.talent.Talent;
import redmagic.tileentities.TileEntityAltar;
import redmagic.tileentities.TileEntityRune;

public class BlockAltar extends BlockContainer{
	
	protected BlockAltar(int par1) {
		super(par1, Material.rock);
		this.setUnlocalizedName(BlockIndex.RUNE_NAME);
		this.setCreativeTab(Redmagic.tabRedmagic);
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntityAltar();
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
        return RenderIndex.ALTAR;
    }
	
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float offX, float offY, float offZ){
		ItemStack current = player.getCurrentEquippedItem();
		
		
		
		
		TileEntityAltar altar = (TileEntityAltar)world.getBlockTileEntity(x, y, z);
		
		if(altar != null){
			PlayerInformation information = PlayerInformationHelper.getPlayerInformation(player);
			if(information != null && information.pathManager.isTalentUnlockable(Talent.lifeExorcism) && altar.path != null && altar.path.isTalent(Talent.life) && current != null && current.isItemEqual(new ItemStack(Item.stick))){
				player.inventory.decrStackSize(player.inventory.currentItem, 1);
				ItemStack staff = new ItemStack(ItemManager.staff);
				InventoryHelper.dropItemStack(staff, world, x + 0.5, y + 1, z + 0.5);
				information.pathManager.setTalentUnlocked(Talent.lifeExorcism);
				TalentRenderHandler.guiTalent.queueTakenTalent(Talent.lifeExorcism);
				return true;
			}
			
			if(altar.path != null && !(current != null && current.getItem() instanceof ItemBlock)){
				if(!world.isRemote)InventoryHelper.dropItemStack(ArtifactHelper.getNewArtifact(altar.path), world, x, y, z);
				altar.unsetPath();
			}
			if(current != null && current.getItem() instanceof ItemArtifact){
				ArtifactInformation artifact = ArtifactHelper.getArtifactInformation(current);
				altar.setPath(artifact.getPath());
				// Unlock Altar Talent
				Talent.getTalent(AbilityIndex.ALTAR + "." + artifact.getPath().unlockableName).unlockable.unlock(player, false);
				TalentRenderHandler.guiTalent.queueTakenTalent(Talent.getTalent(AbilityIndex.ALTAR + "." + artifact.getPath().unlockableName));
				
				player.inventory.setInventorySlotContents(player.inventory.currentItem, null);
			}
			return true;
		}
		
		return false;
    }
	
}
