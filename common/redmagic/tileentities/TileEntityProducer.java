package redmagic.tileentities;

import net.minecraftforge.liquids.LiquidDictionary;
import redmagic.api.essence.IProducer;

public class TileEntityProducer extends TileEntityStorage implements IProducer{

	public TileEntityProducer(int maxEssences) {
		super(maxEssences);
	}

	@Override
	public int produce(int amount, Object... data) {
		return this.store(LiquidDictionary.getLiquid("Essence", amount), data);
	}

}
