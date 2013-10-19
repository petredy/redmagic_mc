package com.petredy.redmagic.redvalue.element;

import com.petredy.redmagic.lib.Elements;

public class ElementNature extends Element {

	public ElementNature(){
		name = Elements.NATURE;
	}
	
	public ElementNature(float influence) {
		super(influence);
		name = Elements.NATURE;
	}

}
