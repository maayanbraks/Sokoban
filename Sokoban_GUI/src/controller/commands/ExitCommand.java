/**
* This class responsible to run the 'exit' command
* @author Maayan & Eden
*/

package controller.commands;

import java.io.IOException;

import common.Level2D;
import controller.Controller;
import javafx.application.Platform;
import model.Model;
import view.View;

public class ExitCommand extends GeneralCommand{

	View _view;
	Controller _controller;


	/**
	* C'TOR
	*/

	public ExitCommand(Model model,View view,Controller controller) {
		super(model);
		this._view=view;
		this._controller=controller;
	}

	public void execute() {

		_model.exit();

		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				_view.closeStage();

			}
		});

		_controller.stop();
		//System.exit(0);//////////////////////////////check Exit!!!!!!!!!!!!!!!!!!!-=-s-bdsbfdsbfdsbb

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