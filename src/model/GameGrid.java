package model;

import java.awt.Point;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;

import model.animals.PushableBlock;

public class GameGrid {
	private static GameGrid grid = null;
	public static int xDimension = 12;
	public static int yDimension = 12;
	Random rand;
	Map<String, PushableBlock> pushableBlocks = new HashMap<String, PushableBlock>();
	HashSet<String> blockedCells = new HashSet<String>();

	public static GameGrid getInstance(){
		if (grid == null){
			grid = new GameGrid();
		}
		return grid;
	}

	private GameGrid(){
		rand = new Random();
	}

	public int getXDimension(){
		return xDimension;
	}

	public int getYDimension(){
		return yDimension;
	}
	
	public void addBlockedCell(String newBlock){
		blockedCells.add(newBlock);
	}
	
	public void addPushableBlock(int x, int y, PushableBlock block){
		pushableBlocks.put(buildHashString(x, y),block);
	}
	
	public void removeBlockedCell(String oldBlock){
		if(blockedCells.contains(oldBlock))
			blockedCells.remove(oldBlock);
	}
	
	public void removePushableBlock(String oldIceBlock){
		if(pushableBlocks.containsKey(oldIceBlock))
			pushableBlocks.remove(oldIceBlock);
	}
	
	public String buildHashString(int x, int y){
		return ((Integer)x).toString() + "-" + ((Integer)y).toString();		
	}
	
	public void blockCells() { BuildRoomLayout roomBuilder = new BuildRoomLayout(this); }
	
	public Point findFreeCell(Point topLeft, Point bottomRight){
		Point p = null;
		while (true) {
			int newX = rand.nextInt(bottomRight.x-topLeft.x) + topLeft.x;
			int newY = rand.nextInt(bottomRight.y-topLeft.y) + topLeft.y;
			if(!isBlocked(newX, newY)){
				p = new Point(newX, newY);
				break;
			}			
		}
		return p;  // For now we assume we can find an empty spot.
	}


	public Point XfindFreeCell(Point topLeft, Point bottomRight){
		Point p = null;
		while (true) {
			int newX = rand.nextInt(xDimension);
			int newY = rand.nextInt(yDimension);
			if(!isBlocked(newX, newY)){
				p = new Point(newX, newY);
				break;
			}
		}
		return p;  // For now we assume we can find an empty spot.
	}
	
	public boolean inBounds(int x, int y){
		if((x>=0 && x < xDimension)&& (y >=0 && y < yDimension)) 
			return true;
		else
			return false;
	}
	
	public Point findFreeNeighbor(Point currentPoint){
		Point newPoint = null;
		int counter = 6;  // Maximum times willing to try to move.
		while (true) {
			int moveX = rand.nextInt(2);
			if(moveX == 0)
				moveX--;
			int moveY = rand.nextInt(2);
			if (moveY == 0)
				moveY--;
			int currentX = currentPoint.x + moveX;
			int currentY = currentPoint.y + moveY;
			if(!isBlocked(currentX,currentY) && inBounds(currentX, currentY)){
				newPoint = new Point(currentX,currentY);
				break;
			}			
			if(counter++ > 6)
				break;
		}
		if (newPoint == null)
			return currentPoint;
		else
			return newPoint;  // For now we assume we can find an empty spot.
	}
	
	public Boolean isBlocked(int x, int y)
	{
		if(blockedCells.contains(buildHashString(x,y)))
			return true;
		else
			return false;
	}	
	
	public Boolean isIceBlock(int x, int y){
		return pushableBlocks.containsKey(buildHashString(x,y));
	}
	public PushableBlock getIceBlock(int x, int y){
		return pushableBlocks.get(buildHashString(x, y));
	}
	public void removePushBlock(int x, int y){
		pushableBlocks.remove(buildHashString(x, y));
	}
}
