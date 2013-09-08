package shape.editable;

import java.awt.geom.RoundRectangle2D;

public class RoundRectangleShape extends EditableShape {
	protected RoundRectangle2D.Double roundRect;

	public RoundRectangleShape(double x, double y, double w, double h, double arcw, double arch) {
		roundRect = new RoundRectangle2D.Double(x, y, w, h, arcw, arch);
	}

	// --- 仮のゲッタ --- //
	public RoundRectangle2D.Double getRoundRect() {
		return roundRect;
	}

	@Override
	public boolean isIncluding(double xpos, double ypos) {
		// TODO 自動生成されたメソッド・スタブ
		return roundRect.contains(xpos, ypos);
	}

	@Override
	public void move(double dx, double dy) {
		// TODO 自動生成されたメソッド・スタブ
		roundRect.x += dx;
		roundRect.y += dy;
	}

	@Override
	public void show() {
		// TODO 自動生成されたメソッド・スタブ
		System.out.println("RoundRectangle(" + isSelected() + "):" +
				"(" + roundRect.getX() + ", " + roundRect.getY() + ") " +
				"(" + (roundRect.getX() + roundRect.getWidth()) + ", " + (roundRect.getY() + roundRect.getHeight()) + ") " +
				"(" + roundRect.getArcWidth() + ", " + roundRect.getArcHeight() + ")");
	}

	@Override
	public String toString() {
		return "RoundRectangle " + String.format("%f %f %f %f %f %f", roundRect.getX(), roundRect.getY(),
				roundRect.getWidth(), roundRect.getHeight(), roundRect.getArcWidth(), roundRect.getArcHeight());
	}
}
