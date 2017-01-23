/**
* This class responsible to run the 'exit' command
* @author Maayan & Eden
*/

package controller.commands;

import java.io.IOException;

import common.Level2D;
import model.Model;

public class ExitCommand extends GeneralCommand{

	/**
	* C'TOR
	*/
	public ExitCommand(Model model) {
		super(model);
	}

	public void execute() {

		_model.exit();

		/*
		System.out.println("Exiting program,TY");
		System.out.close();

		try {
			System.in.close();
			System.exit(0);
		}
		catch (IOException e) {
			System.out.println("Somthing went wrong with EXIT...");
		}  */
	}
}