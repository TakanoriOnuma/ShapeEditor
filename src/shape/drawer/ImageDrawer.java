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
import shape.editable.EditableShape;
import shape.editable.MyPoint;

public class ImageDrawer extends Drawer {
	private Image image;
	private Image ovalImage;
	private Image triangleImage;

	private Component comp;

	public ImageDrawer(String filename, Component comp){
		this.comp = comp;
		MediaTracker mt = new MediaTracker(comp);
		image = Toolkit.getDefaultToolkit().createImage(filename);
		mt.addImage(image, 0);
		try{
			mt.waitForID(0);
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

	private Image createTransparentImage(EditableShape shape) {
		Image img;
		MediaTracker mt = new MediaTracker(comp);
		img = Toolkit.getDefaultToolkit().createImage(
				new FilteredImageSource(image.getSource(), new EditableShapeFilter(shape)));
		mt.addImage(img, 0);
		try {
			mt.waitForID(0);
		}
		catch(InterruptedException e) {
		}

		return img;
	}

	@Override
	void accept(DrawerVisitor visitor) throws NoUsingPropatyException {
		// TODO 自動生成されたメソッド・スタブ
		visitor.visiteImageDrawer(this);
	}

	@Override
	public void draw(Graphics g, DrawOvalObject oval) {
		// TODO 自動生成されたメソッド・スタブ
		if(ovalImage == null){
			ovalImage = createTransparentImage(oval);
		}

		g.drawImage(ovalImage, (int)oval.getX(), (int)oval.getY(), null);
	}

	@Override
	public void draw(Graphics g, DrawRectangleObject rect) {
		// TODO 自動生成されたメソッド・スタブ
		g.drawImage(image, (int)rect.getX(), (int)rect.getY(), null);

	}

	@Override
	public void draw(Graphics g, DrawTriangleObject triangle) {
		// TODO 自動生成されたメソッド・スタブ
		if(triangleImage == null) {
			triangleImage = createTransparentImage(triangle);
		}

		MyPoint pt = triangle.getPoint(0);
		g.drawImage(triangleImage, (int)pt.getX(), (int)pt.getY(), null);
	}


	/**
	 * EditableShape領域内のみ表示させるフィルター
	 */
	class EditableShapeFilter extends RGBImageFilter {
		EditableShape shape;

		public EditableShapeFilter(EditableShape shape) {
			canFilterIndexColorModel = true;
			this.shape = shape;
		}

		@Override
		public int filterRGB(int x, int y, int rgb) {
			// TODO 自動生成されたメソッド・スタブ
			// shapeの座標が(0, 0)でないと上手くいかない可能性がある
			if(shape.isIncluding(x, y) == true){
				return rgb;
			}
			return 0;
		}
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
