package shape.drawer;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.geom.Rectangle2D;
import java.awt.image.FilteredImageSource;
import java.awt.image.RGBImageFilter;

import shape.drawable.DrawOvalObject;
import shape.drawable.DrawRectangleObject;
import shape.drawable.DrawRoundRectangleObject;
import shape.drawable.DrawTriangleObject;
import shape.drawable.DrawableObject;
import shape.editable.EditableShape;
import shape.editable.MyPoint;

public class ImageDrawer extends Drawer {
	private Image image;			// 元の画像
	private Image drawImg;			// 描画に使う画像

	private String filename;		// 今使っている画像のファイル名

	private Component comp;

	public ImageDrawer(String filename, Component comp){
		this.comp = comp;
		this.image = readImage(filename);
		this.filename = new String(filename);
	}

	private Image readImage(String filename) {
		Image img;
		MediaTracker mt = new MediaTracker(comp);
		img = Toolkit.getDefaultToolkit().createImage(filename);
		mt.addImage(img, 0);
		try{
			mt.waitForID(0);
		}
		catch(InterruptedException e){
		}
		return img;
	}

	private Image copyImage(Image image) {
		Image img;
		MediaTracker mt = new MediaTracker(comp);
		img = Toolkit.getDefaultToolkit().createImage(image.getSource());
		mt.addImage(img, 0);
		try{
			mt.waitForID(0);
		}
		catch(InterruptedException e){
		}
		return img;
	}

	public String getFileName() {
		return filename;
	}
	public Image getImage(){
		return image;
	}
	public void setImage(Image image){
		this.image = image;		// 元の画像を変える
		this.drawImg = null;	// 元の画像が変わったため描画の画像も変わる
	}
	public void setImage(String filename) {
		this.filename = new String(filename);
		Image img = readImage(filename);

		// 読み込みに失敗したら
		if(img.getWidth(null) == -1) {
			System.out.println("ファイルが読み込めません。");
		}
		// 読み込みに成功したら
		else {
			this.image = img;		// 元の画像を変える
			this.drawImg = null;	// 元の画像が変わったため、描画の画像も変わる
		}
	}

	private Image createTransparentImage(EditableShape shape) {
		DrawableObject obj = (DrawableObject)shape;		// 無理やりのキャスト
		Rectangle2D.Double field = obj.getDrawField();	// 描画領域を貰う
		shape.move(-field.x, -field.y);					// (0, 0)に移動させる

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

		shape.move(field.x, field.y);			// 元の場所に戻る

		return img;
	}

	@Override
	void accept(DrawerVisitor visitor) throws NoUsingPropatyException {
		// TODO 自動生成されたメソッド・スタブ
		visitor.visitImageDrawer(this);
	}

	@Override
	public void draw(Graphics g, DrawOvalObject oval) {
		// TODO 自動生成されたメソッド・スタブ
		if(drawImg == null){
			oval.setWidth(image.getWidth(null));
			oval.setHeight(image.getHeight(null));
			drawImg = createTransparentImage(oval);
		}

		g.drawImage(drawImg, (int)oval.getX(), (int)oval.getY(), null);
	}

	@Override
	public void draw(Graphics g, DrawRectangleObject rect) {
		// TODO 自動生成されたメソッド・スタブ
		if(drawImg == null) {
			rect.setWidth(image.getWidth(null));
			rect.setHeight(image.getHeight(null));
			drawImg = image;		// 長方形はそのまま
		}

		g.drawImage(drawImg, (int)rect.getX(), (int)rect.getY(), null);

	}

	@Override
	public void draw(Graphics g, DrawRoundRectangleObject roundRect) {
		// TODO 自動生成されたメソッド・スタブ
		if(drawImg == null) {
			roundRect.getRoundRect().width = image.getWidth(null);
			roundRect.getRoundRect().height = image.getHeight(null);
			roundRect.getRoundRect().arcwidth = roundRect.getRoundRect().width / 2;
			roundRect.getRoundRect().archeight = roundRect.getRoundRect().height / 2;

			drawImg = createTransparentImage(roundRect);
		}

		g.drawImage(drawImg, (int)roundRect.getRoundRect().getX(),
				(int)roundRect.getRoundRect().getY(), null);
	}

	@Override
	public void draw(Graphics g, DrawTriangleObject triangle) {
		// TODO 自動生成されたメソッド・スタブ
		if(drawImg == null) {
			Rectangle2D.Double old_field = triangle.getDrawField();

			// 新しい画像に合う座標を指定する
			double width = image.getWidth(null);
			double height = image.getHeight(null);
			triangle.setPoint(0, new MyPoint(width / 2, 0.0));
			triangle.setPoint(1, new MyPoint(0, height));
			triangle.setPoint(2, new MyPoint(width, height / 2));

			// 画像を三角形にくり抜く
			drawImg = createTransparentImage(triangle);

			// 昔の描画領域の左上の点に戻る
			triangle.move(old_field.x, old_field.y);
		}

		Rectangle2D.Double field = triangle.getDrawField();
		g.drawImage(drawImg, (int)field.x, (int)field.y, null);
	}

	@Override
	public ImageDrawer clone() {
		ImageDrawer imgDrawer = (ImageDrawer)super.clone();
		imgDrawer.image = copyImage(this.image);
		imgDrawer.drawImg = null;
		imgDrawer.filename = new String(this.filename);
		return imgDrawer;
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
