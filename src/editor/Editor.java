package editor;


import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import shape.drawable.DrawArcObject;
import shape.drawable.DrawGroupObject;
import shape.drawable.DrawOvalObject;
import shape.drawable.DrawRectangleObject;
import shape.drawable.DrawRoundRectangleObject;
import shape.drawable.DrawTriangleObject;
import shape.drawable.DrawableObject;
import shape.drawer.ColorDrawerGetter;
import shape.drawer.ColorDrawerSetter;
import shape.drawer.Drawer;
import shape.drawer.FillDrawer;
import shape.drawer.ImageDrawer;
import shape.drawer.ImageDrawerSetter;
import shape.drawer.LineDrawer;
import shape.drawer.NoUsingPropatyException;
import shape.editable.EditableShape;
import shape.editable.MyPoint;
import shape.factory.EditableShapeFactory;
import shape.factory.FillObjectFactory;
import shape.factory.ImageObjectFactory;
import shape.factory.LineObjectFactory;
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

		f = FillObjectFactory.getInstance();	// 始めは塗りつぶすFactoryにする

		shapeList = f.create();			// 図形要素の配列を生成
		show();							// 図形要素を順に表示
		myWindow = new DisplayWindow((LinkedList<?>)shapeList);
		myWindow.drawAll();

		ImageObjectFactory.create(myWindow);	// あらかじめ作っておく

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
		else if(ec.token.length == 4){
			if(ec.token[0].equals("color") == true) {
				ec.command = CommandType.COLOR;
			}
		}
		else if(ec.token[0].equals("factory") == true){
			if(ec.token[1].equals("color")) {
				ec.command = CommandType.FACTORY_COLOR;
			}
			else if(ec.token[1].equals("image")) {
				ec.command = CommandType.FACTORY_IMAGE;
			}
			else {
				ec.command = CommandType.FACTORY;
			}
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
		else if(ec.token[0].equals("drawer")) {
			ec.command = CommandType.DRAWER;
		}
		else if(ec.token[0].equals("image")) {
			ec.command = CommandType.IMAGE;
		}
		else if(ec.token[0].equals("info")) {
			if(ec.token[1].equals("color")) {
				ec.command = CommandType.INFO_COLOR;
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
		else if(ec.token[0].equals("save")) {
			ec.command = CommandType.SAVE;
		}
		else if(ec.token[0].equals("load")) {
			ec.command = CommandType.LOAD;
		}
		else if(ec.token[0].equals("clear")) {
			ec.command = CommandType.CLEAR;
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
		{
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
		}

		case FRONT:
		{
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
		}

		case BACK:
		{
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
		}

		case CREATE:
		{
			EditableShape shape = f.createShape(ec.token);
			if(shape != null) {
				((LinkedList<EditableShape>)shapeList).addFirst(shape);
				shape.select();
				System.out.print("Create: ");
				shape.show();
			}
			break;
		}

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
			if(ec.token[1].equals("ImageObject") == true) {
				f = ImageObjectFactory.getInstance();
			}
			else if(ec.token[1].equals("LineObject")) {
				f = LineObjectFactory.getInstance();
			}
			else if(ec.token[1].equals("FillObject")) {
				f = FillObjectFactory.getInstance();
			}
			else {
				EditableShapeFactory factory = EditableShapeFactory.getFactory(ec.token[1]);
				if(factory != null){
					f = factory;
				}
			}

			break;
		}

		case FACTORY_COLOR:
		{
			int r, g, b;
			try {
				r = Integer.parseInt(ec.token[2]);
				g = Integer.parseInt(ec.token[3]);
				b = Integer.parseInt(ec.token[4]);
			}
			// 変換に失敗した場合
			catch(NumberFormatException e) {
				System.out.println("Int values needed: " + ec.token[0]);
				break;
			}

			if(r >= 0 && r <= 255 &&
				g >= 0 && g <= 255 &&
				b >= 0 && b <= 255) {
					ColorDrawerSetter setter = new ColorDrawerSetter(new Color(r, g, b));
					try{
						setter.visitDrawerPropaty(f.getDrawer());
					}
					catch (NoUsingPropatyException e) {
						System.out.println(e);
					}
			}

			break;
		}

		case COLOR:
		{
			int r, g, b;
			try {
				r = Integer.parseInt(ec.token[1]);
				g = Integer.parseInt(ec.token[2]);
				b = Integer.parseInt(ec.token[3]);
			}
			// 変換に失敗した場合
			catch(NumberFormatException e) {
				System.out.println("Int values needed: " + ec.token[0]);
				break;
			}

			if(r >= 0 && r <= 255 &&
				g >= 0 && g <= 255 &&
				b >= 0 && b <= 255) {
					ColorDrawerSetter setter = new ColorDrawerSetter(new Color(r, g, b));
					for(EditableShape item : shapeList) {
						if(item.isSelected()) {
							// DrawableObjectに無理やりキャスト
							DrawableObject obj = (DrawableObject)item;

							try {
								setter.visitDrawerPropaty(obj.getDrawer());
							}
							catch(NoUsingPropatyException e) {
								System.out.println(e);
							}
						}
					}
			}

			break;
		}

		case INFO_COLOR:
		{
			ColorDrawerGetter getter = new ColorDrawerGetter();
			for(EditableShape item : shapeList) {
				if(item.isSelected()) {
					item.show();
					System.out.print(":");

					// DrawableObjectに無理やりキャスト
					DrawableObject obj = (DrawableObject)item;

					try {
						getter.visitDrawerPropaty(obj.getDrawer());
						Color color = getter.getColor();
						System.out.println("color(" + color.getRed() + ", " + color.getGreen() +
								", " + color.getBlue() + ")");
					}
					catch(NoUsingPropatyException e) {
						System.out.println(e);
					}
				}
			}

			break;
		}

		case IMAGE:
		{
			ImageDrawerSetter setter = new ImageDrawerSetter(ec.token[1]);
			for(EditableShape item : shapeList) {
				if(item.isSelected()) {
					DrawableObject obj = (DrawableObject)item;		// 無理やりキャスト

					try {
						setter.visitDrawerPropaty(obj.getDrawer());
					}
					catch (NoUsingPropatyException e) {
						System.out.println(e);
					}
				}
			}

			break;
		}

		case FACTORY_IMAGE:
		{
			ImageDrawerSetter setter = new ImageDrawerSetter(ec.token[2]);

			try {
				setter.visitDrawerPropaty(f.getDrawer());
			}
			catch (NoUsingPropatyException e) {
				System.out.println(e);
			}

			break;
		}

		case DRAWER:
		{
			Drawer drawer = getDrawer(ec.token[1]);

			if(drawer != null) {
				for(EditableShape item : shapeList) {
					if(item.isSelected()) {
						DrawableObject obj = (DrawableObject)item;		// 無理やりキャスト
						obj.setDrawer(drawer.clone());					// それぞれにdrawerを作って渡す
					}
				}
			}
			else {
				System.out.println("no such drawer: " + ec.token[1]);
			}


			break;
		}

		case SAVE:
		{
			try {
				File file = new File(ec.token[1] + ".sav");
				FileWriter fileWriter = new FileWriter(file);

				for(EditableShape shape : shapeList) {
					String inpStr = shape.toString() + "/";
					inpStr += ((DrawableObject)shape).getDrawer().toString();
					fileWriter.write(inpStr + "\r\n");
				}

				fileWriter.close();
				System.out.println("Saveしました。");
			}
			catch(IOException e) {
				System.out.println(e);
			}

			break;
		}

		case LOAD:
		{
			try {
				shapeList.clear();

				File file = new File(ec.token[1] + ".sav");
				FileReader fileReader = new FileReader(file);
				BufferedReader bufferedReader = new BufferedReader(fileReader);

				String line = bufferedReader.readLine();
				while(line != null) {
					String[] info = line.split("/");
					String[] shape_data = info[0].split(" ");
					String[] drawer_data = info[1].split(" ");
					double[] shape_values = new double[shape_data.length - 1];
					int[] drawer_values = new int[drawer_data.length - 1];
					String filename = "";

					for(int i = 1; i < shape_data.length; i++) {
						try {
							shape_values[i - 1] = Double.parseDouble(shape_data[i]);
						}
						catch(NumberFormatException e) {
							System.out.println("値の変換が出来ませんでした。");
							bufferedReader.close();
							return false;		// 強制終了
						}
					}

					if(drawer_data[0].equals("image")) {
						filename = drawer_data[1];
					}
					else {
						for(int i = 1; i < drawer_data.length; i++) {
							try {
								drawer_values[i - 1] = Integer.parseInt(drawer_data[i]);
							}
							catch(NumberFormatException e) {
								System.out.println("値の変換が出来ませんでした。");
								bufferedReader.close();
								return false;		// 強制終了
							}
						}
					}

					Drawer drawer;

					if(drawer_data[0].equals("fill")) {
						drawer = new FillDrawer(new Color(drawer_values[0], drawer_values[1], drawer_values[2], drawer_values[3]));
					}
					else if(drawer_data[0].equals("line")) {
						drawer = new LineDrawer(new Color(drawer_values[0], drawer_values[1], drawer_values[2], drawer_values[3]));
					}
					else if(drawer_data[0].equals("image")) {
						drawer = new ImageDrawer(filename, myWindow);
					}
					else {
						System.out.println("そのようなdrawerはありません。:" + drawer_data[0]);
						bufferedReader.close();
						return false;			// 強制終了
					}

					if(shape_data[0].equals("Rectangle")) {
						shapeList.add((EditableShape)new DrawRectangleObject(shape_values[0],
								shape_values[1], shape_values[2], shape_values[3], drawer));
					}
					else if(shape_data[0].equals("Triangle")) {
						shapeList.add((EditableShape)new DrawTriangleObject(new MyPoint(shape_values[0], shape_values[1]),
								new MyPoint(shape_values[2], shape_values[3]), new MyPoint(shape_values[4], shape_values[5]),
								drawer));
					}
					else if(shape_data[0].equals("Oval")) {
						shapeList.add((EditableShape)new DrawOvalObject(shape_values[0],
								shape_values[1], shape_values[2], shape_values[3], drawer));
					}
					else if(shape_data[0].equals("RoundRectangle")) {
						shapeList.add((EditableShape)new DrawRoundRectangleObject(shape_values[0],
								shape_values[1], shape_values[2], shape_values[3], shape_values[4],
								shape_values[5], drawer));
					}
					else if(shape_data[0].equals("Arc")) {
						shapeList.add((EditableShape)new DrawArcObject(shape_values[0], shape_values[1],
								shape_values[2], shape_values[3], shape_values[4], shape_values[5],
								(int)shape_values[6], drawer));
					}
					else {
						System.out.println("そのような図形はありません。:" + shape_data[0]);
					}



					line = bufferedReader.readLine();
				}

				bufferedReader.close();

				System.out.println("Loadしました。");
				show();
			}
			catch(IOException e) {
				System.out.println(e);
			}

			break;
		}


		case CLEAR:
			shapeList.clear();
			break;


		case ERROR:
		default:
			break;
		}
		myWindow.drawAll();
		return goOn;
	}


	private static Drawer getDrawer(String str) {
		Drawer drawer = null;

		if(str.equals("fill")) {
			drawer = FillObjectFactory.getInstance().createDrawer();
		}
		else if(str.equals("line")) {
			drawer = LineObjectFactory.getInstance().createDrawer();
		}
		else if(str.equals("image")) {
			drawer = ImageObjectFactory.getInstance().createDrawer();
		}

		return drawer;
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
