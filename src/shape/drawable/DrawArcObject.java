package shape.drawable;

import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

import shape.drawer.Drawer;
import shape.editable.ArcShape;

public class DrawArcObject extends ArcShape implements DrawableObject {
	private Drawer drawer;

	public DrawArcObject(double x, double y, double width, double height,
				double start, double extent, int type, Drawer drawer) {
		super(x, y, width, height, start, extent, type);
		this.drawer = drawer;
	}

	@Override
	public void draw(Graphics g) {
		// TODO 自動生成されたメソッド・スタブ
		drawer.draw(g, this);
	}

	@Override
	public Rectangle2D.Double getDrawField() {
		// TODO 自動生成されたメソッド・スタブ
		return new Rectangle2D.Double(arc.getX(), arc.getY(), arc.getWidth(), arc.getHeight());
	}

	@Override
	public Drawer getDrawer() {
		// TODO 自動生成されたメソッド・スタブ
		return drawer;
	}

	@Override
	public void setDrawer(Drawer drawer) {
		// TODO 自動生成されたメソッド・スタブ
		this.drawer = drawer;
	}

}
