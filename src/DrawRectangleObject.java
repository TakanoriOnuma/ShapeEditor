package sample4;

import java.awt.*;

public class DrawRectangleObject extends RectangleShape implements DrawableObject {
	private Color  color;

	public DrawRectangleObject(double x, double y, double width, double height){
		super(x, y, width, height);
		color = Color.gray;			// デフォルトで設定
	}


	@Override
	public void draw(Graphics g) {
		// TODO 自動生成されたメソッド・スタブ
		g.setColor(color);
		g.fillRect((int)x, (int)y, (int)width, (int)height);
		g.setColor(Color.black);
		g.drawRect((int)x, (int)y, (int)width, (int)height);
	}

	@Override
	public void setColor(Color color) {
		// TODO 自動生成されたメソッド・スタブ
		this.color = color;
	}


}
