package com.petredy.redmagic.utils;

import com.petredy.redmagic.knowledge.Knowledge;

import net.minecraft.item.ItemStack;

public class ArtifactUtils {

	public static void setKnowledge(ItemStack artifact, Knowledge knowledge){
		if(artifact != null){
			ItemUtils.setString(artifact, "redmagic.artifact.knowledge", knowledge.name);
		}
	}
	
	public static String getKnowledge(ItemStack artifact){
		if(artifact != null){
			return ItemUtils.getString(artifact, "redmagic.artifact.knowledge");
		}
		return null;
	}
	
	public static boolean isKnowledge(ItemStack stack, Knowledge knowledge){
		if(stack != null){
			String name = getKnowledge(stack);
			if(name == null)return false;
			return name.equals(knowledge.name);
		}
		return false;
	}
	
}
