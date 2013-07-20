package shape.drawable;

import java.awt.Color;
import java.awt.Graphics;

import shape.drawer.Drawer;
import shape.drawer.FillDrawer;
import shape.editable.OvalShape;

public class DrawOvalObject extends OvalShape implements DrawableObject {
	private Drawer drawer;

	public DrawOvalObject(double x, double y, double width, double height){
		super(x, y, width, height);
		drawer = new FillDrawer(Color.blue);
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
