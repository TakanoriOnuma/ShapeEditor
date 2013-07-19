package sample4;

import java.util.*;

public abstract class EditableShapeFactory {
	// 具象 Factory を生成するメソッド
	public static EditableShapeFactory getFactory(String maker){
		EditableShapeFactory ret;
		try{
			Class<?> c = Class.forName("sample4." + maker + "Factory");		// パッケージ名も含めたクラス名
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
