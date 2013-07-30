package shape.drawer;

import java.awt.Color;

public class ColorDrawerGetter extends DrawerVisitor {
	private Color color;

	public Color getColor() {
		return color;
	}

	@Override
	void visiteFillDrawer(FillDrawer fillDrawer) throws NoUsingPropatyException {
		// TODO 自動生成されたメソッド・スタブ
		color = fillDrawer.getColor();
	}

	@Override
	void visiteLineDrawer(LineDrawer lineDrawer) throws NoUsingPropatyException {
		// TODO 自動生成されたメソッド・スタブ
		color = lineDrawer.getColor();
	}

	@Override
	void visiteImageDrawer(ImageDrawer imageDrawer) throws NoUsingPropatyException {
		// TODO 自動生成されたメソッド・スタブ
		// 画像の情報は取得できなため例外を投げる
		throw new NoUsingPropatyException("画像の情報は取得できません。");
	}

}
