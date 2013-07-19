package sample4;

public abstract class EditableShape {
	private boolean selected;		// 選択フラグ

	public boolean isSelected() {
		return selected;
	}

	public void select() {
		selected = true;
	}

	public void unselect(){
		selected = false;
	}
	public boolean isGroupObject(){
		return false;
	}

	public abstract boolean isIncluding(double xpos, double ypos);	// 領域内判定
	public abstract void move(double dx, double dy);				// 移動する
	public abstract void show();									// 表示する
}
