package shape.drawer;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.image.ImageObserver;

import shape.drawable.DrawOvalObject;
import shape.drawable.DrawRectangleObject;
import shape.drawable.DrawTriangleObject;

public class ImageDrawer implements Drawer {
	Image image;
	ImageObserver observer;

	public ImageDrawer(String filename, Component comp){
		MediaTracker mt = new MediaTracker(comp);
		image = Toolkit.getDefaultToolkit().createImage("test.jpg");
		mt.addImage(image, 0);
		try{
			mt.waitForID(0);
		}
		catch(InterruptedException e){
		}

		this.observer = comp;
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
	}

	@Override
	public void draw(Graphics g, DrawRectangleObject rect) {
		// TODO 自動生成されたメソッド・スタブ
		g.drawImage(image, (int)rect.getX(), (int)rect.getY(), null);

	}

	@Override
	public void draw(Graphics g, DrawTriangleObject triangle) {
		// TODO 自動生成されたメソッド・スタブ

	}

}
