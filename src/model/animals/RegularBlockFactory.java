package model.animals;

public class RegularBlockFactory extends PushBlockFactory{

	public RegularBlockFactory() {
		super();
	}

	@Override
	PushableBlock buildPushableBlock(int x, int y) {
		// TODO Auto-generated method stub
		return new NormalBlock(x,y);
	}
	
}
