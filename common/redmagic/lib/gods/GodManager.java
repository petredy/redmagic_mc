package redmagic.lib.gods;

import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;

import net.minecraft.util.Vec3;
import net.minecraft.world.World;

import redmagic.Redmagic;
import redmagic.configuration.SenseIndex;
import redmagic.helpers.LogHelper;
import redmagic.lib.influences.ClientInfluence;
import redmagic.lib.senses.PlayerSense;
import redmagic.lib.senses.Sense;

import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

public class GodManager implements ITickHandler{
	
	/**
	 * Gods
	 */
	
	public static HashMap<String, God> gods = new HashMap<String, God>();
	
	public static void registerGod(God god){
		gods.put(god.name, god);
	}
	
	public static Collection<God> getGods(){
		return gods.values();
	}
	
	
	/**
	 * Spheres
	 * They are influence areas of gods
	 */
	
	public static List<Sphere> spheres = new ArrayList<Sphere>();
	
	
	public static List<Sphere> getSpheres(){
		return spheres;
	}
	
	public static boolean addSphere(Sphere sphere){
		for(Sphere sph: spheres){
			if(Vec3.createVectorHelper(sph.x, sph.y, sph.z).distanceTo(Vec3.createVectorHelper(sphere.x, sphere.y, sphere.z)) < (sphere.range + sph.range))return false;
		}
		spheres.add(sphere);
		return true;
	}
	
	/***
	 * Senses
	 */
	
	public static HashMap<String, Sense> senses = new HashMap<String, Sense>();
	
	public static void registerSense(Sense sense){
		senses.put(sense.name, sense);
	}
	
	public static Sense getSense(String name){
		return senses.get(name);
	}
	
	
	@Override
	public void tickStart(EnumSet<TickType> type, Object... tickData) {
		
	}


	@Override
	public void tickEnd(EnumSet<TickType> type, Object... tickData) {
		if(type.contains(TickType.PLAYER)){
			PlayerSense playerSense = (PlayerSense) getSense(SenseIndex.PLAYER);
			if(playerSense != null){
				playerSense.perceive(tickData);
			}
		}
		if(type.contains(TickType.CLIENT)){
			World world = Redmagic.proxy.getClientWorld();
			for(God god: gods.values()){
				for(ClientInfluence influence: god.clientInfluences){
					influence.act(world, god);
				}
			}
		}
	}


	@Override
	public EnumSet<TickType> ticks() {
		return EnumSet.of(TickType.CLIENT, TickType.PLAYER, TickType.WORLD);
	}


	@Override
	public String getLabel() {
		return this.getClass().getSimpleName();
	}
	
	
	
}
