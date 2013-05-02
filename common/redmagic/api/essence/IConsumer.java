package redmagic.api.essence;

import net.minecraftforge.liquids.LiquidStack;

public interface IConsumer extends IStorage{
	
	/**
	 * Consumes the amount of energy
	 * @param amount
	 * @param data
	 * @return the amount of energy needed
	 */
	public abstract LiquidStack consume(int amount, Object...data);
}
