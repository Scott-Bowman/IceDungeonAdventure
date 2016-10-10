package model.animals;

import model.GameGrid;

public abstract class PushBlockFactory {
	
	GameGrid grid;
	public PushBlockFactory(){
		grid = GameGrid.getInstance();
	}
	
	
	public PushableBlock createPushableBlock(int x, int y) {
		PushableBlock block = buildPushableBlock(x,y);
		//Point p = grid.findFreeCell(block.getTopLeft(), animal.getBottomRight());
		return block;
	}	
	
    abstract PushableBlock buildPushableBlock(int x, int y);
	
}
