package model;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Switch  {
	int x;
	int y;
	GameGrid grid;

	public Switch(int x,int y) {
		this.x = x;
		this.y = y;
		this.grid = GameGrid.getInstance();
	}
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}


}
