package model.animals;

import java.awt.Point;
import model.GameGrid;


// Note I changed this to a class
// I had to change extends Runnable to implements Runnable because I have a class/interface relationship now
// instead of an interface/interface one.
public abstract class PushableBlock {
	
	GameGrid grid;
	String name;
	Point topLeftBounds;
	Point bottomRightBounds;
	int x,y;
	
	public PushableBlock(){
		grid = GameGrid.getInstance();
		setBounds();
	}
	
	public void setBounds(){
		topLeftBounds = new Point(1,1);
		bottomRightBounds = new Point(grid.getXDimension()-1,grid.getYDimension()-1);  // default
	}
	
	public boolean inBounds(int x, int y){
		if((x>=topLeftBounds.x && x <= bottomRightBounds.x)&& (y>= topLeftBounds.y && y <= bottomRightBounds.y))
		//if((x>=0 && x < xDimension)&& (y >=0 && y < yDimension)) 
			return true;
		else
			return false;
	}
	
	public String getName() {
		return name;
	}

	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public Point getTopLeft(){
		return topLeftBounds;
	}
	
	public Point getBottomRight(){
		return bottomRightBounds;
	}
	
	public void updateAdd(){
		grid.addBlockedCell(grid.buildHashString(x, y));
		grid.addPushableBlock(x,y,this);
	}
	
	public void updateRemove(){
		grid.removeBlockedCell(grid.buildHashString(x, y));
		grid.removePushBlock(x,y);
	}
	
	
	
	// Abstract method
	public abstract void move();
	public abstract void slide(String str);

	public void run() {
		while (true){
			move();												  
		}
	}
}
