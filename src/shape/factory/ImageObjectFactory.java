package shape.factory;

import java.awt.Component;
import java.util.LinkedList;

import shape.drawable.DrawOvalObject;
import shape.drawable.DrawRectangleObject;
import shape.drawable.DrawTriangleObject;
import shape.drawer.ImageDrawer;
import shape.editable.EditableShape;
import shape.editable.MyPoint;

public class ImageObjectFactory extends EditableShapeFactory {
	Component comp;

	public ImageObjectFactory(Component comp){
		this.comp= comp;
	}

	@Override
	public EditableShape createShape(String[] token) {
		// TODO 自動生成されたメソッド・スタブ
		EditableShape shape = null;
		if(token.length > 1){
			if(token[1].equals("Triangle") == true){
				shape = new DrawTriangleObject(new MyPoint(0, 0), new MyPoint(0, 10),
							new MyPoint(20, 0), new ImageDrawer("rectangle.png", comp));
			}
			else if(token[1].equals("Rectangle") == true){
				ImageDrawer imgDrawer = new ImageDrawer("rectangle.png", comp);
				int width = imgDrawer.getImage().getWidth(null);
				int height = imgDrawer.getImage().getHeight(null);
				shape = new DrawRectangleObject(0, 0, width, height, imgDrawer);
			}
			else if(token[1].equals("Oval") == true){
				ImageDrawer imgDrawer = new ImageDrawer("rectangle.png", comp);
				int width = imgDrawer.getImage().getWidth(null);
				int height = imgDrawer.getImage().getHeight(null);
				shape = new DrawOvalObject(0, 0, width, height, imgDrawer);
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

}
