package shape.editable;

public class TriangleShape extends EditableShape {
	protected MyPoint[] points = new MyPoint[3];

	// 三角形の3つ頂点を右回りで入力する(注：ウィンドウ座標上では左回り)
	// 同一線上の点は入力されないことを仮定している
	public TriangleShape(MyPoint pt1, MyPoint pt2, MyPoint pt3) {
		super();
		points[0] = pt1;
		points[1] = pt2;
		points[2] = pt3;
	}

	// --- getter, setter --- //
	public MyPoint getPoint(int at){
		if(at < 0 || at >= points.length){
			throw new ArrayIndexOutOfBoundsException();
		}
		return points[at];
	}
	public void setPoint(int at, MyPoint pt){
		if(at < 0 || at >= points.length){
			throw new ArrayIndexOutOfBoundsException();
		}
		points[at] = pt;
	}

	@Override
	public boolean isIncluding(double xpos, double ypos) {
		boolean ret = true;
		// 点が3辺の右回りベクトルのどれかに対して、左に見えていれば外の点になる
		for(int i = 0; i < points.length; i++) {
			if((points[(i + 1) % 3].getY() - points[i].getY()) * (xpos - points[i].getX()) <
					(ypos - points[i].getY()) * (points[(i + 1) % 3].getX() - points[i].getX())) {
				ret = false;
			}
		}
		return ret;
	}

	@Override
	public void move(double dx, double dy) {
		// 指定した差分の移動をする
		for(int i = 0; i < points.length; i++) {
			points[i].move(dx, dy);
		}
	}


	@Override
	public void show() {
		System.out.print("Triangle(" + isSelected() + "): ");
		for(int i = 0; i < points.length; i++) {
			System.out.print(points[i] + " ");
		}
		System.out.println();
	}

}
