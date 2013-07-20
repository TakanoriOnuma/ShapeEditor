package shape.factory;


import java.util.LinkedList;

import shape.drawable.DrawRectangleObject;
import shape.drawable.DrawTriangleObject;
import shape.editable.EditableShape;
import shape.editable.MyPoint;

public class DefaultFactory extends EditableShapeFactory {

	@Override
	public EditableShape createShape(String[] token) {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		EditableShape shape = null;
		if(token.length > 1){
			if(token[1].equals("Triangle") == true){
				shape = new DrawTriangleObject(new MyPoint(0, 0), new MyPoint(0, 10), new MyPoint(20, 0));
			}
			else if(token[1].equals("Rectangle") == true){
				shape = new DrawRectangleObject(0, 0, 10, 10);
			}
		}

		return shape;
	}

	@Override
	public LinkedList<EditableShape> create() {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		LinkedList<EditableShape> shapeList = new LinkedList<EditableShape>();
		shapeList.add(new DrawRectangleObject(0, 0, 10, 10));
		shapeList.add(new DrawRectangleObject(20, 20, 20, 10));
		shapeList.add(new DrawTriangleObject(new MyPoint(5, 5), new MyPoint(5, 25), new MyPoint(40, 5)));
		return shapeList;

	}

}
