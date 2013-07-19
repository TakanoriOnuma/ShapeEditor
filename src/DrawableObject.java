package sample4;

import java.awt.*;

public interface DrawableObject {
	public void draw(Graphics g);			// 図形のウィンドウ上への絵画
	public void setColor(Color color);		// 図形を塗りつぶす色の設定
}
