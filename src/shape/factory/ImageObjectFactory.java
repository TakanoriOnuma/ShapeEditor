package shape.factory;

import java.awt.Component;
import java.awt.geom.Arc2D;
import java.util.LinkedList;

import shape.drawable.DrawArcObject;
import shape.drawable.DrawOvalObject;
import shape.drawable.DrawRectangleObject;
import shape.drawable.DrawRoundRectangleObject;
import shape.drawable.DrawTriangleObject;
import shape.drawer.Drawer;
import shape.drawer.ImageDrawer;
import shape.editable.EditableShape;
import shape.editable.MyPoint;

public class ImageObjectFactory extends EditableShapeFactory {
	private ImageDrawer imgDrawer;

	static private ImageObjectFactory instance = null;

	private ImageObjectFactory(Component comp){
		imgDrawer = new ImageDrawer("dog.png", comp);
	}


	public static void create(Component comp) {
		instance = new ImageObjectFactory(comp);
	}

	public static ImageObjectFactory getInstance() {
		return instance;
	}



	@Override
	public ImageDrawer getDrawer() {
		return imgDrawer;
	}

	@Override
	public EditableShape createShape(String[] token) {
		// TODO 自動生成されたメソッド・スタブ
		EditableShape shape = null;
		if(token.length > 1){
			double width = imgDrawer.getImage().getWidth(null);
			double height = imgDrawer.getImage().getHeight(null);
			if(token[1].equals("Triangle") == true){
				shape = new DrawTriangleObject(new MyPoint(width / 2, 0),
							new MyPoint(0, height), new MyPoint(width, height / 2), imgDrawer.clone());
			}
			else if(token[1].equals("Rectangle") == true){
				shape = new DrawRectangleObject(0, 0, width, height, imgDrawer.clone());
			}
			else if(token[1].equals("RoundRectangle") == true) {
				shape = new DrawRoundRectangleObject(0, 0, width, height,
							width / 3, height / 3, imgDrawer.clone());
			}
			else if(token[1].equals("Oval") == true){
				shape = new DrawOvalObject(0, 0, width, height, imgDrawer.clone());
			}
			else if(token[1].equals("Arc") == true) {
				shape = new DrawArcObject(0, 0, width, height, 20, 300, Arc2D.PIE, imgDrawer.clone());
			}
		}
		return shape;
	}

	@Override
	public LinkedList<EditableShape> create() {
		// TODO 自動生成されたメソッド・スタブ
		LinkedList<EditableShape> shapeList = new LinkedList<EditableShape>();
		shapeList.add(new DrawRectangleObject(0, 0, 10, 10));
		shapeList.add(new DrawRectangleObject(20, 20, 20, 10));
		shapeList.add(new DrawTriangleObject(new MyPoint(5, 5), new MyPoint(5, 25), new MyPoint(40, 5)));
		shapeList.add(new DrawOvalObject(40, 10, 20, 10));
		return shapeList;
	}


	@Override
	public Drawer createDrawer() {
		// TODO 自動生成されたメソッド・スタブ
		return imgDrawer.clone();
	}

}
