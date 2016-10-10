package controller;

import java.util.ArrayList;

import view.*;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import model.Link;
import model.Switch;
import model.GameGrid;
import model.animals.IceBlockFactory;
import model.animals.PushBlockFactory;
import model.animals.PushableBlock;
import model.animals.RegularBlockFactory;
import view.IceDungeonPanel;

public class IceDungeonAdventure {

	IceDungeonPanel iceDungeonPanel;

	Link link;
	GameGrid gameGrid;
	Switch switchTile;
	ArrayList<PushableBlock> blocks = new ArrayList<PushableBlock>();

	public IceDungeonAdventure(){
		this.link = new Link();
		this.gameGrid = GameGrid.getInstance();
		createBunnies();
		createIceBlocks();
		placeSwitch(5,5);
	}

	public void startGUI(){
		int windowWidth = 13*50;
		int windowHeight = 13*50;
		int blockCount = 15;
	    iceDungeonPanel = IceDungeonPanel.getInstance(gameGrid, link, blocks, switchTile);   // Create Panel in View
	    IceDungeonWindowMaker w = new IceDungeonWindowMaker(iceDungeonPanel);  // Create a window and display the panel
	    w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // Format the window etc.
	    w.setSize(windowWidth, windowHeight);
        w.pack();
	    //w.setLayout(new FlowLayout(FlowLayout.CENTER,800,800)); 
	    w.setVisible(true);
	    JOptionPane.showMessageDialog(w, "Move with the arrow keys. Push the blocks on to the switch tile!");
	}
	
	public void placeSwitch(int x, int y){
		switchTile = new Switch(x,y);
	}
	
   	public void createIceBlocks()
	{
		PushBlockFactory iceBlockFactory = new IceBlockFactory();
		blocks.add(iceBlockFactory.createPushableBlock(2,2));
		blocks.add(iceBlockFactory.createPushableBlock(5, 2));
		blocks.add(iceBlockFactory.createPushableBlock(7, 7));
	}
	
	public void createBunnies()
	{
		PushBlockFactory normalBlockFactory = new RegularBlockFactory();
		blocks.add(normalBlockFactory.createPushableBlock(4,4));
	}
	
	
	public void startThreads()
	{
		PaintController paintController = new PaintController(iceDungeonPanel);
		(new Thread(paintController)).start();
	}
	
	public static void main(String[] args){
		// Create main controller objects
		IceDungeonAdventure iceDungeonAdventure = new IceDungeonAdventure();
		
		// Create View objects
	 	iceDungeonAdventure.startGUI();  // Start up the view
	 	iceDungeonAdventure.startThreads();
	}
}