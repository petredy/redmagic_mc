package redmagic.api.essence;

public interface IConsumer extends IStorage{
	
	/**
	 * Consumes the amount of energy
	 * @param amount
	 * @param data
	 * @return the amount of energy needed
	 */
	public abstract int consume(int amount, Object...data);
}
