package redmagic.lib.souls;

import java.util.Random;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.liquids.LiquidDictionary;
import redmagic.blocks.multi.tree.TreeStructure;
import redmagic.configuration.LogicIndex;
import redmagic.core.Logger;
import redmagic.helpers.SoulHelper;
import redmagic.lib.souls.container.SoulContainer;
import redmagic.lib.souls.gui.SoulGui;
import redmagic.lib.souls.inventory.SoulInventory;
import redmagic.lib.tree.SoulBlock;
import redmagic.tileentities.tree.TileEntityTreeWood;

public class SoulFilter extends Soul{

	public int update = 0, neededUpdates = 100;
	
	@Override
	public void init(ItemStack soul, TileEntityTreeWood entity, TreeStructure structure, int x, int y, int z) {
		
	}

	@Override
	public void onUpdate(ItemStack soul, TileEntityTreeWood entity, TreeStructure structure, int x, int y, int z) {
		if(update > neededUpdates){
			update = 0;
			int intelligence = SoulHelper.getIntelligence(soul);
			int strength = SoulHelper.getStrength(soul);
			int capacity = SoulHelper.getCapacity(soul);
			int illusion = SoulHelper.getIllusion(soul);
			int satisfaction = SoulHelper.getSatisfaction(soul);
			int leaves = structure.getBlockType(TreeStructure.leaveKey).size();
			leaves = leaves > capacity ? capacity : leaves;
			leaves = leaves * (LogicIndex.SOUL_MAX_INTELLIGENCE / intelligence);
			float multiplier = satisfaction < 50 ? 0.1F : satisfaction < 90 ? 0.3F : 1.0F;
			int crit = new Random().nextInt(100) < illusion ? 2 : 1;
			int essence = (int) (strength * leaves * multiplier * crit);
			entity.store(LiquidDictionary.getLiquid("Essence", essence));
		}
		update++;
		
		
	}

	@Override
	public void onWrench(ItemStack soul, TileEntityTreeWood entity, TreeStructure structure, EntityPlayer player, int x, int y, int z) {
		Logger.log("wrench");
		
	}

	@Override
	public void remove(ItemStack soul, TileEntityTreeWood entity, TreeStructure structure, int x, int y, int z) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public SoulInventory getInventory() {
		return null;
	}
	
	@Override
	public SoulGui getGui() {
		return null;
	}
	
	public SoulContainer getContainer(){
		return null;
	}

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void writeToNBT(NBTTagCompound tag) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onRedstoneOn(ItemStack soul, TileEntityTreeWood entity, TreeStructure structure, int x, int y, int z) {
		
	}

	@Override
	public void onRedstoneOff(ItemStack soul, TileEntityTreeWood entity, TreeStructure structure, int x, int y, int z) {
		
	}
	
	@Override
	public void onActivated(ItemStack soulStack, TileEntityTreeWood entity, TreeStructure structure, EntityPlayer player, int x, int y, int z) {
		
	}
}
