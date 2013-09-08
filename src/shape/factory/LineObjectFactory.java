package shape.factory;

import java.awt.Color;
import java.awt.geom.Arc2D;
import java.util.LinkedList;

import shape.drawable.DrawArcObject;
import shape.drawable.DrawOvalObject;
import shape.drawable.DrawRectangleObject;
import shape.drawable.DrawRoundRectangleObject;
import shape.drawable.DrawTriangleObject;
import shape.drawer.Drawer;
import shape.drawer.LineDrawer;
import shape.editable.EditableShape;
import shape.editable.MyPoint;

public class LineObjectFactory extends EditableShapeFactory {
	private LineDrawer lineDrawer;

	// --- シングルトンの実装 --- //
	static private LineObjectFactory instance = new LineObjectFactory();

	private LineObjectFactory() {
		lineDrawer = new LineDrawer(Color.blue);
	}

	public static LineObjectFactory getInstance() {
		return instance;
	}

	@Override
	public LineDrawer getDrawer() {
		return lineDrawer;
	}


	@Override
	public EditableShape createShape(String[] token) {
		// TODO 自動生成されたメソッド・スタブ
		EditableShape shape = null;
		if(token.length > 1){
			if(token[1].equals("Triangle") == true){
				shape = new DrawTriangleObject(new MyPoint(0, 0), new MyPoint(0, 10),
							new MyPoint(20, 0), lineDrawer.clone());
			}
			else if(token[1].equals("Rectangle") == true){
				shape = new DrawRectangleObject(0, 0, 10, 10, lineDrawer.clone());
			}
			else if(token[1].equals("RoundRectangle") == true) {
				shape = new DrawRoundRectangleObject(0, 0, 15, 15, 5, 5, lineDrawer.clone());
			}
			else if(token[1].equals("Oval") == true){
				shape = new DrawOvalObject(0, 0, 20, 10, lineDrawer.clone());
			}
			else if(token[1].equals("Arc") == true) {
				shape = new DrawArcObject(0, 0, 20, 20, 20, 300, Arc2D.PIE, lineDrawer.clone());
			}
		}
		return shape;
	}

	@Override
	public LinkedList<EditableShape> create() {
		// TODO 自動生成されたメソッド・スタブ
		LinkedList<EditableShape> shapeList = new LinkedList<EditableShape>();
		shapeList.add(new DrawRectangleObject(0, 0, 10, 10, new LineDrawer()));
		shapeList.add(new DrawRectangleObject(20, 20, 20, 10, new LineDrawer()));
		shapeList.add(new DrawTriangleObject(new MyPoint(5, 5),
				new MyPoint(5, 25), new MyPoint(40, 5), new LineDrawer()));
		shapeList.add(new DrawOvalObject(40, 10, 20, 10, new LineDrawer()));
		return shapeList;
	}

	@Override
	public Drawer createDrawer() {
		// TODO 自動生成されたメソッド・スタブ
		return lineDrawer.clone();
	}

}
