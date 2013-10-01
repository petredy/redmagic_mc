package com.petredy.redmagic.soul;

import java.util.ArrayList;
import java.util.List;

import com.petredy.redmagic.utils.BlockUtils.VirtualBlock;

public class MemoryBlock extends Memory{

	List<VirtualBlock> occurrence = new ArrayList<VirtualBlock>();
	
	public boolean inventory;
	
	public MemoryBlock(String name, int x, int y, int z) {
		super(name);
		this.occurrence.add(new VirtualBlock(x, y, z));
		
	}

}
