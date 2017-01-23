/**
* This class is "parent class" of all the command classses
* @author Maayan & Eden
* @version 2D
*/

package controller.commands;

import common.Level2D;
import model.Model;


public class GeneralCommand implements Command{
	Model _model;

	/**
	* C'TOR
	*/
	public GeneralCommand(Model model) {
		this._model=model;
	}

	/**
	* This function return the level
	*/
	public Level2D get_lvl() {
		return _model.getLevel() ;
	}

	/**
	* This function does nothing. It here only for the Command Implementation
	*/
	public void execute() {
		//Just for the implement of 'Command'
	}
}
