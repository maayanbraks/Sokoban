/**
* This class get the command string from 'Cli' Class and run it
* @author Maayan & Eden
*/

package controller;

import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import controller.commands.CommandCreator;
import controller.commands.DisplayCommand;
import controller.commands.ExitCommand;
import controller.commands.FinishCommand;
import controller.commands.GeneralCommand;
import controller.commands.LoadCommand;
import controller.commands.MoveCommand;
import controller.commands.SaveCommand;
import controller.server.MyServer;
import model.Model;
import model.MyModel;
import view.View;


public class SokobanController  implements Observer {

	private HashMap<String,CommandCreator> _hm;
	private String _command;
	private String _info;

	Model _model;
	View _view;
	Controller _controller;
	public MyServer _server;

	/**
	* C'TOR
	*/
	public SokobanController(Model model,View view){
		this._model=model;
		this._view=view;
		this._controller = new Controller();
		this._controller.start();
		int port=1234;//POERRRTTTTTTTTTTTTTTTTTTTTTTTTT//////////////////////////////////////////
		if(port>=0){
			this._server=new MyServer(port);
			this._server.getClient().addObserver(this);
		}
		initCommands();

	}

	protected void initCommands() {
		_hm=new HashMap<String,CommandCreator>();
		_hm.put("load",new LoadCommandCreator());
		_hm.put("display",new DisplayCommandCreator());
		_hm.put("move",new MoveCommandCreator());
		_hm.put("save",new SaveCommandCreator());
		_hm.put("finish",new FinishCommandCreator());
		_hm.put("exit",new ExitCommandCreator());
	}

	/**
	* Separate the string to command and info about the command. Create the command
	* @param str The command string (from Cli)
	*/
	public void createCommandGeneral(String str){
		String[] parts=str.split(" ");
		this._command=parts[0];
		if(parts.length>1)
			this._info=parts[1];
		CommandCreator cc=_hm.get(this._command);

		if (cc==null){
			this._model.setLevel(null);
		}
		else{
			GeneralCommand gc=cc.createCommand();
			_controller.insertCommand(gc);

		}
	}


	public void update(Observable o, Object arg) {
		this.createCommandGeneral((String)arg);
	}


//Private Classes Creators
		private class LoadCommandCreator implements CommandCreator
		{

			public GeneralCommand createCommand() {
				return new LoadCommand(_info,_model);
			}
		}

		private class DisplayCommandCreator implements CommandCreator
		{
			public GeneralCommand createCommand() {
				return new DisplayCommand(_view,_model);
			}
		}

		private class MoveCommandCreator implements CommandCreator
		{
			public GeneralCommand createCommand() {
				return new MoveCommand(_info,_model);
			}
		}

		private class SaveCommandCreator implements CommandCreator
		{
			public GeneralCommand createCommand() {
				return new SaveCommand(_info,_model);
			}
		}

		private class ExitCommandCreator implements CommandCreator
		{
			public GeneralCommand createCommand() {
				return new ExitCommand(_model,_view,_controller);
			}
		}
		private class FinishCommandCreator implements CommandCreator
		{
			public GeneralCommand createCommand() {
				return new FinishCommand(_view,_model);
			}
		}

}

