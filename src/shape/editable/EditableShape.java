package shape.editable;


public abstract class EditableShape {
	private boolean selected;		// �I���t���O

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

	public abstract boolean isIncluding(double xpos, double ypos);	// �̈������
	public abstract void move(double dx, double dy);				// �ړ�����
	public abstract void show();									// �\������
}
