package view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import controller.KeyPressController;
import model.Link;
import model.Switch;
import model.GameGrid;
import model.animals.IceBlock;
import model.animals.NormalBlock;
import model.animals.PushableBlock;

public class IceDungeonPanel extends JPanel{
	private static IceDungeonPanel iceDungeonPanel;
	private static final long serialVersionUID = 1L;
	KeyPressController kpc;
    Link link;
    Switch switchTile;
    List<PushableBlock> blocks;
    GameGrid grid;
    int cellSize = 50;
    Image iceTileImg, switchImg, iceBlockImg, blockImg,linkUp,linkDown,linkLeft,linkRight;
    Map<String, Image> linkDirection = new HashMap<String,Image>();
	private BufferedImage normalBlockImg;
    
    
    public static IceDungeonPanel getInstance(GameGrid grid, Link link, ArrayList<PushableBlock> blocks, Switch switchTile) {
    	if(iceDungeonPanel == null)
    		iceDungeonPanel = new IceDungeonPanel(grid,link,blocks, switchTile);
    	return iceDungeonPanel;
    }
    
    private IceDungeonPanel(GameGrid grid, Link link, ArrayList<PushableBlock> blocks, Switch switchTile)
    {

        this.link = link;
        this.grid = grid;
        this.blocks = blocks;
        this.switchTile = switchTile;
        kpc = new KeyPressController(this,grid, link,switchTile );
        this.addKeyListener(kpc);
        this.setFocusable(true);

    	grid.blockCells();
        setBackground(Color.BLACK);
        
        try {
            linkDirection.put("south", ImageIO.read(new File("src/images/link.png")));
            linkDirection.put("west", ImageIO.read(new File("src/images/linkleft.png")));
            linkDirection.put("north", ImageIO.read(new File("src/images/linkup.png")));
            linkDirection.put("east", ImageIO.read(new File("src/images/linkright.png")));

    	} catch (IOException e) {
    		System.out.println("Can't find Link images");
    	}
        
        try{
        	blockImg = ImageIO.read(new File("src/images/darkblock.png"));
        	iceTileImg = ImageIO.read(new File("src/images/icetile.png"));
        	iceBlockImg = ImageIO.read(new File("src/images/iceblock.png"));
        	normalBlockImg = ImageIO.read(new File("src/images/lightblock.png"));
        	switchImg = ImageIO.read(new File("src/images/switch.png"));
        } catch(IOException e){
    		System.out.println("Can't find tile images");

        }      
        
	}
   
   public void drawGrid(Graphics2D g2){
	   // Draw horizontal lines
       //for(int j = 1; j <= grid.getYDimension()+1; j++)
       	//g2.drawLine(cellSize, j*cellSize, cellSize+ (grid.getXDimension()*cellSize), j*cellSize);
       
       // Draw vertical lines
       //for(int j = 0; j <= grid.getXDimension(); j++)
       	//g2.drawLine(j*cellSize+ cellSize, cellSize, j*cellSize+cellSize, cellSize+ grid.getYDimension()*cellSize);

       for(int i = 0; i < grid.getXDimension(); i++){
       	for(int j = 0; j < grid.getYDimension(); j++){
       		if(grid.isBlocked(i,j) && !grid.isIceBlock(i,j))
       			g2.drawImage(blockImg, i*cellSize,j*cellSize,null);
       			//g2.fillRect(i*cellSize+cellSize,j*cellSize+ cellSize,cellSize,cellSize); 
       		else
       			g2.drawImage(iceTileImg, i*cellSize,j*cellSize,null);
       	}
       }
       g2.drawImage(switchImg, switchTile.getX()*cellSize, switchTile.getY()*cellSize, null);

       
   }
   
   public void drawBlocks(Graphics2D g2){
	   // Draw blocks    
       for(PushableBlock block : blocks){
       	if(block instanceof IceBlock)
       		g2.drawImage(iceBlockImg, block.getX()*cellSize,block.getY()*cellSize,null);
       	else if (block instanceof NormalBlock)
       		g2.drawImage(normalBlockImg, block.getX()*cellSize,block.getY()*cellSize,null);
 	    }
       
   }

	public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(1));
        drawGrid(g2);   
        drawBlocks(g2);
                     
        // Draw Paxton
        g2.drawImage(linkDirection.get(link.getDirection()), link.getX()*cellSize, link.getY()*cellSize,null);
        //g2.setPaint(Color.RED);
        //g2.fillRect(link.getX()*cellSize+cellSize, link.getY()*cellSize+cellSize,cellSize, cellSize);
    }

    public Dimension getPreferredSize(){
        return new Dimension(12*50,12*50);
    }

    public Dimension getMinimumSize(){
        return getPreferredSize();
    }

}


