package shape.drawable;



import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.util.LinkedList;
import java.util.ListIterator;

import shape.drawer.Drawer;
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
	public Drawer getDrawer() {
		return null;		// Compositeに対して何を返していいのかが分からない
	}


	@Override
	public void draw(Graphics g) {
		// TODO 自動生成されたメソッド・スタブ
		ListIterator<EditableShape> it = objList.listIterator(objList.size());
		while(it.hasPrevious() == true){
			// EditableShape を DrawableObjectとして扱う
			DrawableObject obj = (DrawableObject)it.previous();
			obj.draw(g);
		}
	}



	@Override
	public boolean isIncluding(double xpos, double ypos) {
		// TODO 自動生成されたメソッド・スタブ
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
		// TODO 自動生成されたメソッド・スタブ
		for(EditableShape item : objList){
			item.move(dx, dy);
		}
	}

	@Override
	public void show() {
		// TODO 自動生成されたメソッド・スタブ
		show(0);
	}
	private void show(int level){
		String indent = "";
		for(int i = 0; i < level; i++){
			indent += "  ";		// レベル段数分字下げ
		}
		System.out.println(indent + "Group(" + isSelected() + "):");
		for(EditableShape item : objList){
			if(item.isGroupObject() == true){
				DrawGroupObject go = (DrawGroupObject)item;
				go.show(level + 1);
			}
			else{
				System.out.print(indent + "  ");		// レベル + 1段字下げ
				item.show();
			}
		}
	}


	@Override
	public Rectangle2D.Double getDrawField() {
		// TODO 自動生成されたメソッド・スタブ
		return null;		// とりあえず空を返す
	}


	// 一応オーバーライドするが、中身は何もしていない
	@Override
	public void setDrawer(Drawer drawer) {
		// TODO 自動生成されたメソッド・スタブ

	}

}
