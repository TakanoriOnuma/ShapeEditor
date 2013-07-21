package shape.drawer;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.image.FilteredImageSource;
import java.awt.image.RGBImageFilter;

import shape.drawable.DrawOvalObject;
import shape.drawable.DrawRectangleObject;
import shape.drawable.DrawTriangleObject;
import shape.editable.MyPoint;

public class ImageDrawer implements Drawer {
	private Image image;

	public ImageDrawer(String filename, Component comp){
		MediaTracker mt = new MediaTracker(comp);
		Image img = Toolkit.getDefaultToolkit().createImage(filename);
		image = Toolkit.getDefaultToolkit().createImage(
					new FilteredImageSource(img.getSource(), new TransparentFilter()));
		mt.addImage(img, 0);
		mt.addImage(image, 1);
		try{
			mt.waitForAll();
		}
		catch(InterruptedException e){
		}


	}

	public Image getImage(){
		return image;
	}
	public void setImage(Image image){
		this.image = image;
	}

	@Override
	public void draw(Graphics g, DrawOvalObject oval) {
		// TODO 自動生成されたメソッド・スタブ
		g.drawImage(image, (int)oval.getX(), (int)oval.getY(), null);
	}

	@Override
	public void draw(Graphics g, DrawRectangleObject rect) {
		// TODO 自動生成されたメソッド・スタブ
		g.drawImage(image, (int)rect.getX(), (int)rect.getY(), null);

	}

	@Override
	public void draw(Graphics g, DrawTriangleObject triangle) {
		// TODO 自動生成されたメソッド・スタブ
		MyPoint pt = triangle.getPoint(0);
		g.drawImage(image, (int)pt.getX(), (int)pt.getY(), null);
	}

}

/**
 * イメージの透かしフィルター
 */
class TransparentFilter extends RGBImageFilter
{
	/**
	 * コンストラクタ
	 */
	public TransparentFilter()
	{
		canFilterIndexColorModel = true;
	}

	/**
	 * RGB ピクセルが黒であれば前景色に変更する。
	 *
	 * @param x 取り出し位置 X。
	 * @param y 取り出し位置 Y。
	 * @param rgb 変更前の RGB ピクセル。
	 * @return 変更後の RGB ピクセル。
	 */
	public int filterRGB(int x,int y,int rgb)
	{
		Color c = new Color(rgb);
		if(c.getRed() != 0 || c.getGreen() != 0  || c.getBlue() != 0) {
			c = new Color(c.getRed(), c.getGreen(), c.getBlue(), 255);
		}
		else {
			c = new Color(0, 0, 0, 0);
		}
		return c.getRGB();
	}
}
