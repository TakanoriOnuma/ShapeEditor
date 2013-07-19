package sample4;

import java.awt.*;

import sample.MyPoint;

public class DrawTriangleObject extends TriangleShape implements DrawableObject {
	private Color color;

	public DrawTriangleObject(MyPoint pt1, MyPoint pt2, MyPoint pt3){
		super(pt1, pt2, pt3);
		color = Color.gray;
	}

	@Override
	public void draw(Graphics g) {
		// TODO 自動生成されたメソッド・スタブ
		int[] xint = new int[3];
		int[] yint = new int[3];
		for(int i = 0; i < 3; i++){
			xint[i] = (int)pt[i].getX();
			yint[i] = (int)pt[i].getY();
		}
		g.setColor(color);
		g.fillPolygon(xint, yint, 3);
		g.setColor(Color.black);
		g.drawPolygon(xint, yint, 3);
	}

	@Override
	public void setColor(Color color) {
		// TODO 自動生成されたメソッド・スタブ
		this.color = color;
	}
}
