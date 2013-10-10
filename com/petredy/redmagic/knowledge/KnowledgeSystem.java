package com.petredy.redmagic.knowledge;

import java.util.Collection;
import java.util.HashMap;

import com.petredy.redmagic.tileentities.TileEntityKnowledgeTransceiver;
import com.petredy.redmagic.utils.ArtifactUtils;

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
}
