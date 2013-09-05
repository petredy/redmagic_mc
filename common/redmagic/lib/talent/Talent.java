package redmagic.lib.talent;

import java.util.ArrayList;
import java.util.List;

import redmagic.api.talent.IUnlockable;
import redmagic.blocks.BlockManager;
import redmagic.configuration.AbilityIndex;
import redmagic.configuration.PathIndex;
import redmagic.configuration.TalentIndex;
import redmagic.items.ItemManager;
import redmagic.lib.abilities.AltarAbility;
import redmagic.lib.abilities.ConstructionTableAbility;
import redmagic.lib.abilities.earth.CompressAbility;
import redmagic.lib.abilities.life.ExorcismAbility;
import redmagic.lib.abilities.life.SelfHealAbility;
import redmagic.lib.path.EarthPath;
import redmagic.lib.path.LifePath;
import redmagic.lib.path.WaterPath;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.AchievementList;
import net.minecraft.util.StatCollector;

public class Talent {
	public static List<Talent> talents = new ArrayList<Talent>();
	
	public static Talent life, lifeAltar, lifeConstructionTable, lifeSelfHeal, lifeExorcism;
	public static Talent earth, earthAltar, earthConstructionTable, earthCompress;
	public static Talent water, waterAltar, waterConstructionTable;

	public static int minDisplayColumn;

	public static int minDisplayRow;

	public static int maxDisplayColumn;

	public static int maxDisplayRow;
	
	public static Talent getTalent(String name) {
		for(Talent talent: talents){
			if(talent.unlockableName.equals(name)){
				return talent;
			}
		}
		return null;
	}
	
	
	
	static{
		/***
		 * --------------------------------------------------------------------------------------------------------------------
		 * Path of Life
		 */
		
		life = new Talent(PathIndex.LIFE, new ItemStack(Item.seeds), 2, 2, null, new LifePath());
		life.setSpecial();
		life.setPath();
		talents.add(life);
		
		lifeAltar = new Talent(AbilityIndex.ALTAR + "." + PathIndex.LIFE, new ItemStack(BlockManager.altar), 2, 4, life, new AltarAbility(PathIndex.LIFE)); 
		talents.add(lifeAltar);
		
		lifeConstructionTable = new Talent(AbilityIndex.CONSTRUCTION_TABLE + "." + PathIndex.LIFE, new ItemStack(BlockManager.construction), 2, 6, lifeAltar, new ConstructionTableAbility(PathIndex.LIFE));
		talents.add(lifeConstructionTable);
		
		lifeSelfHeal = new Talent(AbilityIndex.SELFHEAL, new ItemStack(Item.appleGold), 1, 7, lifeConstructionTable, new SelfHealAbility());
		talents.add(lifeSelfHeal);
		
		lifeExorcism = new Talent(AbilityIndex.EXORCISM, new ItemStack(Item.diamond), 3, 7, lifeConstructionTable, new ExorcismAbility());
		talents.add(lifeExorcism);
		
		//---------------------------------------------------------------------------------------------------------------------
		
		/***
		 * --------------------------------------------------------------------------------------------------------------------
		 * Path of Earth
		 */
		
		earth = new Talent(PathIndex.EARTH, new ItemStack(Block.dirt), 7, 2, null, new EarthPath());
		earth.setSpecial();
		earth.setPath();
		talents.add(earth);
		
		earthAltar = new Talent(AbilityIndex.ALTAR + "." + PathIndex.EARTH, new ItemStack(BlockManager.altar), 7 , 4, earth, new AltarAbility(PathIndex.EARTH));
		talents.add(earthAltar);
		
		earthConstructionTable = new Talent(AbilityIndex.CONSTRUCTION_TABLE + "." + PathIndex.EARTH, new ItemStack(BlockManager.construction), 7, 6, earthAltar, new ConstructionTableAbility(PathIndex.EARTH));
		talents.add(earthConstructionTable);
		
		earthCompress = new Talent(AbilityIndex.COMPRESS, new ItemStack(Block.pistonBase), 7, 8, earthConstructionTable, new CompressAbility());
		talents.add(earthCompress);
		
		// --------------------------------------------------------------------------------------------------------------------
		
		/***
		 * --------------------------------------------------------------------------------------------------------------------
		 * Path of Water
		 */
		
		water = new Talent(PathIndex.WATER, new ItemStack(Item.bucketWater), 9, 2, null, new WaterPath());
		water.setSpecial();
		water.setPath();
		talents.add(water);
		
		waterAltar = new Talent(AbilityIndex.ALTAR + "." + PathIndex.WATER, new ItemStack(BlockManager.altar), 9, 4, water, new AltarAbility(PathIndex.WATER));
		talents.add(waterAltar);
		
		waterConstructionTable = new Talent(AbilityIndex.CONSTRUCTION_TABLE + "." + PathIndex.WATER, new ItemStack(BlockManager.construction), 9, 6, waterAltar, new ConstructionTableAbility(PathIndex.WATER));
		talents.add(waterConstructionTable);
		
		// --------------------------------------------------------------------------------------------------------------------
		
	}
	
	public String name;
	public String unlockableName;
	public String desc;
	public ItemStack itemStack;
	public Talent parent;
	public IUnlockable unlockable;
	
	public int col;
	public int row;
	
	protected boolean path = false;;
	
	public boolean special = false;
	
	public Talent(String name, ItemStack stack, int col, int row, Talent parent, IUnlockable iUnlockable){
		this.unlockableName = name;
		this.name = TalentIndex.TALENT_PREFIX + "." + name + ".name";
		this.desc = TalentIndex.TALENT_PREFIX + "." + name + ".desc";
		this.itemStack = stack;
		this.parent = parent;
		this.col = col;
		this.row = row;
		this.unlockable = iUnlockable;
		
		if (col < Talent.minDisplayColumn)
        {
            Talent.minDisplayColumn = col;
        }

        if (row < Talent.minDisplayRow)
        {
        	Talent.minDisplayRow = row;
        }

        if (col > Talent.maxDisplayColumn)
        {
        	Talent.maxDisplayColumn = col;
        }

        if (row > Talent.maxDisplayRow)
        {
        	Talent.maxDisplayRow = row;
        }

	}
	
	public boolean isPath(){
		return this.path;
	}
	
	public Talent setPath(){
		this.path = true;
		return this;
	}
	
	public Talent setSpecial(){
		this.special = true;
		return this;
	}
	
	public boolean isSpecial(){
		return this.special;
	}
	
	public String getDescription(){
		return StatCollector.translateToLocal(this.desc);
	}
	
	public String getNameForDisplay(){
		return StatCollector.translateToLocal(this.name);
	}

	public boolean isTalent(Talent talent) {
		return this.unlockableName.equals(talent.unlockableName);
	}
	
}
