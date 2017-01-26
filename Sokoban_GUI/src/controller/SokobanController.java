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
import controller.commands.ServerDisplayCommand;
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

	MyServer _server;
	private String _comment;

	/**
	* C'TOR
	*/
	public SokobanController(Model model,View view){
		this._model=model;
		this._view=view;
		this._controller = new Controller();
		this._controller.start();

		this._server=null;
		this._comment="";

		/*
		int port=-1;//POERRRTTTTTTTTTTTTTTTTTTTTTTTTT//////////////////////////////////////////
		if(port>=0){
			this._server=new MyServer(port);
			this._server.getClient().addObserver(this);
		}
		*/

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

		_hm.put("serverDisplay", new ServerDisplayCommandCreator());
	}






	/**
	* Separate the string to command and info about the command. Create the command
	* @param str The command string (from Cli)
	 * @throws InterruptedException
	*/
	public void createCommandGeneral(String str) throws InterruptedException{
		String[] parts=str.split(" ");
		this._command=parts[0];
		if(parts.length>1)
			this._info=parts[1];
		CommandCreator cc=_hm.get(this._command);

		if (cc==null){
			this.setComment("Unknown command!!!!!");
		}
		else{

			GeneralCommand gc=cc.createCommand();
			_controller.insertCommand(gc);

			Thread.sleep(50);//waiting for comment update

			this.setComment(gc.getComment());
		}
	}

	public void update(Observable o, Object arg) {

		String str="";
		str=(String)arg;
		//_view.load();
	//	System.out.println(arg + "Update");

		if ( (this._server!=null)  && (str.compareTo("display"))==0 ){
			str="serverDisplay";
		}

		try {
			this.createCommandGeneral(str);
		} catch (InterruptedException e) {
			System.out.println("Sokoban Controller InterruptedException in update()");
		}
	}

	public void setServer(MyServer server){
		this._server=server;
	}

	public void setComment(String comment) throws InterruptedException{
		this._comment=comment;
		if(this._server!=null)
			_server.getClient().addMsg(_comment);
			//_server.setMsgToClient(comment);
	}

	public String getComment(){
		return this._comment;
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

		private class ServerDisplayCommandCreator implements CommandCreator
		{
			public GeneralCommand createCommand() {
				return new ServerDisplayCommand(_model,_server);
			}
		}

}

