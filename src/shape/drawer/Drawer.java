package shape.drawer;

import java.awt.Graphics;

import shape.drawable.DrawOvalObject;
import shape.drawable.DrawRectangleObject;
import shape.drawable.DrawRoundRectangleObject;
import shape.drawable.DrawTriangleObject;

public abstract class Drawer implements Cloneable {
	// 無名修飾子で同パッケージ内のみアクセス可能
	abstract void accept(DrawerVisitor visitor) throws NoUsingPropatyException;

//	public void draw(Graphics g, DrawableObject drawableObject);
	public abstract void draw(Graphics g, DrawOvalObject oval);
	public abstract void draw(Graphics g, DrawRectangleObject rect);
	public abstract void draw(Graphics g, DrawRoundRectangleObject roundRect);
	public abstract void draw(Graphics g, DrawTriangleObject triangle);


	@Override
	public Drawer clone() {
		Drawer drawer;
		try {
			drawer = (Drawer)super.clone();
		}
		catch (CloneNotSupportedException e) {
			throw new RuntimeException();		// ランタイムエラーを投げる
		}
		return drawer;
	}
}
