package shape.drawable;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

import shape.drawer.Drawer;
import shape.drawer.FillDrawer;
import shape.editable.OvalShape;

public class DrawOvalObject extends OvalShape implements DrawableObject {
	private Drawer drawer;

	public DrawOvalObject(double x, double y, double width, double height){
		super(x, y, width, height);
		drawer = new FillDrawer(Color.blue);
	}
	public DrawOvalObject(double x, double y, double width, double height, Drawer drawer){
		super(x, y, width, height);
		this.drawer = drawer;
	}

	@Override
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
		return new Rectangle2D.Double(x, y, width, height);
	}



}
