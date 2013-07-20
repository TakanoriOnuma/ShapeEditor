package shape.editable;



public class OvalShape extends EditableShape{
	protected double x;
	protected double y;
	protected double width;
	protected double height;

	public OvalShape(double x, double y, double width, double height){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	@Override
	public boolean isIncluding(double xpos, double ypos) {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		double a = width / 2;
		double b = height / 2;
		double x = (this.x + a) - xpos;
		double y = (this.y + b) - ypos;

		return (x * x) / (a * a) + (y * y) / (b * b) <= 1;
	}
	@Override
	public void move(double dx, double dy) {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		x += dx;
		y += dy;
	}
	@Override
	public void show() {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		System.out.println("Oval(" + isSelected() + "): (" +
			x + ", " + y + ") (" + (x + width) + ", " + (y + height) + ")");
	}

}
