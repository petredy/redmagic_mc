package com.petredy.redmagic.handlers;

import java.util.EnumSet;

import com.petredy.redmagic.knowledge.KnowledgeSystem;
import com.petredy.redmagic.utils.LogUtils;

import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

public class KnowledgeSystemHandler implements ITickHandler{

	@Override
	public void tickStart(EnumSet<TickType> type, Object... tickData) {

	}

	@Override
	public void tickEnd(EnumSet<TickType> type, Object... tickData) {
		if(type.contains(TickType.SERVER)){
			KnowledgeSystem.update();
		}
	}

	@Override
	public EnumSet<TickType> ticks() {
		return EnumSet.of(TickType.SERVER);
	}

	@Override
	public String getLabel() {
		// TODO Auto-generated method stub
		return "knowledgesystem";
	}

}
