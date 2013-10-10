package com.petredy.redmagic.knowledge;

import java.util.Collection;
import java.util.HashMap;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;

import com.petredy.redmagic.tileentities.TileEntityKnowledgeTransceiver;
import com.petredy.redmagic.utils.ArtifactUtils;
import com.petredy.redmagic.utils.LogUtils;
import com.petredy.redmagic.utils.WorldSavedDataUtils;

public class KnowledgeSystem {

	protected static HashMap<String, Knowledge> knowledges = new HashMap<String, Knowledge>();
	public static int minDisplayColumn;
	public static int minDisplayRow;
	public static int maxDisplayColumn;
	public static int maxDisplayRow;
	
	public static Collection<Knowledge> getAll(){
		return knowledges.values();
	}
	
	public static void addKnowledge(Knowledge k){
		knowledges.put(k.name, k);
	}
	
	public static Knowledge getKnowledge(String name){
		return knowledges.get(name);
	}

	public static boolean hasKnowledgeUnlocked(Knowledge knowledge) {
		return knowledge.progress >= 100f;
	}

	public static boolean canUnlockKnowledge(Knowledge knowledge) {
		if(knowledge.parent == null)return true;
		return hasKnowledgeUnlocked(knowledge.parent);
	}

	public static boolean isActive(Knowledge knowledge, TileEntityKnowledgeTransceiver entity) {
		if(entity != null){
			return ArtifactUtils.isKnowledge(entity.artifact, knowledge);
		}
		return false;
	}
	
	public static void save(World world){
		NBTTagCompound tag = new NBTTagCompound();
		NBTTagList list = new NBTTagList();
		for(Knowledge k: knowledges.values()){
			if(k != null){
				NBTTagCompound kTag = new NBTTagCompound();
				k.writeToNBT(kTag);
				list.appendTag(kTag);
			}
		}
		tag.setTag("knowledges", list);
		
		WorldSavedDataUtils.saveData(world, "redmagic.knowledge.system", tag);
	}
	
	public static void load(World world){
		NBTTagCompound tag = WorldSavedDataUtils.loadData(world, "redmagic.knowledge.system");
		if(tag != null){
			NBTTagList list = tag.getTagList("knowledges");
			for(int i = 0; i < list.tagCount(); i++){
				NBTTagCompound kTag = (NBTTagCompound) list.tagAt(i);
				String name = kTag.getString("name");
				Knowledge k = getKnowledge(name);
				if(k != null){
					k.readFromNBT(kTag);
				}
			}
		}
	}

	public static void update() {
		LogUtils.log("update");
		for(Knowledge knowledge: knowledges.values()){
			if(knowledge != null && knowledge.progress < 100f){
				knowledge.progress += knowledge.speed;
				LogUtils.log(knowledge.progress);
			}
		}
	}
}
