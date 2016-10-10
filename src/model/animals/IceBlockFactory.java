package model.animals;

public class IceBlockFactory extends PushBlockFactory{

	public IceBlockFactory() {
		super();
	}

	@Override
	PushableBlock buildPushableBlock(int x, int y) {
	return new IceBlock(x,y);	
	}
	
}

