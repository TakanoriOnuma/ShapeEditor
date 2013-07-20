package shape.editable;

public class MyPoint {
	private double x;
	private double y;

	public MyPoint(double x, double y){
		this.x = x;
		this.y = y;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getR(){
		double r = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));

		return r;
	}

	public double getTheta(){
		double theta = Math.atan2(y, x);		// atan�����߂�
		theta = Math.toDegrees(theta);			// �p�x�� ���ɒ���
		return theta;
	}

	@Override
	public String toString(){
		return "(" + x + ", " + y + ")";
	}

	public boolean equals(MyPoint pt){
		return (x == pt.x) && (y == pt.y);
	}

	public double distance(MyPoint pt){
		double diffX = x - pt.x;
		double diffY = y - pt.y;

		return Math.sqrt((double)(diffX * diffX + diffY * diffY));
	}

	public void move(double dx, double dy) {
		x += dx;
		y += dy;
	}
}
