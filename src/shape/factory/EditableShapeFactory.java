package shape.factory;

import java.util.LinkedList;

import shape.editable.EditableShape;

public abstract class EditableShapeFactory {
	// 具象 Factory を生成するメソッド
	public static EditableShapeFactory getFactory(String maker){
		EditableShapeFactory ret;
		try{
			Class<?> c = Class.forName("shape.factory." + maker + "Factory");		// �p�b�P�[�W�����܂߂��N���X��
			ret = (EditableShapeFactory)c.newInstance();
		}
		catch(Exception e){
			System.out.println(maker + ": does not exist");
			return null;
		}
		return ret;
	}

	public abstract EditableShape createShape(String[] token);
	public abstract LinkedList<EditableShape> create();
}
