package shape.editable;

import java.awt.geom.Arc2D;

public class ArcShape extends EditableShape {
	protected Arc2D.Double arc;

	public ArcShape(double x, double y, double width, double height, double start, double extent, int type) {
		arc = new Arc2D.Double(x, y, width, height, start, extent, type);
	}

	// --- 仮のゲッタ --- //
	public Arc2D.Double getArc() {
		return arc;
	}

	@Override
	public boolean isIncluding(double xpos, double ypos) {
		// TODO 自動生成されたメソッド・スタブ
		return arc.contains(xpos, ypos);
	}

	@Override
	public void move(double dx, double dy) {
		// TODO 自動生成されたメソッド・スタブ
		arc.x += dx;
		arc.y += dy;
	}

	@Override
	public void show() {
		// TODO 自動生成されたメソッド・スタブ
		System.out.println("Arc(" + isSelected() + "): " +
				"(" + arc.getX() + ", " + arc.getY() + ") " +
				"(" + (arc.getX() + arc.getWidth()) + ", " + (arc.getY() + arc.getHeight()) + ") " +
				"(" + arc.getAngleStart() + ", " + arc.getAngleExtent() + ")");
	}

}
