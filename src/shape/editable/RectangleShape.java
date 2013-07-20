package shape.editable;



public class RectangleShape extends EditableShape {
	protected double x;			// ���㒸�_�� x ���W�l
	protected double y;			// ���㒸�_�� y ���W�l
	protected double width;		// ��`�̕�
	protected double height;	// ��`�̍���

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
