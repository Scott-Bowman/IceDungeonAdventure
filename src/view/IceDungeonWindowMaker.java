package view;

import java.awt.BorderLayout;
import javax.swing.JFrame;

public class IceDungeonWindowMaker extends JFrame {
	private static final long serialVersionUID = 1L;

	public IceDungeonWindowMaker(IceDungeonPanel p){
        super("Ice Dungeon Puzzle");
        final IceDungeonPanel panel = p;
        add(panel, BorderLayout.CENTER);
    }
}

