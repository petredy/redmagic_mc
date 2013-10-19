package com.petredy.redmagic.redenergy;

import com.petredy.redmagic.redvalue.element.Composition;
import com.petredy.redmagic.redvalue.element.Element;

import net.minecraft.nbt.NBTTagCompound;

public class RedEnergy {
	
	public Composition composition;
	public int x, z, dimension;
	
	public RedEnergy(){
		this.composition = Composition.getStandard(0, 0, 0, 0, 0);
	}
	
	public RedEnergy(int dimension, int x, int z){
		this.dimension = dimension;
		this.x = x;
		this.z = z;
	}
	
	public RedEnergy(int dimension, int x, int z, Composition comp){
		this(dimension, x, z);
		this.composition = comp;
	}
	
	public float getValue(String element){
		return composition.getValue(element);
	}
	
	public RedEnergy merge(RedEnergy energy){
		return new RedEnergy(this.dimension, this.x, this.z, this.composition.merge(energy.composition));
	}
	
	public Composition disjoin(Composition comp){
		return this.composition.disjoin(comp);
	}
	
	public RedEnergy disjoin(RedEnergy energy){
		return new RedEnergy(energy.dimension, energy.x, energy.z, this.composition.disjoin(energy.composition));
	}
	
	public float decreaseValue(String element, float value){
		return this.composition.decreaseValue(element, value);
	}
	
	public void addValue(String element, float value){
		this.composition.addValue(element, value);
	}
	
	public RedEnergy minus(RedEnergy notCollected) {
		return new RedEnergy(this.dimension, this.x, this.z, this.composition.minus(notCollected.composition));
	}
	
	public boolean isEmpty(){
		return this.composition.isEmpty();
	}
	
	public boolean contains(RedEnergy energy){
		return this.contains(energy.composition);
	}
	
	public boolean contains(Composition composition){
		return this.composition.contains(composition);	
	}
	
	public RedEnergy copy(){
		return new RedEnergy(this.dimension, this.x, this.z, this.composition.copy());
	}
	
	
	public boolean isEqual(RedEnergy other){
		if(this.dimension != other.dimension)return false;
		if(this.x != other.x)return false;
		if(this.z != other.z)return false;
		if(!this.composition.isEqual(other.composition))return false;
		return true;
	}
	
	public static RedEnergy getFrom(Composition composition){
		return new RedEnergy(0, 0, 0, composition);
	}
	
	public static RedEnergy disjoin(RedEnergy energy, RedEnergy energy2) {
		RedEnergy first = energy.copy();
		RedEnergy second = energy2.copy();
		return first.disjoin(second);
	}
	
	public void readFromNBT(NBTTagCompound tag){
		this.dimension = tag.getInteger("dimension");
		this.x = tag.getInteger("x");
		this.z = tag.getInteger("z");
		this.composition = Composition.loadFromNBT(tag);
	}
	
	public static RedEnergy loadFromNBT(NBTTagCompound tag){
		RedEnergy energy = new RedEnergy();
		energy.readFromNBT(tag);
		return energy;
	}
	
	public void writeToNBT(NBTTagCompound tag){
		tag.setInteger("dimension", dimension);
		tag.setInteger("x", x);
		tag.setInteger("z", z);
		this.composition.writeToNBT(tag);
	}
	
	@Override
	public String toString(){
		String str = "RedEnergy: ";
		for(Element element: this.composition.getAllElements()){
			str += " | ";
			str += element.name + "=" + element.influence;
		}
		return str;
	}
}
