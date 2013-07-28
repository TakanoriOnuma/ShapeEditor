package shape.drawer;

import java.awt.Color;

public class ColorDrawerVisitor extends DrawerVisitor {
	private Color color;

	public ColorDrawerVisitor(Color color) {
		this.color = color;
	}

	@Override
	void visiteFillDrawer(FillDrawer fillDrawer) {
		// TODO 自動生成されたメソッド・スタブ
		fillDrawer.setColor(color);
	}

	@Override
	void visiteLineDrawer(LineDrawer lineDrawer) {
		// TODO 自動生成されたメソッド・スタブ
		lineDrawer.setColor(color);
	}

	@Override
	void visiteImageDrawer(ImageDrawer imageDrawer) {
		// TODO 自動生成されたメソッド・スタブ
		// 色をセットするわけではないのでスルー
	}

}
