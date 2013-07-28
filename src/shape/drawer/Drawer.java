package shape.drawer;

import java.awt.Graphics;

import shape.drawable.DrawOvalObject;
import shape.drawable.DrawRectangleObject;
import shape.drawable.DrawTriangleObject;

public abstract class Drawer {
	// 無名修飾子で同パッケージ内のみアクセス可能
	abstract void accept(DrawerVisitor visitor);

//	public void draw(Graphics g, DrawableObject drawableObject);
	public abstract void draw(Graphics g, DrawOvalObject oval);
	public abstract void draw(Graphics g, DrawRectangleObject rect);
	public abstract void draw(Graphics g, DrawTriangleObject triangle);
}
