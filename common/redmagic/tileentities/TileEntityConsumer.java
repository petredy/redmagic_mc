package redmagic.tileentities;

import net.minecraftforge.liquids.LiquidDictionary;
import net.minecraftforge.liquids.LiquidStack;
import redmagic.api.essence.IConsumer;

public class TileEntityConsumer extends TileEntityStorage implements IConsumer{

	public TileEntityConsumer(int maxEssences) {
		super(maxEssences);
	}

	@Override
	public LiquidStack consume(int amount, Object... data) {
		return this.extract(amount, data);
	}

}
