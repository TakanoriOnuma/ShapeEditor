package shape.drawable;



import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;
import java.util.ListIterator;

import shape.editable.EditableShape;

public class DrawGroupObject extends EditableShape implements DrawableObject {
	private LinkedList<EditableShape> objList;

	public DrawGroupObject(LinkedList<EditableShape> objList){
		this.objList = new LinkedList<EditableShape>();
		this.objList.addAll(objList);
	}
	@Override
	public boolean isGroupObject(){
		return true;
	}
	public LinkedList<EditableShape> getList() {
		return objList;
	}


	@Override
	public void draw(Graphics g) {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		ListIterator<EditableShape> it = objList.listIterator(objList.size());
		while(it.hasPrevious() == true){
			// EditableShape �� DrawableObject�Ƃ��Ĉ���
			DrawableObject obj = (DrawableObject)it.previous();
			obj.draw(g);
		}
	}

	@Override
	public void setColor(Color color) {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		for(EditableShape item : objList){
			// EditableShape �� DrawableObject�Ƃ��Ĉ���
			DrawableObject drawable = (DrawableObject)item;
			drawable.setColor(color);
		}
	}

	@Override
	public boolean isIncluding(double xpos, double ypos) {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		boolean ret = false;
		for(EditableShape item : objList){
			if(item.isIncluding(xpos, ypos) == true){
				ret = true;
				break;
			}
		}
		return ret;
	}

	@Override
	public void move(double dx, double dy) {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		for(EditableShape item : objList){
			item.move(dx, dy);
		}
	}

	@Override
	public void show() {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		show(0);
	}
	private void show(int level){
		String indent = "";
		for(int i = 0; i < level; i++){
			indent += "  ";		// ���x���i����������
		}
		System.out.println(indent + "Group(" + isSelected() + "):");
		for(EditableShape item : objList){
			if(item.isGroupObject() == true){
				DrawGroupObject go = (DrawGroupObject)item;
				go.show(level + 1);
			}
			else{
				System.out.print(indent + "  ");		// ���x�� + 1�i������
				item.show();
			}
		}
	}

}
