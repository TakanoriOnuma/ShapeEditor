package sample4;

public class RectangleShape extends EditableShape {
	protected double x;			// 左上頂点の x 座標値
	protected double y;			// 左上頂点の y 座標値
	protected double width;		// 矩形の幅
	protected double height;	// 矩形の高さ

	RectangleShape(double x, double y, double width, double height) {
		super();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	@Override
	public boolean isIncluding(double xpos, double ypos) {
		return (xpos >= x && xpos <= x + width &&
				ypos >= y && ypos <= y + height);
	}

	@Override
	public void move(double dx, double dy) {
		x += dx;
		y += dy;
	}

	@Override
	public void show() {
		System.out.println("Rectangle(" + isSelected() + "): (" +
			x + "," + y + ") (" + (x + width) + ", " + (y + height) + ")");
	}

}
