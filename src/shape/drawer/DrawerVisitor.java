package shape.drawer;

public abstract class DrawerVisitor {
	public void visiteDrawerPropaty(Drawer drawer) throws NoUsingPropatyException {
		drawer.accept(this);
	}

	// 無名修飾子で同パッケージ内のみアクセス可能
	abstract void visitFillDrawer(FillDrawer fillDrawer) throws NoUsingPropatyException;
	abstract void visitLineDrawer(LineDrawer lineDrawer) throws NoUsingPropatyException;
	abstract void visitImageDrawer(ImageDrawer imageDrawer) throws NoUsingPropatyException;
}
