package shape.drawable;

import java.awt.Color;
import java.awt.Graphics;

import shape.drawer.Drawer;
import shape.drawer.FillDrawer;
import shape.editable.RectangleShape;

public class DrawRectangleObject extends RectangleShape implements DrawableObject {
	private Drawer drawer;

	public DrawRectangleObject(double x, double y, double width, double height){
		super(x, y, width, height);
		drawer = new FillDrawer(Color.green);
	}
	public DrawRectangleObject(double x, double y, double width, double height, Drawer drawer){
		super(x, y, width, height);
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
