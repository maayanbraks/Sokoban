/**
* This class responsible to run the 'load' command
* @author Maayan & Eden
* @version 2D
*/

package controller.commands;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;

import common.Level2D;
import model.Model;
import model.data.LevelLoader;
import model.data.MyObjectLevelLoader;
import model.data.MyTextLevelLoader;
import model.data.MyXMLLevelLoader;

public class LoadCommand extends GeneralCommand{
	private String _path;
	private String _typeFile;
	private HashMap<String,LevelLoaderCreator> _hm=new HashMap<String,LevelLoaderCreator>();

	/**
	* C'TOR
	*/
	public LoadCommand(String path,Model model) {
		super(model);
		this._path=path;

		this._typeFile=getTypeOfFile(path);//help func
		//Make HashMap
		_hm.put("txt",new TextLevelLoaderCreator());
		_hm.put("xml",new XmlLevelLoaderCreator());
		_hm.put("obj",new ObjectLevelLoaderCreator());
	}

	public void execute() {
		LevelLoaderCreator lc=_hm.get(_typeFile);
		if(lc==null)
			setComment("type file?!?!?!!?");
		else{
			_model.load(lc);
			this.setComment(lc.getComment());
		}



		/*
		if (lc==null){
			this._model.setLevel(new Level2D());
			System.out.println("There is a problem with level loader./n" +
								"Now the level is NULL");
		}
		else{
			lc.create();
			this._model.setLevel(((LevelCreator)lc).getNewLevel());
		}
	*/

	}





	/**
	* This function HELP us to get the type of the file from path
	*/
	private String getTypeOfFile(String pathtoFile){
			String typefile=pathtoFile.substring(pathtoFile.length()-3);
				return typefile;
		}


	/**
	* Classes for our design
	* @author Maayan & Eden
	* @version 2D
	*/
	//TEXT FILE
	private class TextLevelLoaderCreator extends LevelLoaderCreator{

		public TextLevelLoaderCreator() {
			super(_path);

		}

		/*
		public void create() {
			try {
				this._newLevel=((new MyTextLevelLoader()).loadLevel(new FileInputStream(_path)));
			}
			catch (FileNotFoundException e) {
				setComment("The file " + _path +" dosnt exist.");
				this._newLevel=null;
			}
		}*/

		@Override
		public LevelLoader create() {

			return new MyTextLevelLoader();
		}
	}


	//Object File
		private class ObjectLevelLoaderCreator extends LevelLoaderCreator{

				public ObjectLevelLoaderCreator() {
					super(_path);
					}
/*
				public void create() {
					try {
						this._newLevel=((new MyObjectLevelLoader()).loadLevel(new FileInputStream(_path)));
					}
					catch (FileNotFoundException e) {
						setComment("The file " + _path +" dosnt exist.");
						this._newLevel=null;

					}
				}
				*/

				public LevelLoader create() {
					return new MyObjectLevelLoader();
				}
		}


	//XML File
	private class XmlLevelLoaderCreator extends LevelLoaderCreator{
		public XmlLevelLoaderCreator() {
			super(_path);
			}
/*
		public void create() {
			try {
				this._newLevel=((new MyXMLLevelLoader()).loadLevel(new FileInputStream(_path)));
			}
			catch (FileNotFoundException e) {
				setComment("The file " + _path +" dosnt exist.");
				unknownPath(_path);

			}
		}*/

		public LevelLoader create() {
			setComment("command accepted");
			return new MyXMLLevelLoader();
		}
	}


}

