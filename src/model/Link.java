package model;

public class Link {
	int x;
	int y;
	int oldX, oldY;
	String direction;
	
	public Link(){
		x = 1;
		y = 1;
		oldX = 1;
		oldY = 1;
		direction = "south";
	}
	
	public void setDirection(String dir){
		 direction = dir;
	}
	
	public String getDirection(){
		return direction;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public void setX(int x){
		oldX = this.x;
		this.x = x;
	}
	
	public void setY(int y){
		oldY = this.y;
		this.y = y;
	}
}
