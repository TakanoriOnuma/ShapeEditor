package shape.drawable;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

import shape.drawer.Drawer;
import shape.drawer.FillDrawer;
import shape.editable.MyPoint;
import shape.editable.TriangleShape;

public class DrawTriangleObject extends TriangleShape implements DrawableObject {
	private Drawer drawer;

	public DrawTriangleObject(MyPoint pt1, MyPoint pt2, MyPoint pt3){
		super(pt1, pt2, pt3);
		drawer = new FillDrawer(Color.red);
	}
	public DrawTriangleObject(MyPoint pt1, MyPoint pt2, MyPoint pt3, Drawer drawer){
		super(pt1, pt2, pt3);
		this.drawer = drawer;
	}

	/**
	 * 3つの中で最小値を求める
	 * @param a
	 * @param b
	 * @param c
	 * @return
	 */
	private double min_of_3(double a, double b, double c) {
		double min = (a < b) ? a : b;
		min = (min < c) ? min : c;
		return min;
	}


	/**
	 * 3つの中で最大値を求める
	 * @param a
	 * @param b
	 * @param c
	 * @return
	 */
	private double max_of_3(double a, double b, double c) {
		double max = (a > b) ? a : b;
		max = (max > c) ? max : c;
		return max;
	}

	public void setDrawer(Drawer drawer){
		this.drawer = drawer;
	}

	@Override
	public Drawer getDrawer() {
		return drawer;
	}

	@Override
	public void draw(Graphics g) {
		// TODO 自動生成されたメソッド・スタブ
		drawer.draw(g, this);
	}

	@Override
	public Rectangle2D.Double getDrawField() {
		// TODO 自動生成されたメソッド・スタブ
		Rectangle2D.Double field = new Rectangle2D.Double();
		field.x = min_of_3(points[0].getX(), points[1].getX(), points[2].getX());
		field.y = min_of_3(points[0].getY(), points[1].getY(), points[2].getY());
		field.width = max_of_3(points[0].getX(), points[1].getX(), points[2].getX()) - field.x;
		field.height = max_of_3(points[0].getY(), points[1].getY(), points[2].getY()) - field.y;

		return field;
	}
}
