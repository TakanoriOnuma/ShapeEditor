package editor;


enum CommandType{ EXIT, ERROR, SELECT, SHOW, MOVE, DELETE, FRONT, BACK, CREATE, GROUP, UNGROUP, }
public class EditorCommand {
	CommandType command;
	String[] token;			// �R�}���h������ւ̃g�[�N���ւ̕�������
	double x;				// x ���W�l
	double y;				// y ���W�l
}
