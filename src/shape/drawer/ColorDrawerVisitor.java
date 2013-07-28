package shape.drawer;

import java.awt.Color;

public class ColorDrawerVisitor extends DrawerVisitor {
	private Color color;

	public ColorDrawerVisitor(Color color) {
		this.color = color;
	}

	@Override
	void visiteFillDrawer(FillDrawer fillDrawer) throws NoUsingPropatyException {
		// TODO 自動生成されたメソッド・スタブ
		fillDrawer.setColor(color);
	}

	@Override
	void visiteLineDrawer(LineDrawer lineDrawer) throws NoUsingPropatyException {
		// TODO 自動生成されたメソッド・スタブ
		lineDrawer.setColor(color);
	}

	@Override
	void visiteImageDrawer(ImageDrawer imageDrawer) throws NoUsingPropatyException {
		// TODO 自動生成されたメソッド・スタブ
		// 色をセットするわけではないので例外を投げる
		throw new NoUsingPropatyException("ImageDrawerに色の設定は出来ません。");
	}

}
