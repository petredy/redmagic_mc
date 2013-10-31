package com.petredy.redmagic.api.machinery;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;

public interface IMachineIconProvider {

	public Icon getIconForSmallMachine(IconRegister iconRegister);
	
	public Icon[] getIconForLargeMachineInactive(IconRegister iconRegister);
	
	public Icon[] getIconForLargeMachineActive(IconRegister iconRegister);
	
}
