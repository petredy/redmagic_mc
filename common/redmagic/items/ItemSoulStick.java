package redmagic.items;

import cpw.mods.fml.common.network.PacketDispatcher;
import redmagic.Redmagic;
import redmagic.api.items.IKeyBound;
import redmagic.blocks.BlockManager;
import redmagic.configuration.ItemIndex;
import redmagic.configuration.Reference;
import redmagic.helpers.BlockHelper;
import redmagic.helpers.ItemHelper;
import redmagic.tileentities.TileEntitySoulBlock;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class ItemSoulStick extends Item implements IKeyBound{

	public ItemSoulStick(int par1){
		super(par1);
		this.setCreativeTab(Redmagic.tabRedMagic);
		this.setUnlocalizedName(ItemIndex.STICK_NAME);
	}
	
	@Override
	public void registerIcons(IconRegister iconRegister){
		this.itemIcon = iconRegister.registerIcon(Reference.MOD_ID + ":" + ItemIndex.STICK_NAME);
	}
	
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
    {
		MovingObjectPosition position = this.getMovingObjectPositionFromPlayer(world, player, true);
		if(position != null){
			int id = world.getBlockId(position.blockX, position.blockY, position.blockZ);
			int metadata = world.getBlockMetadata(position.blockX, position.blockY, position.blockZ);
			if(id != BlockManager.soulBlock.blockID && Block.blocksList[id].isBlockNormalCube(world, position.blockX, position.blockY, position.blockZ)){
				ItemHelper.setInteger(stack, "redmagic.id", id);
				ItemHelper.setInteger(stack, "redmagic.metadata", metadata);
			}else if(id == BlockManager.soulBlock.blockID){
				id = ItemHelper.getInteger(stack, "redmagic.id");
				metadata = ItemHelper.getInteger(stack, "redmagic.metadata");
				if(player.isSneaking()){
					id = 0;
					metadata = 0;
				}else{
					player.swingItem();
				}
				TileEntitySoulBlock entity = (TileEntitySoulBlock) world.getBlockTileEntity(position.blockX, position.blockY, position.blockZ);
				entity.blockID[position.sideHit] = id;
				entity.blockMetadata[position.sideHit] = metadata;
				NBTTagCompound tag = new NBTTagCompound();
				entity.writeToNBT(tag);
				PacketDispatcher.sendPacketToAllPlayers(new Packet132TileEntityData(position.blockX, position.blockY, position.blockZ, 0, tag));
				world.markBlockForUpdate(position.blockX, position.blockY, position.blockZ);
			}
		}
        return stack;
    }

	@Override
	public void doKeyBindingAction(EntityPlayer player, ItemStack stack, String keyBinding) {
		MovingObjectPosition position = ItemHelper.getMovingObjectPosition(player.worldObj, player, true);
		if(position != null){
			int id = player.worldObj.getBlockId(position.blockX, position.blockY, position.blockZ);
			int metadata = player.worldObj.getBlockMetadata(position.blockX, position.blockY, position.blockZ);
			if(id != 0 && id != BlockManager.soulBlock.blockID){
				if(player.inventory.consumeInventoryItem(ItemManager.soulCrystal.itemID)){
					BlockHelper.breakBlock(player.worldObj, position.blockX, position.blockY, position.blockZ, 10000, true);
					player.worldObj.setBlock(position.blockX, position.blockY, position.blockZ, BlockManager.soulBlock.blockID);
					TileEntitySoulBlock entity = (TileEntitySoulBlock) player.worldObj.getBlockTileEntity(position.blockX, position.blockY, position.blockZ);
					if(entity != null){
						player.swingItem();
						for(int i = 0; i < 6; i++){
							entity.blockID[i] = id;
							entity.blockMetadata[i] = metadata;
						}
					}
				}
			}
		}
		
	}
	
	
	
}
