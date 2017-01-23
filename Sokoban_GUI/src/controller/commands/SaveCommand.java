/**
* This class responsible to run the 'save' command
* @author Maayan & Eden
* @version 2D
*/
package controller.commands;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

import common.Level2D;
import model.Model;
import model.data.MyObjectLevelSaver;
import model.data.MyTextLevelSaver;
import model.data.MyXMLLevelSaver;

public class SaveCommand extends GeneralCommand{
	   // Level2D _lvl; change!!!!!!!!!!!!!!!!!!
		String _path;
		String _typeFile;
		HashMap<String,LevelSaverCreator> _hm=new HashMap<String,LevelSaverCreator>();


		public SaveCommand(String path, Model model) {//start on ConcreteCommandCreator
			super(model);
			this._path=path;
			this._typeFile=getTypeofFile(path);
			_hm.put("txt",new TextLevelSaver());
			_hm.put("xml",new XMLLevelSaver());
			_hm.put("obj",new ObjectLevelSaver());
			//this._lvl=lvl; change!!!!!!!!!!!!!!!!!!

		}

		//HELP FUNCTION - START: get the type of the file from path
		private String getTypeofFile(String pathtoFile){
				String typefile=pathtoFile.substring(pathtoFile.length()-3);
					return typefile;
			}
		//HELP FUNCTION - END


		public void execute() {//run the Hash


			LevelSaverCreator ls=_hm.get(_typeFile);
			_model.save(ls);
			/*
			if (ls==null){
				this._model.setLevel(null);
				System.out.println("There is a problem with level saver.\nNow the level is NULL");
			}
			else{
				ls.create();
			}  */
		}

		//TEXT FILE
		private class TextLevelSaver implements LevelSaverCreator{

			public TextLevelSaver() {
			}
			//public Level2D create() {
			public void create() {
				try {
					MyTextLevelSaver text=new MyTextLevelSaver();
						text.SaveLevel(new FileOutputStream(_path),_model.getLevel());
					} catch (IOException e) {
						System.out.println("coudnt make the save");
					}
				}
			}


		private	class ObjectLevelSaver implements LevelSaverCreator{
			public void create(){
				try {
					MyObjectLevelSaver Object=new MyObjectLevelSaver();
					Object.SaveLevel(new FileOutputStream(_path),_model.getLevel());
					}
				catch (IOException e) {
						System.out.println("coudnt make the save");
					}
			}
		}

		//XML File
		private	class XMLLevelSaver implements LevelSaverCreator{

			public XMLLevelSaver() {
			}
			public void create() {
				try {
					MyXMLLevelSaver XML=new MyXMLLevelSaver();
						XML.SaveLevel(new FileOutputStream(_path),_model.getLevel());
					} catch (IOException e) {
						System.out.println("coudnt make the save");
					}
			}
		}



}