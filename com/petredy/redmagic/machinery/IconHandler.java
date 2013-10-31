package com.petredy.redmagic.machinery;

import com.petredy.redmagic.api.machinery.IMachineIconProvider;
import com.petredy.redmagic.utils.LogUtils;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;

public class IconHandler {

	protected Icon small;
	protected Icon[] largeActive, largeInactive;
	protected int metadata;
	
	public IconHandler(int metadata, IconRegister iconRegister, IMachineIconProvider machine){
		this.metadata = metadata;
		this.initialiseSmallMachine(iconRegister, machine);
		this.initialiseLargeMachine(iconRegister, machine);
	}

	private void initialiseLargeMachine(IconRegister iconRegister, IMachineIconProvider machine) {
		largeActive = machine.getIconForLargeMachineActive(iconRegister);
		largeInactive = machine.getIconForLargeMachineInactive(iconRegister);
	}

	private void initialiseSmallMachine(IconRegister iconRegister, IMachineIconProvider machine) {
		small = machine.getIconForSmallMachine(iconRegister);
	}
	
	public Icon getSmallMachineIcon(){
		return small;
	}
	
	public Icon[] getLargeMachineActive(){
		return largeActive;
	}
	
	public Icon[] getLargeMachineInactive(){
		return largeInactive;
	}
	
	public Icon getLargeMachineActiveForPosition(int position){
		return largeActive[position];
	}
	
	public Icon getLargeMachineInactiveForPosition(int position){
		return largeInactive[position];
	}
	
	
	
	
}
