package shape.drawable;

import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

import shape.drawer.Drawer;
import shape.editable.RoundRectangleShape;

public class DrawRoundRectangleObject extends RoundRectangleShape implements DrawableObject {
	private Drawer drawer;

	public DrawRoundRectangleObject(double x, double y, double width, double height,
			double arcw, double arch, Drawer drawer) {
		super(x, y, width, height, arcw, arch);
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
		return new Rectangle2D.Double(roundRect.getX(), roundRect.getY(),
					roundRect.getWidth(), roundRect.getHeight());
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
