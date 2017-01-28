/**
* This class is the game model
* @author Maayan & Eden
*/

package model;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Observable;

import common.Level2D;
import controller.commands.LevelLoaderCreator;
import controller.commands.LevelSaverCreator;
import model.data.Actor;
import model.data.Box;
import model.data.Item;
import model.data.MyTextLevelSaver;
import model.data.Position2D;
import model.policy.MySokobanPolicy;

public class MyModel extends Observable implements Model{

	Level2D _lvl;
	MySokobanPolicy _msp;


	public Level2D getLevel() {
		return _lvl;
	}

	public void setLevel(Level2D lvl){
		_lvl=new Level2D(lvl);
	}

	public MySokobanPolicy getPolicy() {
		return _msp;
	}

	@Override
	public void move(Position2D dest) {
		Actor actor=_lvl.getActors().get(0);
		Position2D old=actor.getPos();


		_msp=new MySokobanPolicy(old,getLevel());

		if (!(_msp.isFinished())){

			if(_msp.check(dest)){ //our POLICY
				Item itmInDest=getLevel().getItemInPlace(dest);//destination item
				dest.setWasTarget(getLevel().getItemInPlace(itmInDest.getPos()).getPos().isWasTarget());

				if((itmInDest.getType().compareTo("Box"))==0){
					Position2D boxDest = new Position2D(_msp.newPos(dest));
					boxDest.setWasTarget(getLevel().getItemInPlace(boxDest).getPos().isWasTarget());
					((Box)itmInDest).move(dest, boxDest, getLevel());
				}
				actor.move(old, dest,getLevel());

				this._lvl.setCounter(this._lvl.getCounter()+1);
			}
			else{
				System.out.println("You can't move there!\n" +
						"Please try again according the rules:\n" +
						_msp.getPolicy());
			}

			this.setChanged();
			this.notifyObservers("display");



			if (_msp.isFinished()){
				this.setChanged();
				this.notifyObservers("finish");
			}
		}
	}




	@Override
	public void load(LevelLoaderCreator lc) {
		Level2D newLevel=new Level2D();
		if (lc==null){
			this.setLevel(new Level2D());
			lc.setComment("There is a problem with level loader./n" +
								"Now the level is NULL");
		}
		else{
			try {

				newLevel=((lc.create()).loadLevel(new FileInputStream(lc.getPath())));
			}
			catch (FileNotFoundException e) {
				lc.unknownPath();
				lc.setComment("unknown file");
				newLevel=new Level2D();
			}
			_lvl=newLevel;
		}

		this.setChanged();
		this.notifyObservers("display");
	}

	@Override
	public void save(LevelSaverCreator ls) {

		if (ls==null){
			this.setLevel(null);
			System.out.println("There is a problem with level saver.\nNow the level is NULL");
		}
		else{
			try {
					ls.create().SaveLevel(new FileOutputStream(ls.getPath()),_lvl);
				} catch (IOException e) {
					ls.setComment("coudnt make the save");
				}
		}
	}


	@Override
	public void exit() {
		System.out.close();

		try {
			System.in.close();
			System.exit(0);
		}
		catch (IOException e) {
		}

	}





}
