package shape.drawable;

import java.awt.Color;
import java.awt.Graphics;

import shape.editable.OvalShape;

public class DrawOvalObject extends OvalShape implements DrawableObject {
	private Color color;

	DrawOvalObject(double x, double y, double width, double height){
		super(x, y, width, height);
		color = Color.gray;
	}

	@Override
	public void draw(Graphics g) {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		g.setColor(color);
		g.fillOval((int)x, (int)y, (int)width, (int)height);
		g.setColor(Color.black);
		g.drawOval((int)x, (int)y, (int)width, (int)height);
	}

	@Override
	public void setColor(Color color) {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		this.color = color;
	}

}
