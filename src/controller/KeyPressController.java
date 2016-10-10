package controller;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import model.Switch;
import view.IceDungeonPanel;
import model.Link;
import model.GameGrid;
import model.animals.PushableBlock;

import javax.swing.*;

public class KeyPressController implements KeyListener{

	IceDungeonPanel panel;
	GameGrid grid;
	Link link;
	PushableBlock block;
	Switch button;
	final String WEST = "west";
	final String NORTH = "north";
	final String EAST = "east";
	final String SOUTH = "south";


	public KeyPressController(IceDungeonPanel panel, GameGrid grid, Link link, Switch button){
		this.link = link;
		this.grid = grid;
		this.panel = panel;
		this.button = button;
	}
	
	
	
	@Override
	public void keyPressed(KeyEvent e) {

		int keyPressed = e.getKeyCode();
		if (keyPressed < 41 && keyPressed > 36 ){
			switch (keyPressed){
			case KeyEvent.VK_LEFT: // Go west
				link.setDirection(WEST);
			   if(grid.isIceBlock(link.getX()-1, link.getY())){
				   block = grid.getIceBlock(link.getX()-1, link.getY());
				   block.slide(WEST);
			   }
			   if(link.getX()>0 && !grid.isBlocked(link.getX()-1,link.getY())){
				   link.setX(link.getX()-1);
				}
			break;
			case KeyEvent.VK_UP: // Go North
				link.setDirection(NORTH);
				   if(grid.isIceBlock(link.getX(),link.getY()-1)) {
					   block = grid.getIceBlock(link.getX(),link.getY()-1);
					   block.slide(NORTH);
				   }
				if(link.getY()>0 && !grid.isBlocked(link.getX(),link.getY()-1)){
					link.setY(link.getY()-1);
				}
			break;
			case KeyEvent.VK_RIGHT: // Go East
				link.setDirection(EAST);
				if(grid.isIceBlock(link.getX()+1,link.getY())) {
					  block = grid.getIceBlock(link.getX()+1,link.getY());
					  block.slide(EAST);
				   }
				if(link.getX()<grid.getXDimension()-1 && !grid.isBlocked(link.getX()+1,link.getY())){
			        link.setX(link.getX()+1);
				}
			   break;
			case KeyEvent.VK_DOWN: // Go South{
				link.setDirection(SOUTH);
				if(grid.isIceBlock(link.getX(),link.getY()+1)) {
					  block = grid.getIceBlock(link.getX(),link.getY()+1);
					  block.slide(SOUTH);
				   }
				if(link.getY()<grid.getYDimension()-1 && !grid.isBlocked(link.getX(),link.getY()+1)){
				    link.setY(link.getY()+1);
				}
			   break;
			}
		}
		panel.repaint();

		if (grid.isBlocked(button.getX(), button.getY()))
			JOptionPane.showMessageDialog(null, "Level complete!");
		
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
