package controller;

import view.IceDungeonPanel;

public class PaintController implements Runnable{

	IceDungeonPanel idp;
	public PaintController(IceDungeonPanel idp){
		this.idp = idp;
	}
	@Override
	public void run() {
		while (true){
			idp.repaint();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
}
