package com.petredy.redmagic.soul;

import java.util.HashMap;

public class MemoryDictionary {

	public HashMap<String, Memory> memories = new HashMap<String, Memory>();

	public boolean knows(String name) {
		return memories.get(name) != null && memories.get(name).isWellKnown();
	}

	public Memory get(String name) {
		return memories.get(name);
	}
	
	public void learn(Memory mem){
		memories.put(mem.name, mem);
	}
	
}
