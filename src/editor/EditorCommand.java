package editor;


enum CommandType{ EXIT, ERROR, SELECT, SHOW, MOVE, DELETE, FRONT, BACK,
	CREATE, GROUP, UNGROUP, FACTORY, FACTORY_COLOR, COLOR, INFO_COLOR,
	IMAGE, FACTORY_IMAGE, DRAWER, SAVE, LOAD, CLEAR, }
public class EditorCommand {
	CommandType command;
	String[] token;			// コマンド文字列へのトークンへの分解結果
	double x;				// x 座標値
	double y;				// y 座標値
}
