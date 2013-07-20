package shape.drawer;

import java.awt.Graphics;

import shape.drawable.DrawOvalObject;
import shape.drawable.DrawRectangleObject;
import shape.drawable.DrawTriangleObject;

public interface Drawer {
//	public void draw(Graphics g, DrawableObject drawableObject);
	public void draw(Graphics g, DrawOvalObject oval);
	public void draw(Graphics g, DrawRectangleObject rect);
	public void draw(Graphics g, DrawTriangleObject triangle);
}
