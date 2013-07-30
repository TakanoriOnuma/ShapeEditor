package shape.drawer;

public class ImageDrawerSetter extends DrawerVisitor {
	private String filename;

	public ImageDrawerSetter(String filename) {
		this.filename = new String(filename);
	}

	@Override
	void visiteFillDrawer(FillDrawer fillDrawer) throws NoUsingPropatyException {
		// TODO 自動生成されたメソッド・スタブ
		// 画像をセットできないので例外を投げる
		throw new NoUsingPropatyException("FillDrawerに画像の設定は出来ません。");
	}

	@Override
	void visiteLineDrawer(LineDrawer lineDrawer) throws NoUsingPropatyException {
		// TODO 自動生成されたメソッド・スタブ
		// 画像をセットできないので例外を投げる
		throw new NoUsingPropatyException("LineDrawerに画像の設定は出来ません。");
	}

	@Override
	void visiteImageDrawer(ImageDrawer imageDrawer) throws NoUsingPropatyException {
		// TODO 自動生成されたメソッド・スタブ
		imageDrawer.setImage(filename);
	}

}
