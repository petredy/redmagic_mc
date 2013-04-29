package redmagic.api.essence;

public interface IProducer extends IStorage{
	/**
	 * Produces the amount of essences
	 * @param amount
	 * @param data
	 * @return the amount of essences produced
	 */
	public abstract int produce(int amount, Object...data);
}
