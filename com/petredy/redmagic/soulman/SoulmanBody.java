package com.petredy.redmagic.soulman;

import com.petredy.redmagic.lib.Textures;

public class SoulmanBody {
	
	public SoulmanPart leftLeg;
	public SoulmanPart rightLeg;
	public SoulmanPart body;
	public SoulmanPart leftArm;
	public SoulmanPart rightArm;
	public SoulmanPart head;
	
	public SoulmanBody(){
		leftLeg = new SoulmanPart(0);
		rightLeg = new SoulmanPart(0);
		body = new SoulmanPart(0);
		head = new SoulmanPart(0);
		leftArm = new SoulmanPart(0);
		rightArm = new SoulmanPart(0);
	}
	
}
