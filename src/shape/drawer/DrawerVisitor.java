package shape.drawer;

public abstract class DrawerVisitor {
	public void setDrawerPropaty(Drawer drawer) throws NoUsingPropatyException {
		drawer.accept(this);
	}

	// 無名修飾子で同パッケージ内のみアクセス可能
	abstract void visiteFillDrawer(FillDrawer fillDrawer) throws NoUsingPropatyException;
	abstract void visiteLineDrawer(LineDrawer lineDrawer) throws NoUsingPropatyException;
	abstract void visiteImageDrawer(ImageDrawer imageDrawer) throws NoUsingPropatyException;
}
