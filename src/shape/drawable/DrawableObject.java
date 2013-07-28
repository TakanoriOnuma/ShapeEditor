package shape.drawable;

import java.awt.Color;
import java.awt.Graphics;

import shape.drawer.Drawer;

public interface DrawableObject {
	public void draw(Graphics g);			// 図形のウィンドウ上への描画
	public void setColor(Color color);		// 図形を塗りつぶす色の設定

	public Drawer getDrawer();				// Drawerを返す
}
