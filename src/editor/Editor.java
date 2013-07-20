package editor;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import shape.drawable.DrawGroupObject;
import shape.drawable.DrawRectangleObject;
import shape.drawable.DrawTriangleObject;
import shape.editable.EditableShape;
import shape.editable.MyPoint;
import shape.factory.EditableShapeFactory;
import shape.factory.FillObjectFactory;
import window.DisplayWindow;

public class Editor {
	static List<EditableShape> shapeList;		// 図形要素を保持する配列
	static DisplayWindow myWindow;				// shapeList の表示用ウィンドウ
	static EditableShapeFactory f;				// 動的に決定する生成工場のインスタンス

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BufferedReader input =
				new BufferedReader(new InputStreamReader(System.in));

		f = new FillObjectFactory();	// 始めは塗りつぶすFactoryにする

		shapeList = f.create();			// 図形要素の配列を生成
		show();							// 図形要素を順に表示
		myWindow = new DisplayWindow((LinkedList<?>)shapeList);
		myWindow.drawAll();


		boolean goOn = true;
		while(goOn){
			EditorCommand ec = readCommand(input);
			goOn = executeCommand(ec);

		}

	}

	static LinkedList<EditableShape> create(){
		LinkedList<EditableShape> shapeList = new LinkedList<EditableShape>();
		shapeList.add(new DrawRectangleObject(0, 0, 10, 10));
		shapeList.add(new DrawRectangleObject(20, 20, 20, 10));
		shapeList.add(new DrawTriangleObject(new MyPoint(5, 5), new MyPoint(5, 25), new MyPoint(40, 5)));
		return shapeList;
	}

	static void show(){
		for(EditableShape item : shapeList){
			item.show();
		}
	}

	static EditorCommand readCommand(BufferedReader input){
		EditorCommand ec = new EditorCommand();

		System.out.print(">> ");
		String str;
		try{
			str = input.readLine();
		}
		catch(IOException e){
			System.out.println("Please input correctly");
			ec.command = CommandType.ERROR;
			return ec;
		}

		ec.token = str.split(" ");		// strを' 'で分割

		if(ec.token[0].equals("create") == true) {
			ec.command = CommandType.CREATE;
		}
		else if(ec.token.length == 3){
			try{
				// 第1引数を x に第2引数を y にDoubleとして読み込む
				ec.x = Double.parseDouble(ec.token[1]);
				ec.y = Double.parseDouble(ec.token[2]);
			}
			catch(NumberFormatException e){
				// 第1と第2引数がDoubleとして解釈できない場合
				ec.command = CommandType.ERROR;
				System.out.println("Double values needed: " + ec.token[0]);
				return ec;
			}

			// 操作コマンド"select"と"move"を操作コマンドとする
			if(ec.token[0].equals("select") == true){
				ec.command = CommandType.SELECT;
			}
			else if(ec.token[0].equals("move") == true){
				ec.command = CommandType.MOVE;
			}
			else{
				System.out.println("Not a command: " + ec.token[0]);
				ec.command = CommandType.ERROR;
			}
		}
		else if(ec.token.length == 2){
			if(ec.token[0].equals("factory") == true){
				ec.command = CommandType.FACTORY;
			}
			else{
				System.out.println("Not a command: " + ec.token[0]);
				ec.command = CommandType.ERROR;
			}
		}
		else if(ec.token[0].equals("delete") == true) {
			ec.command = CommandType.DELETE;
		}
		else if(ec.token[0].equals("front") == true) {
			ec.command = CommandType.FRONT;
		}
		else if(ec.token[0].equals("back") == true) {
			ec.command = CommandType.BACK;
		}
		else if(ec.token[0].equals("show") == true){
			ec.command = CommandType.SHOW;
		}
		else if(ec.token[0].equals("quit") == true){
			ec.command = CommandType.EXIT;
		}
		else if(ec.token[0].equals("group")){
			ec.command = CommandType.GROUP;
		}
		else if(ec.token[0].equals("ungroup")){
			ec.command = CommandType.UNGROUP;
		}
		else{
			if(str.length() > 0){
				System.out.println(str + ": Not a command!");
			}
			ec.command = CommandType.ERROR;
		}
		return ec;
	}

	static boolean executeCommand(EditorCommand ec){
		Iterator<EditableShape> it;
		LinkedList<EditableShape> addList = new LinkedList<EditableShape>();

		boolean goOn = true;

		switch(ec.command){
		case EXIT:
			myWindow.invisible();
			System.out.println("bye");
			goOn = false;
			break;

		case SELECT:
			System.out.println("select: " + ec.x + ", " + ec.y);
			// x y で指定した座標位置に、最初に存在する図形を選択
			for(EditableShape item : shapeList){
				if(item.isIncluding(ec.x, ec.y) == true){
					if(item.isSelected() == true){
						item.unselect();
					}
					else{
						item.select();
					}
					item.show();
					break;
				}
			}
			break;

		case MOVE:
			for(EditableShape item : shapeList){
				if(item.isSelected() == true){
					item.move(ec.x, ec.y);
					item.show();
				}
			}
			break;

		case SHOW:
			show();
			break;

		case DELETE:
			it = shapeList.iterator();
			while(it.hasNext()){
				EditableShape item = it.next();
				if(item.isSelected() == true) {
					System.out.print("Remove: ");
					item.show();
					it.remove();
				}
			}
			break;

		case FRONT:
			it = shapeList.iterator();
			while(it.hasNext()) {
				EditableShape item = it.next();
				if(item.isSelected() == true) {
					addList.add(item);
					it.remove();
				}
			}
			shapeList.addAll(0, addList);
			break;

		case BACK:
			it = shapeList.iterator();
			while(it.hasNext()) {
				EditableShape item = it.next();
				if(item.isSelected() == true) {
					addList.add(item);
					it.remove();
				}
			}
			shapeList.addAll(addList);
			break;

		case CREATE:
			EditableShape shape = f.createShape(ec.token);
			if(shape != null) {
				((LinkedList<EditableShape>)shapeList).addFirst(shape);
				shape.select();
				System.out.print("Create: ");
				shape.show();
			}
			break;

		case GROUP:
			it = shapeList.iterator();
			while(it.hasNext()){
				EditableShape item = it.next();
				if(item.isSelected() == true){
					item.unselect();
					addList.add(item);
					it.remove();
				}
			}
			DrawGroupObject go = new DrawGroupObject(addList);
			go.select();
			((LinkedList<EditableShape>)shapeList).addFirst(go);
			break;

		case UNGROUP:
			it = shapeList.iterator();
			// 選択グループ要素の保持する要素をaddListに入れ
			// shapeListから外す。最後にaddListをshapeListの先頭に挿入
			while(it.hasNext()){
				EditableShape item = it.next();
				if(item.isSelected() == true && item.isGroupObject() == true){
					DrawGroupObject gobj = (DrawGroupObject)item;
					addList.addAll(gobj.getList());
					it.remove();
				}
			}
			for(EditableShape item : addList){
				item.select();
			}
			break;

		case FACTORY:
		{
			EditableShapeFactory factory = EditableShapeFactory.getFactory(ec.token[1]);
			if(factory != null){
				f = factory;
			}

			break;
		}

		case ERROR:
		default:
			break;
		}
		myWindow.drawAll();
		return goOn;
	}


	public static EditableShape createShape(String[] token) {
		EditableShape ret = null;
		if(token[1].equals("Triangle") == true) {
			ret = new DrawTriangleObject(new MyPoint(0, 0), new MyPoint(0, 10), new MyPoint(20, 0));
		}
		else if(token[1].equals("Rectangle") == true) {
			ret = new DrawRectangleObject(0, 0, 10, 10);
		}
		return ret;
	}
}
