package sample4;

import java.awt.*;
import javax.swing.*;
import java.util.*;

public class DisplayWindow extends JFrame {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private MyPanel myPanel;
	private LinkedList<?> objList;		// 絵画する図形配列への参照

	DisplayWindow(LinkedList<?> objList) {
		super("Shape 表示");
		this.objList = objList;

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(200, 200);
		myPanel = new MyPanel();
		add(myPanel, BorderLayout.CENTER);
		setVisible(true);		// ウィンドウの表示
	}

	public void invisible(){
		setVisible(false);		// ウィンドウの非表示
	}

	public void drawAll(){
		myPanel.repaint();		// ウィンドウの再絵画 (MyPanel の paint の呼び出し)
	}

	public class MyPanel extends JPanel {

		/**
		 *
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public void paint(Graphics g) {
			super.paint(g);
			ListIterator<?> it = objList.listIterator(objList.size());
			while(it.hasPrevious() == true) {
				DrawableObject obj = (DrawableObject)it.previous();
				obj.draw(g);
			}
		}
	}
}
