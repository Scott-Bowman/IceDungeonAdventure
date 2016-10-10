package model;

import java.awt.Point;
import java.util.ArrayList;

class WallBuilder{
	
	GameGrid grid;
	
	public WallBuilder(GameGrid grid){
		this.grid = grid;
	}
	
	public String buildHashString(int x, int y){
		return ((Integer)x).toString() + "-" + ((Integer)y).toString();		
	}
	
	public void blastADoor(Point doorPosition){
		String doorCell = buildHashString(doorPosition.x, doorPosition.y);
		grid.removeBlockedCell(doorCell);
	}

	public void addSingleBlock(Point point){
		String blockCell = buildHashString(point.x,point.y);
		grid.addBlockedCell(blockCell);
	}

	public void constructWall(Point startPoint, Point endPoint)
	{
		// only allow straight walls
		if(startPoint.x != endPoint.x && startPoint.y != endPoint.y)
			return;

		
		if(startPoint.x == endPoint.x)
		{
			for (int j = startPoint.y; j <= endPoint.y; j++)
			{
				String blockedCell = buildHashString(startPoint.x,j);
				grid.addBlockedCell(blockedCell);
			}
		}	
		else
		{ // startPoint.y must equal endPoint.y
			for (int j = startPoint.x; j <= endPoint.x; j++)
			{
				String blockedCell = buildHashString(j,startPoint.y);
				grid.addBlockedCell(blockedCell);
			}
			
		}
	}
		
}
public class BuildRoomLayout
{
	GameGrid grid;
	WallBuilder wallBuilder;
	public BuildRoomLayout(GameGrid grid){
		this.grid = grid;
		wallBuilder = new WallBuilder(grid);
		BuildAllRooms();
	}
	
	public void cutDoors(ArrayList<Point> doors){
		for(Point door:doors)
			wallBuilder.blastADoor(door);
	}
	
	
	public void buildARoom(Point topLeft, Point bottomRight){
		// Construct walls
		wallBuilder.constructWall(topLeft, new Point(bottomRight.x, topLeft.y));
		wallBuilder.constructWall(topLeft, new Point(topLeft.x, bottomRight.y));
		wallBuilder.constructWall(new Point(topLeft.x,bottomRight.y), bottomRight);
		wallBuilder.constructWall(new Point(bottomRight.x,topLeft.y), bottomRight);
		
	}
	
	public void BuildAllRooms()
	{
		ArrayList<Point> doors = new ArrayList<Point>();		
		buildARoom(new Point(0,0),new Point(11,11));

		doors.add(new Point(5,10));

		cutDoors(doors);
		wallBuilder.addSingleBlock(new Point(3,3));
	}
}
