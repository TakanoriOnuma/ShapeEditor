package shape.drawer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import shape.drawable.DrawArcObject;
import shape.drawable.DrawOvalObject;
import shape.drawable.DrawRectangleObject;
import shape.drawable.DrawRoundRectangleObject;
import shape.drawable.DrawTriangleObject;
import shape.editable.MyPoint;

public class FillDrawer extends Drawer {
	private Color color;

	public FillDrawer(){
		this(Color.gray);
	}
	public FillDrawer(Color color){
		this.color = color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
	public Color getColor() {
		return color;
	}

	@Override
	void accept(DrawerVisitor visitor) throws NoUsingPropatyException {
		// TODO 自動生成されたメソッド・スタブ
		visitor.visitFillDrawer(this);
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
		g.setColor(color);
		g.fillRect((int)rect.getX(), (int)rect.getY(), (int)rect.getWidth(), (int)rect.getHeight());
		g.setColor(Color.black);
		g.drawRect((int)rect.getX(), (int)rect.getY(), (int)rect.getWidth(), (int)rect.getHeight());
	}

	@Override
	public void draw(Graphics g, DrawRoundRectangleObject roundRect) {
		// TODO 自動生成されたメソッド・スタブ
		Graphics2D graphics2D = (Graphics2D)g;

		graphics2D.setColor(color);
		graphics2D.fill(roundRect.getRoundRect());
		graphics2D.setColor(Color.black);
		graphics2D.draw(roundRect.getRoundRect());
	}

	@Override
	public void draw(Graphics g, DrawTriangleObject triangle) {
		// TODO 自動生成されたメソッド・スタブ
		int[] xint = new int[3];
		int[] yint = new int[3];
		for(int i = 0; i < 3; i++){
			MyPoint pt = triangle.getPoint(i);
			xint[i] = (int)pt.getX();
			yint[i] = (int)pt.getY();
		}
		g.setColor(color);
		g.fillPolygon(xint, yint, 3);
		g.setColor(Color.black);
		g.drawPolygon(xint, yint, 3);
	}

	@Override
	public void draw(Graphics g, DrawArcObject arc) {
		// TODO 自動生成されたメソッド・スタブ
		Graphics2D graphics2D = (Graphics2D)g;
		graphics2D.setColor(color);
		graphics2D.fill(arc.getArc());
		graphics2D.setColor(Color.black);
		graphics2D.draw(arc.getArc());
	}

	@Override
	public FillDrawer clone() {
		FillDrawer fillDrawer = (FillDrawer)super.clone();
		fillDrawer.setColor(new Color(this.color.getRGB()));
		return fillDrawer;
	}


	@Override
	public String toString() {
		return "fill " + String.format("%d %d %d %d",
				color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
	}
}
