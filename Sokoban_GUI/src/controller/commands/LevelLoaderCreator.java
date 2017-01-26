package controller.commands;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import common.Level2D;
import model.data.LevelLoader;
import model.data.MyTextLevelLoader;

public abstract class LevelLoaderCreator {

	//Level2D _newLevel;
	private String _loadComment;
	String _filePath;

	public LevelLoaderCreator(String path) {
		_filePath=path;
//		this._newLevel=new Level2D();
		this._loadComment= "accep" ;//default comment (only if we create level loader its ok)

	}

	public abstract LevelLoader create();

/*
	public Level2D getNewLevel(){
		return this._newLevel;
	}
*/
	public void unknownPath(){
		setComment("I cant find this file....");

	}


	public void setComment(String comment) {
		_loadComment=comment;
	}

	public String getComment() {
		return _loadComment;
	}

	public String getPath() {
		return this._filePath;
	}

}
