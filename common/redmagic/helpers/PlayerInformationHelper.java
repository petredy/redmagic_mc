package redmagic.helpers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import redmagic.configuration.Reference;
import redmagic.lib.player.PlayerInformation;
import redmagic.lib.player.PlayerInformationData;

public class PlayerInformationHelper {

	public static PlayerInformation load(EntityPlayer player){
		PlayerInformation information = PlayerInformationHelper.getPlayerInformation(player);
		if(information == null){
			information = PlayerInformationHelper.initialisePlayerInformation(player);
			PlayerInformationHelper.setPlayerInformation(information, player.worldObj);
		}
		return information;
	}
	
	public static void save(EntityPlayer player) {
		PlayerInformation information = PlayerInformationHelper.getPlayerInformation(player);
		PlayerInformationHelper.setPlayerInformation(information, player.worldObj);
	}
	
	
	public static PlayerInformation getPlayerInformation(EntityPlayer player){
		
		PlayerInformationData data = PlayerInformationHelper.getPlayerInformationData(player.username, player.worldObj);

		if(data != null){
			return data.information;
		}else{
			return null;
		}
	}
	
	public static void setPlayerInformation(PlayerInformation information, World world){
		PlayerInformationData data = null;
		if(information != null){
			data = PlayerInformationHelper.getPlayerInformationData(information.username, world);
			if(data == null){
				data = new PlayerInformationData(PlayerInformationHelper.getPlayerInformationDataToken(information.username));
				data.information = information;
				data.markDirty();
				world.setItemData(PlayerInformationHelper.getPlayerInformationDataToken(information.username), data);
			}else{
				data.information = information;
				data.markDirty();
				world.perWorldStorage.saveAllData();
			}
		}
	}
	
	
	public static PlayerInformation initialisePlayerInformation(EntityPlayer player) {
		PlayerInformation information = new PlayerInformation(player);
		
		//PlayerInformationHelper.setPlayerInformation(information, player.worldObj);
		
		return information;
	}


	public static PlayerInformationData getPlayerInformationData(String username, World world){
		return (PlayerInformationData) world.loadItemData(PlayerInformationData.class, PlayerInformationHelper.getPlayerInformationDataToken(username));
	}
	
	public static String getPlayerInformationDataToken(String username){
		return Reference.MOD_ID + "." + username;
	}
	
	
	
	
	
	
}
