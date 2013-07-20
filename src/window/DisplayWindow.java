package window;

import java.awt.*;
import javax.swing.*;

import shape.drawable.DrawableObject;

import java.util.*;

public class DisplayWindow extends JFrame {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private MyPanel myPanel;
	private LinkedList<?> objList;		// �G�悷��}�`�z��ւ̎Q��

	DisplayWindow(LinkedList<?> objList) {
		super("Shape �\��");
		this.objList = objList;

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(200, 200);
		myPanel = new MyPanel();
		add(myPanel, BorderLayout.CENTER);
		setVisible(true);		// �E�B���h�E�̕\��
	}

	public void invisible(){
		setVisible(false);		// �E�B���h�E�̔�\��
	}

	public void drawAll(){
		myPanel.repaint();		// �E�B���h�E�̍ĊG�� (MyPanel �� paint �̌Ăяo��)
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
