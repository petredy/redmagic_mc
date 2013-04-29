package redmagic.tileentities;

import redmagic.api.essence.IProducer;

public class TileEntityProducer extends TileEntityStorage implements IProducer{

	@Override
	public int produce(int amount, Object... data) {
		return this.store(amount, data);
	}

}
