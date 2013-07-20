package shape.drawer;

import java.awt.Color;
import java.awt.Graphics;

import shape.drawable.DrawOvalObject;
import shape.drawable.DrawRectangleObject;
import shape.drawable.DrawTriangleObject;

public class FillDrawer implements Drawer {
	Color color;

	public FillDrawer(){
		this(Color.gray);
	}
	public FillDrawer(Color color){
		this.color = color;
	}

	@Override
	public void draw(Graphics g, DrawOvalObject oval) {
		// TODO 自動生成されたメソッド・スタブ
		g.setColor(color);
		g.fillOval((int)oval.getX(), (int)oval.getY(), (int)oval.getWidth(), (int)oval.getHeight());
		g.setColor(Color.black);
		g.drawOval((int)oval.getX(), (int)oval.getY(), (int)oval.getWidth(), (int)oval.getHeight());

	}

	@Override
	public void draw(Graphics g, DrawRectangleObject rect) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void draw(Graphics g, DrawTriangleObject triangle) {
		// TODO 自動生成されたメソッド・スタブ

	}

}
