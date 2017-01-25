/**
* This class responsible to run the 'exit' command
* @author Maayan & Eden
*/

package controller.commands;

import java.io.IOException;

import common.Level2D;
import model.Model;
import view.View;

public class FinishCommand extends GeneralCommand{


	View _view;

	/**
	* C'TOR
	*/
	public FinishCommand(View view,Model model) {
		super(model);
		this._view=view;
	}

	public void execute() {
		_view.stopTimer();
		_view.setComment("You Won!");

	}
}