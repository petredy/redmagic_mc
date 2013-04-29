package redmagic.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import redmagic.Redmagic;
import redmagic.api.workTable.ITool;
import redmagic.configuration.ItemIndex;
import redmagic.configuration.Reference;
import redmagic.configuration.Sounds;
import redmagic.tileentities.TileEntityWorkTable;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class ItemToolHammer extends Item implements ITool{

	public ItemToolHammer(int par1) {
		super(par1);
		this.setCreativeTab(Redmagic.tabRedMagic);
		this.setUnlocalizedName(ItemIndex.HAMMER_NAME);
		this.setMaxStackSize(1);
	}
	
	@SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister)
    {
        this.itemIcon = par1IconRegister.registerIcon(Reference.MOD_ID + ":" + ItemIndex.HAMMER_NAME);
    }

	@Override
	public void build(World world, int x, int y, int z, EntityPlayer player) {
		world.playSoundEffect(x + 0.5, y + 0.5, z + 0.5, Sounds.HAMMER, 1.0F, 0.9F + world.rand.nextFloat() * 0.1F);
		TileEntityWorkTable entity = (TileEntityWorkTable) world.getBlockTileEntity(x, y, z);
		if(entity != null){
			entity.craft(player);
		}
	}
	
	public void onUpdate(ItemStack par1ItemStack, World par2World, Entity par3Entity, int par4, boolean par5) {
		if(par5 && par3Entity instanceof EntityPlayer){
			MovingObjectPosition position = this.getMovingObjectPositionFromPlayer(par2World, (EntityPlayer) par3Entity, true);
			if(position != null){
				TileEntity entity = par2World.getBlockTileEntity(position.blockX, position.blockY, position.blockZ);
				if(entity instanceof TileEntityWorkTable){
					((TileEntityWorkTable)entity).displayMode();
				}
			}
		}
	}

}
