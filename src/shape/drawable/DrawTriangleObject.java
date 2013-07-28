package shape.drawable;

import java.awt.Color;
import java.awt.Graphics;

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
	public void setColor(Color color) {
		// TODO 自動生成されたメソッド・スタブ
//		this.color = color;
	}
}
