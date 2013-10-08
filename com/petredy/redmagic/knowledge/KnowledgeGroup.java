package com.petredy.redmagic.knowledge;

import java.util.Collection;
import java.util.HashMap;

public class KnowledgeGroup {

	public HashMap<String, Knowledge> knowledges = new HashMap<String, Knowledge>();

	public void addKnowledge(Knowledge knowledge) {
		knowledges.put(knowledge.name, knowledge);
	}
	
	public Knowledge get(String name){
		return knowledges.get(name);
	}
	
	public Collection<Knowledge> getAll(){
		return knowledges.values();
	}
}
