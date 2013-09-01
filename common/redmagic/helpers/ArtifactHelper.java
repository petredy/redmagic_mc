package redmagic.helpers;

import redmagic.items.ItemManager;
import redmagic.lib.artifact.ArtifactInformation;
import redmagic.lib.talent.Talent;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class ArtifactHelper {

	public static ArtifactInformation getArtifactInformation(ItemStack stack){
		if(stack != null && stack.stackTagCompound != null){
			ArtifactInformation information = ArtifactInformation.loadFromNBT(stack.stackTagCompound);
			return information;
		}
		return null;
	}
	
	public static void setArtifactInformation(ArtifactInformation information, ItemStack stack){
		if(stack != null){
			if(stack.stackTagCompound == null)stack.stackTagCompound = new NBTTagCompound();
			information.writeToNBT(stack.stackTagCompound);
		}
	}
	
	public static ItemStack getNewArtifact(Talent path){
		ItemStack stack = new ItemStack(ItemManager.artifact);
		stack.stackTagCompound = new NBTTagCompound();
		ArtifactInformation information = new ArtifactInformation(path);
		ArtifactHelper.setArtifactInformation(information, stack);
		return stack;
	}
	
}
