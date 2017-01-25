package controller.commands;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import common.Level2D;
import model.data.MyTextLevelLoader;

public abstract class LevelLoaderCreator {

	Level2D _newLevel;

	public LevelLoaderCreator() {
		this._newLevel=new Level2D();
	}

	public abstract void create();

	public Level2D getNewLevel(){
		return this._newLevel;
	}

	public void unknownPath(String path){
		System.out.println("The file " + path +" not found");
		this._newLevel=null;
	}

}
