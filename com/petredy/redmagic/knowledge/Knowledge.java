package com.petredy.redmagic.knowledge;

import com.petredy.redmagic.client.guis.knowledge.GuiKnowledge;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

public class Knowledge {

	
	public static final String PREFIX = "knowledge.";
	

	public String name;
	
	public float progress;
	public float speed = 0.0001f;

	public int displayColumn;

	public int displayRow;
	public Knowledge parent = null;
	
	public ResourceLocation texture;


	public Class gui = GuiKnowledge.class;
	
	public Knowledge(String name, float progress, int col, int row, Knowledge parent, ResourceLocation texture) {
		this.name = name;
		this.progress = progress;
		this.displayColumn = col;
		this.displayRow = row;
		this.parent = parent;
		this.texture = texture;
		
		
		if (col < KnowledgeSystem.minDisplayColumn)
        {
            KnowledgeSystem.minDisplayColumn = col;
        }

        if (row < KnowledgeSystem.minDisplayRow)
        {
            KnowledgeSystem.minDisplayRow = row;
        }

        if (col > KnowledgeSystem.maxDisplayColumn)
        {
            KnowledgeSystem.maxDisplayColumn = col;
        }

        if (row > KnowledgeSystem.maxDisplayRow)
        {
            KnowledgeSystem.maxDisplayRow = row;
        }
	}

	public String getName() {
		return StatCollector.translateToLocal(PREFIX + name + ".name");
	}

	public String getDescription() {
		return StatCollector.translateToLocal(PREFIX + name + ".description");
	}
	
	public void readFromNBT(NBTTagCompound tag){
		this.progress = tag.getFloat("progress");
	}
	
	public void writeToNBT(NBTTagCompound tag){
		tag.setFloat("progress", progress);
		tag.setString("name", name);
	}
	
}
