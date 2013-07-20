package shape.drawable;

import java.awt.*;

import shape.editable.RectangleShape;

public class DrawRectangleObject extends RectangleShape implements DrawableObject {
	private Color  color;

	public DrawRectangleObject(double x, double y, double width, double height){
		super(x, y, width, height);
		color = Color.gray;			// �f�t�H���g�Őݒ�
	}


	@Override
	public void draw(Graphics g) {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		g.setColor(color);
		g.fillRect((int)x, (int)y, (int)width, (int)height);
		g.setColor(Color.black);
		g.drawRect((int)x, (int)y, (int)width, (int)height);
	}

	@Override
	public void setColor(Color color) {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		this.color = color;
	}


}
