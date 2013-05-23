package redmagic.tileentities;

import java.util.Random;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import redmagic.Redmagic;
import redmagic.api.glasses.IViewable;
import redmagic.configuration.GuiIndex;
import redmagic.configuration.LogicIndex;
import redmagic.core.Logger;
import redmagic.helpers.FragmentHelper;
import redmagic.helpers.SoulHelper;
import redmagic.items.ItemManager;

public class TileEntitySoulForge extends TileEntityInventory implements IViewable{

	public int soulSlot = 0;
	
	public TileEntitySoulForge() {
		super(5, "Soul Forge");
	}

	@Override
	public void view(EntityPlayer player, ItemStack stack) {
		player.openGui(Redmagic.instance, GuiIndex.SOUL_FORGE, player.worldObj, xCoord, yCoord, zCoord);
	}
	
	public void forge(){
		if(this.hasSoul()){
			this.forgeSoul();
		}else{
			this.forgeNewSoul();
		}
		for(int i = 1; i < 5; i++){
			this.inv[i] = null;
		}
	}

	private void forgeNewSoul() {
		Random rand = new Random();
		int intel = 0;
		int str = 0;
		int cap = 0;
		int ill = 0;
		int sat = 0;
		int count = 0;
		for(int i = 1; i < 5; i++){
			if(this.inv[i] != null){
				count++;
				intel += FragmentHelper.getIntelligence(this.inv[i]);
				str += FragmentHelper.getStrength(this.inv[i]);
				cap += FragmentHelper.getCapacity(this.inv[i]);
				ill += FragmentHelper.getIllusion(this.inv[i]);
				sat += FragmentHelper.getSatisfaction(this.inv[i]);
			}
		}
		if(count >= 4){
			ItemStack soul = SoulHelper.createSoul(intel, str, cap, ill, sat);
			SoulHelper.setType(soul, rand.nextInt(LogicIndex.SOUL_TYPES.length - 1) + 1);
			SoulHelper.controlBasicSoul(soul);
			this.inv[soulSlot] = soul;
		}
	}

	private void forgeSoul() {
		ItemStack soul = this.inv[soulSlot];
		for(int i = 1; i < 5; i++){
			if(this.inv[i] != null){
				SoulHelper.setIntelligence(soul, SoulHelper.getIntelligence(soul) + FragmentHelper.getIntelligence(this.inv[i]));
				SoulHelper.setStrength(soul, SoulHelper.getStrength(soul) + FragmentHelper.getStrength(this.inv[i]));
				SoulHelper.setCapacity(soul, SoulHelper.getCapacity(soul) + FragmentHelper.getCapacity(this.inv[i]));
				SoulHelper.setIllusion(soul, SoulHelper.getIllusion(soul) + FragmentHelper.getIllusion(this.inv[i]));
				SoulHelper.setSatisfaction(soul, SoulHelper.getSatisfaction(soul) + FragmentHelper.getSatisfaction(this.inv[i]));
			}
		}
		SoulHelper.controlBasicSoul(soul);
		this.inv[soulSlot] = soul;
	}

	public boolean hasSoul() {
		return inv[soulSlot] != null;
	}
	
	public void storeSoul(ItemStack soul){
		if(!hasSoul()){
			this.inv[soulSlot] = soul;
		}
	}

}
