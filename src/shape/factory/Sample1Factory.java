package shape.factory;


import java.awt.Color;
import java.util.LinkedList;

import shape.drawable.DrawRectangleObject;
import shape.drawable.DrawTriangleObject;
import shape.drawable.DrawableObject;
import shape.editable.EditableShape;
import shape.editable.MyPoint;

public class Sample1Factory extends EditableShapeFactory {

	@Override
	public EditableShape createShape(String[] token) {
		// TODO 自動生成されたメソッド・スタブ
		EditableShape shape = null;
		if(token.length > 1){
			if(token[1].equals("Triangle") == true){
				shape = new DrawTriangleObject(new MyPoint(0, 0), new MyPoint(0, 10), new MyPoint(20, 0));
			}
			else if(token[1].equals("Rectangle") == true){
				shape = new DrawRectangleObject(0, 0, 10, 10);
			}
			else{
				return null;
			}
		}

		((DrawableObject)shape).setColor(Color.red);

		if(token.length == 4){
			try{
				double dx = Double.parseDouble(token[2]);
				double dy = Double.parseDouble(token[3]);
				shape.move(dx, dy);
			}
			catch(Exception e){
				System.out.println("Double values needed: " + token[0]);
				return null;
			}
		}
		return shape;
	}

	@Override
	public LinkedList<EditableShape> create() {
		// TODO 自動生成されたメソッド・スタブ
		LinkedList<EditableShape> shapeList = new LinkedList<EditableShape>();
		shapeList.add(new DrawRectangleObject(0, 0, 20, 20));
		shapeList.add(new DrawRectangleObject(100, 100, 20, 20));
		shapeList.add(new DrawRectangleObject(30, 30, 20, 40));
		shapeList.add(new DrawTriangleObject(new MyPoint(0, 0), new MyPoint(0, 20), new MyPoint(40, 0)));
		shapeList.add(new DrawTriangleObject(new MyPoint(50, 50), new MyPoint(70, 60), new MyPoint(80, 50)));
		return shapeList;
	}

}
