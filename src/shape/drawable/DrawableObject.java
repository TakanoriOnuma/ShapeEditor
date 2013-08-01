package shape.drawable;

import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

import shape.drawer.Drawer;

public interface DrawableObject {
	public void draw(Graphics g);			// 図形のウィンドウ上への描画

	public Rectangle2D.Double getDrawField();	// 描画領域を返す
	public Drawer getDrawer();					// Drawerを返す
}
