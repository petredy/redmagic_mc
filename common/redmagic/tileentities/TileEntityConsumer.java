package redmagic.tileentities;

import redmagic.api.essence.IConsumer;

public class TileEntityConsumer extends TileEntityStorage implements IConsumer{

	@Override
	public int consume(int amount, Object... data) {
		return this.extract(amount, data);
	}

}
