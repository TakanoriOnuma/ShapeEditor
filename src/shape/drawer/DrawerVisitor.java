package shape.drawer;

public abstract class DrawerVisitor {
	public void setDrawerPropaty(Drawer drawer) {
		drawer.accept(this);
	}

	// 無名修飾子で同パッケージ内のみアクセス可能
	abstract void visiteFillDrawer(FillDrawer fillDrawer);
	abstract void visiteLineDrawer(LineDrawer lineDrawer);
	abstract void visiteImageDrawer(ImageDrawer imageDrawer);
}
