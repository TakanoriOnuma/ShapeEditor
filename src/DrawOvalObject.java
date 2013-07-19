package sample4;

import java.awt.Color;
import java.awt.Graphics;

public class DrawOvalObject extends OvalShape implements DrawableObject {
	private Color color;

	DrawOvalObject(double x, double y, double width, double height){
		super(x, y, width, height);
		color = Color.gray;
	}

	@Override
	public void draw(Graphics g) {
		// TODO 自動生成されたメソッド・スタブ
		g.setColor(color);
		g.fillOval((int)x, (int)y, (int)width, (int)height);
		g.setColor(Color.black);
		g.drawOval((int)x, (int)y, (int)width, (int)height);
	}

	@Override
	public void setColor(Color color) {
		// TODO 自動生成されたメソッド・スタブ
		this.color = color;
	}

}
