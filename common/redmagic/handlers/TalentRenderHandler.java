package redmagic.handlers;

import java.util.EnumSet;

import redmagic.client.guis.talent.GuiTalent;

import net.minecraft.client.Minecraft;

import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

public class TalentRenderHandler implements ITickHandler{

	public static GuiTalent guiTalent = new GuiTalent(Minecraft.getMinecraft());
	
	@Override
	public void tickStart(EnumSet<TickType> type, Object... tickData) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tickEnd(EnumSet<TickType> type, Object... tickData) {
		if(type.contains(TickType.RENDER)){
			guiTalent.updateTalentWindow();
		}
		
	}

	@Override
	public EnumSet<TickType> ticks() {
		return EnumSet.of(TickType.RENDER);
	}

	@Override
	public String getLabel() {
		return this.getClass().getSimpleName();
	}

}
