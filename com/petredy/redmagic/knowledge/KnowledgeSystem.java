package com.petredy.redmagic.knowledge;

import java.util.Collection;
import java.util.HashMap;

public class KnowledgeSystem {

	protected static HashMap<String, KnowledgeGroup> groups = new HashMap<String, KnowledgeGroup>();
	
	
	public static Collection<KnowledgeGroup> getGroups(){
		return groups.values();
	}
	
	public static KnowledgeGroup getGroup(String name){
		return groups.get(name);
	}
	
	public static void addGroup(String name, KnowledgeGroup group){
		groups.put(name, group);
	}
	
	public static void addKnowledge(String group, Knowledge knowledge){
		KnowledgeGroup g = groups.get(group);
		if(g != null)g.addKnowledge(knowledge);
	}
	
	public Knowledge getKnowledge(String group, String name){
		KnowledgeGroup g = groups.get(group);
		if(g != null)return g.get(name);
		return null;
	}
}
