package shape.editable;

import sample.MyPoint;

public class TriangleShape extends EditableShape {
	protected MyPoint[] pt = new MyPoint[3];

	// �O�p�`��3���_���E���œ�͂���(���F�E�B���h�E���W��ł͍����)
	// ������̓_�͓�͂���Ȃ����Ƃ����肵�Ă���
	public TriangleShape(MyPoint pt1, MyPoint pt2, MyPoint pt3) {
		super();
		pt[0] = pt1;
		pt[1] = pt2;
		pt[2] = pt3;
	}

	@Override
	public boolean isIncluding(double xpos, double ypos) {
		boolean ret = true;
		// �_��3�ӂ̉E���x�N�g���̂ǂꂩ�ɑ΂��āA���Ɍ����Ă���ΊO�̓_�ɂȂ�
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
		// �w�肵�������̈ړ�������
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
