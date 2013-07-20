package shape.factory;

import java.util.*;

import shape.editable.EditableShape;

public abstract class EditableShapeFactory {
	// ��� Factory �𐶐����郁�\�b�h
	public static EditableShapeFactory getFactory(String maker){
		EditableShapeFactory ret;
		try{
			Class<?> c = Class.forName("sample4." + maker + "Factory");		// �p�b�P�[�W�����܂߂��N���X��
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
