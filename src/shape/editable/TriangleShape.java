package shape.editable;

public class TriangleShape extends EditableShape {
	protected MyPoint[] pt = new MyPoint[3];

	// 三角形の3つ頂点を右回りで入力する(注：ウィンドウ座標上では左回り)
	// 同一線上の点は入力されないことを仮定している
	public TriangleShape(MyPoint pt1, MyPoint pt2, MyPoint pt3) {
		super();
		pt[0] = pt1;
		pt[1] = pt2;
		pt[2] = pt3;
	}

	@Override
	public boolean isIncluding(double xpos, double ypos) {
		boolean ret = true;
		// 点が3辺の右回りベクトルのどれかに対して、左に見えていれば外の点になる
		for(int i = 0; i < pt.length; i++) {
			if((pt[(i + 1) % 3].getY() - pt[i].getY()) * (xpos - pt[i].getX()) <
					(ypos - pt[i].getY()) * (pt[(i + 1) % 3].getX() - pt[i].getX())) {
				ret = false;
			}
		}
		return ret;
	}

	@Override
	public void move(double dx, double dy) {
		// 指定した差分の移動をする
		for(int i = 0; i < pt.length; i++) {
			pt[i].move(dx, dy);
		}
	}


	@Override
	public void show() {
		System.out.print("Triangle(" + isSelected() + "): ");
		for(int i = 0; i < pt.length; i++) {
			System.out.print(pt[i] + " ");
		}
		System.out.println();
	}

}
