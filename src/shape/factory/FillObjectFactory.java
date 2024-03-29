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
import shape.drawer.FillDrawer;
import shape.editable.EditableShape;
import shape.editable.MyPoint;

public class FillObjectFactory extends EditableShapeFactory {
	private FillDrawer fillDrawer;

	// --- シングルトンの実装 --- //
	private static FillObjectFactory instance = new FillObjectFactory();

	private FillObjectFactory() {
		fillDrawer = new FillDrawer(Color.orange);
	}

	public static FillObjectFactory getInstance() {
		return instance;
	}

	@Override
	public FillDrawer getDrawer() {
		return fillDrawer;
	}

	@Override
	public EditableShape createShape(String[] token) {
		// TODO 自動生成されたメソッド・スタブ
		EditableShape shape = null;
		if(token.length > 1){
			if(token[1].equals("Triangle") == true){
				shape = new DrawTriangleObject(new MyPoint(0, 0),
							new MyPoint(0, 10), new MyPoint(20, 0), fillDrawer.clone());
			}
			else if(token[1].equals("Rectangle") == true){
				shape = new DrawRectangleObject(0, 0, 10, 10, fillDrawer.clone());
			}
			else if(token[1].equals("RoundRectangle") == true) {
				shape = new DrawRoundRectangleObject(0, 0, 15, 15, 5, 5, fillDrawer.clone());
			}
			else if(token[1].equals("Oval") == true){
				shape = new DrawOvalObject(0, 0, 20, 10, fillDrawer.clone());
			}
			else if(token[1].equals("Arc") == true) {
				shape = new DrawArcObject(0, 0, 20, 20, 20, 300, Arc2D.PIE, fillDrawer.clone());
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
		return fillDrawer.clone();
	}

}
